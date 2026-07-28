package com.example.tree_rumyancev.client.handlers.selectedNode;

import com.example.tree_rumyancev.client.handlers.event.selectedNode.UpdateNodeEvent;
import com.google.gwt.event.shared.EventHandler;

public interface UpdateNodeEventHandler extends EventHandler {
	void onUpdate(UpdateNodeEvent event);
}
