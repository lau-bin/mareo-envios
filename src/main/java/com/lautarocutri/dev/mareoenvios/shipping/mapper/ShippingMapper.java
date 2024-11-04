package com.lautarocutri.dev.mareoenvios.shipping.mapper;

import java.util.List;

import com.lautarocutri.dev.mareoenvios.shipping.entity.Shipping;
import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingDetailsDisplayJax;
import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingDisplayJax;
import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingItemDisplayJax;
import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.ShippingInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
		componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ShippingMapper {

	@Mapping(target = "productDescription", source = "description")
	@Mapping(target = "productWeight", source = "weight")
	@Mapping(target = "productCount", source = "productCount")
	ShippingItemDisplayJax toShippingItemDisplayJax(ShippingInfoDTO shippingInfoDTO);

	@Mapping(target = "shippingDetails.customerId", source = "shippingInfoDTO.customerId")
	@Mapping(target = "shippingDetails.state", source = "shippingInfoDTO.state")
	@Mapping(target = "shippingDetails.sendDate", source = "shippingInfoDTO.sendDate")
	@Mapping(target = "shippingDetails.arriveDate", source = "shippingInfoDTO.arriveDate")
	@Mapping(target = "shippingDetails.priority", source = "shippingInfoDTO.priority")
	@Mapping(target = "items", source = "items")
	ShippingDisplayJax toShippingDisplayJax(
			ShippingInfoDTO shippingInfoDTO,
			List<ShippingItemDisplayJax> items
	);

	ShippingDetailsDisplayJax toShippingDetailsDisplayJax(Shipping shipping);

}
