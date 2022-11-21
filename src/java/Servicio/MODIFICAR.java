/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import oracle.jdbc.internal.OracleTypes;
/**
 *
 * @author Camilo
 */






@WebService(serviceName = "Servicios")
public class MODIFICAR {

    /**
     * This is a sample web service operation
     */
     @WebMethod(operationName = "Modificar")
    public String Modificar(@WebParam(name="ID_USUARIO") String V_ID,@WebParam(name = "RUT") String V_RUT,@WebParam(name="LOGIN") String V_LOGIN, 
            @WebParam(name="CLAVE") String V_CLAVE ,
           @WebParam(name="FECHA_MODIFICACION") String V_FECHA,@WebParam(name = "NOMBRES")String V_NOMBRES, @WebParam(name = "APELLIDOS")String V_APELLIDOS,@WebParam(name="EMAIL") String V_EMAIL ,@WebParam(name = "ESTADO")String V_ESTADO,@WebParam(name = "ID_ROL" ) String V_ROL,@WebParam(name = "FONO")String V_FONO)  throws Exception{
        
    
        
         try {
             
             DBSqlClass db = new DBSqlClass("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:xe", "RESTAU","RESTAU");
             
             
             String query="";
             
             query="UPDATE USUARIO SET RUT='"+V_RUT+"', LOGIN='"+V_LOGIN+"', CLAVE='"+V_CLAVE+"', FECHA_MODIFICACION='"+V_FECHA+"',NOMBRES='"+V_NOMBRES+"',APELLIDOS='"+V_APELLIDOS+"',EMAIL='"+V_EMAIL+"', ESTADO='"+V_ESTADO+"', ID_ROL='"+V_ROL+"',FONO='"+V_FONO+"' WHERE  ID_USUARIO='"+V_ID+"'";
             
              
             db.ExecuteDML(query);
             
             
             
             return "";
             
             
             
         } catch (Exception ex) {
             
             System.out.println("******************************+ ERROR********************+");
             
             System.out.println(ex.getMessage());
             
             throw  ex;
             
         }
         
    }
    

     
         
    
    
}   
         
         
         
         
         
         
         
         
         
         
         

