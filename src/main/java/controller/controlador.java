package controller;

//librerias necesarias del modelo
import model.dao.usuarios_dao;
import model.vo.usuarios_vo;
import util.JDBCUtilities;
import model.dao.asistencia_dao;
import model.dao.compra_paquetes_dao;
import model.dao.listas_seleccion_dao;
import model.dao.paquete_dao;
import model.vo.asistencia_vo;
import model.dao.asistencia_dao;
import model.vo.compra_paquetes_vo;
import model.vo.listas_seleccion_vo;
import model.vo.paquete_vo;
//librerias necesarias para usar las vistas
import view.iniciar_app;
import view.menu_inicial_app;
import view.formulario_crear_nuevo_usuario;
import view.formulario_agregar_compra;
import view.tabla_agregar_asistencia;
import view.vista_reportes;

//Librerias necesarias
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.Date;

//Librerias necesarias para escuchar a los botones
import javax.swing.JButton;
import javax.swing.JOptionPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



//Clase intermediaria entre la vista y el modelo
public class controlador implements ActionListener{
    //El controlador va a alojar instancias de las clases del modelo que le interesan

    //Instancias de las clases necesarias del modelo
    //Atributos
    private final usuarios_dao listado_usuarios;
    private final asistencia_dao listado_asistencia;
    //private final iniciar_app iniciar;
    private menu_inicial_app abrir_menu_inicio;
    private formulario_crear_nuevo_usuario form_usuario_nuevo;
    private formulario_agregar_compra form_nueva_compra;
    private tabla_agregar_asistencia form_agregar_asistencia;
    private vista_reportes visualizar_reportes;

    public controlador(){

        //Enviar un arraylist que se obtiene en creación_usuario_dao
        this.listado_usuarios = new usuarios_dao();
        this.listado_asistencia = new asistencia_dao();
        //this.iniciar = new iniciar_app();
        this.abrir_menu_inicio = new menu_inicial_app();
        this.form_usuario_nuevo = null;
        this.form_nueva_compra = null;
    }

    public ArrayList<usuarios_vo> consultar_clientes() throws SQLException{
        return this.listado_usuarios.consultar_usuarios();
    }

    public void iniciarAplicacion(){
    	this.abrir_menu_inicio = new menu_inicial_app(this);
    	//abrir_menu_inicio.abrir_menu_principal(this);
    	
    //     /*try{  
    //     iniciar.abrir_menu(listado_usuarios.consultar_usuarios(), this);
    //     }catch(SQLException exc){
    //         System.err.println("Ha ocurrido un error!"+exc.getMessage());
    //     }*/
    }


    //Método para obtener los elementos de la lista desplegable del formulario Crear nuevo usuario Razon de ingreso
    //Quedaría obsoleto con el método de elementos_lista_desplegable
	public String[] elementos_lista_desplegable_razon_ingreso(){
		listas_seleccion_dao obtener_listado_razon_ingreso = new listas_seleccion_dao();
		ArrayList<listas_seleccion_vo> listado = new ArrayList<>();

		try {
			listado = obtener_listado_razon_ingreso.lista_tabla_razon_ingreso();
			
		} catch (SQLException e) {
			System.err.println("error al traer el listado");
		}
		
		String[] listado_array = new String[listado.size()];
        
			for(int i = 0; i < listado_array.length; i++) {
				listado_array[i] = String.valueOf(listado.get(i).getDescripcion()); 
									//listado.get(i).getDescripcion().toString();
			}
		
		return listado_array;
	}


    //Método para obtener los elementos de una lista desplegable para un formulario
    //el método va a pedir el nomnbre de la columna que se quiere obtener
    //y la tabla en la cual está ubicada.
	public String[] elementos_lista_desplegable(String nombre_columna, String nombre_tabla){
		listas_seleccion_dao obtener_lista_desplegable = new listas_seleccion_dao();
		ArrayList<listas_seleccion_vo> listado = new ArrayList<>();

		try {
			listado = obtener_lista_desplegable.crear_lista_desplegable(nombre_columna, nombre_tabla);
			
		} catch (SQLException e) {
			System.err.println("error al traer el listado");
		}
		
		String[] listado_array = new String[listado.size()];
        
			for(int i = 0; i < listado_array.length; i++) {
				listado_array[i] = String.valueOf(listado.get(i).getDescripcion()); 
									//listado.get(i).getDescripcion().toString();
			}
		
		return listado_array;
	}


