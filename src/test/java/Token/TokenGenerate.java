package Token;

import java.io.IOException;

import CommonResources.TestDataBuild;
import CommonResources.Utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TokenGenerate {

	public static String CreateToken(){

		RequestSpecification reqspec = RestAssured.given();

		Response response = reqspec.baseUri("https://restful-booker.herokuapp.com")
				.header("Content-Type", "application/json")
				.body(TestDataBuild.authTokenPayload("admin", "password123"))
				.when().post("/auth").then().statusCode(200).extract().response();

		System.out.println(response.asPrettyString());
		JsonPath js = new JsonPath(response.asString());

		String token = js.getString("token");

		return token;

	}

}
