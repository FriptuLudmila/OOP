
// File: DeleteEventHandler.java

/*import java.nio.file.WatchEvent;

public class DeleteEventHandler extends EventHandler {
    @Override
    public void handleEvent(WatchEvent<?> event) {
        System.out.println("A file has been deleted: " + event.context());
    }
}*/

// File: DeleteEventHandler.java
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;

public class DeleteEventHandler extends EventHandler {
    @Override
    public void handleEvent(WatchEvent<?> event) {
        WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
        Path fileName = pathEvent.context();
        System.out.println("A file has been deleted: " + fileName);

        // Print file info
        Path filePath = Path.of("C:\\Users\\liuda\\Desktop\\lab3test", fileName.toString());
        WatchFolder.printFileInfo(fileName.toString());
    }
}


