package com.qa.apipractice;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaTest {
	
@Test	
public void validateSchema()
{
	given().log().all()
	.contentType(ContentType.JSON)
	.body(new File("C:\\Users\\SABARISH\\eclipse-workspace\\APIPracticeTest\\"
			+ "src\\test\\java\\dadafile\\booking.json"))
	.when().post("https://restful-booker.herokuapp.com/booking")
	.then().log().all().assertThat().statusCode(200)
	.and().body(matchesJsonSchemaInClasspath("schemadatanew.json"));
}

}
