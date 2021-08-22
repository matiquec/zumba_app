package util;

//Clase de utilidades del software
//permite manejo de archivos, manejo de bases de datos
//en este caso, esta clase va a permitir la conexión con la base de datos

//Librerias necesarias
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.*;

//Librerias archivos
import java.io.File;

public class JDBCUtilities {


//---------------------------------------Conexión a sqlite3--------------------------------------//

//Atributos

    //ubicación base de datos. Inmodificable (Relativa o absoluta)
    private static final String UBICACION_BD = "resources/zumba.db"; //Ruta absoluta
    
    //Método proveer conexión
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:sqlite:" + JDBCUtilities.UBICACION_BD;

        return DriverManager.getConnection(url);
        
    }

     //Método verificar conexión, porque sqlite crea la base si no existe
     public static boolean base_vacia(){
        boolean valor;
        File archivo = new File(JDBCUtilities.UBICACION_BD);
        if (archivo.length() == 0){
            valor =true;
        }else{
            valor = false;
        }
        System.out.println(valor);
        return valor;
    }

//---------------------------------------Conexión a Mysql---------------------------------------//

    // Connection conexion = null;
    
    // private static final String url = "jdbc:mysql://localhost:3306/zumba";
    
    // public static Connection getConnection() throws SQLException{

    //     try {

    //         //Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    //         System.out.println("conexion exitosa?");
    //     } 
    //     catch (Exception ex) {
    //         // handle the error
    //         System.out.println("Error al conectar"+ex);
    // }

    // return DriverManager.getConnection(url,"root","");
    // }

}     

            
            
