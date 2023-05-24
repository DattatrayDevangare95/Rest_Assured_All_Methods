package API;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;

import io.restassured.path.json.*;

public class post_API {
	public static void main(String[] args) {
		String baseURI="https://reqres.in";
		RestAssured.baseURI=baseURI;
		
		//req body
		String req_body="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		System.out.println(req_body);
		
		//statuscode and res body
		int statusCode=given().header("Content-Type", "application/json").body(req_body).when()
				.post("/api/users").then().extract().response().statusCode();
		System.out.println(statusCode);
		
		String res_body=given().header("Content-Type", "application/json").body(req_body).when()
				.post("/api/users").then().extract().response().asString();
		System.out.println(res_body);
		
		JsonPath jsp=new JsonPath(req_body);
		String req_name=jsp.getString("name");
		String req_job=jsp.getString("job");
		
		System.out.println(req_name);
		System.out.println(req_job);
		
		JsonPath jspres= new JsonPath(res_body);
		String res_name=jspres.getString("name");
		String res_job=jspres.getString("job");
		String res_id=jspres.getString("id");
		String res_createdAt=jspres.getString("createdAt");
		String expecteddate=res_createdAt.substring(0,10);
		
		
		
		System.out.println(res_name);
		System.out.println(res_job);
		System.out.println(res_id);
		System.out.println(res_createdAt);
		System.out.println(expecteddate);
		
		Assert.assertEquals(req_name, res_name);
		Assert.assertEquals(req_job, res_job);
		Assert.assertNotNull(res_id);
		
		//validate date
		String currentDate=LocalDate.now().toString();
		System.out.println(currentDate);
		
		Assert.assertEquals(currentDate, expecteddate);


	}
}