    //método que muestre un mensaje de error en pantalla informando la situación
    //Cuando los campos que deben llevar solo número, contienen algun texto
    public boolean verificar_numeros(String texto_formulario_documento,
                                    String texto_formulario_celular){

        boolean validacion=false;
        while (validacion==false){
            int letras_doc = 0;
            for( int i = 0; i < texto_formulario_documento.length(); i++ ){
                if( !Character.isDigit( texto_formulario_documento.charAt( i ) ) )
                    letras_doc = letras_doc+1;
            }

            int letras_cel = 0;
            for( int i = 0; i < texto_formulario_celular.length(); i++ ){
                if( !Character.isDigit( texto_formulario_celular.charAt( i ) ) )
                    letras_cel = letras_cel+1;
            }


            if(letras_doc>0 || letras_cel>0 || texto_formulario_celular.length()>10 || texto_formulario_documento.length()>10){
                JOptionPane.showMessageDialog(
                    form_usuario_nuevo, 
                    "Campos numéricos con texto", 
                    "Numeros", 
                    JOptionPane.ERROR_MESSAGE
                    );
                validacion = false;
                break;
            }else{
                validacion = true;
                System.out.println("ambos son correctos");
            }
                    
        }
        return validacion;

    }


    //método que muestre un mensaje de error en pantalla informando la situación
    public boolean verificar_texto(String texto_formulario_nombre,
                                    String texto_formulario_apellido){

        boolean validacion=false;
        while (validacion==false){
            int letras_nom = 0;
            for( int i = 0; i < texto_formulario_nombre.length(); i++ ){
                if( Character.isDigit( texto_formulario_nombre.charAt( i ) ) )
                    letras_nom = letras_nom+1;
            }

            int letras_ape = 0;
            for( int i = 0; i < texto_formulario_apellido.length(); i++ ){
                if( Character.isDigit( texto_formulario_apellido.charAt( i ) ) )
                    letras_ape = letras_ape+1;
            }


            if(letras_nom>0 || letras_ape>0){
                JOptionPane.showMessageDialog(
                    form_usuario_nuevo, 
                    "Campos con numeros", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                    );
                validacion = false;
                break;
            }else{
                validacion = true;
                System.out.println("ambos son correctos");
            }
                    
        }
        return validacion;
    }


