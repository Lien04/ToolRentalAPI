package stepsdefinition.ToolsAPI;

import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckResponseWhenSendRequestSuccessfullySteps {
	String url;
	HttpResponse<String> response;
@When("send request with valid URL and method and params")
public void sendRequest() {
	url = "https://simple-tool-rental-api.glitch.me?category=ladders&results=2&available=1";
	HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
	HttpRequest request =  HttpRequest.newBuilder(URI.create(url)).header("Accept-Encoding","gzip,deflate,br").header("Accept","application/json").GET().build();
	try {
		response = client.send(request, HttpResponse.BodyHandlers.ofString());
	} catch (Exception e) {
		e.printStackTrace();
	}
}

@Then("API responds status code {int} and list of tools")
public void verifyResponse(int expectedStatusCode) {
	assertEquals(expectedStatusCode, response.statusCode());
}
}