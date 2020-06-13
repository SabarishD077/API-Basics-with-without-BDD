package com.qa.apipractice;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NonBDDGetCall {
	
@Test
public void getUsersNonBdd()
{
RestAssured.baseURI="https://gorest.co.in";
RequestSpecification request=RestAssured.given();
request.contentType(ContentType.JSON);
request.header("Authorization", "Bearer 3s3xRgt7IrZM7jpORC2rqk-r-kLiDGCrgOji");
//request.queryParam("first_name", "Anushka");
//request.queryParam("gender", "female");
Map<String, String> params=new HashMap<String, String>();
params.put("first_name", "Herman");
params.put("gender", "male");
request.queryParams(params); 
Response response=request.get("/public-api/users");
System.out.println(response.prettyPrint());
int statuscode=response.getStatusCode();
Assert.assertEquals(statuscode, 200);
JsonPath js=response.jsonPath();
String limit=js.getString("_meta.rateLimit.limit");
System.out.println("the total limit is : "+ limit);

}

}
