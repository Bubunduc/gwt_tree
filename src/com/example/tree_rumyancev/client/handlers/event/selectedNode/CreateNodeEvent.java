package com.example.tree_rumyancev.client.handlers.event.selectedNode;

import com.example.tree_rumyancev.client.handlers.selectedNode.CreateNodeEventHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.shared.GwtEvent;

public class CreateNodeEvent extends GwtEvent<CreateNodeEventHandler> {

	public static final Type<CreateNodeEventHandler> TYPE = new Type<CreateNodeEventHandler>();

	private final Node node;

	public CreateNodeEvent(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

	@Override
	public Type<CreateNodeEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateNodeEventHandler handler) {
		handler.onCreateNode(this);
	}

}