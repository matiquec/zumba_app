package model.dao;

//librerias necesarias del modelo
import util.JDBCUtilities;
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



public class usuarios_dao {
    
    //----------------------------------Consultar Usuarios----------------------------------//
    
    public ArrayList<usuarios_vo> consultar_usuarios() throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        ArrayList<usuarios_vo> respuesta_registros_todos_usuarios = new ArrayList<>();
        Connection conexion = null;

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {
            conexion = JDBCUtilities.getConnection();
            //consulta a realizar
            String consulta = "SELECT * FROM cliente;";
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                //Creación del objeto vo donde se guardara la información de cada registro
                usuarios_vo usuarios_base_datos = new usuarios_vo();
                usuarios_base_datos.setDocumento(resultSet.getString(1));
                usuarios_base_datos.setNombres(resultSet.getString(2));
                usuarios_base_datos.setApellidos(resultSet.getString(3));
                usuarios_base_datos.setFecha_nac(resultSet.getDate(4));
                usuarios_base_datos.setRazon_ingreso(resultSet.getInt(5));
                usuarios_base_datos.setCorreo(resultSet.getString(6));
                usuarios_base_datos.setCelular(resultSet.getString(7));
                usuarios_base_datos.setUsuario_activo(resultSet.getInt(8));
            
                //usuarios_base_datos.setTipo_doc(resultSet.getInt(4));
                

                //almacenamiento de cada registro en un arraylist con toda la info del objeto usuario vo
                respuesta_registros_todos_usuarios.add(usuarios_base_datos);

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
        return respuesta_registros_todos_usuarios;
    }


    //-------------------------------------------------------------------------------------------------------------------------//
    //---------------------------hacer una consulta de usuario por ID----------------------//

    //Hacer una consulta ID en la tabla......
    public usuarios_vo consultar_usuarios_por_id(String id ) throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        usuarios_vo respuesta_registro_usuario_id = null;
        Connection conexion = null;
        //Consulta a realizar, se puede hacer tanto por fuera como dentro del try
        String solicitud_sql = "select * FROM  cliente  WHERE  cedula  ="+  id+ ";";
        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {

            conexion = JDBCUtilities.getConnection();
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(solicitud_sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                //Creación del objeto vo donde se guardara la información de cada registro
                usuarios_vo usuarios_base_datos = new usuarios_vo();
                usuarios_base_datos.setDocumento(resultSet.getString(1));
                usuarios_base_datos.setNombres(resultSet.getString(2));
                usuarios_base_datos.setApellidos(resultSet.getString(3));
                usuarios_base_datos.setFecha_nac(resultSet.getDate(4));
                usuarios_base_datos.setRazon_ingreso(resultSet.getInt(5));
                usuarios_base_datos.setCorreo(resultSet.getString(6));
                usuarios_base_datos.setCelular(resultSet.getString(7));
                usuarios_base_datos.setDias_disponibles(resultSet.getInt(8));
                usuarios_base_datos.setUsuario_activo(resultSet.getInt(9));

                //almacenamiento de cada registro en otro objeto vo si todo fue exitoso, para poder enviarlo
                respuesta_registro_usuario_id = usuarios_base_datos;
                
            }
            //cerrar interacciones con la base
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al consultar el cliente de la base"+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene un objeto de tipo creacion_usuario_vo
        //usando la información del id que se pasa. debe devolver los datos disponibles en los vo
        return respuesta_registro_usuario_id;
    }



    //-------------------------------------------------------------------------------------------------------------------------//
    //----------------------------------Crear usuario----------------------------------//

    public usuarios_vo crear_nuevo_usuario(usuarios_vo nuevo_usuario) throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        usuarios_vo creacion_usuario_nuevo = null;
        Connection conexion = null;

        //Consulta a realizar, se puede hacer tanto por fuera como dentro del try
        String consulta = "INSERT INTO cliente( "+
                                                    "cedula, "+ 
                                                    "Nombre,  "+
                                                    "Apellido,  "+
                                                    "Fecha_nacimiento,  "+
                                                    "Razon,  "+
                                                    "Correo,  "+
                                                    "Telefono "+
                                                    ") " +
                                        "values( ?, ?, ?, ?, ?, ?, ?) " +
                                        ";";

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {

            //Impresión de prueba
            System.out.println("Entrada al try agregar usuario");
            conexion = JDBCUtilities.getConnection();
            
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nuevo_usuario.getDocumento());
            statement.setString(2, nuevo_usuario.getNombres());
            statement.setString(3, nuevo_usuario.getApellidos());            
            statement.setDate(4, new Date(nuevo_usuario.getFecha_nac().getTime())); //new java.sql.Date(date.getTime()). Objeto tipo Date de SQL
            statement.setInt(5, nuevo_usuario.getRazon_ingreso());
            statement.setString(6, nuevo_usuario.getCorreo() );
            statement.setString(7, nuevo_usuario.getCelular());


            statement.executeUpdate();

            //Impresión de prueba
            System.out.println("final del try, antes del while");
            //cerrar interacciones con la base
            statement.close();

