package com.example.tree_rumyancev.client.ServerStatus;

import com.example.tree_rumyancev.shared.dto.ServerStatusViewData;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ServerStatusView implements ServerStatusDisplay {
	private FlowPanel statusPanel;
	private FlexTable statusTable;
	
	public ServerStatusView() {
		initTable();
	}
	private void initTable() {
		statusPanel = new FlowPanel();
		statusTable = new FlexTable();
		
		loadHeaders();
		
		statusPanel.add(statusTable);
		
		statusPanel.setStyleName("SelectedNodePanel");
	}
	private void loadHeaders() {
		statusTable.setWidget(0, 0, new Label("Timestamp"));
		statusTable.setWidget(1, 0, new Label("Server id"));
		statusTable.setWidget(2, 0, new Label("Status"));
	}
	
	@Override
	public void showData(ServerStatusViewData viewData) {
		statusTable.clear();
		loadHeaders();
		if (viewData == null) {
			return;
		}
		statusTable.setWidget(0, 1, new Label(viewData.getTime().toString()));
		if(viewData.getSereverId() == null) {
			statusTable.setWidget(1, 1, new Label("N/A"));
		}
		else {
			statusTable.setWidget(1, 1, new Label(viewData.getSereverId().toString()));
		}
		statusTable.setWidget(2, 1, new Label(viewData.getStatus()));
		
	}
	@Override
	public Widget asWidget() {
		return statusPanel;
	}
	

}
