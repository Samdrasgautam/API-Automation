package day2;
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

public class differentWaysPostBody {
	
	@Test(priority = 1)
	public void way1UsingHashMap() {
				
		HashMap data= new HashMap();
		data.put("name", "Samdras1");
		data.put("location", "MRT");
		data.put("phone", "8909557200");
		String courseArray[]= {"C", "Java", "Php"};
		data.put("course", courseArray);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/student")
		
		
		.then()
		
		.statusCode(201)
		.body("name", equalTo("Samdras1"))
		.body("location", equalTo("MRT"))
		.body("phone", equalTo("8909557200"))
		.body("course[0]", equalTo("C"))
		.body("course[1]", equalTo("Java"))
		.log().all();
	}
	
	
	// second way for json body creation 
	@Test(priority = 2)
	public void way2OrgJsonLibrary() {
				
		//HashMap data= new HashMap();
		
		JSONObject data1= new JSONObject();
		data1.put("name", "Samdras1");
		data1.put("location", "MRT");
		data1.put("phone", "8909557200");
		String courseArray[]= {"C", "Java", "Php"};
		data1.put("course", courseArray);
		
		given()
		.contentType("application/json")
		.body(data1.toString())
		
		.when()
		.post("http://localhost:3000/student")
		
		
		.then()
		
		.statusCode(201)
		.body("name", equalTo("Samdras1"))
		.body("location", equalTo("MRT"))
		.body("phone", equalTo("8909557200"))
		.body("course[0]", equalTo("C"))
		.body("course[1]", equalTo("Java"))
		.log().all();
	}
	
	// third way to create post req using pojo class
	
	@Test(priority = 3)
	public void way2PojoClass() {
				
		pojo_postRequest data= new pojo_postRequest();
		data.setName("Samdras");
		data.setLocation("Meerut");
		data.setPhone("8909557200");
		String arrcou[]= {"C", "Java"};
		data.setCourses(arrcou);
		
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/student")
		
		.then()		
		.statusCode(201)
		.body("name", equalTo("Samdras"))
		.body("location", equalTo("Meerut"))
		.body("phone", equalTo("8909557200"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("Java"))
		.log().all();
	}
	
	//fourth way to create post req using external json file 
	
		@Test(priority = 4)
		public void way3ExternalJSONFile() throws FileNotFoundException {
					
			File f = new File(".\\body.json");
			FileReader fe = new FileReader(f);
			JSONTokener jtk = new JSONTokener(fe);
			JSONObject data = new JSONObject(jtk);
			
			given()
			.contentType("application/json")
			.body(data.toString())
			
			.when()
			.post("http://localhost:3000/student")
			
			.then()		
			.statusCode(201)
			.body("name", equalTo("Sam Abc Sam"))
			.body("location", equalTo("NewDelhi"))
			.body("Phone", equalTo("890298765"))
			.body("courses[0]", equalTo("Java"))
			.body("courses[1]", equalTo("Selenium"))
			.log().all();
		}

}
