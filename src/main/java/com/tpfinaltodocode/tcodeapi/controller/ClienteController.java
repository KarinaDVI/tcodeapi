
package com.tpfinaltodocode.tcodeapi.controller;

import com.tpfinaltodocode.tcodeapi.model.Cliente;
import com.tpfinaltodocode.tcodeapi.service.IClienteService;
import java.util.List;
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
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private IClienteService clienServi;
    private Cliente clienSave;
    

//****CRUD: *******
    
    //alta
    @PostMapping("/crear")
    public Cliente crearCliente(@RequestBody Cliente clien){
        clienServi.saveCliente(clien);
        clienSave=this.traerCliente(clien.getId_cliente());
        return clienSave;
    }
    
    //Listado de clientes
    @GetMapping()
    public List<Cliente>getClientes(){
        return clienServi.getClientes();
    }
    
    //Listar un cliente
    @GetMapping("/{id_cliente}")
    public Cliente traerCliente(@PathVariable Long id_cliente){
        return clienServi.findCliente(id_cliente);
    }
    
    //Modificar cliente con @RequestParam
    @PutMapping("/editar/{id_cliente}")
    public Cliente modificarCliente(@PathVariable Long id_cliente,
            @RequestParam(required=false, name="id") Long idNueva,
            @RequestParam(required=false, name="nombre") String nuevoNombre,
            @RequestParam(required=false, name="apellido") String nuevoApellido,
            @RequestParam(required=false, name="dni") String nuevoDni){
        
        clienServi.editCliente(id_cliente, idNueva,nuevoNombre,nuevoApellido,nuevoDni);
        Cliente clien= clienServi.findCliente(id_cliente);
        return clien;
    }
    
    //Modificar cliente con @RequestBody
    @PutMapping("/editar")
    public Cliente modificarCliente(@RequestBody Cliente clien){
        clienServi.editCliente(clien);
        return clienServi.findCliente(clien.getId_cliente());
    }

    //Eliminar un cliente
    @DeleteMapping("/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable Long id_cliente){
        clienServi.deleteCliente(id_cliente);
        return "Borrado exitoso";
    }
}
        
