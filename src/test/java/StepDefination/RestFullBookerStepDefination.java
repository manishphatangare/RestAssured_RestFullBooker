package StepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import APIEndpoints.APIResources;
import CommonResources.TestDataBuild;
import CommonResources.Utils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestFullBookerStepDefination extends Utils {

	static RequestSpecification req;
	static Response response;
	static int bookingid;
	static APIResources resources;
	static JsonPath js;

	@Given("^add booking payload (.+),(.+),(.+),(.+),(.+),(.+),(.+)$")
	public void add_booking_payload_true(String firstname, String lastname, String totalprice, boolean depositpaid,
			String additionalneed, String checkin, String checkout) throws IOException {

		req = given().spec(requestSpecification()).body(TestDataBuild.createUserPaylaod(firstname, lastname, totalprice,
				depositpaid, additionalneed, checkin, checkout));

	}

	@When("User calls {string} with POST httpRequest")
	public void user_calls_with_post_http_request(String resourceAPI) {

		resources = APIResources.valueOf(resourceAPI);
		response = req.when().post(resources.getResources());
	}

	@Then("User validate the Response {int}")
	public void user_validate_the_response(int statusCode) {

		Assert.assertEquals(response.getStatusCode(), statusCode);

	}

	@Given("user have valid BookingId")
	public void user_have_valid_booking_id() throws IOException {

		JsonPath js = new JsonPath(response.asString());
		bookingid = js.getInt("bookingid");
		System.out.println("Booking Id :" + bookingid);

		req = given().spec(requestSpecification());
	}

	@When("User calls {string} with GET httpRequest")
	public void user_calls_with_get_http_request(String resourceAPI) {
		System.out.println(resources.getResources() + bookingid + "");
		resources = APIResources.valueOf(resourceAPI);

		response = req.when().get(resources.getResources() + bookingid + "");
		System.out.println(response.asPrettyString());
	}

	@When("User calls {string} with PUT httpRequest")
	public void user_calls_with_put_http_request(String resourceAPI) {

		resources = APIResources.valueOf(resourceAPI);

		response = req.when().put(resources.getResources() + bookingid + "");
		System.out.println(response.asPrettyString());

	}

	@Then("^User validate the data is updated (.+),(.+)$")
	public void user_validate_the_data_is_updated(String firstname, String lastname) {

		js = new JsonPath(response.asString());

		Assert.assertEquals(js.getString("firstname"), firstname);
		Assert.assertEquals(js.getString("lastname"), lastname);

	}

	@Given("^user add payload (.+),(.+)$")
	public void user_add_payload_(String firstname, String lastname) throws IOException {

		req = given().spec(requestSpecification()).body(TestDataBuild.updateUserPayload(firstname, lastname));

	}

	@When("User calls {string} with PATCH httpRequest")
	public void user_calls_with_patch_http_request(String resourceAPI) {

		resources = APIResources.valueOf(resourceAPI);
		response = req.when().patch(resources.getResources() + bookingid + "");
	}

	@Given("User Authorized the token for delete records")
	public void user_authorized_the_token_for_delete_records() throws IOException {

		req = given().spec(requestSpecification());
	}

	@When("User calls {string} with DELETE httpRequest")
	public void user_calls_with_delete_http_request(String resourceAPI) {

		resources = APIResources.valueOf(resourceAPI);
		response = req.when().delete(resources.getResources() + bookingid + "");

	}

}
