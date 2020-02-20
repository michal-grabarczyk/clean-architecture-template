package cleanarch;

import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
class GreekService {

  private final GreekServiceConfig greekServiceConfig;

  String generateGreekText() {
    return greekServiceConfig.getWelcomeText();
  }
}
