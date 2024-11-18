package com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String username, String password, List<String> roles) {
}