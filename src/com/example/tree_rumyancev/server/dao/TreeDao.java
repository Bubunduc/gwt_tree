package com.example.tree_rumyancev.server.dao;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;

public interface TreeDao {
	
	public Node getRootNode();
	
	public List<Node> getAllData();
	
	public List<Node> getChildrenList(Long parentId);
	
	public void update(Node node);
	
	public void delete(Long id);
	
	public void findById(Long id);
	
	
	
}
