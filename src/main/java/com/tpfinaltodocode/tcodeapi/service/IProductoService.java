
package com.tpfinaltodocode.tcodeapi.service;

import com.tpfinaltodocode.tcodeapi.model.Producto;
import java.util.List;


public interface IProductoService {
    
    public void saveProducto(Producto produ);
    
    public List<Producto> getProductos();

    public Producto findProducto(Long codigo_producto);
    
   

    public void editProducto(Long codigo_producto, Long Nuevocod_producto, 
            String nuevoNombre, String nuevoMarca, Double nuevoCosto, 
            Double nuevaCantidad_disponible,boolean borrado);
    
    public void editProducto(Producto produ);
    
    public void deleteProducto(Long codigo_producto);

    public void borradoLogicoProducto(Long codigo_producto, boolean borrado);
    
    public void descontarStock(Long codigo_producto, Double cantidad_disponible);
}
