package API;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
public class patch_API {
	public static void main(String[] args) {
		String BaseURI="https://reqres.in/";
		RestAssured.baseURI=BaseURI;
		
		//reqbody
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		//System.out.println(requestBody);
		
		//date
		String Exp_Date=LocalDateTime.now().toString().substring(0,10);
		
		//extract req body parameter
		JsonPath jsp=new JsonPath(requestBody);
		String req_name=jsp.getString("name");
		String req_job=jsp.getString("job");
		
		//configure requestBody
		int statusCode=given().header("Content-type", "application/json").body(requestBody).when().patch("/api/users/2").then().extract().statusCode();
		
		String responseBody=given().header("Content-type","application/json").body(requestBody).when().patch("/api/users/2").then().extract().response().asString();
		System.out.println(responseBody);
		//parse response body
		JsonPath res_jsp=new JsonPath(responseBody);
		String res_name=res_jsp.getString("name");
		String res_job=res_jsp.getString("job");
		String res_updatedAt=res_jsp.getString("updatedAt");
		String Actualdate=res_updatedAt.substring(0,10);
		
		System.out.println(res_name);
		System.out.println(res_job);
		System.out.println(Actualdate);
		
		//validate response body parameter
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(Actualdate, Exp_Date);
	}
}
