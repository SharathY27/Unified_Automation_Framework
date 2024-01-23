package api_Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTestForApiAutomation;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class API_Tests extends BaseTestForApiAutomation {

	private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

	@Test
	public static void testGetUser() {
		// given
		int userId = 2;

		// when
		Response response = RestAssured.given().baseUri(BASE_URL)
				.config(RestAssured.config.sslConfig(new SSLConfig().relaxedHTTPSValidation())).when()
				.get("/users/" + userId);

		response.prettyPrint();
		assertEquals(response.getStatusCode(), 200);
		assertNotNull(response.getBody().jsonPath().getString("name"));
		if (response.getStatusCode() == 200) {
			api_logger.log(Status.PASS, "Status code is " + response.getStatusCode());
			api_logger.log(Status.PASS, " Response body ");
			api_logger.log(Status.PASS, response.getBody().asString());
		}
	}

}
