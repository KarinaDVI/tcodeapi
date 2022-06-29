
package com.tpfinaltodocode.tcodeapi.dto;

import com.tpfinaltodocode.tcodeapi.model.Cliente;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDto implements Serializable{
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    private List<ProductoDto> listaProductos;
    private Cliente unCliente;

    public VentaDto() {
    }

    public VentaDto(Long codigo_venta, LocalDate fecha_venta, Double total, List<ProductoDto> listaProductos, Cliente unCliente) {
        this.codigo_venta= codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }

    

   
    
    
}
