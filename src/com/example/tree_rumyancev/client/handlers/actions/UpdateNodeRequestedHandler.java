package com.example.tree_rumyancev.client.handlers.actions;

import com.example.tree_rumyancev.client.handlers.event.actionRequest.UpdateNodeRequestedEvent;
import com.google.gwt.event.shared.EventHandler;

public interface UpdateNodeRequestedHandler extends EventHandler {

    void onUpdateNodeRequested(UpdateNodeRequestedEvent event);

}