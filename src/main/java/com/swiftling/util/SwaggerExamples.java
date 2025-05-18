package com.swiftling.util;

public class SwaggerExamples {

    private SwaggerExamples() {}
    
    public static final String USER_CREATE_REQUEST_EXAMPLE = "{\n" +
            "  \"firstname\": \"John\",\n" +
            "  \"lastname\": \"Doe\",\n" +
            "  \"email\": \"john.doe@example.com\",\n" +
            "  \"password\": \"Pa$$w0rd123\"\n" +
            "}";

    public static final String USER_UPDATE_REQUEST_EXAMPLE = "{\n" +
            "  \"firstname\": \"John\",\n" +
            "  \"lastname\": \"Doe\",\n" +
            "  \"email\": \"john.doe@example.com\",\n" +
            "}";

    public static final String RESET_PASSWORD_REQUEST_EXAMPLE = "{\n" +
            "  \"newPassword\": \"Pa$$w0rd123\"\n" +
            "}";

    public static final String CHANGE_PASSWORD_REQUEST_EXAMPLE = "{\n" +
            "  \"currentPassword\": \"Pa$$w0rd123\"\n" +
            "  \"newPassword\": \"Pa$$w0rd123\"\n" +
            "}";

    public static final String RESET_PASSWORD_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"Password has been reset successfully.\"\n" +
            "}";

    public static final String CHANGE_PASSWORD_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"Password has been changed successfully.\"\n" +
            "}";
    
    public static final String USER_CREATE_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"CREATED\",\n" +
            "  \"message\": \"The user account has been signed up successfully.\",\n" +
            "  \"data\": {\n" +
            "    \"firstname\": \"John\",\n" +
            "    \"lastname\": \"Doe\",\n" +
            "    \"username\": \"john.doe\",\n" +
            "    \"email\": \"john.doe@example.com\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"User\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public static final String USER_UPDATE_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"The regular user is successfully updated.\",\n" +
            "  \"data\": {\n" +
            "    \"firstname\": \"John\",\n" +
            "    \"lastname\": \"Doe\",\n" +
            "    \"username\": \"john.doe\",\n" +
            "    \"email\": \"john.doe@example.com\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"User\"\n" +
            "    }\n" +
            "  }\n" +
            "}";
    
    public static final String USER_ENABLE_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"The user account has been enabled successfully.\"\n" +
            "}";
    
    public static final String USER_GET_RESPONSE_SINGLE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"The regular user is successfully retrieved.\",\n" +
            "  \"data\": {\n" +
            "    \"firstname\": \"John\",\n" +
            "    \"lastname\": \"Doe\",\n" +
            "    \"username\": \"john.doe\",\n" +
            "    \"email\": \"john.doe@example.com\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"User\"\n" +
            "    }\n" +
            "  }\n" +
            "}";
    
    public static final String ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Access is denied\",\n" +
            "  \"httpStatus\": \"FORBIDDEN\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String USER_FORGOT_PASSWORD_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"The forgot password email was sent successfully.\"\n" +
            "}";

    public static final String USER_ALREADY_EXISTS_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"The user account with the given email address (sample@email.com) already exists.\",\n" +
            "  \"httpStatus\": \"CONFLICT\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";
    
    public static final String USER_NOT_FOUND_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"The user account does not exist: + sample@email.com\",\n" +
            "  \"httpStatus\": \"NOT_FOUND\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String TOKEN_NOT_FOUND_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"The token does not exist.\",\n" +
            "  \"httpStatus\": \"NOT_FOUND\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String TOKEN_EXPIRED_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"The token has expired.\",\n" +
            "  \"httpStatus\": \"NOT_FOUND\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String USER_ALREADY_ENABLED_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"The user account is already enabled/verified.\",\n" +
            "  \"httpStatus\": \"NOT_FOUND\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String USER_NOT_DELETED_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"User can not be deleted.\",\n" +
            "  \"httpStatus\": \"CONFLICT\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";
    
    public static final String VALIDATION_EXCEPTION_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Invalid Input(s)\",\n" +
            "  \"httpStatus\": \"BAD_REQUEST\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\",\n" +
            "  \"errorCount\": 1,\n" +
            "  \"validationExceptions\": [\n" +
            "    {\n" +
            "      \"errorField\": \"email\",\n" +
            "      \"rejectedValue\": \"john.doe.123456\",\n" +
            "      \"reason\": \"The email address must be in a valid email format.\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

}
