package com.example.tree_rumyancev.server.dao;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;

public interface TreeDao {

	List<Node> getAllData();

	Node create(Node node);

	void update(Node node);

	void delete(Long id);

	Node findById(Long id);

}
