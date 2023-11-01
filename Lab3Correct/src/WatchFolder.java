

// File: WatchFolder.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class WatchFolder {

    private static final FileInfoDispatcher fileInfoDispatcher = new FileInfoDispatcher();
    private static LocalDateTime lastSnapshotTime;

    public static void main(String[] args) {
        //commit(); // Update the snapshot time at the start of the program
        setupFileInfoHandlers();  // Ensure this is called
        //printAllFilesInfo();

        // Start the directory watcher in a separate thread
        Executors.newSingleThreadExecutor().execute(() -> {
            EventDispatcher dispatcher = new EventDispatcher();
            dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_CREATE, new CreateEventHandler());
            dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_DELETE, new DeleteEventHandler());
            dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_MODIFY, new ModifyEventHandler());

            DirectoryWatcher watcher = new DirectoryWatcher(Path.of("C:\\Users\\liuda\\Desktop\\lab3test"), dispatcher);
            watcher.watch();
        });

        // Command line input loop
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Type 'commit' to create a snapshot, 'file info' to display all files information, and clear the screen:");
        while (true) {
            command = scanner.nextLine().trim(); // Trim whitespace from the user input
            if ("commit".equalsIgnoreCase(command)) {
                commit();
                clearConsole();
            } else if ("file info".equalsIgnoreCase(command)) {
                printAllFilesInfo(); // Call the method to print information for all files
            } else {
                System.out.println("Unknown command. Available commands: 'commit', 'file info'.");
            }
        }
    }


       /* EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_CREATE, new CreateEventHandler());
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_DELETE, new DeleteEventHandler());
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_MODIFY, new ModifyEventHandler());

        DirectoryWatcher watcher = new DirectoryWatcher(Path.of("C:\\Users\\liuda\\Desktop\\lab3test"), dispatcher);
        watcher.watch();*/





    private static void setupFileInfoHandlers() {
        fileInfoDispatcher.registerHandler("TXT", new TextFileInfoHandler());
        fileInfoDispatcher.registerHandler("java", new ProgramFileInfoHandler());
        fileInfoDispatcher.registerHandler("py", new ProgramFileInfoHandler());
        fileInfoDispatcher.registerHandler("png", new ImageFileInfoHandler());
        fileInfoDispatcher.registerHandler("jpg", new ImageFileInfoHandler());

    }
    // Method to "commit" a new snapshot
    public static void commit() {
        lastSnapshotTime = LocalDateTime.now();
        System.out.println("Created Snapshot at : " + lastSnapshotTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // In case of any IO errors, just print out a series of new lines as a fallback
            for (int i = 0; i < 50; i++) System.out.println();
        }
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



