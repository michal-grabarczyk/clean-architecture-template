package cleanarch;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
class GreekServiceConfig {

  @NotEmpty
  private String welcomeText;

  @Valid
  @NotEmpty
  private List<Greeting> greetings = new ArrayList<>();

  @Data
  static class Greeting {
    @Positive
    private int joyLevel;

    @NotEmpty
    private String text;
  }
}
