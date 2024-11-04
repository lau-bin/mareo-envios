package com.lautarocutri.dev.mareoenvios.shipping.jax;

import javax.validation.constraints.NotNull;

import com.lautarocutri.dev.mareoenvios.shipping.state.ShippingStateMachineFactory.Trigger;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class ShippingUpdateStateJax {

	@NotNull
	@NonNull
	@Schema(required = true)
	private Trigger status;
}
