import java.sql.*;
import java.util.Scanner;

public class Runner
{
    Runner(String driver, String url, String userName, String password)
    {
        JdbcClass jdbcClass = new JdbcClass();
        try
        {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,userName,password);
            if(connection != null)
            {
                System.out.println("connect success");

                jdbcClass.executeSQL(connection,"DROP TABLE product");

                if(jdbcClass.creatTable(connection, "product",  "(Pid Int(5), Pname varchar(30), Qty Int(4), Price float(10))"))
                {
                    System.out.println("[success] create table");
                }
                else
                {
                    System.out.println("[Failed] create table");
                }

                System.out.println("\nHow many records you wan to insert?");

                Scanner scanner = new Scanner(System.in);
                int count = scanner.nextInt();


                int i = 0;
                for(;i < count;)
                {
                    try
                    {
                        System.out.println("please input p_id:");
                        int pid = scanner.nextInt();
                        System.out.println("please input p_name:");
                        String pname = scanner.next();
                        System.out.println("please input qty:");
                        int qty = scanner.nextInt();
                        System.out.println("please input price:");
                        float price = scanner.nextFloat();

                        int result = jdbcClass.insertData(connection, "`product`", "(`Pid`, `Pname`, `Qty`, `Price`)", "("+ pid + ", '" + pname + "'," +qty + "," + price + ")");
                        if(result != 0)
                        {
                            System.out.println("[success] insert table\n");
                            i++;
                        }
                        else
                        {
                            System.out.println("[Failed] insert table, Please try again\n");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("[Exception] please try again\n");
                        scanner.next();
                    }
                }
                System.out.println("successful insert " + i + " records");

                ResultSet resultSet = jdbcClass.searchData(connection, "*", "product", "1");
                while(resultSet.next())
                {
                    System.out.println(resultSet.getInt("Pid") + " " + resultSet.getString("Pname") + " " + resultSet.getInt("Qty") + " " + resultSet.getFloat("Price"));
                }
            }
            else
            {
                System.out.println("connect table failed");
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
