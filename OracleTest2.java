package pm;

import java.sql.*;

public class OracleTest2 {
    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    public static final String USER = "scott";
    public static final String PASSWORD = "tiger";

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
