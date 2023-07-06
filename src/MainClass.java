public class MainClass
{
    public static void main(String[] args)
    {
        String driver   = "com.mysql.cj.jdbc.Driver";
        String url      = "jdbc:mysql://localhost:3306/TestDB?useSSL=false";
        String userName = "root";
        String password = "";
        new Runner(driver, url, userName, password);
    }
}
