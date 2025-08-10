package FileReader;
/*
Key Points
char[] buffer: Stores chunks of data temporarily in memory.

reader.read(buffer): Reads multiple characters into the buffer at once.

writer.write(buffer, 0, charsRead): Writes exactly the number of characters read.

Avoids repeatedly creating String objects (faster for large files).

Still uses BufferedReader / BufferedWriter for efficiency.

💡 When to use which

readLine() version → When you care about text structure (lines, formatting).

char[] buffer version → When you just need raw speed, and line boundaries don’t matter.

 */

import java.io.*;
import java.nio.Buffer;

// https://medium.com/@AlexanderObregon/reading-files-from-the-local-disk-using-filereader-and-bufferedreader-2abfdae987b2
public class Eg {
    public static void main(String[] args){
        String inputFile = "C:\\Users\\goldi\\Documents\\ecom\\MultithreadingPractice\\src\\FileReader\\input.txt";
        String outputFile = "C:\\Users\\goldi\\Documents\\ecom\\MultithreadingPractice\\src\\FileReader\\output.txt";

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            char[] buffer = new char[1024]; // 1 KB buffer
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 4, charsRead);
            }

            System.out.println("File copied successfully using char buffer!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