    //método de escucha de los botones
    @Override
    public void actionPerformed(ActionEvent e) {

        //Obtener el evento asociado al botón
        String actionCommand = ((JButton)e.getSource()).getActionCommand();

        switch(actionCommand){

            case "Crear Usuario":
                this.form_usuario_nuevo = new formulario_crear_nuevo_usuario(this);
                //Impresión de prueba en consola
                System.out.println("()()()Interfaz de crear usuario nuevo (nuevo formulario para crear un usuario)");
            break;

            case "Agregar Compra":
                this.form_nueva_compra = new formulario_agregar_compra(this);
                System.out.println("$$$Interfaz registrar compra!");
            break;

            case "Asistencia":
                
                //Inicialmente borrará toooodooo lo que exista en las filas de la tabla
                //debería cargar una tabla vacía, para luego llenarla con los elementos de la tabla asistencia
                //
                this.form_agregar_asistencia = new tabla_agregar_asistencia(this);
                //Limpiar los registros de la tabla
                while (form_agregar_asistencia.getModelo_tabla_asistencia().getRowCount()>0) {
                    form_agregar_asistencia.getModelo_tabla_asistencia().removeRow(0);
                    
                }
                
                //Cargar todos los registros de asistencias realizadas
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    
                    System.out.println("try inicial de asistencia");
                    for (asistencia_vo fila_tabla_asistencia : listado_asistencia.consultar_asistencia()) {
                        System.out.println("ingreso al for");
                        form_agregar_asistencia.getModelo_tabla_asistencia().addRow(
                            new Object[]{
                                //Id_formato String
                                String.valueOf(fila_tabla_asistencia.getId_asistencia()),
                                String.valueOf(fila_tabla_asistencia.getId_cliente()),
                                String.valueOf(fila_tabla_asistencia.getId_clase()),
                                String.valueOf(formato.format(fila_tabla_asistencia.getFecha_asistencia())),
                            }
                        );
                    }
                    System.out.println("De donde sale ese conexion exitosa?");
                } catch (Exception exception_asistencia) {
                    JOptionPane.showMessageDialog(
                        form_agregar_asistencia, 
                        "Error al intentar consultar la tabla de asistencia", 
                        "Error consulta", 
                        JOptionPane.ERROR_MESSAGE);
                }
                //Crear en la tabla una fila vacía debajo automáticamente para que se pueda
                //ingresar valores
                form_agregar_asistencia.getModelo_tabla_asistencia().addRow(new Object [4]);
                int n_filas = form_agregar_asistencia.getModelo_tabla_asistencia().getRowCount();
                Date fecha_asistencia = new Date();
                form_agregar_asistencia.getModelo_tabla_asistencia().setValueAt("1", n_filas-1, 2); 
                form_agregar_asistencia.getModelo_tabla_asistencia().setValueAt(formato.format(fecha_asistencia), n_filas-1, 3);
                //Hasta aqui va el cargue de la información en la tabla


            break;

            case "Reportes":
                this.visualizar_reportes = new vista_reportes(this);
            break;

            case "Registrar nuevo usuario":

                usuarios_vo nuevo_usuario = new usuarios_vo();
                usuarios_dao llenar_usuario = new usuarios_dao();
                //listas_seleccion_vo lista_jcombo_razon = new listas_seleccion_vo();
                listas_seleccion_dao lista_jcombo_razon = new listas_seleccion_dao();
                
                //obtener cada uno de los datos en el formulario y enviarlos al objeto usuarios_vo

                //validar que el documento solo contenga número
                boolean ver_num = verificar_numeros(form_usuario_nuevo.getTxt_documento_id().getText(),form_usuario_nuevo.getTxt_celular().getText());
                boolean ver_text = verificar_texto(form_usuario_nuevo.getTxt_nombres().getText(), form_usuario_nuevo.getTxt_apellidos().getText());
               
                
                usuarios_vo verificacion_existe_usuario = null;
                usuarios_dao consulta_existe_usuario_nuevo = new usuarios_dao();
                boolean existencia_usuario_nuevo = true;
                try {
                    verificacion_existe_usuario = consulta_existe_usuario_nuevo.consultar_usuarios_por_id(form_usuario_nuevo.getTxt_documento_id().getText());
                    System.out.println(verificacion_existe_usuario.getDocumento());
                    if (verificacion_existe_usuario.getDocumento()==null){
                        existencia_usuario_nuevo=false;

                        //ES un if que verifica si se cumplen los requisitos, de no ser asi, no se envía nada a la base de datos
                        if (ver_num && ver_text && existencia_usuario_nuevo==false){

                            nuevo_usuario.setDocumento(form_usuario_nuevo.getTxt_documento_id().getText());
                            nuevo_usuario.setNombres(form_usuario_nuevo.getTxt_nombres().getText());
                            nuevo_usuario.setApellidos(form_usuario_nuevo.getTxt_apellidos().getText());
                        
                            //Cambiar el formato del valor obtenido en Date para poder manipularlo
                            long formato_fecha = form_usuario_nuevo.getDateChooser_fecha_nacimiento().getDate().getTime();
                            java.sql.Date fecha_enviar_sql = new java.sql.Date(formato_fecha);
                            nuevo_usuario.setFecha_nac(fecha_enviar_sql);
                            //Consultar el key para enviarlo a la base como número y no un string

                            try {
                                String razon_escogida = String.valueOf(form_usuario_nuevo.getComboBox_razon_ingreso().getSelectedItem());
                                
                                //int key = lista_jcombo_razon.key_razon_escogida(razon_escogida);
                                int key = lista_jcombo_razon.llave_lista_desplegable("Id_razon_ingreso", "razon_ingreso", "Descripcion", razon_escogida);
                                //Enviar al value_object el entero
                                nuevo_usuario.setRazon_ingreso(key);
                            } catch (SQLException exception) {
                                System.out.println("Imposible consultar la razon escogida");
                            }
                            nuevo_usuario.setCorreo(form_usuario_nuevo.getTxt_correo().getText());
                            nuevo_usuario.setCelular(form_usuario_nuevo.getTxt_celular().getText());

                            //Intentar solicitar al modelo la adición del material a la base
                            usuarios_vo adicion_nuevo_usuario = null;
                            try {
                                adicion_nuevo_usuario = llenar_usuario.crear_nuevo_usuario(nuevo_usuario);
                                
                            } catch (SQLException ex_nuevo_usuario) {
                                JOptionPane.showMessageDialog(
                                        form_usuario_nuevo, 
                                        "Error insertando nuevo material", 
                                        "Error BD", 
                                        JOptionPane.ERROR_MESSAGE
                                        );
                                
                            }
                            //En caso que la adición sea exitosa, reportar el exito
                            if (adicion_nuevo_usuario != null){
                                JOptionPane.showMessageDialog(
                                        form_usuario_nuevo, 
                                        "Nuevo usuario agregado!!", 
                                        "Exito", 
                                        JOptionPane.INFORMATION_MESSAGE
                                        );
                            }
                            
                            //Limpiar los campos
                            form_usuario_nuevo.getTxt_documento_id().setText("");
                            form_usuario_nuevo.getTxt_nombres().setText("");
                            form_usuario_nuevo.getTxt_apellidos().setText("");
                            form_usuario_nuevo.getDateChooser_fecha_nacimiento().setCalendar(null);;
                            form_usuario_nuevo.getComboBox_razon_ingreso().setSelectedItem(null);
                            form_usuario_nuevo.getTxt_correo().setText(null);
                            form_usuario_nuevo.getTxt_celular().setText(null);    

                        }else{
                            JOptionPane.showMessageDialog(
                            form_usuario_nuevo, 
                            "Campos con datos incorrectos o vacios no se pudo enviar a la base", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE
                            );
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(
                                form_usuario_nuevo, 
                                "El cliente ya existe no se puede agregar", 
                                "Existe cliente", 
                                JOptionPane.ERROR_MESSAGE
                                );
                        System.out.println("El cliente si existe");
                        existencia_usuario_nuevo=true;
                    }
                    
                } catch (Exception excepcion_usuario_nuevo_consulta) {
                    JOptionPane.showMessageDialog(
                                form_usuario_nuevo, 
                                "no se pudo consultar si el cliente ya existe", 
                                "Existe cliente", 
                                JOptionPane.ERROR_MESSAGE
                                );
                    
                }

                

            break;

            case "Registrar compra":
                //Cuando se da clic en el boton regsitrar compra debería hacer dos cosas
                //1. enviar los datos del formulario a la tabla de cliente_compra_paquete
                //Aqui quedaran guardados los registros las compras realizas por los clientes
                //
                //2. Al dar clic se debe actualizar la tabla del cliente cambiando dos registros
                //utilizando el id ingresado como llave para la tabla de cliente.
                //El primer registro que se debe cambiar del usuario es días disponibles
                //el valor debe aumentar de acuerdo al paquete seleccionado, es decir,
                //con el key de la lista desplegable del paquete, se debe consultar 
                //la cantidad de días que ofrece con la columna dias_disponibles y 
                //enviarlo al dato de cliente
                //El segundo registro que se debe cambiar es la columna de activo, debe pasar de cero (0)
                //a un uno (1), lo cual significa que el usuario está activo y tiene días disponibles para
                //ser usados.
                System.out.println("clic en Registrar una compra en el formulario");
                
                //Primera parte. Registrar la compra en la tabla correspondiente.

                compra_paquetes_vo nueva_compra = new compra_paquetes_vo();
                compra_paquetes_dao crear_registro_compra = new compra_paquetes_dao();
                listas_seleccion_dao lista_jcombo_paquete = new listas_seleccion_dao();

                //obtener cada uno de los datos en el formulario y enviarlos al objeto usuarios_vo
                
                //Consultar que el cliente ya existe en la base de datos usando lo usuarios_vo             
                try {
                    
                    usuarios_vo id_usuario_existe = new usuarios_vo();
                    usuarios_dao consultar_existe_cliente = new usuarios_dao();

                    //objetos tipo paquete para obtener los días
                    paquete_vo paquete = new paquete_vo();
                    paquete_dao consulta_paquete = new paquete_dao();
                    int key = 0;

                    id_usuario_existe = consultar_existe_cliente.consultar_usuarios_por_id(form_nueva_compra.getTxt_id_cliente().getText());
                    //consultar que el cliente existe, si es verdadero
                    //permite enviar el contenido
                    if (id_usuario_existe.getDocumento().isEmpty()){
                        JOptionPane.showMessageDialog(
                            form_nueva_compra, 
                            "El cliente no existe", 
                            "Error Cliente", 
                            JOptionPane.ERROR_MESSAGE
                            );
                    }else{

                        nueva_compra.setId_cliente_compra(form_nueva_compra.getTxt_id_cliente().getText());
                        try {                            
                            
                            String valor_escogido_lista_desplegable = String.valueOf(form_nueva_compra.getComboBox_paquete().getSelectedItem());
                            System.out.println("Valor escogido de la lista: "+ valor_escogido_lista_desplegable);
                            
                            //Buscar la llave del valor de la lista desplegable
                            key = lista_jcombo_paquete.llave_lista_desplegable("Id_paquete", "paquete", "Nombre", valor_escogido_lista_desplegable);
                            //Enviar el código del paquete
                            nueva_compra.setId_paquete_compra(key);

                        } catch (SQLException exception) {
                            System.out.println("Imposible consultar el id del paquete seleecionado");
                        }

                        //Cambiar el formato del valor obtenido en Date para poder manipularlo
                        long formato_fecha = form_nueva_compra.getDateChooser_fecha_compra().getDate().getTime();
                        java.sql.Date fecha_enviar_sql = new java.sql.Date(formato_fecha);
                        
                        //Enviar la fecha de compra
                        nueva_compra.setFecha_compra(fecha_enviar_sql);

                        //En este punto todos los valores del formulario han sido enviados, ahora se deben de almacenar y al mismo tiempo
                        //que se almacenan se debe realizar el update a la tabla de usuarios. Siempre y cuando el retorno de la consulta
                        //sea verdadero, por eso sigue dentro del if.

                        //Intentar solicitar al modelo la adición de la nueva compra a la base
                        compra_paquetes_vo adicion_nueva_compra = null;

                        try {
                            adicion_nueva_compra = crear_registro_compra.crear_compra(nueva_compra);
                            //Aqui es donde se registra la nueva compra, una vez termina de pasar por este método
                            //inmediatamente debe ir a actualizar la tabla de usuarios
                            //si adición_nueva_compra resulta tener algun valor se hará la actualización
                            //el if esta abajo
                            
                        } catch (SQLException ex_nueva_compra) {
                            JOptionPane.showMessageDialog(
                                    form_nueva_compra, 
                                    "Error registrando la compra", 
                                    "Error BD", 
                                    JOptionPane.ERROR_MESSAGE
                                    );
                            
                        }
                        //En caso que la adición sea exitosa, reportar el exito y actualizar los datos del cliente
                        //días disponibles de acuerdo al paquete y activo
                        if (adicion_nueva_compra != null){

                            //Los datos de la consulta creada arriba enviaron los vo. es decir que el objeto
                            //id_usuario_existe, contiene todos los datos lo que se debe hacer aqui es 
                            //actualizar dos de esos valores y luego ejecutar la actualización

                            //Parte 1. Seleccionar el valor del paquete teniendo la Key y consultando en paquetes_dao
                            System.out.println(key);
                            paquete = consulta_paquete.consultar_paquete_id(key);
                            System.out.println("dias del paquete de la compra"+paquete.getDias());

                            id_usuario_existe.setUsuario_activo(1);
                            id_usuario_existe.setDias_disponibles(paquete.getDias());

                            System.out.println("nombre " + id_usuario_existe.getNombres());
                            System.out.println("doc " + id_usuario_existe.getDocumento());
                            System.out.println("razon ing " + id_usuario_existe.getRazon_ingreso());
                            System.out.println("activo " + id_usuario_existe.getUsuario_activo());
                            System.out.println("dias dispon " + id_usuario_existe.getDias_disponibles());
                            //Esto deberia actualizar la base de datos con los datos de id_usuario_existe

                            consultar_existe_cliente.actualizar_datos(id_usuario_existe);


                            System.out.println();

                            JOptionPane.showMessageDialog(
                                    form_nueva_compra, 
                                    "Nuevo compra registrada!!", 
                                    "Exito", 
                                    JOptionPane.INFORMATION_MESSAGE
                                    );
                        }
                        

                        //Limpiar los campos
                        form_nueva_compra.getTxt_id_cliente().setText("");
                        form_nueva_compra.getDateChooser_fecha_compra().setCalendar(null);
                        form_nueva_compra.getComboBox_paquete().setSelectedItem(null);

                    }
                    
                } catch (Exception existe_cliente) {
                    System.out.println("no fue posible consultar si el cliente existe");   
                }
                                
            break;

            case "Registrar Asistencia":


                //registrar usuario sin seleccionar ninguna fila, esto hace que atrape la excepcion
                try {
                    asistencia_vo asistencia = new asistencia_vo();
                    asistencia_dao adicionar_asistencia = new asistencia_dao();              
                    tabla_agregar_asistencia tabla_asistencia = this.form_agregar_asistencia;

                    int[] fila_seleccionada = tabla_asistencia.getTabla_asistencia().getSelectedRows();

                    ArrayList <String> elementos_fila = new ArrayList<>();
                    int no_columnas = tabla_asistencia.getModelo_tabla_asistencia().getColumnCount();
                    for (int j = 0; j < no_columnas; j++) {
                        elementos_fila.add((String) tabla_asistencia.getModelo_tabla_asistencia().getValueAt(fila_seleccionada[0], j));
                    }

                    //Impresion de prueba
                    for (int i = 0; i < elementos_fila.size(); i++) {
                        System.out.println(elementos_fila.get(i));                    
                    }

                    //Transformar la fecha a un formato adecuado para ser enviado a al objeto del vo
                    Date utilDate = new Date(); //DateFormat
                    utilDate = null;
                    SimpleDateFormat formato_inicio = new SimpleDateFormat("dd-MM-yyyy"); // parse string into a DATE format
                    try {
                        utilDate = formato_inicio.parse(elementos_fila.get(3));    
                    } catch (java.text.ParseException exc_fecha) {
                        System.err.println("no fue posible convertir a fecha");
                    }
                    java.sql.Date fecha_vo_asistencia = new java.sql.Date(utilDate.getTime());
                    
                    //Enviar los elementos al vo de asistencia
                    //asistencia.setId_asistencia(Integer.valueOf(elementos_fila.get(0))); //debe ser automático en la bbdd por eso no se envía
                    asistencia.setId_cliente(elementos_fila.get(1));
                    asistencia.setId_clase(Integer.valueOf(elementos_fila.get(2)));
                    asistencia.setFecha_asistencia(fecha_vo_asistencia);

                    //Consultar si la cédula digitada existe en la base de datos
                    //en caso de no existir, mostrar un error y que no haga nada

                    
                    // //Consultar el key para enviarlo a la base como número y no un string
                    usuarios_vo id_usuario_existe = null;
                    usuarios_dao consultar_existe_cliente = new usuarios_dao();
                    boolean existe = false;
                    try {
                        id_usuario_existe = consultar_existe_cliente.consultar_usuarios_por_id(asistencia.getId_cliente());
                        System.out.println(id_usuario_existe.getDocumento());
                        if (id_usuario_existe.getDocumento()==null){
                            JOptionPane.showMessageDialog(
                                form_nueva_compra, 
                                "El cliente no existe", 
                                "Error Cliente", 
                                JOptionPane.ERROR_MESSAGE
                                );
                        }else{
                            System.out.println("El cliente si existe");
                            existe=true;
                        }
                        
                    } catch (Exception excepcion_usuario_id_asistencia) {
                        JOptionPane.showMessageDialog(
                                form_nueva_compra, 
                                "El cliente no existe", 
                                "Error Cliente", 
                                JOptionPane.ERROR_MESSAGE
                                );
                    }
                    //Si el cliente existe, entonces hacer el siguiente proceso
                    //1. Registrar la información de asistencia que ha sido guardada en su vo.
                    //2. si la asistencia se registró correctamente, actualizar los días disponibles
                    //3. si días disponibles es igual a 0, activo es igual a 0
                    //4. Si días disponibles es igual a 1, alertar que le queda solo una clase

                    if (existe){
                        //1. Registrar la información de asistencia que ha sido guardada en su vo.

                        //Intentar solicitar al modelo la adición de la nueva compra a la base
                        asistencia_vo adicion_nueva_asistencia = null;

                        try {
                            adicion_nueva_asistencia = adicionar_asistencia.registrar_asistencia(asistencia);
                            //Aqui es donde se registra la nueva compra, una vez termina de pasar por este método
                            //inmediatamente debe ir a actualizar la tabla de usuarios
                            
                        } catch (SQLException ex_nueva_asistencia) {
                            JOptionPane.showMessageDialog(
                                    form_nueva_compra, 
                                    "Error registrando la asistencia", 
                                    "Error BD", 
                                    JOptionPane.ERROR_MESSAGE
                                    );
                            
                        }
                        //En caso que la adición sea exitosa, reportar el exito y actualizar los datos del cliente
                        //días disponibles de acuerdo al paquete y activo
                        if (adicion_nueva_asistencia != null){

                            //2. si la asistencia se registró correctamente, actualizar los días disponibles
                            id_usuario_existe.setDias_disponibles(id_usuario_existe.getDias_disponibles()-1);
                            if (id_usuario_existe.getDias_disponibles()==0){
                                id_usuario_existe.setUsuario_activo(0);
                            }
                            try {
                                consultar_existe_cliente.actualizar_datos(id_usuario_existe);    
                            } catch (Exception exc_actualizar_usuario) {
                                System.out.println("no se pudo actualizar el usuario");
                            }
                            
                            JOptionPane.showMessageDialog(
                                    form_agregar_asistencia, 
                                    "Asistencia agregada correctamente", 
                                    "Asistencia BD", 
                                    JOptionPane.INFORMATION_MESSAGE
                                    );
                            
                        }

                        if(id_usuario_existe.getDias_disponibles()==1){
                            JOptionPane.showMessageDialog(
                                        form_agregar_asistencia, 
                                        "Al usuario "+id_usuario_existe.getNombres()+id_usuario_existe.getApellidos()+" le queda una clase", 
                                        "Una clase disponible", 
                                        JOptionPane.INFORMATION_MESSAGE
                                        );
                        }else if(id_usuario_existe.getDias_disponibles()==0){
                            JOptionPane.showMessageDialog(
                                        form_agregar_asistencia, 
                                        "Al usuario "+id_usuario_existe.getNombres()+id_usuario_existe.getApellidos()+" Se le acabaron las clases", 
                                        "Sin clases", 
                                        JOptionPane.INFORMATION_MESSAGE
                                        );
                        }

                    }


                    
                    
                    
                    //El boton registrar asistencia debe 
                    //1. verificar si existe la cédula ingresada, en caso contrario enviar un mensaje de error
                    //Obtener el valor de la celda especificada, hacer la consulta de id_usuario
                    //verificar si el valor es vacio, en caso de ser vacio enviar el error
                    //si no está vacio ir al siguiente paso

                    //realizar la consulta del usuario
                    //Repetir el código de arriba (opcion mejorable modularizando el método)

                } catch (ArrayIndexOutOfBoundsException exc_fila_no_seleccionada) {
                    JOptionPane.showMessageDialog(
                                    form_agregar_asistencia, 
                                    "Ninguna fila fue seleccionada", 
                                    "Error seleccion", 
                                    JOptionPane.ERROR_MESSAGE
                                    );
                }
                
            break;
                
            case "Insertar Fila":

                //La idea ahora es Crear un botón en el frame para insertar una fila vacía
                //esa fila vacía deberá contener un autonumérico en el id.
                
                //Crear en la tabla una fila vacía debajo automáticamente para que se pueda
                //ingresar valores
                form_agregar_asistencia.getModelo_tabla_asistencia().addRow(new Object [4]);
                int cant_filas = form_agregar_asistencia.getModelo_tabla_asistencia().getRowCount();
                fecha_asistencia = new Date();
                SimpleDateFormat formato_fila_nueva = new SimpleDateFormat("dd-MM-yyyy");
                form_agregar_asistencia.getModelo_tabla_asistencia().setValueAt("1", cant_filas-1, 2); 
                form_agregar_asistencia.getModelo_tabla_asistencia().setValueAt(formato_fila_nueva.format(fecha_asistencia), cant_filas-1, 3);
                //Hasta aqui va el cargue de la información en la tabla

                //Cargar todos los registros de asistencias realizadas
                


            break;


            case "Clientes Activos":

                //Limpiar los registros de la tabla
                while (visualizar_reportes.getModelo_tabla_reporte_usuario().getRowCount()>0) {
                    visualizar_reportes.getModelo_tabla_reporte_usuario().removeRow(0);
                    
                }
            
                try {
                    
                    System.out.println("try inicial de reporte usuario activo");
                    for (usuarios_vo fila_usuario_activo : listado_usuarios.consultar_usuarios_activos()) {
                        System.out.println("ingreso al for usuarios activos");
                        visualizar_reportes.getModelo_tabla_reporte_usuario().addRow(
                            new Object[]{
                                //Id_formato String
                                String.valueOf(fila_usuario_activo.getDocumento()),
                                String.valueOf(fila_usuario_activo.getNombres()),
                                String.valueOf(fila_usuario_activo.getApellidos()),
                                String.valueOf(fila_usuario_activo.getCelular()),
                                String.valueOf(fila_usuario_activo.getDias_disponibles()),
                                String.valueOf(fila_usuario_activo.getUsuario_activo()),
                            }
                        );
                    }
                    System.out.println("De donde sale ese conexion exitosa?");
                } catch (Exception exception_asistencia) {
                    JOptionPane.showMessageDialog(
                        form_agregar_asistencia, 
                        "Error al intentar consultar la tabla de asistencia", 
                        "Error consulta", 
                        JOptionPane.ERROR_MESSAGE);
                }
            break;

            case "Clientes Inactivos":

                //Limpiar los registros de la tabla
                while (visualizar_reportes.getModelo_tabla_reporte_usuario().getRowCount()>0) {
                    visualizar_reportes.getModelo_tabla_reporte_usuario().removeRow(0);
                    
                }
            
                try {
                    
                    System.out.println("try inicial de reporte usuario inactivo");
                    for (usuarios_vo fila_usuario_inactivo : listado_usuarios.consultar_usuarios_inactivos()) {
                        System.out.println("ingreso al for usuarios activos");
                        visualizar_reportes.getModelo_tabla_reporte_usuario().addRow(
                            new Object[]{
                                //Id_formato String
                                String.valueOf(fila_usuario_inactivo.getDocumento()),
                                String.valueOf(fila_usuario_inactivo.getNombres()),
                                String.valueOf(fila_usuario_inactivo.getApellidos()),
                                String.valueOf(fila_usuario_inactivo.getCelular()),
                                String.valueOf(fila_usuario_inactivo.getDias_disponibles()),
                                String.valueOf(fila_usuario_inactivo.getUsuario_activo()),
                            }
                        );
                    }
                    System.out.println("De donde sale ese conexion exitosa?");
                } catch (Exception exception_asistencia) {
                    JOptionPane.showMessageDialog(
                        form_agregar_asistencia, 
                        "Error al intentar consultar la tabla de asistencia", 
                        "Error consulta", 
                        JOptionPane.ERROR_MESSAGE);
                }

            break;
        }
    
    }
}