package Servicio;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBSqlClass {
	private Connection con;
	private String _jdbcDriver;
	
	public String getJdbcDriver() {
		return _jdbcDriver;
	}

	public String getJdbcUser() {
		return _jdbcUser;
	}

	private String _jdbcURL;
	private String _jdbcUser;
	private String _jdbcPassword;
	
	public DBSqlClass(String jdbcDriver, String jdbcURL, String jdbcUser, String jdbcPassword) throws Exception {
		_jdbcDriver= jdbcDriver;
		_jdbcURL= jdbcURL;
		_jdbcUser= jdbcUser;
		_jdbcPassword= jdbcPassword; 

		try{
			openDataBaseConnection();
		}catch(Exception e){
			throw e;
		}
	}
	
	private void openDataBaseConnection() throws Exception {
		try{
			Class.forName(_jdbcDriver);
			con=DriverManager.getConnection(_jdbcURL,_jdbcUser,_jdbcPassword);
		}catch(Exception e){
			throw e;
		}
	}
	
	public void closeDataBaseConnection() throws Exception {
		try{
			con.close();
		}catch(Exception e){
			throw e;
		}
	}
	
	public ResultSet QuerySelectDataReturnResultSet(String query) throws Exception{
		ResultSet rs = null;
		try{
			if(con.isClosed() || con.isValid(30) == false)
				openDataBaseConnection();
			
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery(query);
                        
			
			
		}catch(Exception e){
			throw e;
		}
		
		return rs;
	}
	public void ExecuteDDL(String query) throws Exception {
		try{
			if(con.isClosed() || con.isValid(30) == false)
				openDataBaseConnection();
			
			Statement stmt=con.createStatement();
			stmt.executeUpdate(query);
		}catch(Exception e){
			throw e;
		}
	}
	
	public Integer ExecuteDML(String query) throws Exception {
		Integer AffectedRows = 0;
		try{
			if(con.isClosed() || con.isValid(30) == false)
				openDataBaseConnection();
			
			Statement stmt=con.createStatement();
			stmt.executeUpdate(query);
			AffectedRows = stmt.getUpdateCount();
		}catch(Exception e){
			throw e;
		}
		return AffectedRows;
	}

	public Integer ExecutePRocedure(String query,java.sql.CallableStatement SP) throws Exception {
		Integer AffectedRows = 0;
		try{
			if(con.isClosed() || con.isValid(30) == false)
				openDataBaseConnection();
			
			Statement stmt=con.createStatement();
			stmt.executeUpdate(query);
			AffectedRows = stmt.getUpdateCount();
		}catch(Exception e){
			throw e;
		}
		return AffectedRows;
	}        
        
	
}





