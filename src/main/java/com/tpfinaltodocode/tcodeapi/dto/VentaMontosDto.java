
package com.tpfinaltodocode.tcodeapi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class VentaMontosDto implements Serializable{
    
    private LocalDate fecha_venta;
    private Double monto_Total_de_Ventas;
    private int cantVentas;

    public VentaMontosDto() {
    }

    public VentaMontosDto(LocalDate fecha_venta, Double monto_Total_de_Ventas, int cantVentas) {
        this.fecha_venta = fecha_venta;
        this.monto_Total_de_Ventas = monto_Total_de_Ventas;
        this.cantVentas = cantVentas;
    }

    
    
   
    
    
}
    
    
    
    

