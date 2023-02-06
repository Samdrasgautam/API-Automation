package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Multipart;

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

public class FileUpload {
	
	@Test
	public void singleFileUpload() {
		
		File myfile= new File("C:\\Users\\samdras\\jsonfiles\\txt1.txt");
		given()
			.multiPart("file", myfile)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadFile") 
		
		
		.then()
			.statusCode(200)
			.body("fileDownloadUri", equalTo("http://localhost:8080/downloadFile/txt1.txt"))
			.log().all();
	}
	
	@Test
	public void MultiFileUpload() {
		
		File myfile= new File("C:\\Users\\samdras\\jsonfiles\\txt1.txt");
		File myfile2= new File("C:\\Users\\samdras\\jsonfiles\\txt2.txt");
		given()
			.multiPart("files", myfile)
			.multiPart("files", myfile2)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadMultipleFiles") 
		
		
		.then()
			.statusCode(200)
			.body("[0].fileDownloadUri", equalTo("http://localhost:8080/downloadFile/txt1.txt"))
			.body("[1].fileDownloadUri", equalTo("http://localhost:8080/downloadFile/txt2.txt"))
			.log().all();
	}

}
