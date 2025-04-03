package api.stepdefinitions;

import api.JsonPost;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.*;

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
        List<JsonPost> posts = SerenityRest.lastResponse()
                .as(new TypeRef<List<JsonPost>>() {});

        Ensure.that("The response code is 200", res -> res.statusCode(200));
        Ensure.that("The list contains at least one post", res -> {
            assertThat(posts).isNotEmpty();
        });
        posts.forEach(post -> {
            assertThat(post.getUserId()).as("userId").isNotNull();
            assertThat(post.getId()).as("id").isNotNull().isGreaterThan(0);
            assertThat(post.getTitle()).as("title").isNotBlank();
            assertThat(post.getBody()).as("body").isNotBlank();
        });
    }
}
