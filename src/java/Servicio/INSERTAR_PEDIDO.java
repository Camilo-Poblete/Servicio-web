 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.jws.WebService;
import java.text.DateFormat;
import javax.jws.WebService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.jws.WebMethod;
import java.util.Date;
import javax.jws.WebParam;
import javax.print.attribute.standard.DateTimeAtCreation;


/**
 *
 * @author NICOLE
 */
@WebService(serviceName = "INSERTAR_PEDIDO")
public class INSERTAR_PEDIDO {
  static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";


    // Database credentials
    static final String USERNAME = "RESTAU";
    static final String PASSWORD = "RESTAU";  
    
    
    
    
          
    @WebMethod(operationName = "INSERTAR_PEDIDO")
    public String INSERTAR_PEDIDO( @WebParam(name = "ID_CLIENTE")String ID_CLIENTE,@WebParam(name="ID_MESA")String ID_MESA) throws Exception{
        //TODO write your implementation code here:
{
        String runSP = "{ call INSERTAR_PEDIDO(?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
		try
		{
                // INSERTAR_PEDIDO (V_CLIENTE INT,V_MESA INT) id
			
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
            
              callableStatement = conn.prepareCall(runSP);
          
         
              
            callableStatement.setInt("V_CLIENTE", Integer.parseInt(ID_CLIENTE));
            callableStatement.setInt("V_MESA", Integer.parseInt(ID_MESA));
        

            
                        
           callableStatement.executeUpdate();


            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                e.printStackTrace();


            } catch (Exception e) {
                e.printStackTrace();
            } 
     
  
        } 
         return "Pedido Ingresado";
    }  

    
    
    
    
    
    
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "AgregarPlato")
    public String AgregarPlato(@WebParam(name = "ID_PLATO")String ID_PLATO ,@WebParam(name = "ID_PEDIDO")String ID_PEDIDO , @WebParam(name = "CANTIDAD")String CANTIDAD) throws Exception {
        
        /**
create or replace PROCEDURE INGRESO_PEDIDO_PLATO (V_ID_PLATO INT, V_ID_PEDIDO INT, V_CANTIDAD INT)
AS
BEGIN
    INSERT INTO pedidoplato (id_plato,id_pedido,cantidad) 
    VALUES (V_ID_PLATO, V_ID_PEDIDO, V_CANTIDAD);
END;
*/
       String runSP = "{ call INGRESO_PEDIDO_PLATO ?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
	 
    
            try{
             
            DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
            
            
            System.out.println("Conectado a la Base de datos Restaurante");
            String query = "";
            System.out.println("ANTES");
            System.out.println(query);
            
         query="INSERT INTO pedidoplato (id_plato,id_pedido,cantidad) VALUES( ";
             
                query = query + "'"+ID_PLATO+"','"
                +ID_PEDIDO+"','"
                +CANTIDAD+"')";
                
                
                
            
                
            System.out.println("DESPUES");
            System.out.println(query);
            db.ExecuteDML(query);
          
            //ResultSet rs = db.QuerySelectDataReturnResultSet(query);
            
            
            //while(rs.next()){
            //    //System.out.println(rs.getString("USUARIO"));
              //  System.out.println("DESPUES DE GETSTRING");

              
          //  }      
          
           
       return "Plato Agregado";
            
        }catch(Exception ex){
            System.out.println("*******************error*******************");
            System.out.println(ex.getMessage());
            System.out.println("*******************error*******************");
            
            throw ex;
        }
        
        
        
        
    }
    
    
    
    
    
    
    
    @WebMethod(operationName = "MESA")
    public String Mesa(@WebParam(name = "ID")String ID_MESA) throws Exception {
        

       String runSP = "{ call  INGRESO_PEDIDO_MESA (?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
		try
		{
			Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);            
                //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "RESTAU", "RESTAU");
                  //statement = conn.createStatement();
                  callableStatement = conn.prepareCall(runSP);
                // create or replace stored procedure
                //statement.execute(runSPP);

             //(V_DIRECCION VARCHAR2, V_ID_USUARIO INT)
               //callableStatement.setString("NOMBRES", NOMBRES);
              callableStatement.setInt("V_ID_MESA", Integer.parseInt(ID_MESA));
             
                } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "MESA";
       
  
    }
   
    
    
    
    
     @WebMethod(operationName = "PEDIDO_MESA")
    public String PEDIDO_MESA(@WebParam(name = "ID")String ID) throws Exception {
        

       String runSP = "{ call  PEDIDO_COCINA_LISTO (?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
		try
		{
			Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);            
                //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "RESTAU", "RESTAU");
                  //statement = conn.createStatement();
                  callableStatement = conn.prepareCall(runSP);
                // create or replace stored procedure
                //statement.execute(runSPP);

             //(V_DIRECCION VARCHAR2, V_ID_USUARIO INT)
               //callableStatement.setString("NOMBRES", NOMBRES);
              callableStatement.setInt("V_ID", Integer.parseInt(ID));
             
                } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Pedido listo a su mesa";
       
  
    }
    
      
    
    
      @WebMethod(operationName = "ELIMINAR_PLATO")
    public String ELIMINAR_PLATO(@WebParam(name = "ID_PLATO")String ID_PLATO) throws Exception {
        

       String runSP = "{ call   ELIMINAR_PEDIDOPLATO (?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
       
       
  
    
    
    try{
             
            DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
            
            
            System.out.println("Conectado a la Base de datos Restaurante");
            String query = "";
            System.out.println("ANTES");
            System.out.println(query);
            
            
       query = "Delete from pedidoplato where ID='"+ID_PLATO+"'";
             
                  
            
                
            System.out.println("DESPUES");
            System.out.println(query);
            db.ExecuteDML(query);
          
            //ResultSet rs = db.QuerySelectDataReturnResultSet(query);
            
            
            //while(rs.next()){
            //    //System.out.println(rs.getString("USUARIO"));
              //  System.out.println("DESPUES DE GETSTRING");

              
          //  }      
          
           
       return "Plato Agregado";
            
        }catch(Exception ex){
            System.out.println("*******************error*******************");
            System.out.println(ex.getMessage());
            System.out.println("*******************error*******************");
            
            throw ex;
        }
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
 