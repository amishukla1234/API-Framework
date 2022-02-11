package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
// Above import is for given,when,then
public class ReadAllProducts {

	@Test
     public void readAllProducts() {
		
		/*given: all input details(base URI,Headers,Payload/Body,QueryParameters)
		when:  submit api requests(Http method,Endpoint/Resource)
		then:  validate response(status code, Headers, responseTime, Payload/Body)
		End point/ Resource : /read.php*/
		
		
		Response response =
		given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type", "application/json; charset=UTF-8")
		.when()
		.get("/read.php")
		.then()
		.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actual Status Code: " + actualStatusCode);
		Assert.assertEquals(actualStatusCode, 200);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actual Header " + actualHeader);
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		
		// here  used asString. so we can data for response body
        String responseBody = response.asString();		
		System.out.println("Response Body: " + responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		// this will print response body as json
		System.out.println ("jp " +jp.prettyPrint());
		
	}
}
