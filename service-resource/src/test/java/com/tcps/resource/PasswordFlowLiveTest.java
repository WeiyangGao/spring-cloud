package com.tcps.resource;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
public class PasswordFlowLiveTest {

    public BCryptPasswordEncoder passwordEncoder;

    @Before
    public void initPwb() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void givenUser_whenUseFooClient_thenOkForFooResourceOnly() {
        // 到授权服务器获取Token。
        final String accessToken = obtainAccessToken("fooClientIdPassword", "john","123");
        // 用拿到的Token获取资源服务器数据。
        final Response fooResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8082/user");
        assertEquals(200, fooResponse.getStatusCode());
        JsonPath thisJsonPath = fooResponse.jsonPath();
        assertNotNull(fooResponse.jsonPath().get("name"));
        final Response barResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8082/user");
        assertEquals(403, barResponse.getStatusCode());
    }

    @Test
    public void givenUser_whenUseBarClient_thenOkForBarResourceReadOnly() {
        final String accessToken = obtainAccessToken("barClientIdPassword", "john", "123");

        final Response fooResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8082/spring-security-oauth-resource/foos/1");
        assertEquals(403, fooResponse.getStatusCode());

        final Response barReadResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8082/spring-security-oauth-resource/bars/1");
        assertEquals(200, barReadResponse.getStatusCode());
        assertNotNull(barReadResponse.jsonPath().get("name"));

        final Response barWritResponse = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).header("Authorization", "Bearer " + accessToken).body("{\"id\":1,\"name\":\"MyBar\"}").post("http://localhost:8082/spring-security-oauth-resource/bars");
        assertEquals(403, barWritResponse.getStatusCode());
    }

    @Test
    public void givenAdmin_whenUseBarClient_thenOkForBarResourceReadWrite() {
        final String accessToken = obtainAccessToken("barClientIdPassword", "tom", "111");

        final Response fooResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8082/spring-security-oauth-resource/foos/1");
        assertEquals(403, fooResponse.getStatusCode());

        final Response barResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8082/spring-security-oauth-resource/bars/1");
        assertEquals(200, barResponse.getStatusCode());
        assertNotNull(barResponse.jsonPath().get("name"));

        final Response barWritResponse = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).header("Authorization", "Bearer " + accessToken).body("{\"id\":1,\"name\":\"MyBar\"}").post("http://localhost:8082/spring-security-oauth-resource/bars");
        assertEquals(201, barWritResponse.getStatusCode());
        assertEquals("MyBar", barWritResponse.jsonPath().get("name"));
    }

    //

    private String obtainAccessToken(String clientId, String username, String password) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        final Response response = RestAssured.given().auth().preemptive().basic(clientId, "secret").and().with().params(params).when().post("http://localhost:8081/oauth/token");
        return response.jsonPath().getString("access_token");
    }
}
