package com.example.tree_rumyancev.client.store;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;

public interface TreeSelectionStore {
	
	Node get(Long nodeId);
	
	List<Node> getRoots();
	
	void setSelectedNodeId(Long nodeId);
	
	Long getSelectedNodeId();
	
	Node getSelectedNode();
	
	void clearSelection();
	
	List<Node> getChildrenList(Long parentId);
	
	List<Long> getHierarchyIdList(Long id);
	
	boolean hasChild(Long id);
}
