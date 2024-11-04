package com.lautarocutri.dev.mareoenvios.statistic.resource;

import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerJax;
import com.lautarocutri.dev.mareoenvios.statistic.jax.MostPopularShippingsDisplayJax;
import com.lautarocutri.dev.mareoenvios.statistic.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class StatisticResource {

	private final StatisticService statisticService;

	public StatisticResource(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	@Operation(summary = "Get 3 most popular products", description = "Returns the top 3 most popular products by product quantity amount")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful operation",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerJax.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content),
			@ApiResponse(responseCode = "403", description = "Unathorized",
					content = @Content)
	})
	@GetMapping("/most-popular")
	public ResponseEntity<MostPopularShippingsDisplayJax> getMostPopularProducts() {
		return ResponseEntity.ok(statisticService.getMostPopularShippings());
	}
}
