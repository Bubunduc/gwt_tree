package com.example.tree_rumyancev.client.request;

import java.sql.Timestamp;

import com.example.tree_rumyancev.client.dto.ServerStatusViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;

public class HealthRequest {
	
	public static void ping(final Node selectedNode,final PingCallback callback) {
		if (selectedNode == null) {
			Window.alert("Для посылки запроса выберите ноду");
			return;
		}

		StringBuilder url = new StringBuilder("http://");
		url.append(selectedNode.getIp());
		url.append(":" + selectedNode.getPort().toString());
		url.append("/health");
		
		RequestBuilder request = new RequestBuilder(RequestBuilder.GET, url.toString());

		try {
			request.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {

					String jsonText = response.getText();
					if (response.getStatusCode() == 0) {

						Timestamp time = new Timestamp(System.currentTimeMillis());
						Long serverId = null;
						String statusString = "N/A";
						
						final ServerStatusViewData viewData = new ServerStatusViewData(time, serverId, statusString);
						callback.onSuccess(viewData);
						
						
						return;
					}

					JSONValue jsonValue = JSONParser.parseStrict(jsonText);

					if (jsonValue.isObject() != null) {
						try {
							JSONObject jsonObject = jsonValue.isObject();
							String timeStr = jsonObject.get("timestamp").isString().stringValue();
							DateTimeFormat isoFormat = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

							Timestamp time = new Timestamp(isoFormat.parse(timeStr).getTime());
							Long serverId = (long) jsonObject.get("serverId").isNumber().doubleValue();
							String statusString = jsonObject.get("status").isString().stringValue();

							final ServerStatusViewData viewData = new ServerStatusViewData(time, serverId, statusString);
							callback.onSuccess(viewData);
							
						} catch (Exception e) {
							Window.alert("При чтении ответа произошла ошибка" + e.getMessage());
						}

					}

				}

				@Override
				public void onError(Request request, Throwable exception) {
					Window.alert(exception.getMessage());
				}
			});

		} catch (RequestException e) {
			Window.alert(e.getMessage());
		}
	}
	
}
