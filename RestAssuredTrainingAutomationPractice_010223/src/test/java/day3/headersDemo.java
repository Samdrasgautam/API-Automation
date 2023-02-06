package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class headersDemo {
	
	@Test()
	public void testHeader() {
		
		
		//Response res= 
				given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.header("X-Frame-Options", "SAMEORIGIN");
		
		//System.out.println(res.headers());
		//System.out.println(res.header("X-Frame-Options"));
		//Assert.assertEquals(res.header("X-Frame-Options"), "SAMEORIGIN");
	}
	
	@Test()
	public void getHeaders() {
		
		
		Response res= given()
		
		.when()
		.get("https://www.google.com/");
		
		Headers myheaders= res.getHeaders();
		
		for(Header hd: myheaders)
		{
			System.out.println(hd.getName() +"------------->>>>>" +hd.getValue());
		}
	}

}
