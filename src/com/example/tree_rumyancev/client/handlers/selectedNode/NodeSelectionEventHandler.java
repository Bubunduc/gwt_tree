package com.example.tree_rumyancev.client.handlers.selectedNode;

import com.example.tree_rumyancev.client.handlers.event.NodeSelectionEvent;
import com.google.gwt.event.shared.EventHandler;

public interface NodeSelectionEventHandler extends EventHandler {
	
	void onNodeSelected(NodeSelectionEvent event);

}
