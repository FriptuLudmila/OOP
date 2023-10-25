// File: ModifyEventHandler.java

import java.nio.file.WatchEvent;

public class ModifyEventHandler extends EventHandler {
    @Override
    public void handleEvent(WatchEvent<?> event) {
        System.out.println("A file has been modified: " + event.context());
    }
}

