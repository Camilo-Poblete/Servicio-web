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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Camilo
 */
@WebService(serviceName = "INGRESO_PEDIDO_PLATO")
public class INGRESO_PEDIDO_PLATO {

   
    @WebMethod(operationName = "INGRESO_PEDIDO_PLATO")
    public String INGRESO_PEDIDO_PLATO(@WebParam(name = "ID_PLATO") String ID_PLATO,@WebParam(name = "PEDIDO")String PEDIDO,@WebParam(name = "CANTIDAD")String CANTIDAD) throws Exception {
       
        
           String runSP = "{ call INGRESO_PEDIDO_PLATO(?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
		try
		{
			
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
            
              callableStatement = conn.prepareCall(runSP);
              callableStatement.setInt("V_ID_PLATO", Integer.parseInt(ID_PLATO));
             callableStatement.setInt("V_ID_PEDIDO", Integer.parseInt(PEDIDO));
            callableStatement.setInt(" V_CANTIDAD", Integer.parseInt(CANTIDAD));
            
           callableStatement.executeUpdate();
              
            
           
          
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

        } catch (Exception e) {
            e.printStackTrace();
        } 
      
       
 
        
        return "PEDIDO PLATO" ;
    }




    
    
    @WebMethod(operationName = "EliminarPlato")
    public String EliminarPlato(@WebParam(name = "ID")String ID ) throws Exception {
        

       String runSP = "{ call ELIMINAR_PEDIDOPLATO(?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
		try
		{
			Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);            
               
                  callableStatement = conn.prepareCall(runSP);
              
              
              callableStatement.setInt("V_ID", Integer.parseInt(ID));
             
                } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Eliminado";
       
  
    }
    
    }

        
        
   

