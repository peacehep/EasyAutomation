package com.easy.automation.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.db.type.Request;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.easy.automation.util.DBLink.TEST;

public class DBUtil {
    private static Logger log = LogManager.getLogger(DBUtil.class);

    public static void updateSQL(String sql) {
        updateSQL(TEST, sql); //默认使用TEST环境
    }

    public static void updateSQL(DBLink dbLink, String sql) {
        QueryRunner queryRunner = getQueryRunner(dbLink);
        try {
            log.info("sql：" + sql);
            int i = queryRunner.update(sql);
            log.info("结果：" + i + " 行数据已经更新");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> getMultiResult(String sql) {
        return getMultiResult(TEST, sql);

    }

    public static List<Map<String, Object>> getMultiResult(DBLink dbLink, String sql) {
        List<Map<String, Object>> list = null;
        QueryRunner queryRunner = getQueryRunner(dbLink);

        try {
            log.info("sql:" + sql);
            list = queryRunner.query(sql, new MapListHandler());

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;


    }

    public static Map<String, Object> getSingleResult(String sql) {
        return getSingleResult(TEST, sql);
    }

    public static Map<String, Object> getSingleResult(DBLink dbLink, String sql) {
        Map<String, Object> map = null;
        QueryRunner queryRunner = getQueryRunner(dbLink);

        try {
            log.info("sql:" + sql);
            map = queryRunner.query(sql, new MapHandler());
            return map;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;

    }


    public static Request getAssertJRequest(String sql) {

        return getAssertJRequest(TEST, sql);

    }

    public static Request getAssertJRequest(DBLink dbLink, String sql) {
        log.info("sql:" + sql);
        Request request = new Request(chooseDataSource(dbLink), sql);
        return request;
    }


    public static QueryRunner getQueryRunner(DBLink dbLink) {
        QueryRunner queryRunner = null;
        switch (dbLink) {
            case TEST:
                queryRunner = new QueryRunner(DbcpConnection.getDataSource());
                log.info("在" + dbLink.getDescription() + "中执行sql");
                break;
            case SIT:
                queryRunner = new QueryRunner(DbcpConnection.getDataSource2());
                log.info("在" + dbLink.getDescription() + "中执行sql");
                break;
        }
        return queryRunner;
    }

    public static DataSource chooseDataSource(DBLink dbLink) {
        DataSource dataSource = null;
        switch (dbLink) {
            case TEST:
                dataSource = DbcpConnection.getDataSource();
                log.info("在" + dbLink.getDescription() + "中获取数据源");
                break;
            case SIT:
                dataSource = DbcpConnection.getDataSource2();
                log.info("在" + dbLink.getDescription() + "中获取数据源");
                break;
        }
        return dataSource;
    }
}
