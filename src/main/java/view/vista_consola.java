package view;

import java.sql.SQLException;
import controller.controlador;
import java.util.ArrayList;
import model.vo.usuarios_vo;
import model.dao.usuarios_dao;


public class vista_consola {

    //Atributos
    //Controlador necesario para el funcionamiento de la vista
    public static final controlador controlador = new controlador();

    public static void mostrar_usuarios_consola(){

        System.out.println("-----Ranking Descendente Bancos (Área Proyectos)-------");       

        try{

            ArrayList<usuarios_vo> listado_usuarios = controlador.consultar_clientes();
            System.out.println("-----------Usuarios------------");

            for (usuarios_vo usuario : listado_usuarios) {
                
                System.out.printf("%s\t %s\t %s\t %tD\t %d\t %s\t %s\t %d\t %n",
                    usuario.getDocumento(),
                    usuario.getNombres(),
                    usuario.getApellidos(),
                    usuario.getFecha_nac(),
                    usuario.getRazon_ingreso(),
                    //usuario.getTipo_doc(), 
                    usuario.getCorreo(),
                    usuario.getCelular(),
                    usuario.getUsuario_activo()
                );

            }
        }catch(SQLException e){
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }
    }

    
}
