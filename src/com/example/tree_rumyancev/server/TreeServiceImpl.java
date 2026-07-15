package com.example.tree_rumyancev.server;
import java.util.ArrayList;
import java.util.List;
import com.example.tree_rumyancev.client.TreeService;
import com.example.tree_rumyancev.server.dao.TreeDao;
import com.example.tree_rumyancev.server.dao.impl.TreeDaoImlMocked;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TreeServiceImpl extends RemoteServiceServlet implements TreeService {
	
	private TreeDao dao;
	public TreeServiceImpl() {
		this.dao = new TreeDaoImlMocked();
	}
	@Override
	public Node create(Node node) {
		Node newNode = new Node(node.getId(),
				node.getParentId(),
				node.getName(),
				node.getIp(),
				node.getPort()
				);
		return newNode;
	}

	@Override
	public Node read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Node node) {
		if (node.getPort().toString().length() != 4)
		{
			throw new IllegalArgumentException("Port must be exactly 4 digits");
		}
		if (node.getIp().matches("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b")) {
			throw new IllegalArgumentException("incorrect ip address");
		}
		dao.update(node);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isparent(Long childId,Long parentId) {
		if (childId.equals(parentId)){
			return true;
		}
		return false;
	}
	@Override
	public List<Node> getChildrenList(Long parentId)
	{
		
		return dao.getChildrenList(parentId);
	}
	@Override
	public Node getRootNode() 
	{
		return dao.getRootNode();
	}
	@Override
	public List<Node> getAllData() {
		return dao.getAllData();
	}
}
