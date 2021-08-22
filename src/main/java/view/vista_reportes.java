package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

//Librerias propias del modelo
import controller.controlador;
import model.vo.usuarios_vo;

//librerias necesarias
import java.util.ArrayList;

public class vista_reportes extends JFrame {

//Mostrar una modelo de tabla que se pueda estar actualizando constantemente

private DefaultTableModel modelo_tabla_reporte_usuario;
private JTable tabla_reporte_usuario;
private ArrayList<usuarios_vo> reporte_usuario = new ArrayList<>();


private JPanel contentPane;


public vista_reportes(controlador control){   

    
    //Propiedades del frame
    setTitle("Tabla reporte de clientes");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //setLayout(new GridLayout());
    setBounds(100, 100, 605, 384);
    
    setBounds(100, 100, 560, 286);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));
    
    JPanel panel_superior_form_asistencia = new JPanel();
    contentPane.add(panel_superior_form_asistencia, BorderLayout.NORTH);
    panel_superior_form_asistencia.setLayout(new GridLayout(0, 1, 0, 0));
    
    JLabel lbl_nombre_formulario_compra = new JLabel("Clientes");
    lbl_nombre_formulario_compra.setVerticalAlignment(SwingConstants.TOP);
    panel_superior_form_asistencia.add(lbl_nombre_formulario_compra);
    lbl_nombre_formulario_compra.setHorizontalAlignment(SwingConstants.CENTER);
    lbl_nombre_formulario_compra.setFont(new Font("Tahoma", Font.BOLD, 14));

    JPanel panel_central = new JPanel();
    contentPane.add(panel_central, BorderLayout.CENTER);
    panel_central.setLayout(new GridLayout(0,1,0,0));

    //Construcción de la tabla que presentará los materiales
    String[] encabezado = {"Cédula", "Nombre", "Apellido", "Teléfono", "Días disponibles", "Activo"};
    
     //Construcción de la tabla a partir del modelo -> personalización y actualización
     //Defaulttablemodel(filas, columnas)
     this.modelo_tabla_reporte_usuario = new DefaultTableModel(this.formatoRegistros(reporte_usuario, encabezado.length), encabezado){
        
        //Personalizar que columnas NO SON EDITABLES
        @Override
        public boolean isCellEditable(int row, int column) {                
            
            //Que sea editable cuando la columna es diferente de la primera o tercera
            return false;

            //Establecer cuáles son editables (Ejemplo Alex)
            //return column == 1 || column == 3;
        }             

    };
    
    this.tabla_reporte_usuario = new JTable(modelo_tabla_reporte_usuario);

    //Colocar dentro de un scroll panel en caso de que sobrepase el tamaño
    JScrollPane sp = new JScrollPane(this.tabla_reporte_usuario);

    //Poner en el panel del centro
    panel_central.add(sp);

    JPanel panel_inferior_form_crear_usuario = new JPanel();
    panel_inferior_form_crear_usuario.setLayout(new GridLayout());

    JButton btn_registrar_asistencia = new JButton("Clientes Activos");
    btn_registrar_asistencia.setVerticalAlignment(SwingConstants.TOP);
    panel_inferior_form_crear_usuario.add(btn_registrar_asistencia);
    //Envío de acción a escuchar y quien escucha
    btn_registrar_asistencia.addActionListener(control);			//Quién me va a escuchar
    btn_registrar_asistencia.setActionCommand("Clientes Activos");	//Qué voy a decir para que se haga lo que tengo asociado


    JButton btn_insertar_fila = new JButton("Clientes Inactivos");
    btn_insertar_fila.setVerticalAlignment(SwingConstants.TOP);
    panel_inferior_form_crear_usuario.add(btn_insertar_fila);
    //Envío de acción a escuchar y quien escucha
    btn_insertar_fila.addActionListener(control);			//Quién me va a escuchar
    btn_insertar_fila.setActionCommand("Clientes Inactivos");	//Qué voy a decir para que se haga lo que tengo asociado

    contentPane.add(panel_inferior_form_crear_usuario, BorderLayout.SOUTH);

    //Mostrar ventana/frame
    setSize(800,500);
    setLocationRelativeTo(null);
    setVisible(true);

}

//Debe recibir un arraylist de tipo consultar la asistencia de la tabla asistencias
private String[][] formatoRegistros(ArrayList<usuarios_vo> elementos_usuario, int numeroEncabezados){
    
    //Declaración del contenedor de retorno
    String[][] registros = new String[elementos_usuario.size()][numeroEncabezados];        

    //Desenvolver los objetos de la colección
    for (int i = 0; i < elementos_usuario.size(); i++) {
        registros[i][0] = String.valueOf(elementos_usuario.get(i).getDocumento()); 
        registros[i][1] = elementos_usuario.get(i).getNombres();            
        registros[i][2] = String.valueOf(elementos_usuario.get(i).getApellidos()) ;
        registros[i][3] = String.valueOf(elementos_usuario.get(i).getCelular());        
        registros[i][4] = String.valueOf(elementos_usuario.get(i).getDias_disponibles());
        registros[i][5] = String.valueOf(elementos_usuario.get(i).getUsuario_activo());
    }

    //Retornar registros en formato JTable
    return registros;

}



public JTable getTabla_reporte_usuario() {
    return tabla_reporte_usuario;
}

public void setTabla_reporte_usuario(JTable tabla_asistencia) {
    this.tabla_reporte_usuario = tabla_asistencia;
}

public DefaultTableModel getModelo_tabla_reporte_usuario() {
    return modelo_tabla_reporte_usuario;
}

public void setModelo_tabla_reporte_usuario(DefaultTableModel modelo_tabla_asistencia) {
    this.modelo_tabla_reporte_usuario = modelo_tabla_asistencia;
}




}
