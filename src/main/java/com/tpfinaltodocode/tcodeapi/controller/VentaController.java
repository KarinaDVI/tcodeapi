
package com.tpfinaltodocode.tcodeapi.controller;

import com.tpfinaltodocode.tcodeapi.dto.ProductoDto;
import com.tpfinaltodocode.tcodeapi.dto.VentaDto;
import com.tpfinaltodocode.tcodeapi.dto.VentaMontosDto;
import com.tpfinaltodocode.tcodeapi.dto.VentaProductoDto;
import com.tpfinaltodocode.tcodeapi.model.Cliente;
import com.tpfinaltodocode.tcodeapi.model.Producto;
import com.tpfinaltodocode.tcodeapi.model.Venta;
import com.tpfinaltodocode.tcodeapi.service.IProductoService;
import com.tpfinaltodocode.tcodeapi.service.IVentaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private IVentaService ventaServi;
    private IProductoService produServi;
    
//****CRUD: ******* 
 
    //alta
    @PostMapping("/crear")
    public Venta altaVenta(@RequestBody Venta venta){
        Venta ventaS;
        ventaServi.guardarVentas(venta);
        ventaS=ventaServi.findVenta(venta.getCodigo_venta());
        return ventaS;
        
    }
    
    // Listar una venta completa
    @GetMapping("/{codigo_ventas}")
    public VentaDto obtenerUnaVenta(@PathVariable Long codigo_ventas){
        Venta vlist = ventaServi.findVenta(codigo_ventas);
        List<Producto> prlist = vlist.getListaProductos();
        List<ProductoDto> pdtolist = new ArrayList<>();
        VentaDto vdto = new VentaDto();
        vdto.setCodigo_venta(vlist.getCodigo_venta());
        vdto.setFecha_venta(vlist.getFecha_venta());
        vdto.setTotal(vlist.getTotal());
        vdto.setUnCliente(vlist.getUnCliente());
        
        for(Producto pr:prlist){
            ProductoDto p = new ProductoDto();
            p.setCodigo_producto(pr.getCodigo_producto());
            p.setNombre(pr.getNombre());
            p.setMarca(pr.getMarca());
            p.setCosto(pr.getCosto());
            p.setCantidad_disponible(pr.getCantidad_disponible());
            pdtolist.add(p);
        }
        vdto.setListaProductos(pdtolist);
        
        return vdto;
        
    }
    
    // Listar ventas
    @GetMapping()
    public List<VentaDto> traerVentaDto() {

        List<Venta> listaVentas = ventaServi.getVentas();
        //listaVentas = ventaServi.getVentas();
        List<VentaDto> listaVentaDto = new ArrayList<>();
        

        for (Venta ven : listaVentas) {
            List<Producto> listaProducto = ven.getListaProductos();
            //listaProducto = ven.getListaProductos();
            VentaDto venDto = new VentaDto();    
            List<ProductoDto> listPdto = new ArrayList<>();

            for (Producto pOrig : listaProducto) {
                ProductoDto pDto = new ProductoDto();

                pDto.setCodigo_producto(pOrig.getCodigo_producto());
                pDto.setNombre(pOrig.getNombre());
                pDto.setMarca(pOrig.getMarca());
                pDto.setCosto(pOrig.getCosto());
                pDto.setCantidad_disponible(pOrig.getCantidad_disponible());
                listPdto.add(pDto);
            }

            venDto.setCodigo_venta(ven.getCodigo_venta());
            venDto.setFecha_venta(ven.getFecha_venta());
            venDto.setTotal(ven.getTotal());
            venDto.setListaProductos(listPdto);
            venDto.setUnCliente(ven.getUnCliente());

            listaVentaDto.add(venDto);
        }
        return listaVentaDto;
    }
    
    // Modificar con @RequestParam
    @PutMapping("/editar/{codigo_venta}")    
    public Venta modificarVenta(@PathVariable Long codigo_venta,
            @RequestParam(required=false, name="codNuevo") Long codNuevo,
            @RequestParam(required=false, name="fechaNueva") String fecha_ingresada,
            @RequestParam(required=false, name="totalNuevo") Double totalNuevo,
            @RequestParam(required=false, name="nuevosProductos") List<Producto> nuevosProductos,
            @RequestParam(required=false, name="nuevoCliente") Cliente nuevoCliente)
            {
            LocalDate fechaNueva = LocalDate.parse(fecha_ingresada);
            ventaServi.editVenta(codigo_venta, codigo_venta, fechaNueva, totalNuevo, 
                    nuevosProductos, nuevoCliente);
            Venta venta = ventaServi.findVenta(codNuevo);
            
            return venta;
    }
    
    // Modificar con @RequestBody
    @PutMapping("/editar")
    public Venta modificarVenta(@RequestBody Venta venta){
        Venta ventaM;
        ventaServi.editVenta(venta);
        ventaM=ventaServi.findVenta(venta.getCodigo_venta());
        return ventaM;
    }
    
    
    // Modificar con ResponseEntity
    @PutMapping("/editar_re/{codigo_venta}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable Long codigo_venta, @RequestBody Venta venReq){
        VentaDto vdto = this.obtenerUnaVenta(codigo_venta);
        List<ProductoDto> pDtoList = vdto.getListaProductos();
        List<Producto> pList=new ArrayList<>();
        Venta venta = new Venta();
        
        for(ProductoDto p : pDtoList){
        Producto prod = new Producto();
        prod.setCodigo_producto(p.getCodigo_producto());
        prod.setNombre(p.getNombre());
        prod.setMarca(p.getMarca());
        prod.setCosto(p.getCosto());
        prod.setCantidad_disponible(p.getCantidad_disponible());
        pList.add(prod);
        }
        venta.setCodigo_venta(vdto.getCodigo_venta());
        venta.setFecha_venta(vdto.getFecha_venta());
        venta.setListaProductos(pList);
        venta.setUnCliente(vdto.getUnCliente());
        ventaServi.editVenta(venta);

        return new ResponseEntity<>(ventaServi.findVenta
        (venta.getCodigo_venta()), HttpStatus.OK);
    }
       
    // Eliminar
    @DeleteMapping("/eliminar/{codigo_venta}")
    public void eliminarVenta(@PathVariable Long codigo_venta){
        ventaServi.deleteVentas(codigo_venta);

    }
    
