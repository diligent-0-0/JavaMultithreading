package FunctionalInterface;

import java.util.*;
import java.util.function.*;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        Supplier<List<String>> listSupplier = () -> Arrays.asList("Apple", "Banana", "Avocado", "Mango");

        Predicate<String> startsWithA = fruit -> fruit.startsWith("A");

        Consumer<String> printer = fruit -> System.out.println("Fruit: " + fruit);

        // Get list from supplier
        List<String> fruits = listSupplier.get();

        // Filter and consume
        fruits.stream()
                .filter(startsWithA)  // uses Predicate
                .forEach(printer);    // uses Consumer
    }
}
