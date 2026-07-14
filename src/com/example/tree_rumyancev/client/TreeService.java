package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tree")
public interface TreeService  extends RemoteService {
	Node create(Node node);
	Node read(Long id);
	void update(Node node);
	void delete(Long id);
	boolean isparent(Long childId, Long parentId);
	public List<Node> getChildrenList(Long parentId);
	public Node getRootNode();
}
