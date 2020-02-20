package cleanarch;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreekFunctionalTest {

  @LocalServerPort
  private int serverPort;

  @Autowired
  private GreekServiceConfiguration greekServiceConfiguration;

  @Test
  void thatGreetsInGreek() {

    final var result = requestSpecification()
      .when()
      .get("/")
      .then()
      .log().all()
      .and()
      .statusCode(HttpStatus.SC_OK)
      .contentType(ContentType.JSON)
      .extract()
      .as(GreekResponse.class);

    assertThat(greekServiceConfiguration.getWelcomeText()).isNotEmpty();
    assertThat(result.getResponse()).isEqualTo(greekServiceConfiguration.getWelcomeText());
  }

  protected RequestSpecification requestSpecification() {
    return RestAssured
      .given()
      .log().all()
      .port(serverPort)
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON);
  }
}
