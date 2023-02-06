package day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Authentication {
	
	@Test
	public void BasicAuthenticationUserNameAndPassword() {
		
		given()
		.auth().basic("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.body("authenticated", equalTo(true))
		.statusCode(200)
		.log().all();
	}
	
	@Test
	public void digestAuthenticationUserNameAndPassword() {
		
		given()
		.auth().digest("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.body("authenticated", equalTo(true))
		.statusCode(200)
		.log().all();
	}
	
	@Test
	public void preemptiveAuthenticationUserNameAndPassword() {
		
		given()
		.auth().preemptive().basic("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.body("authenticated", equalTo(true))
		.statusCode(200)
		.log().all();
	}
}
