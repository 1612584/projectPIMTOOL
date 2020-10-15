package vn.elca.training.common;

import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.scene.Parent;

import java.util.EventListener;

public class CustomCommonListener implements EventListener {
    private final Parent parent;

    public CustomCommonListener(Parent parent) {
        this.parent = parent;
    }

    public void createSuccess(boolean success) {
        this.parent.setEventDispatcher(new EventDispatcher() {
            @Override
            public Event dispatchEvent(Event event, EventDispatchChain tail) {
                return null;
            }
        });
    }
}
