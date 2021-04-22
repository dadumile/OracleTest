package pm;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class OracleTest3 {
    public static String DRIVER;
    public static String URL;
    public static String USER;
    public static String PASSWORD;
    static {
        Properties properties = new Properties();
        try {
//            System.out.println(OracleTest3.class.getClassLoader().getResourceAsStream("dp.properties"));
            properties.load(OracleTest3.class.getClassLoader().getResourceAsStream("dp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER = properties.getProperty("oracle.driver");
        URL = properties.getProperty("oracle.url");
        USER = properties.getProperty("oracle.user");
        PASSWORD = properties.getProperty("oracle.password");
    }

    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static Statement getStatement(Connection connection){
        try {
            return connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    // 增删改
    private static int executeUpdate(Statement statement, String sql){
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    // 查询
    private static void executeUpdate(Statement statement, String sql, String[] index){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            // 1.判断是否有下一条数据
            // 2.指针向下移动一位
            while(resultSet.next()){
                for(String i:index){
                    System.out.printf(resultSet.getString(i));
                }
                System.out.printf("\n");
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void close(Statement statement, Connection connection) {
        try {
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        Statement statement = getStatement(connection);
        String message = "select * from student";
        executeUpdate(statement, message, new String[]{"ID", "NAME"});
        close(statement, connection);
    }

}
