package api.test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTest {

	User userPayload;
	List<User> allUsers = new ArrayList<User>();
	
	@BeforeClass
	public void setupData()
	{
		userPayload = new User();
		
		userPayload.setId(43433);
		userPayload.setUsername("323555531qw");
		userPayload.setFirstName("PQQR");
		userPayload.setLastName("LAL");
		userPayload.setEmail("we@gmail.com");
		userPayload.setPassword("23dwewe");
		userPayload.setPhone("0004433");
		userPayload.setUserStatus(0);
			
		allUsers.add(userPayload);
	}
	
	@Test(priority=1)
	public void testing_POST()
	{
		Response response = UserEndPoints.createUser(allUsers);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);				
	}
	
	
	@Test(priority=2)
	public void testing_GET()
	{
		
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
			
		Assert.assertEquals(response.getStatusCode(), 200);			
	}
	
	@Test(priority=3)
	public void testing_name_id_UPDATE()
	{
		userPayload.setUsername("323555531qw");
		userPayload.setFirstName("Jacques");
		userPayload.setLastName("Sanon");
		
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);	
		
		
		Response responseAfterUpdate = UserEndPoints.readUser(userPayload.getUsername());
		responseAfterUpdate.then().log().all();
			
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);		
		
	}
	
	@Test(priority=4)
	public void testing_DELETE()
	{
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());	
		Assert.assertEquals(response.getStatusCode(), 200);		
	}	

}
