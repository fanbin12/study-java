package cn.itcast.dao;
import cn.itcast.domain.*;
import cn.itcast.util.JDBCutil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/*操作数据库中user表中的类*/
public class userDao {

    //申明数据库连接
    private JdbcTemplate template=new JdbcTemplate(JDBCutil.getDataSource());

    public  user login(user loginuser){
        /*快捷键：CTRL+alt+T*/
        try {
            String sql="select * from user where username=? and password=?";
            //queryForObject会将参数带入sql，并执行，最后返回一个user对象
            user use = template.queryForObject(sql,
                    new BeanPropertyRowMapper<user>(user.class),
                    loginuser.getUsername(), loginuser.getPassword());
            return use;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
            /*
            * 将函数快抛出异常时，一定要分析其返回值
            * */
        }
    }
}
