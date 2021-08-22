package model.vo;

import java.sql.Date;

public class usuarios_vo {
    String nombres;
    String apellidos;
    int tipo_doc;       //Falta crearlo en la base de datos
    String documento;
    String correo;
    String celular;
    Date fecha_nac;
    int razon_ingreso;
    int dias_disponibles;
    int usuario_activo;

        

    public usuarios_vo() {
    }

    public usuarios_vo(String nombres, String apellidos, int tipo_doc, String documento, String correo,
            String celular, Date fecha_nac, int razon_ingreso) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo_doc = tipo_doc;
        this.documento = documento;
        this.correo = correo;
        this.celular = celular;
        this.fecha_nac = fecha_nac;
        this.razon_ingreso = razon_ingreso;
        this.dias_disponibles = 0;
        this.usuario_activo = 0;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(int tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getRazon_ingreso() {
        return razon_ingreso;
    }

    public void setRazon_ingreso(int razon_ingreso) {
        this.razon_ingreso = razon_ingreso;
    }

    public int getDias_disponibles() {
        return dias_disponibles;
    }

    public void setDias_disponibles(int dias_disponibles) {
        this.dias_disponibles = dias_disponibles;
    }

    public int getUsuario_activo() {
        return usuario_activo;
    }

    public void setUsuario_activo(int usuario_activo) {
        this.usuario_activo = usuario_activo;
    }

    
}
