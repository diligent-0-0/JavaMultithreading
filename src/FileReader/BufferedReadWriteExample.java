package FileReader;

import java.io.*;


/*
How it works
BufferedReader wraps around FileReader to improve efficiency for reading characters.

readLine() reads a whole line at a time until null (end of file).

BufferedWriter wraps around FileWriter to make writing more efficient.

newLine() writes a system-specific line separator (so your file formatting is correct).

Try-with-resources automatically closes the streams after use.

✅ When to use:

Use BufferedReader/BufferedWriter for text files.

For binary files, use BufferedInputStream/BufferedOutputStream.
 */

public class BufferedReadWriteExample {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (
                // Reader with buffering
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                // Writer with buffering
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine(); // adds a newline (important if you want same formatting)
            }
            System.out.println("File copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
