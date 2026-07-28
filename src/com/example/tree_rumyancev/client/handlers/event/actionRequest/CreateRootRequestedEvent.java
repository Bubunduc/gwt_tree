package com.example.tree_rumyancev.client.handlers.event.actionRequest;

import com.example.tree_rumyancev.client.handlers.actions.CreateRootRequestedHandler;
import com.google.gwt.event.shared.GwtEvent;

public class CreateRootRequestedEvent extends GwtEvent<CreateRootRequestedHandler> {

	public static final Type<CreateRootRequestedHandler> TYPE = new Type<CreateRootRequestedHandler>();

	@Override
	public Type<CreateRootRequestedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateRootRequestedHandler handler) {
		handler.onCreateRootRequested(this);
	}
}