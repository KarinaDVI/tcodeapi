
package com.tpfinaltodocode.tcodeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpfinaltodocode.tcodeapi.model.Cliente;
import com.tpfinaltodocode.tcodeapi.model.Producto;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaProductoDto {
    private Long codigo_venta;
    private String nombreCliente;
    private String apellido;
    @JsonIgnore
    private LocalDate fecha_venta;
    private Double total;
    @JsonIgnore
    private String nombre;
    @JsonIgnore
    private String marca;
    @JsonIgnore
    private Double costo;
    private Double cantidad_productos;
    
    //probar haciendo listas de 
    public VentaProductoDto() {
    }

    public VentaProductoDto(Long codigo_venta, String nombreCliente, String apellido, LocalDate fecha_venta, Double total, String nombre, String marca, Double costo, Double cantidad_productos) {
        this.codigo_venta = codigo_venta;
        this.nombreCliente = nombreCliente;
        this.apellido = apellido;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_productos = cantidad_productos;
    }

   

    

    
    
    
    
}
