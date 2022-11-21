
package Servicio;



import static Servicio.CAJA.JDBC_DRIVER;
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

import java.sql.Statement;



/**
 *
 * @author Camilo
 */
@WebService(serviceName = "SERVICIOS_SOAP")
public class SERVICIOS_SOAP {

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";


    // Database credentials
    static final String USERNAME = "RESTAU";
    static final String PASSWORD = "RESTAU";  
            
    /**
     * This is a sample web service operation
     */
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

            try{
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);            
               
                  callableStatement = conn.prepareCall(runSP);
                
               callableStatement.setString("V_DIRECCION", DIRECCION);
               callableStatement.setInt("V_ID_USUARIO", Integer.parseInt(ID_USUARIO));

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
         return "Cliente Registrado";
    }  
    
    
    
    
    
    
       @WebMethod(operationName = "login")
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws Exception{
        
           
     try{
            DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "servicios","servicios");
            
            
            System.out.println("Conectado a la Base de datos Restaurante");
            
            String query="select * from usuario where usuario='"+username+"' and contrasena='"+password+"'";
            System.out.println(query);
            
            ResultSet rs = db.QuerySelectDataReturnResultSet(query);
            
            System.out.println("Query Ejecutandose");
            while(rs.next()){
                System.out.println(rs.getString("usuario"));
                return "Bienvenidos al Restaurante SIGLO XXI";
            }
            return "";
            
        }catch(Exception ex){
            System.out.println("error");
            throw ex;
        }
        }
        
    
    
    
    
    
    
    @WebMethod(operationName = "Registro")
        public String Registro(@WebParam(name = "RUT") String RUT,@WebParam(name="LOGIN") String LOGIN, @WebParam(name="CLAVE") String CLAVE ,@WebParam(name="FECHA_CREACION")String FECHA_CREACION,@WebParam(name="FECHA_MODIFICACION") String FECHA_MODIFICACION,@WebParam(name = "NOMBRES")String NOMBRES, @WebParam(name = "APELLIDOS")String APELLIDOS,@WebParam(name="EMAIL") String EMAIL ,@WebParam(name = "ESTADO")String ESTADO,@WebParam(name = "ID_ROL" ) String ID_ROL,@WebParam(name = "FONO")String FONO)  throws Exception{
         try{
             
            DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
            
            
            System.out.println("Conectado a la Base de datos Restaurante");
            String query = "";
            System.out.println("ANTES");
            System.out.println(query);
            
         query="INSERT INTO USUARIO (RUT,LOGIN,CLAVE,FECHA_CREACION,FECHA_MODIFICACION,NOMBRES,APELLIDOS,EMAIL,ESTADO,ID_ROL,FONO) VALUES( ";
             
                query = query + "'"+RUT+"','"
                +LOGIN+"','"
                +CLAVE+"','"
                +FECHA_CREACION+"','"
                +FECHA_MODIFICACION+"','"
                 +NOMBRES+"','"
                 +APELLIDOS+"','"
                +EMAIL+"','"
                +ESTADO+"','"
                +ID_ROL+"','"
                +FONO+"')";
                
                
                
            
                
            System.out.println("DESPUES");
            System.out.println(query);
            db.ExecuteDML(query);
          
            //ResultSet rs = db.QuerySelectDataReturnResultSet(query);
            
            
            //while(rs.next()){
            //    //System.out.println(rs.getString("USUARIO"));
              //  System.out.println("DESPUES DE GETSTRING");

              
          //  }      
          
           
       return "";
            
        }catch(Exception ex){
            System.out.println("*******************error*******************");
            System.out.println(ex.getMessage());
            System.out.println("*******************error*******************");
            
            throw ex;
        }
        
        }
        

  
    /////----------------servicio web PREPARACION -------------------------------------------------------------
     
    
    @WebMethod(operationName = "INSERTAR_MENU")
    public String INSERTAR_MENU(@WebParam(name = "NOMBRE") String NOMBRE,@WebParam(name = "DESCRIPCION")String DESCRIPCION,@WebParam(name = "TIEMPO")String TIEMPO,@WebParam(name = "VALOR")String VALOR,@WebParam(name = "DISPONIBILIDAD")String DISPONIBILIDAD,@WebParam(name = "ID_CATEGORIA")String ID_CATEGORIA,@WebParam(name = "IMAGEN")String IMAGEN)throws  Exception{
        
        
        String runSP = "{ call INSERTAR_MENU (?,?,?,?,?,?,?) }";
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
            callableStatement.setString("V_NOMBRE", NOMBRE);
            callableStatement.setString("V_DESCRIPCION", DESCRIPCION);
            callableStatement.setInt("V_TIEMPO",Integer.parseInt(TIEMPO));
            callableStatement.setInt("V_VALOR ", Integer.parseInt(VALOR));
            callableStatement.setString("V_DISPONIBILIDAD", DISPONIBILIDAD);
             callableStatement.setInt("V_ID_CATEGORIA", Integer.parseInt(ID_CATEGORIA));
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
         return "Preparacion Agregada correctamente";
    }  

    

    //////////////////// SERVICIO WEB PROVEEDORES
    
     @WebMethod(operationName = "PROVEEDOR")
    public String PROVEEDOR(@WebParam(name = "RUT") String RUT,@WebParam(name = "RAZON")String RAZON,@WebParam(name = "DIRECCION")String DIRECCION,@WebParam(name = "TELEFONO")String TELEFONO,@WebParam(name = "EMAIL")String EMAIL,@WebParam(name = "DESCRIPCION")String DESCRIPCION)
            
