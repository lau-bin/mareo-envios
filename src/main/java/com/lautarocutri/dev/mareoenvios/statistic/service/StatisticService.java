package com.lautarocutri.dev.mareoenvios.statistic.service;

import java.util.stream.Collectors;

import com.lautarocutri.dev.mareoenvios.shipping.repository.ShippingRepository;
import com.lautarocutri.dev.mareoenvios.statistic.jax.MostPopularShippingsDisplayJax;
import com.lautarocutri.dev.mareoenvios.statistic.mapper.StatisticMapper;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

	private final ShippingRepository shippingRepository;
	private final StatisticMapper statisticMapper;

	public StatisticService(ShippingRepository shippingRepository, StatisticMapper statisticMapper) {
		this.shippingRepository = shippingRepository;
		this.statisticMapper = statisticMapper;
	}

	public MostPopularShippingsDisplayJax getMostPopularShippings() {
		return new MostPopularShippingsDisplayJax(shippingRepository.getMostPopularProductsByOrderedProductQuantity()
				.stream()
				.map(statisticMapper::toProductDescription)
				.collect(
						Collectors.toList()));
	}
}
