package com.example.tree_rumyancev.client.handlers.event;

import com.example.tree_rumyancev.client.handlers.selectedNode.CreateRootEventHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.NodeDeleteEventHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class CreateRootEvent extends GwtEvent<CreateRootEventHandler> {

	public static final Type<CreateRootEventHandler> TYPE = new Type<CreateRootEventHandler>();

	private final Node node;

	public CreateRootEvent(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

	@Override
	public Type<CreateRootEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateRootEventHandler handler) {
		handler.onCreateRoot(this);
	}

}
