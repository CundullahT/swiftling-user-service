package com.swiftling.util;

public class SwaggerExamples {

    private SwaggerExamples() {}
    //Done
    public static final String USER_CREATE_REQUEST_EXAMPLE = "{\n" +
            "  \"firstname\": \"John\",\n" +
            "  \"lastname\": \"Doe\",\n" +
            "  \"username\": \"john.doe\",\n" +
            "  \"email\": \"john.doe@example.com\",\n" +
            "  \"password\": \"Pa$$w0rd\"\n" +
            "}";
    //Done
    public static final String USER_UPDATE_REQUEST_EXAMPLE = "{\n" +
            "  \"firstname\": \"John\",\n" +
            "  \"lastname\": \"Doe\",\n" +
            "  \"email\": \"john.doe@example.com\"\n" +
            "}";
    //Done
    public static final String ADMIN_USER_CREATE_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"CREATED\",\n" +
            "  \"message\": \"The admin user is successfully created.\",\n" +
            "  \"data\": {\n" +
            "    \"firstname\": \"John\",\n" +
            "    \"lastname\": \"Doe\",\n" +
            "    \"username\": \"john.doe\",\n" +
            "    \"email\": \"john.doe@example.com\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"Admin\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    //Done
    public static final String REGULAR_USER_CREATE_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"CREATED\",\n" +
            "  \"message\": \"The regular user is successfully created.\",\n" +
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
    //Done
    public static final String ADMIN_USER_UPDATE_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"The admin user is successfully updated.\",\n" +
            "  \"data\": {\n" +
            "    \"firstname\": \"John\",\n" +
            "    \"lastname\": \"Doe\",\n" +
            "    \"username\": \"john.doe\",\n" +
            "    \"email\": \"john.doe@example.com\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"Admin\"\n" +
            "    }\n" +
            "  }\n" +
            "}";
    //Done
    public static final String REGULAR_USER_UPDATE_RESPONSE_EXAMPLE = "{\n" +
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
    //Done
    public static final String ADMIN_USER_GET_RESPONSE_SINGLE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"The admin user is successfully retrieved.\",\n" +
            "  \"data\": {\n" +
            "    \"firstname\": \"John\",\n" +
            "    \"lastname\": \"Doe\",\n" +
            "    \"username\": \"john.doe\",\n" +
            "    \"email\": \"john.doe@example.com\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"Admin\"\n" +
            "    }\n" +
            "  }\n" +
            "}";
    //Done
    public static final String REGULAR_USER_GET_RESPONSE_SINGLE_EXAMPLE = "{\n" +
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
    //Done
    public static final String ADMIN_USER_GET_RESPONSE_LIST_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"All the admin users are successfully retrieved.\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"firstname\": \"John\",\n" +
            "      \"lastname\": \"Doe\",\n" +
            "      \"username\": \"john.doe\",\n" +
            "      \"email\": \"john.doe@example.com\",\n" +
            "      \"enabled\": true,\n" +
            "      \"role\": {\n" +
            "        \"description\": \"Admin\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"firstname\": \"Jane\",\n" +
            "      \"lastname\": \"Doe\",\n" +
            "      \"username\": \"jane.doe\",\n" +
            "      \"email\": \"jane.doe@example.com\",\n" +
            "      \"enabled\": true,\n" +
            "      \"role\": {\n" +
            "        \"description\": \"Admin\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    //Done
    public static final String REGULAR_USER_GET_RESPONSE_LIST_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"All the regular users are successfully retrieved.\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"firstname\": \"John\",\n" +
            "      \"lastname\": \"Doe\",\n" +
            "      \"username\": \"john.doe\",\n" +
            "      \"email\": \"john.doe@example.com\",\n" +
            "      \"enabled\": true,\n" +
            "      \"role\": {\n" +
            "        \"description\": \"User\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"firstname\": \"Jane\",\n" +
            "      \"lastname\": \"Doe\",\n" +
            "      \"username\": \"jane.doe\",\n" +
            "      \"email\": \"jane.doe@example.com\",\n" +
            "      \"enabled\": true,\n" +
            "      \"role\": {\n" +
            "        \"description\": \"User\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    //Done
    public static final String ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Access is denied\",\n" +
            "  \"httpStatus\": \"FORBIDDEN\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";
    //Done
    public static final String USER_ALREADY_EXISTS_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"User already exists.\",\n" +
            "  \"httpStatus\": \"CONFLICT\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";
    //Done
    public static final String USER_NOT_FOUND_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"User does not exist.\",\n" +
            "  \"httpStatus\": \"NOT_FOUND\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";
    //Done
    public static final String USER_NOT_DELETED_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"User can not be deleted.\",\n" +
            "  \"httpStatus\": \"CONFLICT\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    //Done
    public static final String VALIDATION_EXCEPTION_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Invalid Input(s)\",\n" +
            "  \"httpStatus\": \"BAD_REQUEST\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\",\n" +
            "  \"errorCount\": 1,\n" +
            "  \"validationExceptions\": [\n" +
            "    {\n" +
            "      \"errorField\": \"userName\",\n" +
            "      \"rejectedValue\": \"john.doe.123456@email.com\",\n" +
            "      \"reason\": \"Username must be between 6 and 24 characters long.\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

}
