package com.example.tree_rumyancev.client.service;

import java.util.List;

import com.example.tree_rumyancev.shared.exception.NodeValidationException;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tree")
public interface TreeService extends RemoteService {
	Node create(Node node) throws NodeValidationException; ;

	void update(Node node) throws NodeValidationException; ;

	void delete(Long id);

	List<Node> getChildrenList(Long parentId);

	List<Node> getAllData();

	List<Node> getParentList();

	Node findById(Long id);
}
