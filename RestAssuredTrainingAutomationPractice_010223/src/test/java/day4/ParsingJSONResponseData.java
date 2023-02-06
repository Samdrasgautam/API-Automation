package day4;
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

import groovy.util.logging.Log;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
	
	
	
	//approach 1
	@Test()
	public void testJSONResponseApproach1() {
		
		given()
		.contentType("ContentType.JSON")
		
		.when()
		.get("http://localhost:3000/store")
		
		.then()
		.statusCode(200)
		.log().all()
		.body("book[0].author", equalTo("Nigel Rees"))
		.body("book[1].author", equalTo("Samdras"));
		
	}
	
	
	//approach 2  with capturing response in variable 
		@Test()
		public void testJSONResponseApproach2() {
			
			Response res= given()
			.contentType("ContentType.JSON")
			
			.when()
			.get("http://localhost:3000/store");
			
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(res.jsonPath().get("book[0].author").toString(), "Nigel Rees");
			Assert.assertEquals(res.jsonPath().get("book[1].author").toString(), "Samdras");
			Assert.assertEquals(res.jsonPath().get("book[0].category").toString(), "reference");
			Assert.assertEquals(res.jsonPath().get("book[1].category").toString(), "New123");
			
		}
		
		//approach 3 
				@Test()
				public void testJSONResponseBodyData() {
									
					Response res= 
							given()
								.contentType(ContentType.JSON)
					
					.when()
								.get("http://localhost:3000/store");
					
					JSONObject jo= new JSONObject(res.asString()); //conversting response to json object
					//System.out.println(jo.getJSONArray("book").length());
					
					for(int i=0;i<jo.getJSONArray("book").length();i++)
					{
						String Book_Title= jo.getJSONArray("book").getJSONObject(i).get("title").toString();
						System.out.println("Book Title--->" +Book_Title);
						if(Book_Title.equalsIgnoreCase("New ERA"))
						{
							System.out.println("Book TitleMatch");
							System.out.println("Item Found on Index----->>>" +i);
							System.out.println("\n");
							//break;
						}
						else {
							System.out.println("Not Match go Next");
							System.out.println("\n");
						}
					}
				}
				
				@Test()
				public void totalBookPrice() {

					double total_bookPrice = 0;

					Response res = given()
										.contentType(ContentType.JSON)

									.when()
										.get("http://localhost:3000/store");

					JSONObject jo = new JSONObject(res.asString()); // conversting response to json object

					for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
						String SingleBookPriceString= jo.getJSONArray("book").getJSONObject(i).get("price").toString();
						double SingleBookPrice = Double.valueOf(SingleBookPriceString);
						System.out.println("Index of " + i + " Book Price is--->" + SingleBookPrice + "\n");
						total_bookPrice = total_bookPrice + SingleBookPrice;
					}
					if (total_bookPrice == 1421.5) {
						System.out.println("Total Books Price Matched---->>>" + total_bookPrice + "\n");
					}
				}

}
