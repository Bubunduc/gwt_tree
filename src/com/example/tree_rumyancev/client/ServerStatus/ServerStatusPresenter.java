package com.example.tree_rumyancev.client.ServerStatus;

import java.util.HashMap;
import java.util.Map;

import com.example.tree_rumyancev.shared.dto.ServerStatusViewData;

public class ServerStatusPresenter {

	private final ServerStatusDisplay view;

	private final Map<Long, ServerStatusViewData> responses;

	public ServerStatusPresenter(ServerStatusDisplay view) {
		responses = new HashMap<Long, ServerStatusViewData>();
		this.view = view;
	}

	public void setData(Long id, ServerStatusViewData data) {

		if (id == null || data == null) {
			return;
		}
		responses.put(id, data);

		view.showData(data);

	}

	public void showData(Long id) {
		if (id == null) {
			return;
		}
		view.showData(responses.get(id));
	}
}
