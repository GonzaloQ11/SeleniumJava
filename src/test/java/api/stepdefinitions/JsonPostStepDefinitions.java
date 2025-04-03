package api.stepdefinitions;

import pojos.JsonPost;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.equalTo;

public class JsonPostStepDefinitions {
    private RequestSpecification request;


    @Given("There is a list of posts available")
    public void there_is_a_list_of_posts_available() {
        request = SerenityRest.given()
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


    @Given("I want to create a new post")
    public void i_want_to_create_a_new_post() {
        JsonPost newPost = new JsonPost(1, "foo", "bar");

        request = SerenityRest.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .contentType(ContentType.JSON)
                .body(newPost);
    }

    @When("I create a new post")
    public void i_create_a_new_post() {
        request.post();
    }

    @Then("The response code should be 201")
    public void the_response_code_should_be_201() {
        SerenityRest.lastResponse()
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }


    @Given("I want to update a post")
    public void i_want_to_update_a_post() {
        JsonPost updatedPost = new JsonPost(1, 1, "updated title", "updated body");
        request = SerenityRest.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts/1")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(updatedPost);
    }

    @When("I update a post")
    public void i_update_a_post() {
        request.put();
    }

    @Then("The post is updated")
    public void the_post_is_updated() {
        SerenityRest.lastResponse()
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("updated title"))
                .body("body", equalTo("updated body"))
                .body("userId", equalTo(1));
    }

    @Given("I want to delete a post")
    public void i_want_to_delete_a_post() {
        request = SerenityRest.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts/1")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }

    @When("I delete a post")
    public void i_delete_a_post() {
        request.delete();
    }

    @Then("The post is deleted")
    public void thePostIsDeleted() {
        SerenityRest.lastResponse()
                .then()
                .statusCode(200);
        // the deletion is not actually executed in the server, so it's possible to retrieve again using GET
    }


}
