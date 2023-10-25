// File: EventDispatcher.java
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;

public class EventDispatcher {
    private final Map<WatchEvent.Kind<?>, EventHandler> handlers = new HashMap<>();

    public void registerHandler(WatchEvent.Kind<?> kind, EventHandler handler) {
        handlers.put(kind, handler);
    }

    public void dispatch(WatchEvent<?> event) {
        EventHandler handler = handlers.get(event.kind());
        if (handler != null) {
            handler.handleEvent(event);
        }
    }
}

