
package com.tpfinaltodocode.tcodeapi.controller;

import com.tpfinaltodocode.tcodeapi.dto.ProductoDto;
import com.tpfinaltodocode.tcodeapi.model.Producto;
import com.tpfinaltodocode.tcodeapi.service.IProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private IProductoService produServi;
    

    
//****CRUD: *******
    
    //alta
    @PostMapping("/crear")
    public void crearProducto(@RequestBody Producto produ){
        produServi.saveProducto(produ);
    }
    
    //Listar productos
    @GetMapping()
    public List<ProductoDto> getProductoDto(){
        
        List<Producto> listProdOrig = new ArrayList<>();
       
        List<ProductoDto> listProdDto = new ArrayList<>(); 
        listProdOrig = produServi.getProductos();
        
        for (Producto produ : listProdOrig){

           ProductoDto prodDto = new ProductoDto(); 
           
           prodDto.setCodigo_producto(produ.getCodigo_producto());
           prodDto.setNombre(produ.getNombre());
           prodDto.setMarca(produ.getMarca());
           prodDto.setCosto(produ.getCosto());
           prodDto.setCantidad_disponible(produ.getCantidad_disponible());
           prodDto.setBorrado(produ.isBorrado());
           listProdDto.add(prodDto);
        }
        return listProdDto;
    }
    
    //Listar un producto
    @GetMapping("/{codigo_producto}")
    public ProductoDto getUnProductoDto(@PathVariable Long codigo_producto){
        
        Producto prodBuscado=new Producto();
        prodBuscado = produServi.findProducto(codigo_producto);
        ProductoDto prodDevuelto = new ProductoDto();
        
        prodDevuelto.setCodigo_producto(prodBuscado.getCodigo_producto());
        prodDevuelto.setNombre(prodBuscado.getNombre());
        prodDevuelto.setMarca(prodBuscado.getMarca());
        prodDevuelto.setCosto(prodBuscado.getCosto());
        prodDevuelto.setCantidad_disponible(prodBuscado.getCantidad_disponible());
        prodDevuelto.setBorrado(prodBuscado.isBorrado());
        return prodDevuelto;
    }
    
    //Editar producto con @RequestParam
    @PutMapping("/editar/{codigo_producto}") 
    public Producto modificarProducto(@PathVariable Long codigo_producto,
            @RequestParam(required=false, name="codNuevo") Long codNuevo,
            @RequestParam(required=false, name="nombre") String nuevoNombre,
            @RequestParam(required=false, name="marca") String nuevaMarca,
            @RequestParam(required=false, name="costo") Double nuevoCosto,
            @RequestParam(required=false, name="nuevaCant_disponible") Double nuevaCant_disponible,
            @RequestParam(required=false, name="borrado") boolean borrado){
            
        produServi.editProducto(codigo_producto, codNuevo, nuevoNombre, 
                nuevaMarca, nuevoCosto, nuevaCant_disponible, borrado);
        
        Producto produ= produServi.findProducto(codigo_producto);
        
        return produ;
    }
    
    //Editar producto con @RequestBody
    @PutMapping("/editar")
    public Producto modificarProducto(@RequestBody Producto produ){
        produServi.editProducto(produ);
        return produServi.findProducto(produ.getCodigo_producto());
    }
    
    //Eliminar producto
    @DeleteMapping("/eliminar/{codigo_producto}")
    public String eliminarProducto(@PathVariable Long codigo_producto){
        produServi.deleteProducto(codigo_producto);
        return "Borrado exitoso";
    }
    
    //Eliminado lógico de un producto por parametro codigo
    @PutMapping("/eliminado_logico/{codigo_producto}")
    public Producto borrarByIdProducto(@PathVariable Long codigo_producto,
            @RequestParam(required=false, name="borrado") boolean borrado){
        produServi.borradoLogicoProducto(codigo_producto,borrado);
        Producto produ= produServi.findProducto(codigo_producto);
        return produ;
    }

//***************************************************************************
    
    // 4. Traer todos los productos cuya cantidad_disponible sea menor a 5
    
    @GetMapping("/falta_stock")
    public List<Producto>getProdMenorCinco(){
        List<Producto> todos= new ArrayList<>();
        todos=produServi.getProductos();
        Predicate<Producto> menorCinco = produ->produ.getCantidad_disponible()>5;
        todos.removeIf(menorCinco);
        
        return todos;
    }
 }   

