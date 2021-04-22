package com.jameszmapepa.zsestockpriceapi.security;


import com.jameszmapepa.zsestockpriceapi.common.api.ApiConstants;
import com.jameszmapepa.zsestockpriceapi.common.api.ApiResponse;
import com.jameszmapepa.zsestockpriceapi.security.payload.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jameszmapepa.zsestockpriceapi.common.api.ApiConstants.SUCCESS_MESSAGE;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    @PostMapping("/generatetoken")
    public ApiResponse<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
        log.info("Generate Token Request....");
        if (loginRequest.getUsername().isEmpty() || loginRequest.getPassword().isEmpty()) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ApiConstants.USERNAME_OR_PASSWORD_INVALID, null);
        }
        final String jwt = tokenProvider.generateToken(loginRequest);
        return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_MESSAGE, jwt);
    }
}