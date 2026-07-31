package com.example.tree_rumyancev.shared.exception;

import java.io.Serializable;

public class NodeValidationException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public NodeValidationException() {
	}

	public NodeValidationException(String message) {
		super(message);
	}
}