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
import model.vo.asistencia_vo;

//librerias necesarias
import java.util.ArrayList;

public class tabla_agregar_asistencia extends JFrame{

    
    //Mostrar una modelo de tabla que se pueda estar actualizando constantemente

    private DefaultTableModel modelo_tabla_asistencia;
    private JTable tabla_asistencia;
    private ArrayList<asistencia_vo> registros_asistencia = new ArrayList<>();
    
    
    private JPanel contentPane;


    public tabla_agregar_asistencia(controlador control){   

        
        //Propiedades del frame
        setTitle("Tabla de asistencia");
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
		
		JLabel lbl_nombre_formulario_compra = new JLabel("Asistencia");
		lbl_nombre_formulario_compra.setVerticalAlignment(SwingConstants.TOP);
		panel_superior_form_asistencia.add(lbl_nombre_formulario_compra);
		lbl_nombre_formulario_compra.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_formulario_compra.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panel_central = new JPanel();
		contentPane.add(panel_central, BorderLayout.CENTER);
		panel_central.setLayout(new GridLayout(0,1,0,0));

        //Construcción de la tabla que presentará los materiales
        String[] encabezado = {"ID_asistencia", "Cedula Cliente", "Clase", "Fecha"};
        
         //Construcción de la tabla a partir del modelo -> personalización y actualización
         //Defaulttablemodel(filas, columnas)
         this.modelo_tabla_asistencia = new DefaultTableModel(this.formatoRegistros(registros_asistencia, encabezado.length), encabezado){
            
            //Personalizar que columnas NO SON EDITABLES
            @Override
            public boolean isCellEditable(int row, int column) {                
                
                //Que sea editable cuando la columna es diferente de la primera o tercera
                return (column != 0 && column !=2);

                //Establecer cuáles son editables (Ejemplo Alex)
                //return column == 1 || column == 3;
            }             

        };
        
        this.tabla_asistencia = new JTable(modelo_tabla_asistencia);

        //Colocar dentro de un scroll panel en caso de que sobrepase el tamaño
        JScrollPane sp = new JScrollPane(this.tabla_asistencia);

        //Poner en el panel del centro
        panel_central.add(sp);

        JPanel panel_inferior_form_crear_usuario = new JPanel();
        panel_inferior_form_crear_usuario.setLayout(new GridLayout());

        JButton btn_registrar_asistencia = new JButton("Registrar Asistencia");
		btn_registrar_asistencia.setVerticalAlignment(SwingConstants.TOP);
		panel_inferior_form_crear_usuario.add(btn_registrar_asistencia);
        //Envío de acción a escuchar y quien escucha
		btn_registrar_asistencia.addActionListener(control);			//Quién me va a escuchar
        btn_registrar_asistencia.setActionCommand("Registrar Asistencia");	//Qué voy a decir para que se haga lo que tengo asociado


        JButton btn_insertar_fila = new JButton("Insertar Fila");
		btn_insertar_fila.setVerticalAlignment(SwingConstants.TOP);
		panel_inferior_form_crear_usuario.add(btn_insertar_fila);
        //Envío de acción a escuchar y quien escucha
		btn_insertar_fila.addActionListener(control);			//Quién me va a escuchar
        btn_insertar_fila.setActionCommand("Insertar Fila");	//Qué voy a decir para que se haga lo que tengo asociado

		contentPane.add(panel_inferior_form_crear_usuario, BorderLayout.SOUTH);



        //Mostrar ventana/frame
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    //Debe recibir un arraylist de tipo consultar la asistencia de la tabla asistencias
    private String[][] formatoRegistros(ArrayList<asistencia_vo> elementos_asistencia, int numeroEncabezados){
        
        //Declaración del contenedor de retorno
        String[][] registros = new String[elementos_asistencia.size()][numeroEncabezados];        

        //Desenvolver los objetos de la colección
        for (int i = 0; i < elementos_asistencia.size(); i++) {
            registros[i][0] = String.valueOf(elementos_asistencia.get(i).getId_asistencia()); 
            registros[i][1] = elementos_asistencia.get(i).getId_cliente();            
            registros[i][2] = String.valueOf(elementos_asistencia.get(i).getId_clase()) ;
            registros[i][3] = String.valueOf(elementos_asistencia.get(i).getFecha_asistencia());        
        }

        //Retornar registros en formato JTable
        return registros;

    }

    

    public JTable getTabla_asistencia() {
        return tabla_asistencia;
    }

    public void setTabla_asistencia(JTable tabla_asistencia) {
        this.tabla_asistencia = tabla_asistencia;
    }

    public DefaultTableModel getModelo_tabla_asistencia() {
        return modelo_tabla_asistencia;
    }

    public void setModelo_tabla_asistencia(DefaultTableModel modelo_tabla_asistencia) {
        this.modelo_tabla_asistencia = modelo_tabla_asistencia;
    }
    
    
}
