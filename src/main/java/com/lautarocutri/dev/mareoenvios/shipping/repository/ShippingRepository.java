package com.lautarocutri.dev.mareoenvios.shipping.repository;

import com.lautarocutri.dev.mareoenvios.shipping.entity.Shipping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends CrudRepository<Shipping, Integer>, CustomShippingRepository {
}
