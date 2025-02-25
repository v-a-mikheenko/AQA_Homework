import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostmanEchoTest {

    protected static final String BASE_URI = "https://postman-echo.com";
    protected static final String KEY1 = "Key1";
    protected static final String VALUE1 = "Value1";
    protected static final String KEY2 = "Key2";
    protected static final String VALUE2 = "Value2";
    protected static final String REQUEST_BODY = "This is expected to be sent back as part of response body.";
    protected static final String CONTENT_TYPE_TEXT = "text/plain; charset=UTF-8";

    private static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";

        responseSpec = RestAssured.expect()
                .statusCode(200)
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept-encoding", equalTo("gzip,deflate"));
    }

    @Test
    public void testGetRequest() {
        given()
                .param(KEY1, VALUE1)
                .param(KEY2, VALUE2)
                .when()
                .get("/get")
                .then().log().body()
                .spec(responseSpec)
                .body("args." + KEY1, equalTo(VALUE1))
                .body("args." + KEY2, equalTo(VALUE2))
                .body("url", equalTo(BASE_URI + "/get?" + KEY1 + "=" + VALUE1 + "&" + KEY2 + "=" + VALUE2));
    }

    @Test
    public void testPostRawText() {
        String requestBody = "{\n    \"test\": \"value\"\n}";

        given()
                .log().body()
                .contentType(CONTENT_TYPE_TEXT)
                .body(requestBody)
                .when()
                .post("/post")
                .then().log().body()
                .spec(responseSpec)
                .body("args", equalTo(emptyMap()))
                .body("data", equalTo(requestBody))
                .body("files", equalTo(emptyMap()))
                .body("form", equalTo(emptyMap()))
                .body("headers.content-length", equalTo("23"))
                .body("headers.content-type", equalTo(CONTENT_TYPE_TEXT))
                .body("json", equalTo(null))
                .body("url", equalTo(BASE_URI + "/post"));
    }

    @Test
    public void testPostFormData() {
        String contentTypeFormData = "application/x-www-form-urlencoded; charset=UTF-8";

        given()
                .contentType(contentTypeFormData)
                .formParam(KEY1, VALUE1)
                .formParam(KEY2, VALUE2)
                .when()
                .post("/post")
                .then().log().body()
                .spec(responseSpec)
                .body("args", equalTo(emptyMap()))
                .body("data", equalTo(""))
                .body("files", equalTo(emptyMap()))
                .body("form." + KEY1, equalTo(VALUE1))
                .body("form." + KEY2, equalTo(VALUE2))
                .body("headers.content-length", equalTo("23"))
                .body("headers.content-type", equalTo(contentTypeFormData))
                .body("json." + KEY1, equalTo(VALUE1))
                .body("json." + KEY2, equalTo(VALUE2))
                .body("url", equalTo(BASE_URI + "/post"));
    }

    @Test
    public void testPutRequest() {
        given()
                .log().body()
                .contentType(CONTENT_TYPE_TEXT)
                .body(REQUEST_BODY)
                .when()
                .put("/put")
                .then().log().body()
                .spec(responseSpec)
                .body("args", equalTo(emptyMap()))
                .body("data", equalTo(REQUEST_BODY))
                .body("files", equalTo(emptyMap()))
                .body("form", equalTo(emptyMap()))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", equalTo(CONTENT_TYPE_TEXT))
                .body("json", equalTo(null))
                .body("url", equalTo(BASE_URI + "/put"));
    }

    @Test
    public void testPatchRequest() {
        given()
                .log().body()
                .contentType(CONTENT_TYPE_TEXT)
                .body(REQUEST_BODY)
                .when()
                .patch("/patch")
                .then().log().body()
                .spec(responseSpec)
                .body("args", equalTo(emptyMap()))
                .body("data", equalTo(REQUEST_BODY))
                .body("files", equalTo(emptyMap()))
                .body("form", equalTo(emptyMap()))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", equalTo(CONTENT_TYPE_TEXT))
                .body("json", equalTo(null))
                .body("url", equalTo(BASE_URI + "/patch"));
    }

    @Test
    public void testDeleteRequest() {
        given()
                .log().body()
                .contentType(CONTENT_TYPE_TEXT)
                .body(REQUEST_BODY)
                .when()
                .delete("/delete")
                .then().log().body()
                .spec(responseSpec)
                .body("args", equalTo(emptyMap()))
                .body("data", equalTo(REQUEST_BODY))
                .body("files", equalTo(emptyMap()))
                .body("form", equalTo(emptyMap()))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", equalTo(CONTENT_TYPE_TEXT))
                .body("json", equalTo(null))
                .body("url", equalTo(BASE_URI + "/delete"));
    }
}
