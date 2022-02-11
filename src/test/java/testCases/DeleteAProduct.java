package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
// Above import is for given,when,then
public class DeleteAProduct {

	@Test(priority=1)
     public void deleteAProducts() {
		
		/*given: all input details(base URI,Headers,Payload/Body,QueryParameters)
		when:  submit api requests(Http method,Endpoint/Resource)
		then:  validate response(status code, Headers, responseTime, Payload/Body)
		End point/ Resource :/delete .php
		 Body : {
    "id" : "12",
    
}
		*/
		
		
		Response response =
		given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type", "application/json; charset=UTF-8")
		.body(new File("src\\main\\java\\data\\DeletePayload.json"))
		.when()
		.delete("/delete.php")
		.then()
		.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actual Status Code: " + actualStatusCode);
		Assert.assertEquals(actualStatusCode, 200);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actual Header " + actualHeader);
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		
		// here  used asString. so we can data for response body
		String responseBody = response.getBody().asString();	
			System.out.println("Response Body: " + responseBody);
			
			JsonPath jp = new JsonPath(responseBody);
			
			
			// here we are working to assert for Response body
			String productMessage = jp.get("message");
			System.out.println("productMessage " + productMessage);
			Assert.assertEquals(productMessage, "Product was deleted.");
				
	}
	
	@Test(priority=2)
public void readAProducts() {
		
		Response response =
		given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type", "application/json;")
		.queryParam("id", "12")
	//	.auth().preemptive().basic("username", "password")
	//	.headers("Authorization","Bearer aasddffghjmk@#$^**&^%")
		.when()
		.get("/read_one.php")
		.then()
		.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actual Status Code: " + actualStatusCode);
		Assert.assertEquals(actualStatusCode,404);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actual Header " + actualHeader);
		Assert.assertEquals(actualHeader, "application/json");
		
		// here  used asString. so we can data for response body
             String responseBody = response.getBody().asString();		
			System.out.println("Response Body: " + responseBody);
			
			JsonPath jp = new JsonPath(responseBody);
						
			// here we are working to assert for message
			
			  String productMessage = jp.get("message");
			  System.out.println("productMessage " + productMessage); 
			  Assert.assertEquals(productMessage, "Product does not exist.");
			 		
	}
	
	
}

