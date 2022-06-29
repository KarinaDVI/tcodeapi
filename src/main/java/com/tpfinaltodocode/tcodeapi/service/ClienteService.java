
package com.tpfinaltodocode.tcodeapi.service;

import com.tpfinaltodocode.tcodeapi.model.Cliente;
import com.tpfinaltodocode.tcodeapi.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private IClienteRepository clienRepo;
   
    @Override
    public void saveCliente(Cliente clien) {
        clienRepo.save(clien);
    } 
    
    @Override
    public Cliente findCliente(Long id_cliente) {
        Cliente clien= clienRepo.findById(id_cliente).orElse(null);
        return clien;
    }
    
    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaClientes= clienRepo.findAll();
        return listaClientes;
    }

    @Override
    public void editCliente(Long id_cliente, Long idNueva, String nuevoNombre, String nuevoApellido, String nuevoDni) {
       
        Cliente clien = this.findCliente(id_cliente);
        
        clien.setId_cliente(idNueva);
        clien.setNombre(nuevoNombre);
        clien.setApellido(nuevoApellido);
        clien.setDni(nuevoDni);
        
        this.saveCliente(clien);
    }

    @Override
    public void editCliente(Cliente clien) {
        this.saveCliente(clien);
    }
    
    @Override
    public void deleteCliente(Long id_cliente) {
        clienRepo.deleteById(id_cliente);
    }

    
}
