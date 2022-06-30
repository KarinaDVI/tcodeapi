
package com.tpfinaltodocode.tcodeapi.service;

import com.tpfinaltodocode.tcodeapi.dto.VentaProductoDto;
import com.tpfinaltodocode.tcodeapi.model.Cliente;
import com.tpfinaltodocode.tcodeapi.model.Producto;
import com.tpfinaltodocode.tcodeapi.model.Venta;
import java.time.LocalDate;
import java.util.List;


public interface IVentaService {
    
    public void saveVentas(Venta venta);
    
    public Venta guardarVentas(Venta venta);
    
    public List<Venta> getVentas();

    public Venta findVenta(Long codigo_venta);

    public void editVenta(Long codigo_venta, Long nuevoCodigo_venta, LocalDate newFecha_venta, Double newTotal, List<Producto> newListaProductos, Cliente newUnCliente);

    public void editVenta(Venta ven);
    
    public List<VentaProductoDto> getVenProd();
    
    public Double totalCosto(List<Double> prods);
    
    public void deleteVentas(Long codigo_venta);
    
}
