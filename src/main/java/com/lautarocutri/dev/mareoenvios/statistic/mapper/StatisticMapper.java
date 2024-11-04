package com.lautarocutri.dev.mareoenvios.statistic.mapper;

import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.ProductInfoDTO;
import com.lautarocutri.dev.mareoenvios.statistic.jax.ProductDescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
		componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface StatisticMapper {
	@Mapping(target = "quantity", source = "productCount")
	ProductDescription toProductDescription(ProductInfoDTO productInfoDTO);
}
