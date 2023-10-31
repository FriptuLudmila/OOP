// File: TextFileInfoHandler.java
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class TextFileInfoHandler extends FileInfoHandler {

    @Override
    public void printInfo(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath);
            int lineCount = lines.size();
            int wordCount = lines.stream().map(line -> line.split("\\s+").length).reduce(0, Integer::sum);
            int charCount = lines.stream().map(String::length).reduce(0, Integer::sum);

            System.out.printf("%s: Line count - %d, Word count - %d, Character count - %d%n",
                    filePath, lineCount, wordCount, charCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
