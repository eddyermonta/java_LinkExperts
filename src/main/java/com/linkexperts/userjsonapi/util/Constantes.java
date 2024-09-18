package com.linkexperts.userjsonapi.util;

public final class Constantes {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String USERS = "/users";
    public static final String ID = "/{id}";

    public static final String ERROR_FETCHING_USERS ="Error occurred while fetching users";
    public static final String INTERNAL_SERVER_ERROR ="Internal server error";
    public static final String AN_UNEXPECTED_ERROR_OCURRED = "An unexpected error occurred";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String UTILITY_CLASS = "Utility class";

    private Constantes() {
        throw new AssertionError("Utility class cannot be instantiated");
    }
}
