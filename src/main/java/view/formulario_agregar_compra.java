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

import com.toedter.calendar.JCalendar;
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

public class formulario_agregar_compra extends JFrame {

	private JPanel contentPane;
	private JTextField txt_id_cliente;
	private JDateChooser dateChooser_fecha_compra;
	private JComboBox comboBox_paquete;
	/**
	 * Creación del frame formulario
	 */

	//constructor 
	public formulario_agregar_compra(controlador control){

		txt_id_cliente = new JTextField();
		dateChooser_fecha_compra = new JDateChooser();		
		comboBox_paquete = new JComboBox();
		
		setTitle("Registrar Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 605, 384);
		
		setBounds(100, 100, 560, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_superior_form_compras = new JPanel();
		contentPane.add(panel_superior_form_compras, BorderLayout.NORTH);
		panel_superior_form_compras.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lbl_nombre_formulario_compra = new JLabel("Formulario crear compra");
		lbl_nombre_formulario_compra.setVerticalAlignment(SwingConstants.TOP);
		panel_superior_form_compras.add(lbl_nombre_formulario_compra);
		lbl_nombre_formulario_compra.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_formulario_compra.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panel_agrupar_panel_central = new JPanel();
		contentPane.add(panel_agrupar_panel_central, BorderLayout.CENTER);
		panel_agrupar_panel_central.setLayout(new GridLayout(0,1,0,0));
		
		JPanel panel_central_form_compra = new JPanel();
		panel_agrupar_panel_central.add(panel_central_form_compra);
		panel_central_form_compra.setLayout(new GridLayout(3,2, 10, 30));
		//panel_central_form_crear_usuario.setLayout(null);
		
		JLabel lbl_cedula_usuario = new JLabel("Cédula Cliente");
		lbl_cedula_usuario.setBounds(36, 28, 41, 13);
		lbl_cedula_usuario.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_compra.add(lbl_cedula_usuario);
		
		this.txt_id_cliente = new JTextField();
		this.txt_id_cliente.setBounds(125, 25, 134, 19);
		panel_central_form_compra.add(this.txt_id_cliente);
		this.txt_id_cliente.setColumns(10);

        JLabel lbl_paquete = new JLabel("Paquete");
		lbl_paquete.setBounds(36, 77, 80, 13);
		lbl_paquete.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_compra.add(lbl_paquete);
		
		this.comboBox_paquete = new JComboBox(control.elementos_lista_desplegable("Nombre","Paquete"));
		this.comboBox_paquete.setBounds(125, 73, 29, 21);
		panel_central_form_compra.add(this.comboBox_paquete);
				
		JLabel lbl_fecha_compra = new JLabel("Fecha de Compra");
		lbl_fecha_compra.setBounds(269, 52, 95, 13);
		lbl_fecha_compra.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_central_form_compra.add(lbl_fecha_compra);
		
		this.dateChooser_fecha_compra = new JDateChooser();
		this.dateChooser_fecha_compra.setBounds(375, 49, 96, 19);
		this.dateChooser_fecha_compra.getMaxSelectableDate();
		//java.util.Calendar max_fecha = new java.util.Date().getTime();
		this.dateChooser_fecha_compra.setDateFormatString("dd/MM/yyyy");

		panel_central_form_compra.add(this.dateChooser_fecha_compra);

				
		JPanel panel_inferior_form_crear_usuario = new JPanel();
		contentPane.add(panel_inferior_form_crear_usuario, BorderLayout.SOUTH);
		
		JButton btn_registrar_compra = new JButton("Registrar compra");
		panel_inferior_form_crear_usuario.add(btn_registrar_compra);
		//Envío de acción a escuchar y quien escucha
		btn_registrar_compra.addActionListener(control);			//Quién me va a escuchar
        btn_registrar_compra.setActionCommand("Registrar compra");	//Qué voy a decir para que se haga lo que tengo asociado


		//Mostrar ventana/frame
        setSize(400,250);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	public JTextField getTxt_id_cliente() {
		return txt_id_cliente;
	}
	public void setTxt_id_cliente(JTextField txt_id_cliente) {
		this.txt_id_cliente = txt_id_cliente;
	}
	public JDateChooser getDateChooser_fecha_compra() {
		return dateChooser_fecha_compra;
	}
	public void setDateChooser_fecha_compra(JDateChooser dateChooser_fecha_compra) {
		this.dateChooser_fecha_compra = dateChooser_fecha_compra;
	}
	public JComboBox getComboBox_paquete() {
		return comboBox_paquete;
	}
	public void setComboBox_paquete(JComboBox comboBox_paquete) {
		this.comboBox_paquete = comboBox_paquete;
	}


	//Getters and setters
	

	
}
