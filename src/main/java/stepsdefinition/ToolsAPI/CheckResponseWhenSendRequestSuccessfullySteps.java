package stepsdefinition.ToolsAPI;

import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckResponseWhenSendRequestSuccessfullySteps {
	String url;
	HttpResponse<String> response;
	Map<String, String> headers = new HashMap<String, String>();
	@Given("I have header")
	public void i_have_header(DataTable dataTable) {
		List<Map<String, String>> originalHeaders = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> header : originalHeaders) {
			String key = header.get("key");
			String value = header.get("value");
			headers.put(key, value);
		}

	}

	@Given("I have {string} and {string} of tools and {string} status")
	public void i_have_and_of_tools_and_status(String givenCategory, String givenResult, String givenAvailable) {
		url = "https://simple-tool-rental-api.glitch.me?category=" + givenCategory + "&results=" + givenResult
				+ "&available=" + givenAvailable;

	}

	@When("send request with valid URL and method and params")
	public void sendRequest() {
//		if headers
//		HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
//		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("Accept-Encoding", "gzip,deflate,br")
//				.header("Accept", "application/json").GET().build();
//		try {
//			response = client.send(request, HttpResponse.BodyHandlers.ofString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Then("API responds status code {int} and list of tools")
	public void verifyResponse(String expectedStatusCode) {
		assertEquals(Integer.parseInt(expectedStatusCode), response.statusCode());
	}
}