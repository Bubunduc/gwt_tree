package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tree")
public interface TreeService  extends RemoteService {
	Node create(Node node);
	Node read(Node id);
	void update(Node node);
	void delete(Node id);
	boolean isparent(Node childId, Node parentId);
	public List<Node> getChildrenList(Node parentId);
	public Node getRootNode();
	public List<Node> getAllData();
}
