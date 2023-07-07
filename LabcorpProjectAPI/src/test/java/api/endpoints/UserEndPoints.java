package api.endpoints;
import static io.restassured.RestAssured.given;
import java.util.List;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(List<User> payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(URLs.post_url);		
		
		return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response=given()
			.pathParam("username", userName)
			
		.when()
			.get(URLs.get_url);		
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
		.when()
			.put(URLs.update_url);		
		
		return response;
	}
	
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
			.pathParam("username", userName)
			
		.when()
			.delete(URLs.delete_url);		
		
		return response;
	}

}
