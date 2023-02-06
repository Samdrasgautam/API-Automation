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

public class bearerTokenAuthentication {
	
	
	@Test
	public void BearerAuthentication() {
		
		String bearerToken= "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";
		
		given()
		.headers("Authorization", "Bearer " +bearerToken)
		
		.when()
		.get("https://api.github.com/user/repos")
		
		.then()
		.statusCode(200)
		.log().all();
	}
	

}