throws  Exception{
        
        
         String runSP = "{ call INSERTAR_PROVEEDOR(?,?,?,?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
          try{
			

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            callableStatement = conn.prepareCall(runSP);
           
       
        
        callableStatement.setString("V_RUT",RUT);
        callableStatement.setString("V_RAZON",RAZON);
        callableStatement.setString("V_DIRECCION",DIRECCION);
        callableStatement.setString("V_TELEFONO",TELEFONO);
        callableStatement.setString("V_EMAIL",EMAIL);
        callableStatement.setString("V_DESCRIPCION",DESCRIPCION);
            
          
            callableStatement.executeUpdate();        
        
         } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

    } catch (Exception e) {
        e.printStackTrace();
    } 
         return "proveedor";
    }

   
    
    
    
    
    @WebMethod(operationName = "PROVEEDOR_PRODUCTO")
    public String PROVEEDOR_PRODUCTO(@WebParam(name = "ID_PROVEEDOR") String ID_PROVEEDOR,@WebParam(name = "ID_PRODUCTO") String ID_PRODUCTO,@WebParam(name = "PRECIO")String PRECIO) 
            throws  Exception{
       
        
         
         String runSP = "{ call  INSERTAR_PROVEEDOR_PRODUCTO(?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
        
        
         try{
             
            DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
            
            
            System.out.println("Conectado a la Base de datos Restaurante");
            String query = "";
            System.out.println("ANTES");
            System.out.println(query);
            
         query="INSERT INTO  proveedorproducto  (id_proveedor,id_producto,precio_compra) VALUES( ";
             
                query = query + "'"+ID_PROVEEDOR+"','"
                         +ID_PRODUCTO+"','"
                +PRECIO+"')";
                         
            System.out.println("DESPUES");
            System.out.println(query);
            db.ExecuteDML(query);
          
         
          
           
       return "Proveedor Producto";
            
        }catch(Exception ex){
            System.out.println("*******************error*******************");
            System.out.println(ex.getMessage());
            System.out.println("*******************error*******************");
            
            throw ex;
        }
        
        }


 
    
    

@WebMethod(operationName = "PREPARACIONES_ACTUALIZAR")
    public String PREPARACION_MODIFICAR(@WebParam(name = "ID_PLATO") String V_ID_PLATO, @WebParam(name = "NOMBRE") String V_NOMBRE, @WebParam(name = "DESCRIPCION") String V_DESCRIPCION, @WebParam(name = "TIEMPO") String V_TIEMPO, @WebParam(name = "VALOR") String V_VALOR, @WebParam(name = "DISP") String V_DISP, @WebParam(name = "ID_CAT") String V_ID_CAT)
            
            throws  Exception{
        
            String runSP = "{ call  MODIFICAR_PREPARACION(?,?,?,?,?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
          try{
              
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                         
              callableStatement = conn.prepareCall(runSP);
        
        callableStatement.setInt("V_ID_PLATO",Integer.parseInt(V_ID_PLATO));
        callableStatement.setString("V_NOMBRE",V_NOMBRE);
        callableStatement.setString("V_DESCRIPCION",V_DESCRIPCION);
        callableStatement.setInt("V_TIEMPO",Integer.parseInt(V_TIEMPO));
        callableStatement.setInt("V_VALOR",Integer.parseInt(V_VALOR));
        callableStatement.setString("V_DISP",V_DISP);
        callableStatement.setInt("V_ID_CAT",Integer.parseInt(V_ID_CAT));
       
            
            // run it
            
                           //statement.execute(runSP);
            callableStatement.executeUpdate();        
        
         } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

    } catch (Exception e) {
        e.printStackTrace();
    } 
         return "MODIFICAR PREPARACIONES";
    }


    
    @WebMethod(operationName = "ELIMINAR_PREPARACION")
    public String ELIMINAR_PREPARACION(@WebParam(name = "ID_PLATO")String ID_PLATO)throws  Exception{
       
        String runSP = "{ call  ELIMINAR_PREPARACION(?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
          try{
              
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                         
              callableStatement = conn.prepareCall(runSP);
        
         callableStatement.setInt("V_ID",Integer.parseInt(ID_PLATO));
        
         callableStatement.executeUpdate();   
         
        
         } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

    } catch (Exception e) {
        e.printStackTrace();
    } 
         return "ELIMINAR PREPARACION";
    }

 
    
    
    
    
    
    
    @WebMethod(operationName = "PROVEEDOR_ACTUALIZAR")
    public String PROVEEDOR_ACTUALIZAR(@WebParam(name = "ID")  String ID, @WebParam(name = "RUT")  String RUT, @WebParam(name = "RAZON")  String RAZON, @WebParam(name = "DIRECCION")  String DIRECCION, @WebParam(name = "TELEFONO")  String TELEFONO, @WebParam(name = "EMAIL")  String EMAIL, @WebParam(name = "DESCRIPCION")  String DESCRIPCION) {
       String runSP = "{ call  ACTUALIZAR_PROVEEDOR(?,?,?,?,?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
          try{
			

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
          
              callableStatement = conn.prepareCall(runSP);
      
        callableStatement.setInt("V_ID",Integer.parseInt(ID));
        callableStatement.setString("V_RUT",RUT);
        callableStatement.setString("V_RAZON",RAZON);
        callableStatement.setString("V_DIRECCION",DIRECCION);
        callableStatement.setString("V_TELEFONO",TELEFONO);
        callableStatement.setString("V_EMAIL",EMAIL);
        callableStatement.setString("V_DESCRIPCION",DESCRIPCION);
       
            
          
            callableStatement.executeUpdate();        
        
         } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

    } catch (Exception e) {
        e.printStackTrace();
    } 
         return "ACTUALIZAR PROVEEDOR";
    }
           
             
    
      

    

    
    
    
  
   
 @WebMethod(operationName = "INGRESAR_COMPRA")
    public String INGRESAR_COMPRA(@WebParam(name = "DESCRIPCION")  String DESCRIPCION) {
       String runSP = "{ call  INSERTAR_ORDEN_COMPRA(?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
          try{
			

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        
            
              callableStatement = conn.prepareCall(runSP);
      
      
        callableStatement.setString("V_DESCRIPCION",DESCRIPCION);
       
            
        
            callableStatement.executeUpdate();        
        
         } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        

    } catch (Exception e) {
        e.printStackTrace();
    } 
         return "ORDEN COMPRA";
    }
           
        

      
