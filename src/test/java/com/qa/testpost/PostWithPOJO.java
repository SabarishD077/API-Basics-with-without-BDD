package com.qa.testpost;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

public class PostWithPOJO {
	
@Test

public void createUserWithPojo()
{
	User user=new User("Anushka", "Sharma", "female", "01-01-1988", "anusharma@gmail.com",
			"+1-999-9876-987", "https://www.google.com", "new avenue Coimbatore", "active");
	
	//Convert Java POJO to Json-->Serialization, Jackson API-->Objectmapper class
	
	ObjectMapper mapper=new ObjectMapper();
	String userjson=null;
	try {
		userjson=mapper.writeValueAsString(user);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
System.out.println(userjson);

given()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 3s3xRgt7IrZM7jpORC2rqk-r-kLiDGCrgOji")
	.body(userjson)
	.when().post("https://gorest.co.in/public-api/users")
	.then().log().all().assertThat().contentType(ContentType.JSON)
	.and().body("result.last_name", equalTo(user.getLast_name()));
	
}


}
