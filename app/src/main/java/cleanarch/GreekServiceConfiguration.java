package cleanarch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("greek")
@Validated
class GreekServiceConfiguration extends GreekServiceConfig {
}
