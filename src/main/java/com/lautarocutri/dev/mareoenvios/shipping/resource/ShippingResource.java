package com.lautarocutri.dev.mareoenvios.shipping.resource;

import java.util.List;

import javax.validation.Valid;

import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerJax;
import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingDetailsDisplayJax;
import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingDisplayJax;
import com.lautarocutri.dev.mareoenvios.shipping.jax.ShippingUpdateStateJax;
import com.lautarocutri.dev.mareoenvios.shipping.service.ShippingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
public class ShippingResource {

	private final ShippingService shippingService;

	public ShippingResource(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	@Operation(summary = "Get all shippings of customer", description = "Returns a list of all active shippings and products of a customer")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful operation",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerJax.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content),
			@ApiResponse(responseCode = "403", description = "Unathorized",
					content = @Content)
	})
	@GetMapping("/{userId}")
	public ResponseEntity<List<ShippingDisplayJax>> getAllShippingsByUserId(
			@NonNull @PathVariable Integer userId
	) {
		return ResponseEntity.ok(shippingService.getShippingsInfoForUser(userId));
	}

	@Operation(summary = "Update shipping status", description = "Update the shipping status of a shipping to a valid status")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful operation",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerJax.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content),
			@ApiResponse(responseCode = "403", description = "Unathorized",
					content = @Content),
			@ApiResponse(responseCode = "409", description = "Invalid state transition",
					content = @Content)
	})
	@PostMapping("/{shippingId}/status")
	public ResponseEntity<ShippingDetailsDisplayJax> updateShippingStatus(
			@NonNull @PathVariable Integer shippingId,
			@Valid @NonNull @RequestBody ShippingUpdateStateJax status
	) {
		return ResponseEntity.ok(shippingService.updateStatus(shippingId, status));
	}
}
