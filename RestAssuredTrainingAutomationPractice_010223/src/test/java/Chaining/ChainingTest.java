package Chaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.errorprone.annotations.Var;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ChainingTest {
	
	String bearerToken = "011ba27046712323ce586694243f43d59e4ffc64cb94fd3abaafe4326627bdce";
	int id=0;
	String email="";
	String name="";
	
	String random1 = ""+ (int)(Math.random()*(400-200+1)+200);
	String random2 = ""+ (int)(Math.random()*(800-600+1)+600);
	String baseUrl="https://gorest.co.in";
	String pathParameter="/public/v2/users/";
	String ids;
	int recoardfoundindex;
	
	@Test(priority = 1)
	public void createUser() {
		
		System.out.println("--------------Create User-Start-----------------------");
		email="Sam"+random1+"@gmail.com";
		name= "Samdras"+random1;
		
		HashMap data= new HashMap();
		data.put("name", name);
		data.put("gender", "Male");
		data.put("email", email);
		data.put("status", "inactive");
		
		
		Response res=
				given()
		.headers("Authorization", "Bearer " +bearerToken)
		.contentType("application/json")
		.body(data)
		
		.when()
		.post(baseUrl+pathParameter);
		
		System.out.println(res.asPrettyString());
		id= res.jsonPath().get("id");
		
		System.out.println("--------------Create User End-----------------------");
	}
	
	@Test(priority = 2)
	public void getUserDetails() {
		
		System.out.println("--------------Get-User-Start-----------------------");
		Response getResponse=
				given()
					.headers("Authorization", "Bearer " +bearerToken)
		
		
				.when()
					.get(baseUrl+pathParameter+id);
		
		System.out.println(getResponse.asPrettyString());
		
		if (getResponse.jsonPath().get("email").toString().equalsIgnoreCase(email)
				& getResponse.jsonPath().get("id").toString().equalsIgnoreCase(Integer.toString(id))
				& getResponse.jsonPath().get("name").toString().equalsIgnoreCase(name)) {
			System.out.println("------------>>>>Email, Name & id is Matched------------>>>");
		}
		else {
			System.out.println("------------>>>>Email, Name & id is Not Matched------------>>>");
		}
		System.out.println("--------------Get-User-End-----------------------");
	}
	
	@Test(priority = 3)
	public void updateUserDetails() {
		
		System.out.println("--------------Update-User-Details-Start-----------------------");
		email="Sam"+random2+"@gmail.com";
		name= "Samdras"+random2;
		
		HashMap<String, String> data1= new HashMap();
		data1.put("name", name);
		data1.put("email", email);
		data1.put("status", "active");
		
		Response getResponse=
				given()
					.headers("Authorization", "Bearer " +bearerToken)
					.contentType("application/json")
					.body(data1)
				
				.when()
					.patch(baseUrl+pathParameter+id);
		
		System.out.println(getResponse.asPrettyString());
		
		if (getResponse.jsonPath().get("email").toString().equalsIgnoreCase(email)
				& getResponse.jsonPath().get("id").toString().equalsIgnoreCase(Integer.toString(id))
				& getResponse.jsonPath().get("name").toString().equalsIgnoreCase(name)) {
			System.out.println("------------>>>>Email, Name & id is Matched------------>>>");
		}
		else {
			System.out.println("------------>>>>Email, Name & id is Not Matched------------>>>");
		}
		System.out.println("--------------Update-User-Details-End-----------------------");
	}
	
	@Test(priority = 4)
	public void deleteUserDetails() {
		
		System.out.println("--------------Delete-User-Start-----------------------");
		
				given()
					.headers("Authorization", "Bearer " +bearerToken)
		
				.when()
					.delete(baseUrl+pathParameter+id)
					
				.then()
				.statusCode(204);
		
		
		System.out.println("--------------Delete-User-End-----------------------" + "\n" +"\n" + "\n");
		
	}
	
	@Test(priority = 5)
	public void FindtheUserAndTheUserIndex() {

		System.out.println("--------------Find-User-Start-----------------------");
		Response res = 
				given().headers("Authorization", "Bearer " + bearerToken)

				.when().get(baseUrl + pathParameter);
		JSONArray jo = new JSONArray(res.asString());
		for (int z = 0; z < jo.length(); z++) {
			if (jo.getJSONObject(z).get("id").toString().equalsIgnoreCase("273681")) {
				recoardfoundindex = z;
				System.out.println("Record Match at index---->>>>" +recoardfoundindex);
			}
		}
		System.out.println("Infotmation of index "+recoardfoundindex+"\n" +jo.getJSONObject(recoardfoundindex).toString());
		System.out.println("--------------Find-User-End-----------------------" + "\n" + "\n" + "\n");
	}
}
