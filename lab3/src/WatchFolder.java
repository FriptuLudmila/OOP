// File: WatchFolder.java
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;

public class WatchFolder {
    public static void main(String[] args) {
        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_CREATE, new CreateEventHandler());
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_DELETE, new DeleteEventHandler());
        dispatcher.registerHandler(StandardWatchEventKinds.ENTRY_MODIFY, new ModifyEventHandler());

        DirectoryWatcher watcher = new DirectoryWatcher(Path.of("C:\\Users\\liuda\\Desktop\\lab3test"), dispatcher);
        watcher.watch();
    }
}

