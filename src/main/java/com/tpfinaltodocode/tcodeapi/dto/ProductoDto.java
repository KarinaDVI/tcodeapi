
package com.tpfinaltodocode.tcodeapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoDto {
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    private boolean borrado;

    public ProductoDto() {
    }

    public ProductoDto(Long codigo_producto, String nombre, String marca, Double costo, Double cantidad_disponible, boolean borrado) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
        this.borrado=borrado;
    }
    
    
}
