package stepsdefinition.ToolsAPI;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.RequestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckResponseWhenSendRequestSuccessfullySteps {
	private static final String jsonString = null;
	String url;
	String method;
	String newUrl;
	String category;
	int result;
	String available;
	HttpResponse<String> response;
	Map<String, String> headers = new HashMap<String, String>();
	@Given("I have header")
	public void i_have_header(DataTable headerTable) {
		List<Map<String, String>> originalHeaders = headerTable.asMaps(String.class, String.class);
		for (Map<String, String> header : originalHeaders) {
			String key = header.get("key");
			String value = header.get("value");
			headers.put(key,value);
		}
	}

	@Given("I have url and method")
	public void i_have_url_and_method(DataTable urlAndMethodTable) {
		List<Map<String, String>> originalUrlMethods = urlAndMethodTable.asMaps(String.class, String.class);
		url = originalUrlMethods.get(0).get("url");
		method = originalUrlMethods.get(0).get("method");
	}
	
	@Given("I have {string} and {string} of tools and {string} status")
	public void i_have_and_of_tools_and_status(String givenCategory, String givenResult, String givenAvailable) {
		category = givenCategory;
		result = Integer.parseInt(givenResult);
		available = givenAvailable;
//		url = "https://simple-tool-rental-api.glitch.me?category=" + givenCategory + "&results=" + givenResult
//				+ "&available=" + givenAvailable;
//		System.out.println(url);
		newUrl = url.replace("@category", givenCategory).replace("@results", givenResult).replace("@available", givenAvailable);
//		System.out.println(newUrl);
	}

	@When("send request with valid URL and method and params")
	public void send_request_with_valid_url_and_method_and_params() {
		RequestUtils req = new RequestUtils();
		response = req.sendRequest(newUrl, method, headers, "");
		System.out.println("abc"+response);
		
		//		HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
//		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("Accept-Encoding", "gzip,deflate,br")
//				.header("Accept", "application/json").GET().build();
//		try {
//			response = client.send(request, HttpResponse.BodyHandlers.ofString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	@Then("Api responds status code {string} and list of tools")
	public void api_responds_status_code_and_list_of_tools(String expectedStatusCode) {
		assertEquals(Integer.parseInt(expectedStatusCode), response.statusCode());
		int actualToolsNumber = 0;
		int actualValidIDNumber = 0;
		int actualValidCategoryNumber = 0;
		int actualStockNumber = 0;
		String responseJson = response.body();
		JSONParser parser = new JSONParser();
		try {
			Object responseObj = parser.parse(responseJson);
			if (responseObj instanceof JSONArray) {
				JSONArray responseArray = (JSONArray) responseObj;
				actualToolsNumber = responseArray.size();
				for (Object toolObj : responseArray) {
					JSONObject toolJsonObject = (JSONObject) toolObj;
					String cat = toolJsonObject.get("category").toString();
					if(cat.equals(category)) {
						actualValidCategoryNumber++;
					}
					String instock = toolJsonObject.get("inStock").toString();
					if(instock.equals(available)) {
						actualStockNumber++;
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertTrue(actualToolsNumber <= result);
		assertEquals(actualToolsNumber, actualValidIDNumber);
		assertEquals(actualValidCategoryNumber, actualToolsNumber);
		}
}