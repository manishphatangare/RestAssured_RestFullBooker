package CommonResources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.System.Logger;
import java.util.Properties;

import Pojo.Token;
import Token.TokenGenerate;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification reqspec;

	static String token = TokenGenerate.CreateToken();

	public RequestSpecification requestSpecification() throws IOException {

		if (reqspec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("Logging.text"));
			reqspec = new RequestSpecBuilder().setBaseUri(getGlobalValue("BaseURI"))
					.addHeader("Content-Type", "application/json").addHeader("Accept", "application/json")
					.addCookie("token", token).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))// .build();
					.setContentType(ContentType.JSON).build();

			return reqspec;
		}
		return reqspec;
	}

	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(
				"src\\test\\java\\PropertiesFile\\global.properties");
		prop.load(fs);

		return prop.getProperty(key);
	}

}
