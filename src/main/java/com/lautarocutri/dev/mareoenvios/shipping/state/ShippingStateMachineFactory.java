package com.lautarocutri.dev.mareoenvios.shipping.state;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.lautarocutri.dev.mareoenvios.shipping.entity.Shipping.State;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ShippingStateMachineFactory {

	public enum Trigger {
		DELIVERED,
		DELIVERED_TO_POST_OFFICE,
		CANCELED,
		IN_TRANSIT
	}

	private final StateMachineConfig<State, Trigger> config;

	public ShippingStateMachineFactory() {
		config = new StateMachineConfig<>();
		config.configure(State.INITIAL)
				.permit(Trigger.DELIVERED_TO_POST_OFFICE, State.DELIVERED_TO_POST_OFFICE)
				.permit(Trigger.CANCELED, State.CANCELED);

		config.configure(State.DELIVERED_TO_POST_OFFICE)
				.permit(Trigger.IN_TRANSIT, State.IN_TRANSIT)
				.permit(Trigger.CANCELED, State.CANCELED);

		config.configure(State.IN_TRANSIT)
				.permit(Trigger.DELIVERED, State.DELIVERED);
	}

	public StateMachine<State, Trigger> createStateMachine(State initialState) {
		// Set up onUnhandledTrigger to throw an exception if there is no state for a trigger
		var stateMachine = new StateMachine<>(initialState, config);
		stateMachine.onUnhandledTrigger((state, trigger) -> {
			throw new FinalStateException();
		});
		return stateMachine;
	}
}
