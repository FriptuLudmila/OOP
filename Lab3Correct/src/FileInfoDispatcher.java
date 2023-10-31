// File: FileInfoDispatcher.java
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class FileInfoDispatcher {
    private final Map<String, FileInfoHandler> handlers = new HashMap<>();

    public void registerHandler(String extension, FileInfoHandler handler) {
        handlers.put(extension, handler);
    }

    public void dispatch(Path filePath) {
        String extension = getFileExtension(filePath);
        FileInfoHandler handler = handlers.get(extension);
        if (handler != null) {
            handler.printInfo(filePath);
        }
    }

    private String getFileExtension(Path filePath) {
        String fileName = filePath.toString();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}

