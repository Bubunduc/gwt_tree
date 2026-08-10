package com.example.tree_rumyancev.client.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.shared.model.Node;

public class NodeStore {

	private final Map<Long, Node> nodes = new HashMap<Long, Node>();

	private Long selectedNodeId;

	public void save(Node node) {
		nodes.put(node.getId(), node);
	}

	public Node get(Long nodeId) {
		return nodes.get(nodeId);
	}

	public void remove(Long nodeId) {
		nodes.remove(nodeId);
	}

	public void setSelectedNodeId(Long nodeId) {
		selectedNodeId = nodeId;
	}

	public Long getSelectedNodeId() {
		return selectedNodeId;
	}

	public void clearSelection() {
		selectedNodeId = null;
	}

	public void clear() {
		nodes.clear();
		selectedNodeId = null;
	}

	public List<Long> getDirectChildIds(Long parentId) {
		List<Long> directChildIds = new ArrayList<Long>();

		for (Node node : nodes.values()) {

			if (parentId == null) {
				if (node.getParentId() == null) {
					directChildIds.add(node.getId());
				}
			} else if (parentId.equals(node.getParentId())) {
				directChildIds.add(node.getId());
			}
		}

		return directChildIds;
	}

	public List<Long> removeSubTrees(Long parentId) {
		List<Long> removedIds = new ArrayList<>();
		List<Long> directChildIds = getDirectChildIds(parentId);

		for (Long childId : directChildIds) {
			removedIds.addAll(removeSubTrees(childId));

			nodes.remove(childId);

			removedIds.add(childId);
		}

		return removedIds;
	}

	public List<Node> getValuesList() {
		return new ArrayList<Node>(nodes.values());
	}
	
	public boolean hasChild(Long id) {
		
		Node node = nodes.get(id);
		for(Node i : nodes.values()) {
			if(i.getParentId().equals(node.getId())) {
				return true;
			}
		}
		return false;
	}

}
