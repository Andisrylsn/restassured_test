package steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class DemoQASteps {
    private static final String BASE_URL = "https://bookstore.toolsqa.com";
    private static Response response;
    private static String jsonString;
    private static String bookId;

    @Given("A list of books are available")
    public void aListOfBooksAreAvailable() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/BookStore/v1/Books");

        jsonString = response.asString();
        List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
        Assert.assertTrue(books.size() > 0);

        bookId = books.get(0).get("title");

        System.out.println(bookId);

        Assert.assertEquals("Git Pocket ", bookId );
    }
}
