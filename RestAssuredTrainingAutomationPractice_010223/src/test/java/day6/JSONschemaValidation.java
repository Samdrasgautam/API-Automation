package day6;

import static io.restassured.RestAssured.*;
import io.restassured.*;
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
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class JSONschemaValidation {
	
	@Test
	public void JSONSchemaValidationTest() {
		
		given()
		
		.when()
		.get("http://localhost:3000/student")
		
		.then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));
	}

}
