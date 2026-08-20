package com.example.tree_rumyancev.shared;

import com.example.tree_rumyancev.shared.model.Node;

public class FieldVerifier {

	public static String validateNode(Node node) {

		if (node == null) {
			return "Нода не может быть null";
		}

		if ((node.getIp() == null) || (node.getName() == null) || node.getPort() == null) {
			return "Поля не могут быть пустыми";
		}

		if (!node.getIp()
				.matches("^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$")) {
			return "Некорректный IP-адрес";
		}

		if ((node.getPort() < 0)) {
			return "Порт не может быть отрицательным";
		}
		if(node.getPort() > 65535) {
			return "Порт не может быть больше 65535";
		}

		return null;
	}
}
