package stepdefinitions.api;

import pojos.JsonPost;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JsonPostStepDefinitions {

    private RequestSpecification request;

    @Given("There is a list of posts available")
    public void thereIsAListOfPostsAvailable() {
        request = SerenityRest.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .accept(ContentType.JSON);
    }

    @When("I ask for the complete list")
    public void iAskForTheCompleteList() {
        when().get();
    }

    @Then("The list has at least one result")
    public void theListHasAtLeastOneResult() {
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
    public void iWantToCreateANewPost() {
        JsonPost newPost = new JsonPost(1, "foo", "bar");

        request = SerenityRest.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .contentType(ContentType.JSON)
                .body(newPost);
    }

    @When("I create a new post")
    public void iCreateANewPost() {
        request.post();
    }

    @Then("The response code should be 201")
    public void theResponseCodeShouldBe201() {
        SerenityRest.lastResponse()
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }

    @Given("I want to update a post")
    public void iWantToUpdateAPost() {
        JsonPost updatedPost = new JsonPost(1, 1, "updated title", "updated body");

        request = SerenityRest.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts/1")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(updatedPost);
    }

    @When("I update a post")
    public void iUpdateAPost() {
        request.put();
    }

    @Then("The post is updated")
    public void thePostIsUpdated() {
        SerenityRest.lastResponse()
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("updated title"))
                .body("body", equalTo("updated body"))
                .body("userId", equalTo(1));
    }

    @Given("I want to delete a post")
    public void iWantToDeleteAPost() {
        request = SerenityRest.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts/1")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }

    @When("I delete a post")
    public void iDeleteAPost() {
        request.delete();
    }

    @Then("The post is deleted")
    public void thePostIsDeleted() {
        SerenityRest.lastResponse()
                .then()
                .statusCode(200);
        // Note: Deletion is faked on JSONPlaceholder, so GET will still return the post.
    }
}
