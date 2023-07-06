import java.sql.Connection;
import java.sql.ResultSet;

public interface JdbcInterface
{
    public void executeSQL(Connection connection, String sql);
    public Boolean creatTable(Connection connection, String tableName, String valueList);
    public ResultSet searchData(Connection connection, String returnList, String table, String whereCondition);
    public int insertData(Connection connection, String tableName, String columList, String valueList);
}
