// File: DeleteEventHandler.java

import java.nio.file.WatchEvent;

public class DeleteEventHandler extends EventHandler {
    @Override
    public void handleEvent(WatchEvent<?> event) {
        System.out.println("A file has been deleted: " + event.context());
    }
}
