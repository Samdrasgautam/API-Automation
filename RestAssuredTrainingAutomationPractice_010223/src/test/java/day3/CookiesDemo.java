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
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class CookiesDemo {
	
	
	
	@Test(priority = 1)
	public void getcookie() {
		
		
		Response res= given()
		
		.when()
		.get("https://www.google.com/");
		
		String cookie_value= res.getCookie("AEC");
		Map<String, String> cookies_value= res.getCookies(); 
		System.out.println(cookies_value);
		
		for(String i : cookies_value.keySet())
		{
			String cookie_value1=res.getCookie(i);
			System.out.println(i+"                        "+cookie_value1);
		}
	}

}
