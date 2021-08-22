package model.dao;

//librerias necesarias del modelo
import util.JDBCUtilities;
import model.vo.asistencia_vo;
import model.vo.usuarios_vo;


//Librerias necesarias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


import java.sql.Date;

public class asistencia_dao {

    //--------------------------------- consultar asistencia -----------------------------------//

    public ArrayList<asistencia_vo> consultar_asistencia() throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        ArrayList<asistencia_vo> respuesta_registroa_asistencia = new ArrayList<>();
        Connection conexion = null;

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {
            conexion = JDBCUtilities.getConnection();
            //consulta a realizar
            String consulta = "SELECT * FROM asistencia;";
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                //Creación del objeto vo donde se guardara la información de cada registro
                asistencia_vo listado_asistencia = new asistencia_vo();
                listado_asistencia.setId_asistencia(resultSet.getInt(1));
                listado_asistencia.setId_cliente(resultSet.getString(2));
                listado_asistencia.setId_clase(resultSet.getInt(3));
                listado_asistencia.setFecha_asistencia(resultSet.getDate(4));
            
                //usuarios_base_datos.setTipo_doc(resultSet.getInt(4));
                

                //almacenamiento de cada registro en un arraylist con toda la info del objeto usuario vo
                respuesta_registroa_asistencia.add(listado_asistencia);

            }
            //cerrar interacciones con la base
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al consulta los clientes de la base "+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene objetos de tipo creacion_usuario_vo
        return respuesta_registroa_asistencia;
    }



    //-------------------------------------------------------------------------------------------------------------------------//
    //--------------------------------- registrar asistencia -----------------------------------------//

    public asistencia_vo registrar_asistencia(asistencia_vo elementos_asistencia) throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        asistencia_vo asistencia_creada = null;
        Connection conexion = null;
        
        //Consulta a realizar, se puede hacer tanto por fuera como dentro del try
        String solicitud_sql = "INSERT INTO asistencia " +
                                "(id_cliente_asistencia,Id_clase_asistencia,Fecha_asistencia) "+
	                            "VALUES (?,?,?);";

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {

            //Impresión de prueba
            System.out.println("Entrada al try agregar usuario");
            conexion = JDBCUtilities.getConnection();
            
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(solicitud_sql);
            //statement.setInt(1, elementos_asistencia.getId_asistencia()); //No necesitaría ser enviado
            statement.setString(1, elementos_asistencia.getId_cliente());
            statement.setInt(2, elementos_asistencia.getId_clase());            
            statement.setDate(3, new Date(elementos_asistencia.getFecha_asistencia().getTime())); //new java.sql.Date(date.getTime()). Objeto tipo Date de SQL

            statement.executeUpdate();

            //Impresión de prueba
            System.out.println("final del try, antes del while registrar asistencia");
            //cerrar interacciones con la base
            statement.close();

            //si el proceso fue exitoso cambiar el estado del objeto y asignarle el valor(es) del objeto recibido
            asistencia_creada = elementos_asistencia;

        } catch (SQLException e) {
            System.err.println("Error al registrar la asistencia a la base "+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene un objeto de tipo creacion_usuario_vo
        //usando la información del id que se pasa. debe devolver los datos disponibles en los vo
        return asistencia_creada;
    }
   
    
}
