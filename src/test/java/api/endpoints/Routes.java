package api.endpoints;
//swagger url : https://petstore.swagger.io/
//create user :https://petstore.swagger.io/#/user
// get user : https://petstore.swagger.io/#/user/{username}
// update user : https://petstore.swagger.io/#/user/{username}
// delete user : https://petstore.swagger.io/#/user/{username}

public class Routes {
	
public static String base_url="https://petstore.swagger.io/v2";
//User Module
public static String post_url=base_url+"/user";
public static String get_url=base_url+"/user/{username}";
public static String update_url=base_url+"/user/{username}";
public static String delete_url=base_url+"/user/{username}";
}
