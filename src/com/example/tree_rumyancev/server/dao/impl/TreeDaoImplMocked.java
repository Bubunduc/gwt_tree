package com.example.tree_rumyancev.server.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tree_rumyancev.server.dao.TreeDao;
import com.example.tree_rumyancev.shared.model.Node;

public class TreeDaoImplMocked implements TreeDao {
	private List<Node> nodes = new ArrayList<Node>();

	public TreeDaoImplMocked() {
		nodes.add(new Node(15L, null, "microservice-orders", "192.168.1.22", 9003));
		nodes.add(new Node(16L, null, "requests", "192.168.2.33", 9001));
		nodes.add(new Node(17L, 16L, "microservice-orders", "192.168.1.22", 9003));
		nodes.add(new Node(0L, null, "rootNode", "127.0.0.1", 1111));
		nodes.add(new Node(1L, 0L, "database-server", "192.168.1.10", 3306));
		nodes.add(new Node(2L, 0L, "web-server", "192.168.1.20", 8080));
		nodes.add(new Node(3L, 0L, "cache-server", "192.168.1.30", 6379));

		// Второй уровень (дочерние узлы database-server)
		nodes.add(new Node(4L, 1L, "db-replica-1", "192.168.1.11", 3307));
		nodes.add(new Node(5L, 1L, "db-replica-2", "192.168.1.12", 3308));
		nodes.add(new Node(6L, 1L, "db-backup", "192.168.1.13", 3309));

		// Второй уровень (дочерние узлы web-server)
		nodes.add(new Node(7L, 2L, "nginx-proxy", "192.168.1.21", 80));
		nodes.add(new Node(8L, 2L, "app-instance-1", "192.168.1.22", 8081));
		nodes.add(new Node(9L, 2L, "app-instance-2", "192.168.1.23", 8082));

		// Второй уровень (дочерние узлы cache-server)
		nodes.add(new Node(10L, 3L, "redis-sentinel-1", "192.168.1.31", 26379));
		nodes.add(new Node(11L, 3L, "redis-sentinel-2", "192.168.1.32", 26380));

		// Третий уровень (дочерние узлы app-instance-1)
		nodes.add(new Node(12L, 8L, "microservice-auth", "192.168.1.22", 9001));
		nodes.add(new Node(13L, 8L, "microservice-users", "192.168.1.22", 9002));
		nodes.add(new Node(14L, 8L, "microservice-orders", "192.168.1.22", 9003));

	}

	@Override
	public List<Node> findAll() {
		return nodes;
	}

	private List<Node> getChildrenList(Long parentId) {
		List<Node> childrenList = new ArrayList<Node>();
		List<Node> nodes = findAll();
		for (Node node : nodes) {
			if (parentId.equals(node.getParentId())) {
				childrenList.add(node);
			}
		}
		return childrenList;
	}

	@Override
	public void update(Node node) {
		for (Node i : nodes) {
			if (i.getId().equals(node.getId())) {
				nodes.set(nodes.indexOf(i), node);
				return;
			}
		}
	}

	@Override
	public void delete(Long id) {
		Node nodeToDelete = findById(id);

		if (nodeToDelete == null) {
			return;
		}

		List<Node> children = new ArrayList<Node>(getChildrenList(id));

		for (Node child : children) {
			delete(child.getId());
		}

		nodes.remove(nodeToDelete);
	}

	@Override
	public Node findById(Long id) {
		for (Node node : nodes) {
			if (node.getId().equals(id)) {
				return node;
			}
		}
		return null;

	}

	@Override
	public Node create(Node node) {
		List<Long> ids = nodes.stream().map(x -> x.getId()).collect(Collectors.toList());
		if (ids.isEmpty()) {
			throw new NullPointerException("Список не может быть пустым");
		}
		Long id = Collections.max(ids) + 1;
		node.setId(id);
		nodes.add(node);
		return node;
	}

}
