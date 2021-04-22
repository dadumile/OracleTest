package pm;

import java.sql.*;

/**
 * url:"D:\oracle\app\oracle\product\11.2.0\server\jdbc\lib\jdbc6.jar";
 * 1. 加载驱动
 * 2. 提供访问数据库url
 * 3. 获得链接
 * 4. 打开语句对象
 * 5. 执行sql语句
 * 6. 操纵结果集(查询语句才有)
 * 7. 关连接
 */
public class OracleTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 静态加载类
        // 加载驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // 提供访问数据库
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        // 获得链接
        Connection connection = DriverManager.getConnection(url,
                "scott", "tiger");
        System.out.println(connection);
        // 打开语句对象
        Statement statement = connection.createStatement();
        // 执行sql语句
        // 增删改查
        // 增加
        String addStr = "insert into student values(5, '刘七', '男', 21)";
        int i = statement.executeUpdate(addStr);
        System.out.println(i==0?"插入失败":"插入成功");

        // 修改
        String updateStr = "update student set name = '篱笆', gender = '女' where id = 5";
        System.out.println(statement.executeUpdate(updateStr) == 0?"修改失败":"修改成功");

        // 删除
        String deleteStr = "delete from student where id = 5";
        System.out.println(statement.executeUpdate(deleteStr) == 0?"删除失败":"删除成功");

        // 查找
        // 操纵结果集
        String selectStr = "select * from student";
        ResultSet resultSet = statement.executeQuery(selectStr);
        // 1.判断是否有下一条数据
        // 2.指针向下移动一位
        while(resultSet.next()){

/*
            // 获取第一列数据，返回值为int类型的值
            System.out.println(resultSet.getInt(1));
            // 获取idx的数据，返回值为int类型的值
            System.out.println(resultSet.getInt("idx"));
*/
            System.out.println(
                    resultSet.getInt("ID") + "\t"
                    + resultSet.getString(2) + "\t"
                    + resultSet.getString("GENDER") + "\t"
                    + resultSet.getString(4)
            );
        }

        // 关连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
