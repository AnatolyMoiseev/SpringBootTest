package hello.restclient;

import lombok.Data;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Data
public class JWTClient {

    static final String URL_LOGIN = "http://localhost:8080/login";
    static final String URL_USER_GET = "http://localhost:8080/user/get";

    // POST Login
    // @return "Authorization string".
    private static String postLogin(String username, String password) {

        // Request Header
        HttpHeaders headers = new HttpHeaders();

        // Request Body
        MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
        parametersMap.add("username", username);
        parametersMap.add("password", password);

        // Request Entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parametersMap, headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // POST Login
        ResponseEntity<String> response = restTemplate.exchange(URL_LOGIN, //
                HttpMethod.POST, requestEntity, String.class);

        HttpHeaders responseHeaders = response.getHeaders();

        List<String> list = responseHeaders.get("Authorization");
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    private static void callRESTApi(String restUrl, String authorizationString) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        //
        // Authorization string (JWT)
        //
        headers.set("Authorization", authorizationString);
        //
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(URL_USER_GET, //
                HttpMethod.GET, entity, String.class);

        String result = response.getBody();

        System.out.println(result);
    }

    public static void main(String[] args) {
        String username = "paul";
        String password = "123";

        String authorizationString = postLogin(username, password);

        System.out.println("Authorization String=" + authorizationString);

        // Call REST API:
        callRESTApi(URL_USER_GET, authorizationString);
    }

}
