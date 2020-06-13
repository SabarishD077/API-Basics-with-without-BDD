package com.qa.apipractice;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TestDeleteCall {
	
	@Test
	public void validateDeleteCall()
	{
		given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 3s3xRgt7IrZM7jpORC2rqk-r-kLiDGCrgOji")
		.when()
		.delete("https://gorest.co.in/public-api/users/14691")
		.then().log().all()
		.assertThat().statusCode(200);
	}

}
