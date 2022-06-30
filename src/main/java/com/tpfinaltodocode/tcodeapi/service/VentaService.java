
package com.tpfinaltodocode.tcodeapi.service;

import com.tpfinaltodocode.tcodeapi.dto.VentaProductoDto;
import com.tpfinaltodocode.tcodeapi.model.Cliente;
import com.tpfinaltodocode.tcodeapi.model.Producto;
import com.tpfinaltodocode.tcodeapi.model.Venta;
import com.tpfinaltodocode.tcodeapi.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaRepository ventaRepo;
    @Autowired
    private IClienteService clienServi;
    @Autowired
    private IProductoService produServi;

    @Override
    public List<Venta> getVentas() {
        List<Venta> listaVentas= ventaRepo.findAll();
        return listaVentas;
    }

    @Override
    public void saveVentas(Venta venta) {
        ventaRepo.save(venta);
    }
  
    @Override
    public Venta guardarVentas(Venta venta) {
        Venta ventao=venta;
        Cliente clien=clienServi.findCliente(ventao.getUnCliente().getId_cliente());
        
        List<Producto> listaObte = new ArrayList<>();
        List<Double> listaCostos = new ArrayList<>();
        listaObte=venta.getListaProductos();
        List<Producto> listSave = new ArrayList<>();
        //Predicate<Producto> borrado = p->p.isBorrado()==true;
        //listaObte.removeIf(borrado);
        
        for (Producto pSave: listaObte){
            Producto produ=produServi.findProducto(pSave.getCodigo_producto());
            if((produ.isBorrado())==false){
            pSave.setCosto(produ.getCosto());
            pSave.setNombre(produ.getNombre());
            pSave.setMarca(produ.getMarca());
            produServi.descontarStock(produ.getCodigo_producto(), produ.getCantidad_disponible());
            pSave.setCantidad_disponible(produ.getCantidad_disponible());
            pSave.setBorrado(pSave.isBorrado());
            
            listaCostos.add(pSave.getCosto());
            listSave.add(pSave);
            }else{
                ventao=null;
            }
        }
        if((clien.isBorrado())==false){
            ventao.setUnCliente(clien);
            ventao.setFecha_venta(LocalDate.now());
            ventao.setListaProductos(listSave);
            ventao.setTotal(totalCosto(listaCostos));
            ventaRepo.save(ventao);
            return ventao; 
        }else{
            return ventao=null;
        }
     
    }
    
    @Override
    public Double totalCosto(List<Double> listaCostos){
        Double total=0.0;
        for(Double prod:listaCostos){
            total+=prod;
        }
        return total;
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        Venta venta = ventaRepo.findById(codigo_venta).orElse(null);
        return venta;
    }

    @Override
    public void deleteVentas(Long codigo_venta) {
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public void editVenta(Long codigo_venta, Long nuevoCodigo_venta, LocalDate newFecha_venta, Double newTotal, List<Producto> newListaProductos, Cliente newUnCliente) {
        Venta venta = this.findVenta(codigo_venta);
        venta.setCodigo_venta(nuevoCodigo_venta);
        venta.setFecha_venta(newFecha_venta);
        venta.setTotal(newTotal);
        venta.setListaProductos(newListaProductos);
        venta.setUnCliente(newUnCliente);
        
        this.saveVentas(venta);  
    }
    
    @Override
    public void editVenta(Venta ven) {
        this.guardarVentas(ven);
    }
    
    @Override
    public List<VentaProductoDto> getVenProd() {
        List<Venta> listaVentas = new ArrayList<>();
        List<VentaProductoDto> listaVentaProd = new ArrayList<>();
        VentaProductoDto detalle = new VentaProductoDto();
        listaVentas = this.getVentas();
        
        for (Venta venta: listaVentas){
            detalle.setFecha_venta(venta.getFecha_venta());
            detalle.setTotal(venta.getTotal());
        } 
        listaVentaProd.add(detalle);
        return listaVentaProd;
    }
 
    
}
