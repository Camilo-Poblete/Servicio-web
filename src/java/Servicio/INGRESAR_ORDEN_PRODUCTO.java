/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import static Servicio.CAJA.DB_URL;
import static Servicio.CAJA.JDBC_DRIVER;
import static Servicio.CAJA.PASSWORD;
import static Servicio.CAJA.USERNAME;
import static Servicio.INSERTAR_STOCK.JDBC_DRIVER;
import static Servicio.SERVICIOS_SOAP.JDBC_DRIVER;

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
import java.sql.Statement;

/**
 *
 * @author Camilo
 */
@WebService(serviceName = "INGRESAR_ORDEN_PRODUCTO")
public class INGRESAR_ORDEN_PRODUCTO {

      static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";


    
    static final String USERNAME = "RESTAU";
    static final String PASSWORD = "RESTAU";  
    
    
    
    
    
    @WebMethod(operationName = "INGRESAR_ORDEN_PRODUCTO")
    public String INGRESAR_ORDEN_PRODUCTO(@WebParam(name = "ID_ORDEN") String ID_ORDEN,@WebParam(name = "ID_PRODUCTO") String ID_PRODUCTO,@WebParam(name = "CANTIDAD") String CANTIDAD,@WebParam(name = "ID_PROVEEDOR")String ID_PROVEEDOR,@WebParam(name = "ID_ORDENES")String ID_ORDENES)throws  Exception{
       
        

        String runSP = "{ call INSERTAR_ORDEN_PRODUCTO(?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
       
        

          try{
			

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
            
              callableStatement = conn.prepareCall(runSP);
              callableStatement.setInt("V_ID_ORDEN",Integer.parseInt(ID_ORDEN));
              callableStatement.setInt("V_ID_PRODUCTO", Integer.parseInt(ID_PRODUCTO));
              callableStatement.setInt("V_CANTIDAD", Integer.parseInt(CANTIDAD));
      
            
             callableStatement.executeUpdate();  
      
            
            
            String runSPP = "{ call INSERTAR_PROVEEDOR_ORDENA(?,?) }";
            
            
            
            
              try{
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);            
               
                  callableStatement = conn.prepareCall(runSP);
                  callableStatement.setInt("V_ID_PROVEEDOR", Integer.parseInt(ID_PROVEEDOR));
                  callableStatement.setInt("V_ID_ORDEN", Integer.parseInt(ID_ORDENES));

               


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
         return "ORDEN PRODUCTO";
         
    }

  
    
    }
    
    
  
   



    
    

      
        
        
        
        
        
      
    

