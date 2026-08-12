package com.example.tree_rumyancev.client.store;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public Node getSelectedNode() {
		return nodes.get(selectedNodeId);
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
		if (id == null) {
			return false;
		}

		for (Node node : nodes.values()) {
			if (id.equals(node.getParentId())) {
				return true;
			}
		}

		return false;
	}

	public List<Node> getRoots() {
		List<Node> roots = new ArrayList<Node>();

		for (Node node : nodes.values()) {
			if (node.getParentId() == null) {
				roots.add(node);
			}
		}

		return roots;
	}

	public List<Node> getChildrenList(Long parentId) {
		List<Node> childrenList = new ArrayList<Node>();

		for (Node node : nodes.values()) {
			if (parentId.equals(node.getParentId())) {
				childrenList.add(node);
			}
		}
		return childrenList;
	}

	public List<Long> getHierarchyIdList(Long id) {

		List<Long> idList = new ArrayList<Long>();

		if (id == null) {
			return idList;
		}

		Long upperId = id;
		while (upperId != null) {
			idList.add(upperId);

			Node node = nodes.get(upperId);

			if (node == null) {
				break;
			}

			upperId = node.getParentId();
		}
		Collections.reverse(idList);
		idList.remove(id);
		return idList;
	}

}
