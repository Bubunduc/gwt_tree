package com.example.tree_rumyancev.client.handlers.event.actionRequest;

import com.example.tree_rumyancev.client.handlers.actions.DeleteNodeRequestedHandler;
import com.google.gwt.event.shared.GwtEvent;

public class DeleteNodeRequestedEvent extends GwtEvent<DeleteNodeRequestedHandler> {

	public static final Type<DeleteNodeRequestedHandler> TYPE = new Type<DeleteNodeRequestedHandler>();

	@Override
	public Type<DeleteNodeRequestedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DeleteNodeRequestedHandler handler) {
		handler.onDeleteNodeRequested(this);
	}
}