package com.example.tree_rumyancev.client.handlers.actions;

import com.example.tree_rumyancev.client.handlers.event.actionRequest.CreateNodeRequestedEvent;
import com.google.gwt.event.shared.EventHandler;

public interface CreateNodeRequestedHandler extends EventHandler {

    void onCreateNodeRequested(CreateNodeRequestedEvent event);
}
