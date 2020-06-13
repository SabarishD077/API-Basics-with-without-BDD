package com.qa.apipractice;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TestPutCall {
	
	@Test
	public void validatePostCall()
	{
		given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 3s3xRgt7IrZM7jpORC2rqk-r-kLiDGCrgOji")
		.body(new File("C:\\Users\\SABARISH\\eclipse-workspace\\APIPracticeTest\\"
				+ "src\\test\\java\\dadafile\\putdata.json"))
		.when()
		.put("https://gorest.co.in/public-api/users/14691")
		.then().log().all()
		.assertThat().statusCode(200);
	}

}
