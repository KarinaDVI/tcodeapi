
package com.tpfinaltodocode.tcodeapi.repository;

import com.tpfinaltodocode.tcodeapi.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long>{
    
}
