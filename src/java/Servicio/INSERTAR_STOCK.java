/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "INSERTAR_STOCK")
public class INSERTAR_STOCK {

       static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";


    // Database credentials
    static final String USERNAME = "RESTAU";
    static final String PASSWORD = "RESTAU";
    
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "INSERTAR_STOCK")
    public String INSERTAR_STOCK(@WebParam(name = "NOMBRE") String NOMBRE,@WebParam(name = "DESCRIPCION")String DESCRIPCION,@WebParam(name = "FECHA")String FECHA,@WebParam(name = "STOCK")String STOCK,@WebParam(name ="STOCK_CRITICO")String STOCK_CRITICO,@WebParam(name = "ID_FAMILIA")String ID_FAMILIA,@WebParam(name = "IMAGEN")String IMAGEN)
    throws  Exception
    {
        
         String runSP = "{ call INSERTAR_STOCK  (?,?,?,?,?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
            try{
			

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "RESTAU", "RESTAU");   
              callableStatement = conn.prepareCall(runSP);
            // create or replace stored procedure
        
           //callableStatement.setString("NOMBRES", NOMBRES);
        //    callableStatement.setString("V_CATEGORIA", CATEGORIA);
        
        
        callableStatement.setString("V_NOMBRE", NOMBRE);
        callableStatement.setString("V_DESCRIPCION", DESCRIPCION);
        callableStatement.setString("V_FECHA", FECHA);
        callableStatement.setInt("V_STOCK", Integer.parseInt(STOCK));
        callableStatement.setInt("V_STOCK_CRITICO",Integer.parseInt(STOCK_CRITICO));
        callableStatement.setInt("V_ID_FAMILIA",Integer.parseInt(ID_FAMILIA));
        callableStatement.setString("V_IMAGEN", IMAGEN);
            
            // run it
            
                           //statement.execute(runSP);
            callableStatement.executeUpdate();
               
    } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

    } catch (Exception e) {
        e.printStackTrace();
    } 
         return "El Stock se agrego correctamente";
    }
        
         /**try{
             
            DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
            String query = "";
             
         query="INSERT INTO PRODUCTO (NOMBRE,DESCRIPCION,FECHA_VENCIMIENTO,STOCK,STOCK_CRITICO,ID_FAMILIA,IMAGEN) VALUES( ";
             
                query = query + "'"+NOMBRE+"','"
                +DESCRIPCION+"','"
                +FECHA+"','"
                +STOCK+"','"
                +STOCK_CRITICO+"','"
                 +ID_FAMILIA+"','" 
                +IMAGEN+"')";

        //    db.ExecuteDML(query);  
           
            return "STOCK ";

            
        }catch(Exception ex){
            
            
            throw ex;
        }
        
    }

   *
    
     */
        

     
    
    @WebMethod(operationName = "AGREGAR_CATEGORIA")
    
    
    public String AGREGAR_CATEGORIA(@WebParam(name = "CATEGORIA") String CATEGORIA) throws  Exception{
            
        
        String runSP = "{ call AGREGAR_CATEGORIA_STOCK (?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
         try{
			

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "RESTAU", "RESTAU");   
              callableStatement = conn.prepareCall(runSP);
            // create or replace stored procedure
        
           //callableStatement.setString("NOMBRES", NOMBRES);
            callableStatement.setString("V_CATEGORIA", CATEGORIA);
        
            
            // run it
            
                           //statement.execute(runSP);
            callableStatement.executeUpdate();
               
    } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

    } catch (Exception e) {
        e.printStackTrace();
    } 
         return "Categoria Agregada";
    }  

  
    
    @WebMethod(operationName = "MODIFICAR_STOCK")
    public String MODIFICAR_STOCK(@WebParam(name = "ID_STOCK")String ID_STOCK,@WebParam(name = "NOMBRE")String NOMBRE,@WebParam(name = "DESCRIPCION")String DESCRIPCION,@WebParam(name = "FECHA")String FECHA,@WebParam(name = "STOCK")String STOCK,@WebParam(name ="STOCK_CRITICO")String STOCK_CRITICO,@WebParam(name = "ID_FAMILIA")String ID_FAMILIA) throws Exception {
       
          try {
             
             DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
             
             
             String query="";
             
             query="UPDATE PRODUCTO SET nombre='"+NOMBRE+"', descripcion='"+DESCRIPCION+"',  fecha_vencimiento='"+FECHA+"',  stock='"+STOCK+"', stock_critico='"+STOCK_CRITICO+"', id_familia='"+ID_FAMILIA+"' WHERE  id_producto='"+ID_STOCK+"'";
             
              
             db.ExecuteDML(query);
             
             
             
            return "Stock Actualizado correctamente";
             
             
             
         } catch (Exception ex) {
             
             System.out.println("******************************+ ERROR********************+");
             
             System.out.println(ex.getMessage());
             
             throw  ex;
             
         }
         
    }
    

    
}   
         
         
         
         
         
 

         
      
    

 
        
        

      
        
        
        
        

    
    
    



    
    
    
    
        
        
        
        
        
    