@WebMethod(operationName = "INSERTAR_ORDEN_PRODUCTO")
    public String INSERTAR_ORDEN_PRODUCTO(@WebParam(name = "ID_ORDEN")  String ID_ORDEN,@WebParam(name = "ID_PRODUCTO")String ID_PRODUCTO,@WebParam(name = "CANTIDAD") String CANTIDAD) throws Exception {
       String runSP = "{ call  INSERTAR_ORDEN_PRODUCTO(?,?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
         
         try{
             
            DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
            
            
            System.out.println("Conectado a la Base de datos Restaurante");
            String query = "";
            System.out.println("ANTES");
            System.out.println(query);
            
         query="INSERT INTO  ordenproducto (id_orden,id_producto,cantidad) VALUES( ";
             
                query = query + "'"+ID_ORDEN+"','"
                +ID_PRODUCTO+"','"
                +CANTIDAD+"')";
                         
            System.out.println("DESPUES");
            System.out.println(query);
            db.ExecuteDML(query);
          
           
       return "ORDEN PRODUCTO";
            
        }catch(Exception ex){
            System.out.println("*******************error*******************");
            System.out.println(ex.getMessage());
            System.out.println("*******************error*******************");
            
            throw ex;
        }
        
        }

   
    
      
@WebMethod(operationName = "INSERTAR_PROVEEDOR_ORDEN")
    public String INSERTAR_PROVEEDOR_ORDEN(@WebParam(name = "ID_PROVEEDOR")  String ID_PROVEEDOR,@WebParam(name = "ID_ORDEN")String ID_ORDEN) throws Exception {
        
       String runSP = "{ call  INSERTAR_PROVEEDOR_ORDEN(?,?) }";
        Connection conn;
        CallableStatement callableStatement;
        Statement statement;
        
        
         try{
             
            DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
            
            
            System.out.println("Conectado a la Base de datos Restaurante");
            String query = "";
            System.out.println("ANTES");
            System.out.println(query);
            
         query="INSERT INTO  proveedororden (id_proveedor,id_orden) VALUES( ";
             
                query = query + "'"+ID_PROVEEDOR+"','"
                +ID_ORDEN+"')";
                         
            System.out.println("DESPUES");
            System.out.println(query);
            db.ExecuteDML(query);
          
         
          
           
       return "ORDEN";
            
        }catch(Exception ex){
            System.out.println("*******************error*******************");
            System.out.println(ex.getMessage());
            System.out.println("*******************error*******************");
            
            throw ex;
        }
        
        }
}
   
    

    


        
        
    



        
        
        
        
    
    
    
  
   



    
    

    
    
    



    
    

    
    
    

    

    


        
        
    



        
        
        
        
    
    
    
  
   



    
    

    
    
    



    
    

    
    
    

    


        
        
    



        
        
        
        
    
    
    
  
   



    
    

    
    
    



    
    

    
    
    

    

    


        
        
    



        
        
        
        
    
    
    
  
   



    
    

    
    
    



    
    

    
    
    
