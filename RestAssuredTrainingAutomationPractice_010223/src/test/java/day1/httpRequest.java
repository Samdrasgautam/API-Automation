package day1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;


public class httpRequest {
	
	int id;
	
	@Test(priority = 1)
	void getUsers() {
		
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		
		.then()
		
		.statusCode(200)
		.log().all();
		
		System.out.println("**********************------------------------------------------************************************");
	}
	
	@Test(priority = 2)
	void createUser() {
		
		HashMap data= new HashMap();
		data.put("name", "Samdras");
		data.put("job", "QA");
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		
		.then()
		
		.statusCode(201)
		.log().all();
		
		System.out.println("**********************------------------------------------------************************************");
	}
	
	@Test(priority = 3)
	void updateUser() {
		
		HashMap data= new HashMap();
		data.put("name", "Samdras");
		data.put("job", "Trainee");
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.put("https://reqres.in/api/users/63")
		
		.then()
		
		.statusCode(200)
		.log().all();
		
		System.out.println("**********************------------------------------------------************************************");
	}
	
	@Test(priority = 4)
	void deleteUser() {
		given()
		
		.when()
		.delete("https://reqres.in/api/users/63")
		
		.then()
		
		.statusCode(204)
		.log().all();
		
		System.out.println("**********************------------------------------------------************************************");
	}
	
	
	@Test(priority = 5)
	void createUserWithChaining() {
		
		HashMap data= new HashMap();
		data.put("name", "Samdras");
		data.put("job", "QA");
		
		
		id= given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		System.out.println(id);
		
		//.then()
		
		System.out.println("**********************------------------------------------------************************************");
}
	
	@Test(priority = 6)
	void updateUserWithChaining() {
		
		HashMap data= new HashMap();
		data.put("name", "Samdras");
		data.put("job", "WER");
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.put("https://reqres.in/api/users/"+id+"")
		
		.then()
		
		.statusCode(200)
		.log().all();
		
		
		System.out.println("**********************------------------------------------------************************************");
	}
	
	@Test(priority = 7)
	void deleteUserWithChaining() {
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id+"")
		
		.then()
		
		.statusCode(204)
		.log().all();
		
		System.out.println("**********************------------------------------------------************************************");
	}	
}
