package model.dao;


//librerias necesarias del modelo
import util.JDBCUtilities;
import model.vo.compra_paquetes_vo;

//Librerias necesarias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class compra_paquetes_dao {

    //-------------------------------------------------------------------------------------------------------------------------//
    //----------------------------------Crear usuario----------------------------------//

    public compra_paquetes_vo crear_compra(compra_paquetes_vo compra) throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        compra_paquetes_vo creacion_nueva_compra = new compra_paquetes_vo();
        Connection conexion = null;

        //Consulta a realizar, se puede hacer tanto por fuera como dentro del try
        String solicitud_sql = "INSERT INTO cliente_compra_paquete( "+
                                                    "id_cliente_compra,  "+
                                                    "Id_paquete_compra,  "+
                                                    "fecha_compra  "+
                                                    ") " +
                                        "values(?, ?, ?) " +
                                        ";";

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {

            //Impresión de prueba
            System.out.println("Entrada al try agregar usuario");
            conexion = JDBCUtilities.getConnection();
            
            //Impresion de prueba
            System.out.println("get "+compra.getId_cliente_compra());
            //System.out.println(creacion_nueva_compra.getId_compra()); 
            System.out.println("get "+compra.getId_paquete_compra()); 
            System.out.println("get "+compra.getFecha_compra()); 

            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(solicitud_sql);
            statement.setString(1, compra.getId_cliente_compra());
            statement.setInt(2, compra.getId_paquete_compra());
            statement.setDate(3, new Date(compra.getFecha_compra().getTime()));

            statement.executeUpdate();

            //cerrar interacciones con la base
            statement.close();

            //si el proceso fue exitoso cambiar el estado del objeto y asignarle el valor(es) del objeto recibido
            creacion_nueva_compra = compra;



        } catch (SQLException e) {
            System.err.println("Error al registrar un nuevo cliente a la base "+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene un objeto de tipo creacion_usuario_vo
        //usando la información del id que se pasa. debe devolver los datos disponibles en los vo
        return creacion_nueva_compra;
    }
    
}
