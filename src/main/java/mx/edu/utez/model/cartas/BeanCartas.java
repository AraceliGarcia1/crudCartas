package mx.edu.utez.model.cartas;

import mx.edu.utez.model.type.BeanType;

public class BeanCartas {
    private int idCartas;
    private String nombre;
    private BeanType idType;
    private String fecha;
    private int status;

    public BeanCartas() {
    }

    public BeanCartas(int idCartas, String nombre, BeanType idType, String date, int status) {
        this.idCartas = idCartas;
        this.nombre = nombre;
        this.idType = idType;
        this.fecha = fecha;
        this.status = status;
    }

    public int getIdCartas() {
        return idCartas;
    }

    public void setIdCartas(int idCartas) {
        this.idCartas = idCartas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BeanType getIdType() {
        return idType;
    }

    public void setIdType(BeanType idType) {
        this.idType = idType;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
