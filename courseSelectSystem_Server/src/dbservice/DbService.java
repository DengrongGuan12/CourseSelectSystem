package dbservice;



import java.sql.ResultSet;



public interface DbService{
	public boolean exeUpdate(String sql,String [] paras);
	public ResultSet query(String sql,String[] paras);
	public void trim(String sql);
	public void close();
	public void deleteTable(String tableName);
	public boolean createTempRecordTable();
	public boolean  judgeTempExist();
	
}
