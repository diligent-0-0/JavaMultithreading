package FunctionalInterface;

import java.util.function.*;

public class FunctionalInterfaceExample {
    public static void main(String[] args) {

        // Supplier: supplies a value without any input
        Supplier<String> nameSupplier = () -> "Alice";

        // Predicate: tests a condition on input
        Predicate<String> nameStartsWithA = name -> name.startsWith("A");

        // Consumer: consumes a value and performs some action
        Consumer<String> greeter = name -> System.out.println("Hello, " + name + "!");

        // Use them together
        String name = nameSupplier.get(); // get the name
        if (nameStartsWithA.test(name)) { // test condition
            greeter.accept(name);         // greet the name
        } else {
            System.out.println("Name does not start with A");
        }
    }
}

