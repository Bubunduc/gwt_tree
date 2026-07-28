package com.example.tree_rumyancev.client.handlers.actions;


import com.example.tree_rumyancev.client.handlers.event.actionRequest.DeleteNodeRequestedEvent;
import com.google.gwt.event.shared.EventHandler;

public interface DeleteNodeRequestedHandler extends EventHandler {

    void onDeleteNodeRequested(DeleteNodeRequestedEvent event);

}