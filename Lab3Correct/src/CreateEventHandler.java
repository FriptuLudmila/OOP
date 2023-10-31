// File: CreateEventHandler.java
/*import java.nio.file.WatchEvent;
public class CreateEventHandler extends EventHandler {
    @Override
    public void handleEvent(WatchEvent<?> event) {
        System.out.println("A new file is created: " + event.context());
        WatchFolder.printAllFilesInfo();
    }

}*/

// File: CreateEventHandler.java
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;

public class CreateEventHandler extends EventHandler {
    @Override
    public void handleEvent(WatchEvent<?> event) {
        WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
        Path fileName = pathEvent.context();
        System.out.println("A new file is created : " + fileName);

        // Print file info
        Path filePath = Path.of("C:\\Users\\liuda\\Desktop\\lab3test", fileName.toString());
        WatchFolder.printFileInfo(fileName.toString());
    }
}

// ... Do the same for DeleteEventHandler and ModifyEventHandler




