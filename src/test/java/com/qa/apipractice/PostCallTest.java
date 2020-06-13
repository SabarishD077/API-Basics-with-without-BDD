package com.qa.apipractice;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotNull;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostCallTest {
	
@Test
public void validatePostCall()
{
	given()
	.contentType(ContentType.JSON)
	.body("{\"username\" : \"admin\",\"password\" : \"password123\"}")
	.when()
	.post("https://restful-booker.herokuapp.com/auth")
	.then().log().all()
	.assertThat().statusCode(200);
}

@Test
public void validatePostWithFile()
{
	given()
	.contentType(ContentType.JSON)
	.body(new File("C:\\Users\\SABARISH\\eclipse-workspace\\APIPracticeTest\\"
			+ "src\\test\\java\\dadafile\\data.json"))
	.when()
	.post("https://restful-booker.herokuapp.com/auth")
	.then().log().all()
	.assertThat().statusCode(200);
}

@Test
public void extractTokenId()
{
	String token=given()
	.contentType(ContentType.JSON)
	.body(new File("C:\\Users\\SABARISH\\eclipse-workspace\\APIPracticeTest\\"
			+ "src\\test\\java\\dadafile\\data.json"))
	.when()
	.post("https://restful-booker.herokuapp.com/auth")
	.then().log().all()
	.extract().path("token");
	System.out.println("My Token Id is : " + token);
	Assert.assertNotNull(token);
}
}
