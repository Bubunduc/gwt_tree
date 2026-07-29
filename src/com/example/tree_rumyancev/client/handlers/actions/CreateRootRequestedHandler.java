package com.example.tree_rumyancev.client.handlers.actions;

import com.example.tree_rumyancev.client.handlers.event.actionRequest.CreateRootRequestedEvent;
import com.google.gwt.event.shared.EventHandler;

public interface CreateRootRequestedHandler extends EventHandler {

	void onCreateRootRequested(CreateRootRequestedEvent event);

}