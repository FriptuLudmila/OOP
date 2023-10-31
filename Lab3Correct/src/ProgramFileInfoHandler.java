// File: ProgramFileInfoHandler.java
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Pattern;

public class ProgramFileInfoHandler extends FileInfoHandler {

    @Override
    public void printInfo(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath);
            int lineCount = lines.size();
            long classCount = lines.stream().filter(line -> line.contains("class ")).count();
            long methodCount = lines.stream().filter(line -> Pattern.matches("\\s*\\w+\\s+\\w+\\s*\\(.*\\)\\s*\\{?", line)).count();

            System.out.printf("%s: Line count - %d, Class count - %d, Method count - %d%n",
                    filePath, lineCount, classCount, methodCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

