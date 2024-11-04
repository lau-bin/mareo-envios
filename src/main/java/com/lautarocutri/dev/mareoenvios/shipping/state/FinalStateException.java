package com.lautarocutri.dev.mareoenvios.shipping.state;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FinalStateException extends IllegalStateException {
	private static final long serialVersionUID = -5687166875567226160L;
}
