package com.example.tree_rumyancev.client.handlers.event.table;

import com.example.tree_rumyancev.client.handlers.table.SelectedFromTableNodeEventHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.shared.GwtEvent;

public class SelectedFromTableNodeEvent extends GwtEvent<SelectedFromTableNodeEventHandler> {

	public static final Type<SelectedFromTableNodeEventHandler> TYPE = new Type<SelectedFromTableNodeEventHandler>();

	private final Node node;

	public SelectedFromTableNodeEvent(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

	@Override
	public Type<SelectedFromTableNodeEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SelectedFromTableNodeEventHandler handler) {
		handler.onSelected(this);
	}

}