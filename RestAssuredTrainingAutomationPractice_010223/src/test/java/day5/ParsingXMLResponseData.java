package day5;

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
import org.mozilla.javascript.xml.XMLObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import groovy.util.logging.Log;
import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.AND;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponseData {
	
	@Test()
	public void ParsingXMLResponseDataApproach1() {
		
		given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=6")
		
		.then()
		.body("TravelerinformationResponse.page", equalTo("6"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].id", equalTo("11187"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].email", equalTo("kar111en@mail.ru"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].createdat", equalTo("0001-01-01T00:00:00"))
		.statusCode(200)
		.log().all();
	}
	
	
	//Storing data into variable 
	@Test()
	public void ParsingXMLResponseDataApproach2() {
		
		Response res=
				given()
		
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler?page=6");
					System.out.println(res);
					
					Assert.assertEquals(res.statusCode(), 200);
					String id=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].id").toString();
					Assert.assertEquals(id, "11187");
					
					String email=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].email").toString();
					Assert.assertEquals(email, "kar111en@mail.ru");
					
					String createdat=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].createdat").toString();
					Assert.assertEquals(createdat, "0001-01-01T00:00:00");				
	}
	
	//Storing data into variable 
		@Test()
		public void ParsingXMLResponseDataApproach3() {
			
			Response res=
					given()
			
					.when()
						.get("http://restapi.adequateshop.com/api/Traveler?page=6");
					
						XmlPath xm= new XmlPath(res.asString());
						
						int NodeSize= xm.getList("TravelerinformationResponse.travelers.Travelerinformation").size();
						System.out.println("Total Node size of Travelerinfromation--->" +NodeSize);
						
						
						for (int i = 0; i < NodeSize; i++) {
							if (xm.get("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].email").toString().equalsIgnoreCase("tigran.inbox@gmail.com")) {
								System.out.println("Recoard found at Node Number " + i + "\n");
								String id = xm.get("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].id").toString();
								String name = xm.get("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].name").toString();
								System.out.println("Name of the above record---->>" + name);
								System.out.println("Id of the above record " + id);
							}
						}
		}
		}
