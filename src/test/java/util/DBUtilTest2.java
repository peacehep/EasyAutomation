package util;

import org.assertj.db.type.Request;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.easy.automation.util.DBLink.SIT;
import static com.easy.automation.util.DBUtil.getAssertJRequest;
import static com.easy.automation.util.DBUtil.getMultiResult;
import static com.easy.automation.util.DBUtil.getSingleResult;
import static org.assertj.db.api.Assertions.assertThat;

public class DBUtilTest2 {
    @Test
    public void test1(){

        String sql = "select * from t_company tsc where tsc.company_id = 1";

        Map<String,Object> companyInfo = getSingleResult(sql);

        for(String s :companyInfo.keySet())
        {
            System.out.println(s+":"+companyInfo.get(s));
        }


        //Iterator<String> itr = companyInfo.keySet().iterator();

        Iterator<Map.Entry<String, Object>> itr2 = companyInfo.entrySet().iterator();

        while(itr2.hasNext())
        {
            Map.Entry<String,Object> entry = itr2.next();
            System.out.println(entry.getKey() + ""+entry.getValue());
        }

    }

    @Test
    public void test2(){
        String sql = "select * from t_company tsc where tsc.company_id = 1";

        Map<String,Object> companyInfo = getSingleResult(SIT,sql);
        Iterator<Map.Entry<String,Object>> itr2 = companyInfo.entrySet().iterator();


        while(itr2.hasNext())
        {
            Map.Entry<String,Object> entry = itr2.next();
            System.out.println(entry.getKey() + " : "+entry.getValue());
        }

    }

    @Test
    public void test3(){
        String sql = "select * from t_company tsc where tsc.company_id in ( '1','2','3')";

        List<Map<String,Object>> multiCompanyInfo = getMultiResult(sql);

        Iterator<Map<String,Object>> itr = multiCompanyInfo.iterator();

        while(itr.hasNext())
        {
            System.out.println("**************************************************");
            Map<String,Object> map = itr.next();

            Iterator<Map.Entry<String,Object>> itr_entry = map.entrySet().iterator();

            while(itr_entry.hasNext())
            {
                Map.Entry<String,Object> entry = itr_entry.next();

                System.out.println(entry.getKey()+" : "+entry.getValue());
            }
        }

    }

    @Test
    public void test4(){

        String sql = "select * from t_company tsc where tsc.company_id in ( '1','2','3')";

        Request request = getAssertJRequest(sql);

        // .column() 下一列
        // .row() 下一行
        // .value() 下一格

        assertThat(request).column().column().column()
                .value().isEqualTo("2");

    }
}
