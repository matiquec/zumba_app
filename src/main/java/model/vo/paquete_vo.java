package model.vo;

public class paquete_vo {
    int id_paquete;
    String Nombre;
    int dias;

    
    public int getId_paquete() {
        return id_paquete;
    }
    public void setId_paquete(int id_paquete) {
        this.id_paquete = id_paquete;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public int getDias() {
        return dias;
    }
    public void setDias(int dias) {
        this.dias = dias;
    }

    
}
