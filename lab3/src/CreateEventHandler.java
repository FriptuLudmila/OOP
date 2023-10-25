// File: CreateEventHandler.java
import java.nio.file.WatchEvent;
public class CreateEventHandler extends EventHandler {
    @Override
    public void handleEvent(WatchEvent<?> event) {
        System.out.println("A new file is created: " + event.context());
    }
}



