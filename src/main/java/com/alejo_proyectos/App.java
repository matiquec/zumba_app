package com.alejo_proyectos;

import controller.controlador;
import view.vista_consola;

public class App {

    public static void main(String[] args) {
        
        //Inicio de la aplicación con GUI
        controlador controlador = new controlador();
        vista_consola mostrar_pantalla = new vista_consola();
        
        //Mostrar en pantalla la edición gráfica
        controlador.iniciarAplicacion(); 
        
        
        //Mostrar en consola lo que se está realizando
        //mostrar_pantalla.mostrar_usuarios_consola(); 

        

    }
}