package com.example.tree_rumyancev.client.ServerStatus;

import com.example.tree_rumyancev.client.dto.ServerStatusViewData;
import com.google.gwt.user.client.ui.Widget;

public interface ServerStatusDisplay {
	
	Widget asWidget();
	
	void showData(ServerStatusViewData viewData);
	
}
