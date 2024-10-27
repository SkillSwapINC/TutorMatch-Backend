package com.skillswap.platform.tutormatch.shared.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Root", description = "Root controller that handles the root endpoint")
public class RootController {

    @Operation(summary = "Root endpoint", description = "Returns an empty response with HTTP 204 No Content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204")
    })
    @GetMapping("/")
    public ResponseEntity<Void> getRoot() {
        return ResponseEntity.noContent().build();
    }
}
