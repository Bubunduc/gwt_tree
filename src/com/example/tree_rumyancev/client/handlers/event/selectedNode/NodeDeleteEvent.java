package com.example.tree_rumyancev.client.handlers.event.selectedNode;

import com.example.tree_rumyancev.client.handlers.selectedNode.NodeDeleteEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class NodeDeleteEvent extends GwtEvent<NodeDeleteEventHandler> {

	public static final Type<NodeDeleteEventHandler> TYPE = new Type<NodeDeleteEventHandler>();

	private final Long id;

	public NodeDeleteEvent(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Override
	public Type<NodeDeleteEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NodeDeleteEventHandler handler) {
		handler.onDelete(this);
	}

}
