package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
// Above import is for given,when,then
public class ReadAProduct {

	@Test
     public void readAProducts() {
		
		/*given: all input details(base URI,Headers,Payload/Body,QueryParameters)
		when:  submit api requests(Http method,Endpoint/Resource)
		then:  validate response(status code, Headers, responseTime, Payload/Body)
		End point/ Resource :/read_one.php?id=3283
		*/
		
		
		Response response =
		given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type", "application/json;")
		.queryParam("id", "274")
		//.queryParam("name", "pen")
	//	.auth().preemptive().basic("username", "password")
	//	.headers("Authorization","Bearer aasddffghjmk@#$^**&^%")
		.when()
		.get("/read_one.php")
		.then()
		.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actual Status Code: " + actualStatusCode);
		Assert.assertEquals(actualStatusCode, 200);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actual Header " + actualHeader);
		Assert.assertEquals(actualHeader, "application/json");
		
		// here  used asString. so we can data for response body
             String responseBody = response.getBody().asString();		
			System.out.println("Response Body: " + responseBody);
			
			JsonPath jp = new JsonPath(responseBody);
			// this will print response body as json
			System.out.println ("jp " +jp.prettyPrint());
			
			// here we are working to assert for id
			
			  String productId = jp.get("id");
			  System.out.println("productId " + productId); 
			  Assert.assertEquals(productId, "274");
			 
			// here we asserting price
			
			  String productPrice = jp.get("price"); 
			  System.out.println("productPrice  " + productPrice );
			  Assert.assertEquals(productPrice , "99");
			 
		
	}
}
