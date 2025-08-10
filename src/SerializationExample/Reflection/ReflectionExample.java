package SerializationExample.Reflection;

import java.lang.reflect.*;

class Person {
    private String name = "John";
    public int age = 30;

    public void sayHello() {
        System.out.println("Hello!");
    }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        Person p = new Person();

        // Get Class object
        Class<?> clazz = p.getClass();

        // Print class name
        System.out.println("Class name: " + clazz.getName());

        // List all fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("Field: " + f.getName());
        }

        // Access private field
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true); // bypass private
        System.out.println("Private field value: " + nameField.get(p));

        // Call method dynamically
        Method method = clazz.getMethod("sayHello");
        method.invoke(p);
    }
}
