package io.windmill.windmill.services;

import javax.json.Json;
import javax.json.JsonObject;

public class Notification {
	
	public static class Messages {

		public static String of(String title, String body) {
		 JsonObject value = Json.createObjectBuilder()
			.add("aps", Json.createObjectBuilder()
				.add("alert", Json.createObjectBuilder()
						.add("title", title)
						.add("body", body)))
			.build();
			
			return value.toString();
		}
		
		public static String on(Platform platform, String message) {
			return platform.message(message);
		}
	}
	
	public static enum Platform {
		APNS,
		APNS_SANDBOX;

		String message(String message) {			
			 JsonObject value = Json.createObjectBuilder()
				.add(this.name(), message).build();
		
			return value.toString();
		}
	}
}