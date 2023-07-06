import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcClass implements JdbcInterface
{

    @Override
    public void executeSQL(Connection connection, String sql)
    {
        try
        {
            Statement statement = connection.createStatement();
            boolean success = statement.execute(sql);
            if(success == false)
            {
                System.out.println("[success] " + sql);
            }
            else
            {
                System.out.println("[Failed] " + sql);
            }
        }
        catch (SQLException e)
        {
            System.out.println("[SQLException]\n" + e + "\n[SQLException]");
        }
    }

    @Override
    public Boolean creatTable(Connection connection, String tableName, String valueList)
    {
        try
        {
            Statement statement = connection.createStatement();
            boolean success = statement.execute("create table " + tableName + " " + valueList);
            if(success == false)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            System.out.println("[SQLException]\n" + e + "\n[SQLException]");
            return false;
        }
    }

    @Override
    public ResultSet searchData(Connection connection, String columList, String tableName, String whereCondition)
    {
        try
        {
            Statement statement = connection.createStatement();
            System.out.println("SELECT " + columList + " FROM " + tableName + " " + whereCondition);
            ResultSet resultSet = statement.executeQuery("SELECT " + columList + " FROM " + tableName + " WHERE " + whereCondition);
            return resultSet;
        }
        catch (SQLException e)
        {
            System.out.println("[SQLException]\n" + e + "\n[SQLException]");
            return null;
        }
    }

    @Override
    public int insertData(Connection connection, String tableName, String columList, String valueList)
    {
        try
        {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO "+ tableName + " " + columList + " VALUES " + valueList;
            return statement.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            System.out.println("[SQLException]\n" + e + "\n[SQLException]");
            return 0;
        }
    }


}
