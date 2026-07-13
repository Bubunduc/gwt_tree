package com.example.tree_rumyancev.server;

import com.example.tree_rumyancev.client.TreeService;
import com.example.tree_rumyancev.model.Node;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TreeServiceImpl extends RemoteServiceServlet implements TreeService {

	@Override
	public Node create(Node node) {
		Node newNode = new Node();
		newNode.setId(node.getId());
		newNode.setParentId(node.getParentId());
		newNode.setName(node.getName());
		newNode.setIp(node.getIp());
		newNode.setPort(node.getPort());
		return newNode;
	}

	@Override
	public Node read(Long id) {
		// TODO Auto-generated method stub
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

	
}
