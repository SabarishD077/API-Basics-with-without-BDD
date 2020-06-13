package com.qa.apipractice;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {
	
	ResponseSpecBuilder builder=new ResponseSpecBuilder();
	ResponseSpecification resspec= 
			builder.expectContentType(ContentType.JSON)
					.expectStatusCode(200).build();
@Test
public void resSpecTest()
{
	given()
	 .auth().oauth2("3s3xRgt7IrZM7jpORC2rqk-r-kLiDGCrgOji")
	  .when().get("https://gorest.co.in/public-api/users")
	  	.then().log().all().assertThat().spec(resspec);
}

}