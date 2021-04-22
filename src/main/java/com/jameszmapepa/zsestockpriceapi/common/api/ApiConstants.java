package com.jameszmapepa.zsestockpriceapi.common.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstants {

    public static final String SUCCESS_MESSAGE = "Transaction Processed Successfully";
    public static final String INSUFFICIENT_FUNDS_MESSAGE = "You have insufficient float balance to complete this operation";
    public static final String NO_FCA_ACCOUNT_MESSAGE = "No FCA wallet account found";
    public static final String INVALID_TOKEN = "Invalid Token";
    public static final String VALID_TOKEN = "Valid token for user ";
    public static final String USERNAME_OR_PASSWORD_INVALID = "Username or Password should not be empty";
}
