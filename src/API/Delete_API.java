package API;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class Delete_API {
	public static void main(String[] args) {
		String baseURI="https://reqres.in/";
		RestAssured.baseURI=baseURI;
		
		//configure req body
		int statusCode=given().header("Content-Type", "application/json").when().delete("/api/users/2").then().extract().statusCode();
		
		System.out.println(statusCode);
		

	}
}
