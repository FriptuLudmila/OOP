

// File: WatchFolder.java
import java.io.IOException;
import java.nio.file.*;

public class WatchFolder {

    private static final FileInfoDispatcher fileInfoDispatcher = new FileInfoDispatcher();

    public static void main(String[] args) {
        setupFileInfoHandlers();  // Ensure this is called
        printAllFilesInfo();


        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_CREATE, new CreateEventHandler());
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_DELETE, new DeleteEventHandler());
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_MODIFY, new ModifyEventHandler());

        DirectoryWatcher watcher = new DirectoryWatcher(Path.of("C:\\Users\\liuda\\Desktop\\lab3test"), dispatcher);
        watcher.watch();


    }


    private static void setupFileInfoHandlers() {
        fileInfoDispatcher.registerHandler("TXT", new TextFileInfoHandler());
        fileInfoDispatcher.registerHandler("java", new ProgramFileInfoHandler());
        fileInfoDispatcher.registerHandler("py", new ProgramFileInfoHandler());
        fileInfoDispatcher.registerHandler("png", new ImageFileInfoHandler());
        fileInfoDispatcher.registerHandler("jpg", new ImageFileInfoHandler());

    }

    public static void printFileInfo(String filename) {
        Path filePath = Path.of("C:\\Users\\liuda\\Desktop\\lab3test", filename);
        if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
            fileInfoDispatcher.dispatch(filePath);
        } else {
            System.out.println("File not found or is not a regular file: " + filename);
        }
    }

    public static void printAllFilesInfo() {
        Path directoryPath = Path.of("C:\\Users\\liuda\\Desktop\\lab3test");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path entry : stream) {
                System.out.println("Processing file: " + entry);  // Log each file being processed
                if (Files.isRegularFile(entry)) {
                    fileInfoDispatcher.dispatch(entry);
                } else {
                    System.out.println("Skipping non-regular file: " + entry);  // Log non-regular files
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



