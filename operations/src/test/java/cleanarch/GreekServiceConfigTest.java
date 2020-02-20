package cleanarch;

import cleanarch.GreekServiceConfig.Greeting;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GreekServiceConfigTest {

  private static Validator validator;

  @BeforeAll
  public static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void thatValidatesEmptyConfig() {
    final var emptyGreetingConfig = new cleanarch.GreekServiceConfig();

    final var constraintViolations = validator.validate(emptyGreetingConfig);

    assertThat(constraintViolations).hasSize(2);
    assertThat(constraintViolations)
      .extracting(ConstraintViolation::getPropertyPath)
      .extracting(Path::toString)
      .containsExactlyInAnyOrder("greetings", "welcomeText");
  }

  @Test
  void thatAssuresPositiveJoyValue() {
    final var config = someValidGreekServiceConfig();
    final var invalidGreeting = someValidGreeting();
    invalidGreeting.setJoyLevel(-5);
    config.setGreetings(List.of(invalidGreeting));

    final var constraintViolations = validator.validate(config);

    assertThat(constraintViolations).hasSize(1);
    assertThat(constraintViolations.iterator().next().getPropertyPath().toString())
      .isEqualTo("greetings[0].joyLevel");
  }

  private GreekServiceConfig someValidGreekServiceConfig() {
    final var config = new GreekServiceConfig();
    config.setWelcomeText("den echo chrono");
    config.setGreetings(List.of(someValidGreeting()));
    return config;
  }

  private Greeting someValidGreeting() {
    final var greeting = new Greeting();
    greeting.setJoyLevel(7);
    greeting.setText("hara");
    return greeting;
  }

}
