package com.lautarocutri.dev.mareoenvios.shipping.repository;

import java.util.List;

import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.ProductInfoDTO;
import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.ShippingInfoDTO;

public interface CustomShippingRepository {
	List<ShippingInfoDTO> findShippingInfoByCustomerId(Integer customerId);
	List<ProductInfoDTO> getMostPopularProductsByOrderedProductQuantity();
}
