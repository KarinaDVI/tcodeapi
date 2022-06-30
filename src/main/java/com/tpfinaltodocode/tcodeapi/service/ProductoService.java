
package com.tpfinaltodocode.tcodeapi.service;


import com.tpfinaltodocode.tcodeapi.model.Producto;
import com.tpfinaltodocode.tcodeapi.repository.IProductoRepository;
import java.util.List;
import java.util.function.Predicate;
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
        Predicate<Producto> true_false = produ->produ.isBorrado()==true;
        listaProducto.removeIf(true_false);
        return listaProducto;
    }

    @Override
    public Producto findProducto(Long codigo_producto) {
        Producto produ = produRepo.findById(codigo_producto).orElse(null);
        return produ;
    }

    @Override
    public void editProducto(Long codigo_producto, Long NuevoCod_producto, 
            String nuevoNombre, String nuevoMarca, Double nuevoCosto, 
            Double nuevaCantidad_disponible, boolean borrado) {
        Producto produ = this.findProducto(codigo_producto);
        produ.setCodigo_producto(NuevoCod_producto);
        produ.setNombre(nuevoNombre);
        produ.setMarca(nuevoMarca);
        produ.setCosto(nuevoCosto);
        produ.setCantidad_disponible(nuevaCantidad_disponible);
        produ.setBorrado(borrado);
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

    @Override
    public void borradoLogicoProducto(Long codigo_producto, boolean borrado) {    
        Producto produc = this.findProducto(codigo_producto);
        produc.setCodigo_producto(produc.getCodigo_producto());
        produc.setBorrado(borrado);
        this.saveProducto(produc);
    }
    
    public void descontarStock(Long codigo_producto, Double cant) {    
        Producto produc = this.findProducto(codigo_producto);
        produc.setCodigo_producto(produc.getCodigo_producto());
        produc.setCantidad_disponible(cant-1);
        this.saveProducto(produc);
    }
 
}
