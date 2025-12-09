package com.emerald.util;

public class UserUtils {
    private UserUtils() {}

    public static String generateUserName(String firstName, String lastName) {
        return String.format("%s.%s", firstName, lastName);
    }

    public static String generateEmail(String firstName, String lastName) {
        return String.format("%s%s@buzzword.com", firstName.charAt(0), lastName);
    }
}
