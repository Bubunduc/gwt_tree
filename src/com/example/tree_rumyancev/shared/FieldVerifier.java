package com.example.tree_rumyancev.shared;

import com.example.tree_rumyancev.shared.model.Node;

public class FieldVerifier {

	public static String validateNode(Node node) {

		if (!node.getIp()
				.matches("^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$")) {
			return "Некорректный IP-адрес";
		}

		if ((node.getPort() < 0)) {
			return "Некорректный порт";
		}

		return null;
	}
}
