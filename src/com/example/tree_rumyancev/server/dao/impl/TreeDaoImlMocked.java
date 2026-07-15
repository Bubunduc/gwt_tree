package com.example.tree_rumyancev.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.tree_rumyancev.server.dao.TreeDao;
import com.example.tree_rumyancev.shared.model.Node;

public class TreeDaoImlMocked implements TreeDao {
	@Override
	public Node getRootNode() 
	{
		return new Node(
	            0L,           // id
	            null,         // parentId (корневой узел не имеет родителя)
	            "rootNode",   // name
	            "127.0.0.1",  // ip
	            (short) 1111  // port
	        );
	}
	@Override
	public List<Node> getAllData() 
	{
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(getRootNode());
	    nodes.add(new Node(1L, 0L, "database-server", "192.168.1.10", (short) 3306));
        nodes.add(new Node(2L, 0L, "web-server", "192.168.1.20", (short) 8080));
        nodes.add(new Node(3L, 0L, "cache-server", "192.168.1.30", (short) 6379));
        
        // Второй уровень (дочерние узлы database-server)
        nodes.add(new Node(4L, 1L, "db-replica-1", "192.168.1.11", (short) 3307));
        nodes.add(new Node(5L, 1L, "db-replica-2", "192.168.1.12", (short) 3308));
        nodes.add(new Node(6L, 1L, "db-backup", "192.168.1.13", (short) 3309));
        
        // Второй уровень (дочерние узлы web-server)
        nodes.add(new Node(7L, 2L, "nginx-proxy", "192.168.1.21", (short) 80));
        nodes.add(new Node(8L, 2L, "app-instance-1", "192.168.1.22", (short) 8081));
        nodes.add(new Node(9L, 2L, "app-instance-2", "192.168.1.23", (short) 8082));
        
        // Второй уровень (дочерние узлы cache-server)
        nodes.add(new Node(10L, 3L, "redis-sentinel-1", "192.168.1.31", (short) 26379));
        nodes.add(new Node(11L, 3L, "redis-sentinel-2", "192.168.1.32", (short) 26380));
        
        // Третий уровень (дочерние узлы app-instance-1)
        nodes.add(new Node(12L, 8L, "microservice-auth", "192.168.1.22", (short) 9001));
        nodes.add(new Node(13L, 8L, "microservice-users", "192.168.1.22", (short) 9002));
        nodes.add(new Node(14L, 8L, "microservice-orders", "192.168.1.22", (short) 9003));
        
        return nodes;
	}
	@Override
	public List<Node> getChildrenList(Long parentId)
	{
		List<Node> childrenList = new ArrayList<Node>();
		List<Node> nodes = getAllData();
		for (Node node : nodes) 
		{
			if (parentId.equals(node.getParentId()))
			{
				childrenList.add(node);
			}
		}
		return childrenList;
	}
}
