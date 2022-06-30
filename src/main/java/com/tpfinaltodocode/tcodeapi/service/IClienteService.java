
package com.tpfinaltodocode.tcodeapi.service;

import com.tpfinaltodocode.tcodeapi.model.Cliente;
import java.util.List;


public interface IClienteService {
    
    public void saveCliente(Cliente clien);
    
    public List<Cliente> getClientes();

    public Cliente findCliente(Long id_cliente);

    public void editCliente(Long id_cliente, Long idNueva, String nuevoNombre, 
            String nuevoApellido, String nuevoDni, boolean borrado);

    public void borradoLogicoCliente(Long id_cliente,boolean borrado);

    public void editCliente(Cliente clien); 
    
    public void deleteCliente(Long id_cliente);
    
}
