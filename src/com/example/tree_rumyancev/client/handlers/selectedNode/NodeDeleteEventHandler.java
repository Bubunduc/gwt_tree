package com.example.tree_rumyancev.client.handlers.selectedNode;

import com.example.tree_rumyancev.client.handlers.event.NodeDeleteEvent;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.shared.EventHandler;

public interface NodeDeleteEventHandler extends EventHandler {
	
	void onDelete(NodeDeleteEvent event);

}
