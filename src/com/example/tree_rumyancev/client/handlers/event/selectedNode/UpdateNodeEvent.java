package com.example.tree_rumyancev.client.handlers.event.selectedNode;

import com.example.tree_rumyancev.client.handlers.selectedNode.UpdateNodeEventHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.shared.GwtEvent;

public class UpdateNodeEvent extends GwtEvent<UpdateNodeEventHandler> {

	public static final Type<UpdateNodeEventHandler> TYPE = new Type<UpdateNodeEventHandler>();

	private final Node node;

	public UpdateNodeEvent(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

	@Override
	public Type<UpdateNodeEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateNodeEventHandler handler) {
		handler.onUpdate(this);
	}

}
