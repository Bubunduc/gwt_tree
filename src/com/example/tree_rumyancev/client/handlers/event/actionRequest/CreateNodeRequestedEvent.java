package com.example.tree_rumyancev.client.handlers.event.actionRequest;

import com.example.tree_rumyancev.client.handlers.actions.CreateNodeRequestedHandler;
import com.google.gwt.event.shared.GwtEvent;

public class CreateNodeRequestedEvent extends GwtEvent<CreateNodeRequestedHandler> {

	public static final Type<CreateNodeRequestedHandler> TYPE = new Type<CreateNodeRequestedHandler>();

	@Override
	public Type<CreateNodeRequestedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateNodeRequestedHandler handler) {
		handler.onCreateNodeRequested(this);
	}
}