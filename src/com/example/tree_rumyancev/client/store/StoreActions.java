package com.example.tree_rumyancev.client.store;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;

public interface StoreActions extends TreeSelectionStore {

	void save(Node node);

	void remove(Long nodeId);

	void clear();

	List<Long> removeSubTrees(Long parentId);

}
