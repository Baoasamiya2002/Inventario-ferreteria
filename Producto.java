/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;
import java.io.Serializable;
/**
 *
 * @author Samsung RV415
 */
public class Producto implements Serializable{
    private String id;
    private String nombre;
    private String descrip;
    private String unidad;
    private int cantidad;
    private float precio;
    private float precioVenta;
    
    public Producto(){
        
    }
    
    public Producto(String id, String nombre, String descrip, String unidad, int cantidad, float precio){
        this.id = id;
        this.nombre = nombre;
        this.descrip = descrip;
        this. unidad = unidad;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioVenta = (precio*100)/100;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
        this.precioVenta = (precio*100)/100;
    }
    
    public float getPrecioVenta() {
        return precioVenta;
    }
    
    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
}
