package common;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Request {
	String url;
	HttpMethod method;
	Map<String, String> headers = new HashMap<String, String>();
	HttpResponse<String> response;

	public Request(String url, HttpMethod method, Map<String, String> header) {
		this.url = url;
		this.method = method;
		this.headers = header;
	}

	public HttpResponse<String> sendGetRequest() {
		HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
		HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(url)).GET();
		// Add headers using helper to keep header logic in one place
		requestBuilder = addHeader(requestBuilder);
		HttpRequest request = requestBuilder.build();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.response;
	}

	public HttpResponse<String> sendGetRequest() {
		HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
		HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(url)).GET();
		headers.forEach(requestBuilder::header);
		HttpRequest request = requestBuilder.build();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.response;
	}
}
