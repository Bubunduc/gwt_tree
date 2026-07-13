package com.example.tree_rumyancev.client;

import com.example.tree_rumyancev.model.Node;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tree")
public interface TreeServiceAsync {
	void create(Node node,AsyncCallback<Node> callback);
	void read(Long id, AsyncCallback<Node> callback);
	void update(Node node,AsyncCallback<Void> callback);
	void delete(Long id, AsyncCallback<Void> callback);
}
