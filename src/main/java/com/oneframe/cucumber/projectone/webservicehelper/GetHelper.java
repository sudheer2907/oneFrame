package com.oneframe.cucumber.projectone.webservicehelper;

import static io.restassured.RestAssured.given;

import org.json.JSONException;
import org.json.JSONObject;

import com.oneframe.cucumber.projectone.beans.ResponseBean;
import com.oneframe.cucumber.projectone.constants.JSONConstants;

import io.restassured.response.Response;

public class GetHelper {
    private static final String LIST_USER_STRING = "https://reqres.in/api/users";
    private static final String SINGLE_USER_STRING = "https://reqres.in/api/users/2";
    private static Response response = null;
    private static String errorMessage = null;

    /**
     * Get details of all the users.
     *
     * @return - Details of all users.
     * @throws JSONException
     *             - if in case of json exceptions.
     * @author sudheer.singh
     */
    public static JSONObject getListUsers() throws JSONException {
        response = given().when().get(LIST_USER_STRING);
        if (response.statusCode() != JSONConstants.OK_STATUS_CODE) {
            errorMessage = response.then().extract().asString();
            throw new JSONException("Service to get access token is not working " + errorMessage);
        }
        ResponseBean.setResponse(new JSONObject(response.asString()));
        return new JSONObject(response.asString());
    }

    /**
     * Get details of single user.
     *
     * @return - details of single user.
     * @throws JSONException
     *             - if in case of json exceptions.
     * @author sudheer.singh
     */
    public static JSONObject getSingleUser() throws JSONException {
        response = given().when().get(SINGLE_USER_STRING);
        if (response.statusCode() != JSONConstants.OK_STATUS_CODE) {
            errorMessage = response.then().extract().asString();
            throw new JSONException("Service to get access token is not working " + errorMessage);
        }
        ResponseBean.setResponse(new JSONObject(response.asString()));
        return new JSONObject(response.asString());
    }
}
