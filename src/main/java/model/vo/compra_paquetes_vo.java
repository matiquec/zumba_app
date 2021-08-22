package model.vo;

import java.sql.Date;

public class compra_paquetes_vo {
    
    int id_compra;              //Codigo de la compra
    String id_cliente_compra;   //Código del cliente como String
    int id_paquete_compra;      //Código del paquete comprado
    Date fecha_compra;          //Fecha en la que se compra el paquete

    public compra_paquetes_vo() {
    }

    //Getters and setters
    public int getId_compra() {
        return id_compra;
    }
    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }
    public String getId_cliente_compra() {
        return id_cliente_compra;
    }
    public void setId_cliente_compra(String id_cliente_compra) {
        this.id_cliente_compra = id_cliente_compra;
    }
    public int getId_paquete_compra() {
        return id_paquete_compra;
    }
    public void setId_paquete_compra(int id_paquete_compra) {
        this.id_paquete_compra = id_paquete_compra;
    }
    public Date getFecha_compra() {
        return fecha_compra;
    }
    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }



    
}
