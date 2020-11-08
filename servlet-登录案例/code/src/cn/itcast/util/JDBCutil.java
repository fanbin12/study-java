package cn.itcast.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCutil {
    /*如果使用者为：springJDBC，其只需要datasource*/
    //如果为其他使用者，其一般会使用连接器。
    private  static DataSource ds;
    static {
        //加载配置文件
        Properties properties = new Properties();
        //使用classloader加载配置文件，获取字节输入流
        InputStream resourceAsStream = JDBCutil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化连接池对象
        try {
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    * h获取连接池对象*/
    public  static  DataSource getDataSource(){
        return ds;
    }
    /*获取connection对象*/
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
