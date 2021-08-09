package space.gavinklfong.demo.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import space.gavinklfong.demo.security.dto.ReCaptchaValidationResp;

import java.util.Optional;

@Slf4j
@Service
public class ReCaptchaService {

    private String secret;

    private WebClient webClient;

    @Autowired
    public ReCaptchaService(@Value("${app.recaptcha.verify-url}") String verifyUrl,
                            @Value("${app.recaptcha.secret-key}") String secret) {
        this.webClient = WebClient.builder().baseUrl(verifyUrl).build();
        this.secret = secret;
    }

    public boolean verifyReCaptchaResponse(String token) {
        Optional<ReCaptchaValidationResp> resp = webClient.post()
                .body(BodyInserters
                        .fromFormData("secret", this.secret)
                        .with("response", token))
                .retrieve()
                .bodyToMono(ReCaptchaValidationResp.class)
                .blockOptional();

        if (resp.isPresent()) {
            return resp.get().isSuccess();
        } else {
            return false;
        }
    }
}
