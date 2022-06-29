
package com.tpfinaltodocode.tcodeapi.repository;

import com.tpfinaltodocode.tcodeapi.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
    
}
