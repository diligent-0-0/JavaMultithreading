package SerializationExample;

import java.io.*;

// Step 1: Class must implement Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for version control

    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        String filename = "student.ser";

        // ---------- SERIALIZATION ----------
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            Student s1 = new Student("Alice", 21);
            out.writeObject(s1);
            System.out.println("Object serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ---------- DESERIALIZATION ----------
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Student s2 = (Student) in.readObject();
            System.out.println("Object deserialized: " + s2.name + ", " + s2.age);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
