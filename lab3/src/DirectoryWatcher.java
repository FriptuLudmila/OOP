// File: DirectoryWatcher.java
import java.nio.file.*;

public class DirectoryWatcher {
    private final Path directory;
    private final EventDispatcher dispatcher;

    public DirectoryWatcher(Path directory, EventDispatcher dispatcher) {
        this.directory = directory;
        this.dispatcher = dispatcher;
    }

    public void watch() {
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            WatchKey watchKey = directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
            while (true) {
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    dispatcher.dispatch(event);
                }
                boolean valid = watchKey.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
