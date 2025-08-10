package FileReader;

import java.io.*;

/*

Uses byte[] buffer instead of char[] — so it’s binary-safe.

Reads & writes in chunks (8 KB here), which is much faster than single-byte reads.

Works for any type of file (text, image, video, etc.).

No conversion to characters → no data loss.

💡 Quick Summary of All Three Approaches

File Type	Best Reader/Writer	Why
Text (line-based)	BufferedReader + readLine()	Easy to process lines
Text (chunk-based)	BufferedReader + char[] buffer	Faster, less GC overhead
Binary (any file type)	BufferedInputStream + byte[] buffer	Preserves raw bytes
 */

public class BufferedBinaryCopy {
    public static void main(String[] args) {
        String inputFile = "input.pdf";    // can be image, PDF, etc.
        String outputFile = "output.pdf";

        try (
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))
        ) {
            byte[] buffer = new byte[8192]; // 8 KB buffer
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Binary file copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
