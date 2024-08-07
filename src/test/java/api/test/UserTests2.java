package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {
	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setupData() {
		faker=new Faker();
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setUsername(faker.name().username());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testPostUser() {
		logger.info("*******creating user*******");
	Response response=	UserEndPoints2.createUser(userpayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("*******user created*******");
	}
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("*******reading user info*******");
	Response response =	UserEndPoints2.readUser(this.userpayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("*******user info displayed*******");	
	}
	@Test(priority=3)
	public void testUpdateUser() {
		logger.info("*******updating user info*******");
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
	Response response =	UserEndPoints2.updateUser(this.userpayload.getUsername(),userpayload);
	response.then().log().all();
	response.then().log().body().statusCode(200);
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("*******user info updated*******");	
	Response responseafterupdate =	UserEndPoints2.readUser(this.userpayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
		
	}
@Test(priority=4)
public void testDeleteUserByName() {
	logger.info("*******deleting user info*******");
	Response response=UserEndPoints2.deleteUser(this.userpayload.getUsername());
	response.then().log().body().statusCode(200);
	logger.info("*******deleted user info*******");
	
}
}
