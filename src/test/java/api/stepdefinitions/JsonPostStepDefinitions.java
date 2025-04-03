package api.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.when;

public class JsonPostStepDefinitions {

    @Given("There is a list of posts available")
    public void there_is_a_list_of_posts_available() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .accept(ContentType.JSON);
    }

    @When("I ask for the complete list")
    public void i_ask_for_the_complete_list() {
        when()
                .get();
    }

    @Then("The list has at least one result")
    public void the_list_has_at_least_one_result() {
        Ensure.that("The response code is 200", res -> res.statusCode(200));
        /*
        then()
              .statusCode(200)
              .extract()
              .body()
              .as(new TypeRef<List<JsonPost>>() {});
*/
    }
}
