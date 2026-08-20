package com.example.tree_rumyancev.client.dto;

import java.util.List;

public class DeletedNodeData {

	Long parentId;

	List<Long> removedIds;

	public DeletedNodeData(Long parentId, List<Long> removedIds) {
		super();
		this.parentId = parentId;
		this.removedIds = removedIds;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<Long> getRemovedIds() {
		return removedIds;
	}

	public void setRemovedIds(List<Long> removedIds) {
		this.removedIds = removedIds;
	}

}
