
import java.nio.file.WatchEvent;

public abstract class EventHandler {
    public abstract void handleEvent(WatchEvent<?> event);
}
