package com.example.tree_rumyancev.client.handlers.selectedNode;
import com.example.tree_rumyancev.client.handlers.event.selectedNode.CreateNodeEvent;
import com.google.gwt.event.shared.EventHandler;

public interface CreateNodeEventHandler extends EventHandler {
	void onCreateNode (CreateNodeEvent event);
}
