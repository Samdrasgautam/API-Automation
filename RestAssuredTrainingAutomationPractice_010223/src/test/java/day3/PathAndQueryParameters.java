package day3;

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
public class PathAndQueryParameters {
	
	@Test()
	public void testPathAndQueryParameters() {

		given()
				// https://reqres.in/api/users?page=2&id=5
				.pathParam("Pathparam1", "user")
				.queryParam("page", 2)
				.queryParam("id", 5)

				.when()
				.get("https://reqres.in/api/{Pathparam1}")

				.then()
				.statusCode(200)
				.body("data.name", equalTo("tigerlily"))
				.body("support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
				.log().all();
	}

}
