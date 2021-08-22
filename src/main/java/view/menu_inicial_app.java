package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.controlador;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.GroupLayout;

//librerias del modelo necesarias
//Librerias del modelo de vista necesarias
import view.formulario_crear_nuevo_usuario;


public class menu_inicial_app extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	//constructor simple
	public menu_inicial_app(){
	}
	
	public menu_inicial_app(controlador control) {
		
		setTitle("Menu principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 384);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		JPanel jpanel_central = new JPanel();
		jpanel_central.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(jpanel_central, BorderLayout.CENTER);
		jpanel_central.setLayout(new GridLayout());

		JLabel imagen = new JLabel(new ImageIcon("img/img_zumba.png"));
		jpanel_central.add(imagen);
		jpanel_central.setBackground(new java.awt.Color(255, 255, 255));
		//imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("D:/Pruebas/Zba/zumba_app/img/img_zumba.png")));//NOI18N
		
		// URL resource = this.getClass().getResource("/");
		// resource.getFile(); // print me somehwere
		// System.out.println("nose");

		// URL resource1 = this.getClass().getResource("img/");
		// resource1.getFile(); // print me as well
		// System.out.println("nose2");
		// URL resource2 = this.getClass().getResource("img/ball.PNG");
		// resource2.getFile(); // print me as well


		JPanel panel_menu = new JPanel();
		contentPane.add(panel_menu, BorderLayout.WEST);
		

		
		JButton btn_crear_usuario = new JButton("Crear Usuario");
		btn_crear_usuario.setMinimumSize(new Dimension(20,20));
		btn_crear_usuario.setMaximumSize(new Dimension(20,30));
		btn_crear_usuario.setPreferredSize(new Dimension(20,30));

		panel_menu.setLayout(new GridBagLayout());
		panel_menu.add(btn_crear_usuario);
		//Envío de acción a escuchar y quien escucha
        btn_crear_usuario.addActionListener(control);			//Quién me va a escuchar
        btn_crear_usuario.setActionCommand("Crear Usuario");	//Qué voy a decir para que se haga lo que tengo asociado

		
		JButton btn_agregar_compra = new JButton("Agregar Compra");
		btn_agregar_compra.setMinimumSize(new Dimension(20,20));
		btn_agregar_compra.setMaximumSize(new Dimension(20,30));
		btn_agregar_compra.setPreferredSize(new Dimension(20,30));
		panel_menu.add(btn_agregar_compra);
		//Envío de acción a escuchar y quien escucha
		btn_agregar_compra.addActionListener(control);			//Quién me va a escuchar
        btn_agregar_compra.setActionCommand("Agregar Compra");	//Qué voy a decir para que se haga lo que tengo asociado

		
		JButton btn_crear_asistencia = new JButton("Asistencia");
		btn_crear_asistencia.setMinimumSize(new Dimension(20,20));
		btn_crear_asistencia.setMaximumSize(new Dimension(20,30));
		btn_crear_asistencia.setPreferredSize(new Dimension(20,30));
		panel_menu.add(btn_crear_asistencia);
		//Envío de acción a escuchar y quien escucha
		btn_crear_asistencia.addActionListener(control);			//Quién me va a escuchar
        btn_crear_asistencia.setActionCommand("Asistencia");	//Qué voy a decir para que se haga lo que tengo asociado

		
		JButton btn_paquetes = new JButton("Reportes");
		btn_paquetes.setMinimumSize(new Dimension(20,20));
		btn_paquetes.setMaximumSize(new Dimension(20,30));
		btn_paquetes.setPreferredSize(new Dimension(20,30));
		panel_menu.add(btn_paquetes);
		//Envío de acción a escuchar y quien escucha
		btn_paquetes.addActionListener(control);			//Quién me va a escuchar
        btn_paquetes.setActionCommand("Reportes");	//Qué voy a decir para que se haga lo que tengo asociado

		
		GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel_menu);
        panel_menu.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_paquetes, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btn_crear_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_agregar_compra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_crear_asistencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

		jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btn_crear_usuario)
                .addGap(18, 18, 18)
                .addComponent(btn_agregar_compra)
                .addGap(18, 18, 18)
                .addComponent(btn_crear_asistencia)
                .addGap(18, 18, 18)
                .addComponent(btn_paquetes)
                .addContainerGap(88, Short.MAX_VALUE))
        );


		//Mostrar ventana/frame
        //setSize(400,250);
        setLocationRelativeTo(null);
        setVisible(true);
	}

	// public void abrir_formulario_crear_usuario(controlador control){
	// 	formulario_crear_nuevo_usuario creacion_formulario_nuevo_usuario = new formulario_crear_nuevo_usuario(control);
	// }
}
