
package com.tpfinaltodocode.tcodeapi.service;

import com.tpfinaltodocode.tcodeapi.model.Producto;
import com.tpfinaltodocode.tcodeapi.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    IProductoRepository produRepo;


    @Override
    public void saveProducto(Producto produ) {
       produRepo.save(produ);
    } 
    
    @Override
    public List<Producto> getProductos() {
        List<Producto> listaProducto=produRepo.findAll();
        return listaProducto;
    }

    @Override
    public Producto findProducto(Long codigo_producto) {
        Producto produ = produRepo.findById(codigo_producto).orElse(null);
        return produ;
    }

    @Override
    public void editProducto(Long codigo_producto, Long NuevoCod_producto, String nuevoNombre, String nuevoMarca, Double nuevoCosto, Double nuevaCantidad_disponible) {
        Producto produ = this.findProducto(codigo_producto);
        produ.setCodigo_producto(NuevoCod_producto);
        produ.setNombre(nuevoNombre);
        produ.setMarca(nuevoMarca);
        produ.setCosto(nuevoCosto);
        produ.setCantidad_disponible(nuevaCantidad_disponible);
        this.saveProducto(produ);
    }
    
    @Override
    public void editProducto(Producto produ) {
        this.saveProducto(produ);
    }

    @Override
    public void deleteProducto(Long codigo_producto) {
        produRepo.deleteById(codigo_producto);
    }
 
}
