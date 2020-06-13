package com.qa.apipractice;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.omg.CORBA.Object;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetApiTest {
	
@Test
public void verifyCircuitCount()
{
	given()
	.when()
	.get("http://ergast.com/api/f1/2017/circuits.json")
	.then().log().all()
		.assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
	
}
@Test
public void verifyCount()
{
	Response response=
	given()
	.when()
	.get("http://ergast.com/api/f1/2017/circuits.json");
	int statuscode=response.statusCode();
	Assert.assertEquals(statuscode, 200);
}

@Test
public void verifyResponse()
{
	given()
	.when()
	.get("http://ergast.com/api/f1/2017/circuits.json")
	.then().log().all()
	.assertThat()
	   .statusCode(200)
	 .and()
	 	.contentType(ContentType.JSON)
	 .and()
	 	.header("Content-Length", equalTo("4551"));
	  	
}

@Test
public void basicAuth()
{
	given()
	.auth().preemptive().basic("admin", "admin")
	.when().get("http://the-internet.herokuapp.com/basic_auth")
	.then().log().all().assertThat().statusCode(200);
}

@Test
public void oAuth2Test()
{
	given()
	 .auth().oauth2("3s3xRgt7IrZM7jpORC2rqk-r-kLiDGCrgOji")
	  .when().get("https://gorest.co.in/public-api/users")
	  	.then().log().all().assertThat().statusCode(200);
}

@Test
public void oAuth2WithHeader()
{
	given()
	 .header("Authorization", "Bearer 3s3xRgt7IrZM7jpORC2rqk-r-kLiDGCrgOji")
	.when().get("https://gorest.co.in/public-api/users")
	.then().log().all().assertThat().statusCode(200);
}

@Test
public void oAuthwithParam()
{
	given()
	.header("Authorization", "Bearer 3s3xRgt7IrZM7jpORC2rqk-r-kLiDGCrgOji")
	.queryParam("first_name", "Yeni")
	.queryParam("gender", "male")
	.when().get("https://gorest.co.in/public-api/users")
	.then().log().all().assertThat().statusCode(200);
}

@Test
public void oAuthDifferApproch()
{
	RequestSpecification request=
			given().auth().oauth2("e828c751c41c4b22683f5d4647be9c164891ef5f");
	Response response=
			request.post("http://coop.apps.symfonycasts.com/api/897/chickens-feed");
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
}

//How to get Access token at runtime and pass it to API
@Test
public void getTokenRuntime()
{
	RequestSpecification requestspc=RestAssured.given()
	.formParam("client_id", "simpleapitest")
	.formParam("client_secret", "bb3b3caa5dbeb5f8696b4932d75cb508")
	.formParam("grant_type", "client_credentials");
	
	Response tokenres=requestspc.post("http://coop.apps.symfonycasts.com/token");
	System.out.println(tokenres.statusCode());
	String accesstoken=tokenres.jsonPath().getString("access_token");
	
	RequestSpecification request=
			given().auth().oauth2(accesstoken);
	Response response=
			request.post("http://coop.apps.symfonycasts.com/api/897/chickens-feed");
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
	
	
}
/*@DataProvider(name="getCircuitInfo")

public Object[][] createTestDataRecords() {
    return new Object[][] {
        {"2017",20},
        {"2016",21},
        {"1966",9},
    };
}

@Test(dataProvider="getCircuitInfo")
public void verifyYearAndCount(String seasonYear, int circuitNumber)
{
	given().log().all()
	.pathParam("year", seasonYear)
	.when().log().all()
	.get("http://ergast.com/api/f1/{year}/circuits.json")
	.then().log().all()
		.assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitNumber));
	
}*/
}
