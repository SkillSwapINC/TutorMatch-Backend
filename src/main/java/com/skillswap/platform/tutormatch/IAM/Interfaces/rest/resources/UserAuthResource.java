package com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources;

import java.util.List;

public record UserAuthResource(Long id, String username, List<String> roles) {
}