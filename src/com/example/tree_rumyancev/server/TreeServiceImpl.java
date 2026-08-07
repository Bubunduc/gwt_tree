package com.example.tree_rumyancev.server;

import java.util.List;

import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.server.dao.TreeDao;
import com.example.tree_rumyancev.server.dao.impl.TreeDaoImplMocked;
import com.example.tree_rumyancev.shared.FieldVerifier;
import com.example.tree_rumyancev.shared.exception.NodeValidationException;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TreeServiceImpl extends RemoteServiceServlet implements TreeService {

	private TreeDao dao;

	public TreeServiceImpl() {
		this.dao = new TreeDaoImplMocked();
	}

	@Override
	public Node create(Node node) throws NodeValidationException {
		String error = FieldVerifier.validateNode(node);
		if (error != null) {
			throw new NodeValidationException(error);
		}
		Node createdNode = dao.create(node);

		return createdNode;

	}

	@Override
	public void update(Node node) throws NodeValidationException {
		String error = FieldVerifier.validateNode(node);
		if (error != null) {
			throw new NodeValidationException(error);
		}
		dao.update(node);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);

	}

	@Override
	public List<Node> getChildrenList(Long parentId) {
		return dao.getChildrenList(parentId);
	}

	@Override
	public List<Node> getAllData() {
		return dao.getAllData();
	}

	@Override
	public List<Node> getParentList() {
		return dao.getParentList();
	}

	@Override
	public Node findById(Long id) {
		return dao.findById(id);
	}
}
