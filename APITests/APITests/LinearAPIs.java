package APITests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class LinearAPIs {

	@Test
	public void Rest_Basic()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification restRequest = RestAssured.given();
		
		Response responseData = restRequest.get("/posts");
		
		System.out.println(responseData.asString());
	}
	
	@Test
	public void Rest_Get()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification restRequest = RestAssured.given();
		
		Response response = restRequest.request(Method.GET, "/posts");
		int responseCode = response.getStatusCode();
		
		System.out.println(responseCode);
		
		Assert.assertEquals(200, responseCode);
	}
	
	@Test
	public void Rest_GetWithValidation()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		// Validate
		RestAssured.given().
					  when().get("/posts").
					  then().statusCode(200);
		
		// Print
		
		System.out.println(
				RestAssured.given().
					get("/posts").
					getBody().asString());
	}
	
	@Test
	public void Rest_GetWithMatcher_hasItems()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		// Validate
		RestAssured.given().
					  when().get("/posts?id=91").
					  then().body("id", hasItems(91));
		
		// Validate
				RestAssured.given().
							  when().get("/posts?id=91").
							  then().body("title", hasItems("aut amet sed"));
	}
	
	@Test
	public void Rest_GetWithMatcher_containsString()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		// Validate
		RestAssured.given().
		    		when().get("/posts?id=91").
					then().body(containsString("similique fugit nam"));
	}
	
	@Test
	public void Rest_GetWithMatcher_equalTo()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		// Validate
		RestAssured.given().
		    		when().get("/posts?id=91").
					then().body("title", equalTo("[aut amet sed]"));
	}
}
