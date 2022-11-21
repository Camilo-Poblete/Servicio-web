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
 * @author Camilo
 */
@WebService(serviceName = "Cliente")
public class Cliente {

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";


   
    static final String USERNAME = "RESTAU";
    static final String PASSWORD = "RESTAU";  
            
    
     @WebMethod(operationName = "Cliente")
    public String Registro(@WebParam(name = "RUT") String RUT,@WebParam(name="LOGIN") String LOGIN, 
            @WebParam(name="CLAVE") String CLAVE ,
            @WebParam(name="FECHA_CREACION")String FECHA_CREACION,@WebParam(name="FECHA_MODIFICACION") String FECHA_MODIFICACION,@WebParam(name = "NOMBRES")String NOMBRES, @WebParam(name = "APELLIDOS")String APELLIDOS,@WebParam(name="EMAIL") String EMAIL ,@WebParam(name = "ESTADO")String ESTADO,@WebParam(name = "ID_ROL" ) String ID_ROL,@WebParam(name = "FONO")String FONO,@WebParam(name="DIRECCION") String DIRECCION ,@WebParam(name="ID_USUARIO") String ID_USUARIO)  throws Exception{
           
  
        String runSP = "{ call INSERTAR_CLIENTE_USUARIO(?,?,?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        try{
			

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
           
              callableStatement = conn.prepareCall(runSP);
              callableStatement.setString("V_NOMBRE", NOMBRES);
              callableStatement.setString("V_APELLIDO", APELLIDOS);
              callableStatement.setString("V_RUT", RUT);
              callableStatement.setString("V_EMAIL", EMAIL);
              callableStatement.setString("V_FONO", FONO);
            
            
            
                callableStatement.executeUpdate();
            
       


            
             String runSPP = "{ call INSERTAR_CLIENTE(?,?) }";            
            
            
            
             

		try
		{
               
			
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
             
            callableStatement.setString("V_DIRECCION", (DIRECCION));
            callableStatement.setInt("V_ID_USUARIO", Integer.parseInt(ID_USUARIO));
        
         
           callableStatement.executeUpdate();
              

           
           
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                e.printStackTrace();


            } catch (Exception e) {
                e.printStackTrace();
            } 
         
          
           
           

            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                e.printStackTrace();


            } catch (Exception e) {
                e.printStackTrace();
            } 
         
          
     
         return "Cliente Registrado Correctamente";
    }  


    }
    

            
            
            
           

  