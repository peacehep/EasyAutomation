package util;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static com.easy.automation.util.DbcpConnection.getConnection;
import static com.easy.automation.util.DbcpConnection.getDataSource;

public class DBUtilTest {
    @Test
    public void test() throws SQLException {
        // Create a ResultSetHandler implementation to convert the
        // first row into an Object[].
        // 将第一行结果存入Object[]中返回
        ResultSetHandler<Object[]> h = new ResultSetHandler<Object[]>() {
            public Object[] handle(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return null;
                }

                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();
                Object[] result = new Object[cols];

                for (int i = 0; i < cols; i++) {
                    result[i] = rs.getObject(i + 1);
                }

                return result;
            }
        };

        // Create a QueryRunner that will use connections from
        // the given DataSource
        QueryRunner run = new QueryRunner(getDataSource());

        // Execute the query and get the results back from the handler
        Object[] result = run.query(
                "SELECT * FROM Person WHERE name=?", h, "John Doe");
    }


    /**
     * You could also perform the previous query using a java.sql.Connection object instead of a DataSource.
     * Notice that you are responsible for closing the Connection in this example.
     *
     */

    @Test
    public void test2() throws SQLException {
        ResultSetHandler<Object[]> h = new ResultSetHandler<Object[]>() {
            public Object[] handle(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return null;
                }

                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();
                Object[] result = new Object[cols];

                for (int i = 0; i < cols; i++) {
                    result[i] = rs.getObject(i + 1);
                }

                return result;
            }
        }; // Define a handler the same as above example

        // No DataSource so we must handle Connections manually
        QueryRunner run = new QueryRunner();

        Connection conn = getConnection(); // open a connection
        try{
            Object[] result = run.query(
                    conn, "SELECT * FROM Person WHERE name=?", h, "John Doe");
            // do something with the result
        } finally {
            // Use this helper method so we don't have to check for null
            DbUtils.close(conn);
        }
    }


    /**
     * You can not only fetch data from the database - you can also insert or update data.
     * The following example will first insert a person into the database and
     * after that change the person's height.
     *
     */

    public void test3(){
        QueryRunner run = new QueryRunner( getDataSource() );
        try
        {
            // Execute the SQL update statement and return the number of
            // inserts that were made
            int inserts = run.update( "INSERT INTO Person (name,height) VALUES (?,?)",
                    "John Doe", 1.82 );
            // The line before uses varargs and autoboxing to simplify the code

            // Now it's time to rise to the occation...
            int updates = run.update( "UPDATE Person SET height=? WHERE name=?",
                    2.05, "John Doe" );
            // So does the line above
        }
        catch(SQLException sqle) {
            // Handle it
        }
    }

    /**
     * For long running calls you can use the AsyncQueryRunner
     * to execute the calls asynchronously.
     * The AsyncQueryRunner class has the same methods
     *    as the QueryRunner calls; however,
     * the methods return a Callable.
     *
     */

    public void test4(){
        ExecutorCompletionService<Integer> executor =
                new ExecutorCompletionService<Integer>( Executors.newCachedThreadPool() );
        AsyncQueryRunner asyncRun = new AsyncQueryRunner((ExecutorService) getDataSource());

        try
        {
            // Create a Callable for the update call
            Callable<Integer> callable = (Callable<Integer>) asyncRun.update( "UPDATE Person SET height=? WHERE name=?",
                    2.05, "John Doe" );
            // Submit the Callable to the executor
            executor.submit( callable );
        } catch(SQLException sqle) {
            // Handle it
        }

        // Sometime later (or in another thread)
        try
        {
            // Get the result of the update
            Integer updates = executor.take().get();
        } catch(InterruptedException ie) {
            // Handle it
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * ResultSetHandler Implementations
     * In recognition of this DbUtils provides a set of ResultSetHandler implementations
     * in the org.apache.commons.dbutils.handlers package
     * that perform common transformations into arrays, Maps, and JavaBeans.
     *
     * an example using the BeanHandler to fetch one row from the ResultSet and turn it into a JavaBean.
     */

    public void test5() throws SQLException {
        QueryRunner run = new QueryRunner(getDataSource());

        // Use the BeanHandler implementation to convert the first
        // ResultSet row into a Person JavaBean.
        ResultSetHandler<LogTest> h = new BeanHandler<LogTest>(LogTest.class);

        // Execute the SQL statement with one replacement parameter and
        // return the results in a new Person object generated by the BeanHandler.
        LogTest logTest = run.query(
                "SELECT * FROM Person WHERE name=?", h, "John Doe");

    }

    /**
     * This time we will use the BeanListHandler to fetch all rows from the ResultSet
     * and turn them into a List of JavaBeans.
     *
     */

    public void test6() throws SQLException {
        QueryRunner run = new QueryRunner(getDataSource());

        // Use the BeanListHandler implementation to convert all
        // ResultSet rows into a List of Person JavaBeans.
        ResultSetHandler<List<LogTest>> h = new BeanListHandler<LogTest>(LogTest.class);

        // Execute the SQL statement and return the results in a List of
        // Person objects generated by the BeanListHandler.
        List<LogTest> persons = run.query("SELECT * FROM Person", h);
    }

    /**
     *
     *
     */
   @Test
    public void testBatch() throws SQLException {
            QueryRunner runner = new QueryRunner(getDataSource());
            String sql = "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
            Object[][] params = new Object[3][5];
            for (int i = 0; i < params.length; i++) {
                params[i] = new Object[]{i+1, "aa"+i, "123", i+"@sian.com", new Date()};
            }
            runner.batch(sql, params);
        }


    /**
     *
     * 返回总数
     *
     * @throws SQLException
     */


    @Test
    public void test7() throws SQLException {
        QueryRunner runner = new QueryRunner(getDataSource());
        String sql = "select count(*) from t_sys_user";
        int totalrecord = ((BigDecimal)runner.query(sql, new ScalarHandler(1))).intValue();
        System.out.println(totalrecord);
    }


}
