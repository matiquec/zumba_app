package model.dao;


//librerias necesarias del modelo
import util.JDBCUtilities;
import model.vo.paquete_vo;

//Librerias necesarias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class paquete_dao {

    //-------------------------------------------------------------------------------------------------------------------------//
    //---------------------------hacer una consulta de paquete por ID----------------------//
    //Hacer una consulta ID en la tabla......
    public paquete_vo consultar_paquete_id(int id ) throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        paquete_vo respuesta_paquete_id = null;
        Connection conexion = null;
        //Consulta a realizar, se puede hacer tanto por fuera como dentro del try
        String solicitud_sql = "select * FROM  paquete  WHERE  Id_paquete  ="+  id+ ";";
        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {

            conexion = JDBCUtilities.getConnection();
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(solicitud_sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                //Creación del objeto vo donde se guardara la información de cada registro
                paquete_vo paquete_id = new paquete_vo();
                paquete_id.setId_paquete(resultSet.getInt(1));
                paquete_id.setNombre(resultSet.getString(2));
                paquete_id.setDias(resultSet.getInt(3));

                //almacenamiento de cada registro en otro objeto vo si todo fue exitoso, para poder enviarlo
                respuesta_paquete_id = paquete_id;
                
            }
            //cerrar interacciones con la base
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al consultar los paquetes de la base"+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene un objeto de tipo creacion_usuario_vo
        //usando la información del id que se pasa. debe devolver los datos disponibles en los vo
        return respuesta_paquete_id;
    }
    
}
