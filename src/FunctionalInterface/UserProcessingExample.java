package FunctionalInterface;

import java.util.*;
import java.util.function.*;

class User {
    String name;
    String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

public class UserProcessingExample {
    public static void main(String[] args) {

        // Supplier: supplies a list of users (could be from DB, API, or file)
        Supplier<List<User>> userSupplier = () -> Arrays.asList(
                new User("Alice", "alice@example.com"),
                new User("Bob", ""),
                new User("Ankit", "ankit@example.com"),
                new User("Chris", "chris@example.com")
        );

        // Predicate: validate if user's name starts with 'A' AND email is not empty
        Predicate<User> validUserCheck = user ->
                user.name.startsWith("A") && !user.email.isBlank();

        // Consumer: process valid users (e.g., send email)
        Consumer<User> sendWelcomeEmail = user ->
                System.out.println("Sending welcome email to " + user.name + " at " + user.email);

        // Use them together
        userSupplier.get().stream()
                .filter(validUserCheck)    // Predicate: filter valid users
                .forEach(sendWelcomeEmail); // Consumer: process each
    }
}
