package com.example.tree_rumyancev.server.dao.impl;

import java.util.List;

import com.example.tree_rumyancev.server.dao.TreeDao;
import com.example.tree_rumyancev.shared.model.Node;

public class TreeDaoImpl  implements TreeDao{
	@Override
	public Node getRootNode()
	{
		return null;
	}
	
	@Override
	public List<Node> getAllData()
	{
	return null;	
	}
	@Override
	public List<Node> getChildrenList(Long parentId)
	{
	return null;	
	}

	@Override
	public void update(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
