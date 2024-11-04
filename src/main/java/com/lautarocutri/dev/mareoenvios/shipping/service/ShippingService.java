package com.lautarocutri.dev.mareoenvios.shipping.service;

import java.util.List;
import java.util.stream.Collectors;

import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingDetailsDisplayJax;
import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingDisplayJax;
import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingUpdateStateJax;
import com.lautarocutri.dev.mareoenvios.shipping.mapper.ShippingMapper;
import com.lautarocutri.dev.mareoenvios.shipping.repository.ShippingRepository;
import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.ShippingInfoDTO;
import com.lautarocutri.dev.mareoenvios.shipping.state.ShippingStateMachineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

	private final ShippingRepository shippingRepository;
	private final ShippingMapper shippingMapper;
	private final ShippingStateMachineFactory shippingStateMachineFactory;

	@Autowired
	public ShippingService(
			ShippingRepository shippingRepository,
			ShippingMapper shippingMapper,
			ShippingStateMachineFactory shippingStateMachineFactory
	) {
		this.shippingRepository = shippingRepository;
		this.shippingMapper = shippingMapper;
		this.shippingStateMachineFactory = shippingStateMachineFactory;
	}

	public List<ShippingDisplayJax> getShippingsInfoForUser(Integer userId) {
		return shippingRepository.findShippingInfoByCustomerId(userId)
				.stream()
				.collect(Collectors.groupingBy(ShippingInfoDTO::getShippingId))
				.values().stream()
				.map(shippingInfoDTOS -> {
					var items = shippingInfoDTOS.stream()
							.map(shippingMapper::toShippingItemDisplayJax)
							.collect(Collectors.toList());
					return shippingMapper.toShippingDisplayJax(shippingInfoDTOS.get(0), items);
				})
				.collect(Collectors.toList());
	}

	public ShippingDetailsDisplayJax updateStatus(Integer shippingId, ShippingUpdateStateJax newState) {
		var shipping = shippingRepository.findById(shippingId).orElseThrow();
		var stateMachine = shippingStateMachineFactory.createStateMachine(shipping.getState());

		// Verify allowed transition, will throw FinalStateException if not allowed
		stateMachine.fire(newState.getStatus());

		shipping.setState(stateMachine.getState());
		return shippingMapper.toShippingDetailsDisplayJax(shippingRepository.save(shipping));
	}
}
