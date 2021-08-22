package view;

//librerias necesarias del modelo
import controller.controlador;
import model.vo.listas_seleccion_vo;
import model.dao.listas_seleccion_dao;

//librerias necesarias
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.InputVerifier;
import java.awt.*;

public class formulario_crear_nuevo_usuario extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombres;
	private JTextField txt_apellidos;
	private JTextField txt_documento_id;
	private JDateChooser dateChooser_fecha_nacimiento;
	private JTextField txt_correo;
	private JTextField txt_celular;
	private JComboBox comboBox_razon_ingreso;
	/**
	 * Creación del frame formulario
	 */

	//constructor 
	public formulario_crear_nuevo_usuario(controlador control){

		txt_nombres = new JTextField();
		txt_apellidos = new JTextField();
		txt_documento_id = new JTextField();
		dateChooser_fecha_nacimiento = new JDateChooser();
		txt_correo = new JTextField();
		txt_celular = new JTextField();
		comboBox_razon_ingreso = new JComboBox();
		
		setTitle("Crear Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 605, 384);
		
		setBounds(100, 100, 560, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_superior_form_crear_usuario = new JPanel();
		contentPane.add(panel_superior_form_crear_usuario, BorderLayout.NORTH);
		panel_superior_form_crear_usuario.setLayout(new GridLayout(2, 1, 20, 10));
		
		JLabel lbl_nombre_formulario_usuario = new JLabel("Formulario crear nuevo usuario");
		lbl_nombre_formulario_usuario.setVerticalAlignment(SwingConstants.TOP);
		panel_superior_form_crear_usuario.add(lbl_nombre_formulario_usuario);
		lbl_nombre_formulario_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_formulario_usuario.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panel_central_form_crear_usuario = new JPanel();
		//panel_agrupar_panel_central.add(panel_central_form_crear_usuario);

		//JPanel panel_agrupar_panel_central = new JPanel();
		contentPane.add(panel_central_form_crear_usuario, BorderLayout.CENTER);
		//panel_agrupar_panel_central.setLayout(new GridLayout(9,2,0,0));
		
		

		panel_central_form_crear_usuario.setLayout(new GridLayout(10,2, 7, 6));
		
		// Component verticalStrut_1 = Box.createVerticalStrut(20);
		// verticalStrut_1.setBounds(0, 0, 0, 0);
		// panel_central_form_crear_usuario.add(verticalStrut_1);
		
		JLabel lbl_nombres = new JLabel("Nombres");
		lbl_nombres.setBounds(36, 28, 41, 13);
		lbl_nombres.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_crear_usuario.add(lbl_nombres);
		
		this.txt_nombres = new JTextField();
		this.txt_nombres.setBounds(125, 25, 134, 19);
		panel_central_form_crear_usuario.add(this.txt_nombres);
		this.txt_nombres.setColumns(10);
		
		JLabel lbl_apellidos = new JLabel("Apellidos");
		lbl_apellidos.setBounds(269, 28, 41, 13);
		lbl_apellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_crear_usuario.add(lbl_apellidos);
		
		this.txt_apellidos = new JTextField();
		this.txt_apellidos.setBounds(375, 25, 134, 19);
		panel_central_form_crear_usuario.add(this.txt_apellidos);
		this.txt_apellidos.setColumns(10);
		
		JLabel lbl_documento = new JLabel("Documento");
		lbl_documento.setBounds(36, 52, 51, 13);
		lbl_documento.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_crear_usuario.add(lbl_documento);


		this.txt_documento_id = new JTextField();
		this.txt_documento_id.setBounds(125, 49, 101, 19);
		panel_central_form_crear_usuario.add(this.txt_documento_id);
		this.txt_documento_id.setColumns(10);
				
		JLabel lbl_fecha_nac = new JLabel("Fecha de Nacimiento");
		lbl_fecha_nac.setBounds(269, 52, 95, 13);
		lbl_fecha_nac.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_crear_usuario.add(lbl_fecha_nac);
		
		this.dateChooser_fecha_nacimiento = new JDateChooser();
		this.dateChooser_fecha_nacimiento.setBounds(375, 49, 96, 19);
		this.dateChooser_fecha_nacimiento.getMaxSelectableDate();
		//java.util.Calendar max_fecha = new java.util.Date().getTime();
		this.dateChooser_fecha_nacimiento.setDateFormatString("dd/MM/yyyy");

		panel_central_form_crear_usuario.add(this.dateChooser_fecha_nacimiento);
		
		JLabel lbl_razon_ingreso = new JLabel("Razón de Ingreso");
		lbl_razon_ingreso.setBounds(36, 77, 80, 13);
		lbl_razon_ingreso.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_crear_usuario.add(lbl_razon_ingreso);
		
		this.comboBox_razon_ingreso = new JComboBox(control.elementos_lista_desplegable("Descripcion", "razon_ingreso"));
		this.comboBox_razon_ingreso.setBounds(125, 73, 29, 21);
		panel_central_form_crear_usuario.add(this.comboBox_razon_ingreso);
		
		JLabel lbl_correo = new JLabel("Correo Electrónico");
		lbl_correo.setBounds(36, 102, 84, 13);
		lbl_correo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_crear_usuario.add(lbl_correo);
		
		this.txt_correo = new JTextField();
		this.txt_correo.setBounds(125, 99, 185, 19);
		panel_central_form_crear_usuario.add(this.txt_correo);
		this.txt_correo.setColumns(10);
		
		JLabel lbl_celular = new JLabel("Celular");
		lbl_celular.setBounds(36, 126, 32, 13);
		lbl_celular.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_crear_usuario.add(lbl_celular);
		
		this.txt_celular = new JTextField();
		this.txt_celular.setBounds(125, 123, 96, 19);
		panel_central_form_crear_usuario.add(this.txt_celular);
		this.txt_celular.setColumns(10);
		
		JPanel panel_inferior_form_crear_usuario = new JPanel();
		contentPane.add(panel_inferior_form_crear_usuario, BorderLayout.SOUTH);
		
		JButton btn_registrar_nuevo_usuario = new JButton("Registrar nuevo usuario");
		panel_inferior_form_crear_usuario.add(btn_registrar_nuevo_usuario);
		//Envío de acción a escuchar y quien escucha
		btn_registrar_nuevo_usuario.addActionListener(control);			//Quién me va a escuchar
        btn_registrar_nuevo_usuario.setActionCommand("Registrar nuevo usuario");	//Qué voy a decir para que se haga lo que tengo asociado


		//Mostrar ventana/frame
        setSize(360,400);
        setLocationRelativeTo(null);
        setVisible(true);
	}


	//Getters and setters
	public JTextField getTxt_nombres() {
		return txt_nombres;
	}
	public void setTxt_nombres(JTextField txt_nombres) {
		this.txt_nombres = txt_nombres;
	}
	public JTextField getTxt_apellidos() {
		return txt_apellidos;
	}
	public void setTxt_apellidos(JTextField txt_apellidos) {
		this.txt_apellidos = txt_apellidos;
	}
	public JTextField getTxt_documento_id() {
		return txt_documento_id;
	}
	public void setTxt_documento_id(JTextField txt_documento_id) {
		this.txt_documento_id = txt_documento_id;
	}
	public JDateChooser getDateChooser_fecha_nacimiento() {
		return dateChooser_fecha_nacimiento;
	}
	public void setDateChooser_fecha_nacimiento(JDateChooser dateChooser_fecha_nacimiento) {
		this.dateChooser_fecha_nacimiento = dateChooser_fecha_nacimiento;
	}
	public JTextField getTxt_correo() {
		return txt_correo;
	}
	public void setTxt_correo(JTextField txt_correo) {
		this.txt_correo = txt_correo;
	}
	public JTextField getTxt_celular() {
		return txt_celular;
	}
	public void setTxt_celular(JTextField txt_celular) {
		this.txt_celular = txt_celular;
	}
	public JComboBox getComboBox_razon_ingreso() {
		return comboBox_razon_ingreso;
	}
	public void setComboBox_razon_ingreso(JComboBox comboBox_razon_ingreso) {
		this.comboBox_razon_ingreso = comboBox_razon_ingreso;
	}
}
