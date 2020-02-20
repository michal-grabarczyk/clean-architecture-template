package cleanarch;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GreekController {

  private final GreekService greekService;

  @GetMapping("/")
  ResponseEntity<GreekResponse> greekText() {
    final var greekText = greekService.generateGreekText();

    return ResponseEntity.ok(new GreekResponse(greekText));
  }
}
