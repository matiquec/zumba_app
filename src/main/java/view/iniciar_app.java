package view;

/*
CLASE QUE CREA UN FRAME EN EL CUAL ES POSIBLE
MOSTAR UN JTABLE CON EL CONTENIDO DE LA 
TABLA CLIENTES EN LA BASE DE DATOS
*/

//Librerias elementos para visualizar
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import controller.controlador;

//Gridlayout
import java.awt.GridLayout;


//libreria necesarias para trabajar
import java.sql.Date;
import java.util.ArrayList;

import model.dao.usuarios_dao;
//Librerias del modelo
import model.vo.usuarios_vo;

public class iniciar_app extends JFrame {
    
    //Botones a utilizar
    private JButton btn_adicionar;
    private JButton btn_actualizar;
    private JButton btn_eliminar;
    
    //campos de texto para un usuario
    private JTextField txt_nombre;
    private JTextField txt_apellido;
    private JTextField txt_tipo_doc;
    private JTextField txt_documento;
    private JTextField txt_correo;
    private JTextField txt_celular;
    private JTextField txt_fecha_nac;
    //JTextField txt_usuario_activo; revisar como funcionaría el usuario por defecto, si al crear usuario se compra un paquete o no

    //Labels de cada campo
    private JLabel label_nombre;
    private JLabel label_apellido;
    private JLabel label_tipo_doc;
    private JLabel label_documento;
    private JLabel label_correo;
    private JLabel label_celular;
    private JLabel label_fecha_nac;

    //Tablas
    private JTable tabla_crud_usuario;

    //objeto tipo dao
    private usuarios_dao usuario_dao;

    //El constructor va a recibir un objeto de arraylist que contiene el registro de los usuarios
    //obtenido en la clase creacion_usuario_dao
    //Constructores
    public iniciar_app(){
    }

    public void abrir_menu(ArrayList<usuarios_vo> usuarios, controlador controlador){

        //Propiedades del frame
        setTitle("--CRUD Usuario--");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        //ventana del frame
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        
        //Creacion de la tabla
        this.tabla_crud_usuario = añadir_usuarios_a_tabla(usuarios);
        //Barra de scroll cuando la tabla no se ajusta a la pantalla
        JScrollPane sp = new JScrollPane(this.tabla_crud_usuario);

        JPanel panel = new JPanel(new GridLayout());
        panel.setBorder(new TitledBorder("Listado de Usuarios"));
        //panel.add(this.tabla_crud_usuario);
        panel.add(sp);


         //Contenedor       
         getContentPane().add(panel);        
        
         //Mostrar ventana/frame
         setSize(400,250);
         setLocationRelativeTo(null);
         setVisible(true);
    }

    private JTable añadir_usuarios_a_tabla(ArrayList<usuarios_vo> usuarios){
        //Tabla nueva
        JTable tabla;
        //Encabezado de la tabla
        String[] encabezado = {"documento","nombres", "apellidos", "fecha_nac",
        //"tipo_doc", 
        "razon_ingreso", "correo", "celular",  "usuario_activo"};

        //Crear el objeto tabla con los elementos que se van a mostrar
        tabla = new JTable(this.formato_registro(usuarios, encabezado.length) ,encabezado);
        return tabla;
    }

    private String[][] formato_registro(ArrayList<usuarios_vo> lista_usuarios, int encabezado){
        //Crear el objeto que contendra la información
        String[][] registros = new String[lista_usuarios.size()][encabezado];
        
        //Impresion de prueba
        System.out.println("Entre al formato registro");

        //el objeto arraylist contiene la información y debe separarse
        //desenvolver el objeto por medio de un for
        for (int i = 0; i < lista_usuarios.size(); i++) {
            registros[i][0] = lista_usuarios.get(i).getDocumento();
            registros[i][1] = lista_usuarios.get(i).getNombres();
            registros[i][2] = lista_usuarios.get(i).getApellidos();
            //registros[i][2] = String.valueOf(lista_usuarios.get(i).getTipo_doc());
            registros[i][3] = String.valueOf(lista_usuarios.get(i).getFecha_nac());
            registros[i][4] = String.valueOf(lista_usuarios.get(i).getRazon_ingreso());
            registros[i][5] = lista_usuarios.get(i).getCorreo();
            registros[i][6] = lista_usuarios.get(i).getCelular();            
            registros[i][7] = String.valueOf(lista_usuarios.get(i).getUsuario_activo());
            
            //Impresion de prueba
            System.out.println(registros[i][0]);

        }
        return registros;
    }


}
