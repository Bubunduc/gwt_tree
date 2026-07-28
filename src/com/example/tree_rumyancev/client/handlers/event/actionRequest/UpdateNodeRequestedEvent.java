package com.example.tree_rumyancev.client.handlers.event.actionRequest;

import com.example.tree_rumyancev.client.handlers.actions.UpdateNodeRequestedHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UpdateNodeRequestedEvent extends GwtEvent<UpdateNodeRequestedHandler> {

	public static final Type<UpdateNodeRequestedHandler> TYPE = new Type<UpdateNodeRequestedHandler>();

	@Override
	public Type<UpdateNodeRequestedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateNodeRequestedHandler handler) {
		handler.onUpdateNodeRequested(this);
	}
}