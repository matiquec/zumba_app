package model.dao;

//librerias necesarias del modelo
import util.JDBCUtilities;
import model.vo.listas_seleccion_vo;

//Librerias necesarias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;


public class listas_seleccion_dao {

    //----------------------------------Consultar tabla razon_ingreso----------------------------------//
    public ArrayList<listas_seleccion_vo> lista_tabla_razon_ingreso() throws SQLException{

        //Crear un objeto tipo lista_seleccion_vo value_object donde estan los atributos
        //que contendran los registros de la tabla razon_ingreso en la base de datos
        ArrayList<listas_seleccion_vo> respuesta_razon_ingreso = new ArrayList<>();
        Connection conexion = null;

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {
            conexion = JDBCUtilities.getConnection();
            //consulta a realizar
            String consulta = "SELECT razon_ingreso.Descripcion FROM razon_ingreso;";
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                //Creación del objeto vo donde se guardara la información de cada registro
                listas_seleccion_vo listado_razon_ingreso = new listas_seleccion_vo();
                //listado_razon_ingreso.setCodigo_razon_ingreso(resultSet.getInt(1));
                listado_razon_ingreso.setDescripcion(resultSet.getString(1));               

                //almacenamiento de cada registro en un arraylist con toda la info del objeto usuario vo
                respuesta_razon_ingreso.add(listado_razon_ingreso);

            }
            //cerrar interacciones con la base
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al consulta la tabla razon_ingreso de la base "+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene objetos de tipo creacion_usuario_vo
        return respuesta_razon_ingreso;
    }

    //-----------------------------------Obtener el key de la razon escogida-------------------------------------//

    public int key_razon_escogida(String descripcion) throws SQLException{

        //crear un entero del tipo listado_vo para almacenar el entero
        //inicializado en 0 por si no puede realizar la consulta
        int key=0;
        Connection conexion = null;

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {
            conexion = JDBCUtilities.getConnection();
            //consulta a realizar
            String consulta = "SELECT Id_razon_ingreso FROM razon_ingreso WHERE Descripcion= '"+descripcion+"';";
            //Métodos necesarios para realizar la consulta y guardarla en el objeto

            PreparedStatement statement = conexion.prepareStatement(consulta);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            
            {
                key = resultSet.getInt("Id_razon_ingreso");
            }
            
            //cerrar interacciones con la base
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al consultar el key de la razon ingresada "+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene objetos de tipo creacion_usuario_vo
        return key;
    }



    
    //----------------------------------Consultar tablas para listas deplegables----------------------------------//
    //El método va a recibir la columna y la tabla de la cual se quieren obtener los valores para 
    //crear una lista desplegable con dichos valores
    public ArrayList<listas_seleccion_vo> crear_lista_desplegable(String columna, String tabla) throws SQLException{

        //Crear un objeto tipo lista_seleccion_vo value_object donde estan los atributos
        //que contendran los registros de la tabla razon_ingreso en la base de datos
        ArrayList<listas_seleccion_vo> respuesta_razon_ingreso = new ArrayList<>();
        Connection conexion = null;

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {
            conexion = JDBCUtilities.getConnection();
            //consulta a realizar
            String consulta = "SELECT "+ columna + " FROM " + tabla +" ;";
            //Métodos necesarios para realizar la consulta y guardarla en el objeto
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                //Creación del objeto vo donde se guardara la información de cada registro
                listas_seleccion_vo listado_razon_ingreso = new listas_seleccion_vo();
                //listado_razon_ingreso.setCodigo_razon_ingreso(resultSet.getInt(1));
                listado_razon_ingreso.setDescripcion(resultSet.getString(1));               

                //almacenamiento de cada registro en un arraylist con toda la info del objeto usuario vo
                respuesta_razon_ingreso.add(listado_razon_ingreso);

            }
            //cerrar interacciones con la base
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al consulta la tabla razon_ingreso de la base "+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene objetos de tipo creacion_usuario_vo
        return respuesta_razon_ingreso;
    }



    //-----------------------------------Obtener el key de la razon escogida-------------------------------------//
    //La lista desplegable genera un valor en texto del cual se buscará el ID en su respectiva tabla
    //para ello se solicitará en el método el valor obtenido, la tabla y la columna del Id de la tabla.
    public int llave_lista_desplegable(String columna_id, String tabla, String Columna_descripcion, String descripcion ) throws SQLException{

        //crear un entero del tipo listado_vo para almacenar el entero
        //inicializado en 0 por si no puede realizar la consulta
        int key=0;
        Connection conexion = null;

        //Intentar acceder a la base de datos y realizar la consulta respectiva
        try {
            conexion = JDBCUtilities.getConnection();
            //consulta a realizar
            String consulta = "SELECT "+ columna_id + " FROM "+ tabla + " WHERE " + Columna_descripcion +" = '"+ descripcion +"';";
            //Métodos necesarios para realizar la consulta y guardarla en el objeto

            PreparedStatement statement = conexion.prepareStatement(consulta);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            
            {
                key = resultSet.getInt(columna_id);
            }
            
            //cerrar interacciones con la base
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al consultar el key de la razon ingresada "+ e);
        } finally{
            if (conexion!= null){
                conexion.close();
            }
        }

        //la clase retornará un arraylist que contiene objetos de tipo creacion_usuario_vo
        return key;
    }




}
