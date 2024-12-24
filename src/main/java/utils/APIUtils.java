package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIUtils {

    public String translateText(String text, String source, String target) {
        String apiUrl = "https://api.mymemory.translated.net/get";
        Response response = RestAssured.given()
                .param("q", text)  // Text to translate
                .param("langpair", source + "|" + target)  // Language pair
                .get(apiUrl);

        String jsonResponse = response.getBody().asString();
        return response.jsonPath().getString("responseData.translatedText");
    }
}
