package API;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
public class Put_API {
public static void main(String[] args) {
		
		//step1 declare base url
		String baseURI="https://reqres.in/";
		RestAssured.baseURI=baseURI;
		
		//save request body in local variable
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		
		System.out.println(requestBody);
		
		//extract request body parameter
		JsonPath jsp=new JsonPath(requestBody);
		String req_name=jsp.getString("name");
		String req_job=jsp.getString("job");
		
		
		//set expected date
		String expectedDate=LocalDateTime.now().toString().substring(0,10);
		
		//configure requestBody
		int statusCode=given().header("Content-Type", "application/json").body(requestBody).when().put("/api/users/2").then().extract().statusCode();
		
		String responseBody=given().header("Content-Type", "application/json").body(requestBody).when().put("/api/users/2").then().extract().response().asString();
		
		System.out.println(statusCode);
		
		System.out.println(responseBody);
		//parse response Body
		
		JsonPath jsp_res=new JsonPath(responseBody);
		String res_name=jsp_res.getString("name");
		String res_job=jsp_res.getString("job");
		String res_updatedate=jsp_res.getString("updatedAt");
		String actualDate=res_updatedate.substring(0,10);
		
		//validate response body parameter
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(res_name,req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(actualDate, expectedDate);
		
		
	}
}