//****************************************************************************************
    
    // 5. Traer lista de productos de una determinada venta
    @GetMapping("/productos/{codigo_venta}")
    public List<ProductoDto> obtenerProductosVenta(@PathVariable Long codigo_venta){
        Venta venta= ventaServi.findVenta(codigo_venta);
        List <Producto> listaProdO = new ArrayList<>();
        listaProdO=venta.getListaProductos();
        List<ProductoDto> produList = new ArrayList<>();
        for(Producto prod : listaProdO){
            
            ProductoDto produDto = new ProductoDto();
            
            produDto.setCodigo_producto(prod.getCodigo_producto());
            produDto.setNombre(prod.getNombre());
            produDto.setMarca(prod.getMarca());
            produDto.setCosto(prod.getCosto());
            produDto.setCantidad_disponible(prod.getCantidad_disponible());
            produList.add(produDto);
        }
        return produList;
    }
    
    /* 6. Obtener sumatoria del monto de ventas y cantidad total de ventas 
    de un determinado dia  */
    
     @GetMapping("/fecha_ventas/{fecha_ventas}")
    public VentaMontosDto sacarMontoFecha(@PathVariable String fecha_ventas){
        VentaMontosDto ventaMC= new VentaMontosDto();
        Double montoTotal=0.0;
        int cantVentas=0;
        Venta ven;
        List<VentaDto> listaVentas=this.traerVentaDto();
        
        for(VentaDto venti : listaVentas){
            
            if ((venti.getFecha_venta().toString()).equals(fecha_ventas)){
                ven=ventaServi.findVenta(venti.getCodigo_venta());
                montoTotal+=ven.getTotal();
                cantVentas++;
                
            }else{
            }
        }
        LocalDate localDate = LocalDate.parse(fecha_ventas);
        ventaMC.setFecha_venta(localDate);
        ventaMC.setCantVentas(cantVentas);
        ventaMC.setMonto_Total_de_Ventas(montoTotal);
        return ventaMC;
        
    }
    
    // Mismo método que el 6 pero con String
    
    @GetMapping("/fecha_ventas2/{fecha_ventas}")
    public String obtenerMontoFecha(@PathVariable String fecha_ventas){
        
        List<VentaDto> listaVentas=this.traerVentaDto();
        List<Double> listaMontos=new ArrayList<>();
        Venta ventaSave;
        int cantVentas=0;
        for(VentaDto venti : listaVentas){
            
            if ((venti.getFecha_venta().toString()).equals(fecha_ventas)){
                ventaSave=ventaServi.findVenta(venti.getCodigo_venta());
                listaMontos.add(ventaSave.getTotal());
                cantVentas++;
            }else{
                
            }
        }
        Double mTotal=0.0;
        for(Double ven : listaMontos){
            mTotal+=ven;
        }
        
        String monto= Double.toString(mTotal);
        String fecha= fecha_ventas;
        String cVentas = Integer.toString(cantVentas);
        
        return "La cantidad total de ventas del dia "+fecha+ 
        " fué de "+cVentas+" y el monto total fué de "+monto;
    }
    
    /* 7. Obtener el codigo_venta, el total, la cantidad de productos,
    el nombre del cliente y el apellido del cliente de la venta 
    con el monto más alto de todas.    */
    
    @GetMapping("/mayor_venta")
    public VentaProductoDto datosMayorMonto(){
        List<Venta> listaVent = ventaServi.getVentas();
        VentaProductoDto venDto = new VentaProductoDto();
        Double mayor=0.0;
        Long code=0L;
        String nameCli="";
        String apeCli="";
        Double cantProd=0.0;
        for (Venta ven : listaVent){

            if (ven.getTotal()>mayor){
                mayor=ven.getTotal();
                code=ven.getCodigo_venta();
                cantProd=Double.valueOf(ven.getListaProductos().size());
                nameCli=ven.getUnCliente().getNombre();
                apeCli=ven.getUnCliente().getApellido();
            }else{
                
            }
        }
        venDto.setCodigo_venta(code);
        venDto.setTotal(mayor);
        venDto.setNombreCliente(nameCli);
        venDto.setApellido(apeCli);
        venDto.setCantidad_productos(cantProd);

        return venDto;
    }
  }