            //si el proceso fue exitoso cambiar el estado del objeto y asignarle el valor(es) del objeto recibido
            creacion_usuario_nuevo = nuevo_usuario;

        } catch (SQLException e) {
            System.err.println("Error al registrar un nuevo cliente a la base "+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene un objeto de tipo creacion_usuario_vo
        //usando la información del id que se pasa. debe devolver los datos disponibles en los vo
        return creacion_usuario_nuevo;
    }
    

    //----------------------------------Actualizar usuario----------------------------------//

    public usuarios_vo actualizar_datos(usuarios_vo usuario_actualizar) throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        usuarios_vo usuario_actualizado = null;
        Connection conexion = null;

        //Consulta a realizar, se puede hacer tanto por fuera como dentro del try
        //UPDATE zumba.cliente
        //SET Activo=1,dias_disponibles=1
        //WHERE cedula='1';
        // UPDATE cliente SET cedula = ?, Nombre = ?, Apellido = ?, Fecha_nacimiento = ?, Razon = ?, Correo = ?, Telefono = ? WHERE cedula = ?
        String solicitud_sql = "UPDATE cliente "+
                                "SET cedula = ?, Nombre = ?, Apellido = ?, Fecha_nacimiento = ?, Razon = ?, Correo = ?, Telefono = ?, dias_disponibles = ?, Activo = ? "+
                                "WHERE cedula = ? "+ 
                                ";";

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {

            conexion = JDBCUtilities.getConnection();
            
            //Métodos necesarios para realizar la solicitud y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(solicitud_sql);
            statement.setString(1, usuario_actualizar.getDocumento());
            statement.setString(2, usuario_actualizar.getNombres());
            statement.setString(3, usuario_actualizar.getApellidos());            
            statement.setDate(4, new Date(usuario_actualizar.getFecha_nac().getTime())); //new java.sql.Date(date.getTime()). Objeto tipo Date de SQL
            statement.setInt(5, usuario_actualizar.getRazon_ingreso());
            statement.setString(6, usuario_actualizar.getCorreo());
            statement.setString(7, usuario_actualizar.getCelular());
            statement.setInt(8, usuario_actualizar.getDias_disponibles());
            statement.setInt(9, usuario_actualizar.getUsuario_activo());
            statement.setString(10, usuario_actualizar.getDocumento());


            statement.executeUpdate();

            //Impresión de prueba
            System.out.println("final del try, antes del while");
            //cerrar interacciones con la base
            statement.close();

            //si el proceso fue exitoso cambiar el estado del objeto y asignarle el valor(es) del objeto recibido
            usuario_actualizado = usuario_actualizar;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la informacion del cliente"+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene un objeto de tipo creacion_usuario_vo
        //usando la información del id que se pasa. debe devolver los datos disponibles en los vo
        return usuario_actualizado;
    }


    //-------------------------------Consultar usuarios activos----------------------------------//

    public ArrayList<usuarios_vo> consultar_usuarios_activos() throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        ArrayList<usuarios_vo> respuesta_registros_todos_usuarios = new ArrayList<>();
        Connection conexion = null;

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {
            conexion = JDBCUtilities.getConnection();
            //consulta a realizar
            String consulta = "SELECT * FROM cliente WHERE Activo=1;";
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                //Creación del objeto vo donde se guardara la información de cada registro
                usuarios_vo usuarios_base_datos = new usuarios_vo();
                usuarios_base_datos.setDocumento(resultSet.getString(1));
                usuarios_base_datos.setNombres(resultSet.getString(2));
                usuarios_base_datos.setApellidos(resultSet.getString(3));
                usuarios_base_datos.setFecha_nac(resultSet.getDate(4));
                usuarios_base_datos.setRazon_ingreso(resultSet.getInt(5));
                usuarios_base_datos.setCorreo(resultSet.getString(6));
                usuarios_base_datos.setCelular(resultSet.getString(7));
                usuarios_base_datos.setDias_disponibles(resultSet.getInt(8));
                usuarios_base_datos.setUsuario_activo(resultSet.getInt(9));
            
                //usuarios_base_datos.setTipo_doc(resultSet.getInt(4));
                

                //almacenamiento de cada registro en un arraylist con toda la info del objeto usuario vo
                respuesta_registros_todos_usuarios.add(usuarios_base_datos);

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
        return respuesta_registros_todos_usuarios;
    }


    //-------------------------------Consultar usuarios inactivos----------------------------------//

    public ArrayList<usuarios_vo> consultar_usuarios_inactivos() throws SQLException{

        //Crear un objeto tipo crear usuario value_object donde estan los atributos para la tabla de usuarios
        ArrayList<usuarios_vo> respuesta_registros_todos_usuarios = new ArrayList<>();
        Connection conexion = null;

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {
            conexion = JDBCUtilities.getConnection();
            //consulta a realizar
            String consulta = "SELECT * FROM cliente WHERE Activo=0;";
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                //Creación del objeto vo donde se guardara la información de cada registro
                usuarios_vo usuarios_base_datos = new usuarios_vo();
                usuarios_base_datos.setDocumento(resultSet.getString(1));
                usuarios_base_datos.setNombres(resultSet.getString(2));
                usuarios_base_datos.setApellidos(resultSet.getString(3));
                usuarios_base_datos.setFecha_nac(resultSet.getDate(4));
                usuarios_base_datos.setRazon_ingreso(resultSet.getInt(5));
                usuarios_base_datos.setCorreo(resultSet.getString(6));
                usuarios_base_datos.setCelular(resultSet.getString(7));
                usuarios_base_datos.setDias_disponibles(resultSet.getInt(8));
                usuarios_base_datos.setUsuario_activo(resultSet.getInt(9));
            
                //usuarios_base_datos.setTipo_doc(resultSet.getInt(4));
                

                //almacenamiento de cada registro en un arraylist con toda la info del objeto usuario vo
                respuesta_registros_todos_usuarios.add(usuarios_base_datos);

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
        return respuesta_registros_todos_usuarios;
    }

    //----------------------------------Eliminar usuario----------------------------------//
}
