package com.example.tree_rumyancev.client.request;

import com.example.tree_rumyancev.client.dto.ServerStatusViewData;

public interface PingCallback {
	void onSuccess(ServerStatusViewData data);
	void onFailure(String message);
}
