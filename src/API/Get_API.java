package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;


public class Get_API {
	public static void main(String[] args) {
		String baseURI="https://reqres.in/";
		RestAssured.baseURI=baseURI;
		
		//responsebody and statuscode
		int statusCode=given().header("Content-Type", "application/json").when()
				.get("/api/users?page=2").then().extract().response().statusCode();
		System.out.println("Status code is " +statusCode);
		
		String responseBody=given().header("Content-Type", "application/json").when()
				.get("/api/users?page=2").then().extract().response().body().prettyPrint();
		System.out.println(responseBody);
		
		//configure response body
		
		
		JsonPath jsp=new JsonPath(responseBody);
		
		int dataSize=jsp.getList("data").size();
		System.out.println(dataSize);
		
		//validate data
		Assert.assertEquals(dataSize, 6);
		
		for(int i=0; i<dataSize; i++)
		{
			String id=jsp.getString("data ["+ i +" ].id ");
			String email=jsp.getString("data ["+ i +" ].email ");
			String first_name=jsp.getString("data ["+ i +" ].first_name ");
			String last_name=jsp.getString("data ["+ i +" ].last_name ");
			String avatar=jsp.getString("data ["+ i +" ].avatar ");
			
			
			Assert.assertNotNull(id);
			Assert.assertNotNull(email);
			Assert.assertNotNull(first_name);
			Assert.assertNotNull(last_name);
			Assert.assertNotNull(avatar);
			
			Assert.assertTrue(Integer.parseInt(id)>=7 && Integer.parseInt(id)<=12);
			Assert.assertTrue(email.contains("@reqres.in"));








			
		}
		
	}
}
