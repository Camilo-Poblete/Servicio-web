
package Servicio;

import static Servicio.SERVICIOS_SOAP.JDBC_DRIVER;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "CAJA")
public class CAJA {

    
     static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";


    // Database credentials
    static final String USERNAME = "RESTAU";
    static final String PASSWORD = "RESTAU";  
    
    
    @WebMethod(operationName = "CAJA")
    public String CAJA(@WebParam(name = "SUBTOTAL") String SUBTOTAL,@WebParam(name = "PROPINA")String PROPINA,@WebParam(name = "DESCUENTO")String DESCUENTO,@WebParam(name = "TOTAL")String TOTAL,@WebParam(name = "PAGO")String PAGO,@WebParam(name = "PEDIDO")String PEDIDO,@WebParam(name = "ID_MESA")String ID_MESA,@WebParam(name = "ID_PEDIDO")String ID_PEDIDO)
            
throws  Exception{
        
        
         String runSP = "{ call  INSERTAR_BOLETA (?,?,?,?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
          try{
			

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
            
              callableStatement = conn.prepareCall(runSP);
            
        
        
        
        callableStatement.setInt("V_SUBTOTAL",Integer.parseInt(SUBTOTAL));
        callableStatement.setInt("V_PROPINA", Integer.parseInt(PROPINA));
        callableStatement.setInt("V_DCTO", Integer.parseInt(DESCUENTO));
        callableStatement.setInt("V_TOTAL", Integer.parseInt(TOTAL));
        callableStatement.setString("V_PAGO",PAGO);
        callableStatement.setInt("V_ID_PEDIDO",Integer.parseInt(PEDIDO));
      
            
                callableStatement.executeUpdate();
           
      
            
            
            String runSPP = "{ call RETIRO_PEDIDO_MESA(?) }";
            
            
            
            
              try{
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);            
                
                  callableStatement = conn.prepareCall(runSP);
                
            
               callableStatement.setInt("V_ID_MESA", Integer.parseInt(ID_MESA));

                // run it


                callableStatement.executeUpdate();
                

               String runS = "{ call TERMINO_PEDIDO(?) }";
               
               
                 try{
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);            
                
                  callableStatement = conn.prepareCall(runS);
                
            
               callableStatement.setInt("V_ID", Integer.parseInt(ID_PEDIDO));

                // run it


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
            
         
       
            
        
         } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

    } catch (Exception e) {
        e.printStackTrace();
    } 
         return "La boleta se agrego correctamente";
    }

  
    
    }
    
    
  
   



    
    
