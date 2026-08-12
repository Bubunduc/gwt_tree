package com.example.tree_rumyancev.client.service;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tree")
public interface TreeServiceAsync {
	void create(Node node, AsyncCallback<Node> callback);

	void update(Node node, AsyncCallback<Void> callback);

	void delete(Long id, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<Node>> callback);

	void findById(Long id, AsyncCallback<Node> callback);
}
