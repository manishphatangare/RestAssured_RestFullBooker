package CommonResources;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.HashMap;

import Pojo.BookingDates;
import Pojo.Details;
import Pojo.Token;
import io.cucumber.java.it.Date.Dates;

public class TestDataBuild {

	public static Token authTokenPayload(String username, String password) {
		Token tokenpaylaod = new Token();

		tokenpaylaod.setUsername(username);
		tokenpaylaod.setPassword(password);

		return tokenpaylaod;
	}

	public static Details createUserPaylaod(String firstname, String lastname, String totalprice, boolean depositpaid,
			String additionalneed, String checkin, String checkout) {
		Details detail = new Details();
		BookingDates data = new BookingDates();

		data.setCheckin(checkin);
		data.setCheckout(checkout);

		detail.setFirstname(firstname);
		detail.setLastname(lastname);
		detail.setDepositpaid(depositpaid);
		detail.setAdditionalneeds(additionalneed);
		detail.setTotalprice(totalprice);
		detail.setBookingdates(data);

		return detail;
	}

	public static HashMap<String, Object> updateUserPayload(String firstname, String lastname) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("firstname", firstname);
		map.put("lastname", lastname);

		return map;
	}
}
