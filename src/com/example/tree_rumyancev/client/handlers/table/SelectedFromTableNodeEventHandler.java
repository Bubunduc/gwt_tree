package com.example.tree_rumyancev.client.handlers.table;

import com.example.tree_rumyancev.client.handlers.event.table.SelectedFromTableNodeEvent;
import com.google.gwt.event.shared.EventHandler;

public interface SelectedFromTableNodeEventHandler extends EventHandler {

	void onSelected(SelectedFromTableNodeEvent event);
}
