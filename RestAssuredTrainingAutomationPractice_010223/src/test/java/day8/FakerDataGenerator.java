package day8;
import static io.restassured.RestAssured.given;
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

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;

import org.testng.annotations.Test;

public class FakerDataGenerator {
	
	@Test
	public void FakeDataGenerator() {
		
		Faker fakeer= new Faker();
		String fullname=fakeer.name().fullName();
		String firstname=fakeer.name().firstName();
		String lastname=fakeer.name().lastName();
		
		
		String username= fakeer.name().username();
		String password= fakeer.internet().password();
		
		String phone= fakeer.phoneNumber().phoneNumber();
		
		String email= fakeer.internet().emailAddress();
		
		System.out.println("Full name---->" +fullname);
		System.out.println("first name---->" +firstname);
		System.out.println("last name---->" +lastname);
		System.out.println("username---->" +username);
		System.out.println("password---->" +password);
		System.out.println("phone---->" +phone);
		System.out.println("email---->" +email);
	}

}
