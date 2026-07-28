package com.example.tree_rumyancev.client.handlers.selectedNode;

import com.example.tree_rumyancev.client.handlers.event.selectedNode.CreateRootEvent;
import com.google.gwt.event.shared.EventHandler;

public interface CreateRootEventHandler extends EventHandler {
	void onCreateRoot(CreateRootEvent event);
}
