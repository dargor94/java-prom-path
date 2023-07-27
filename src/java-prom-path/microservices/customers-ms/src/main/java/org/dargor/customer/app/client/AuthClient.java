package org.dargor.customer.app.client;

import lombok.RequiredArgsConstructor;
import org.dargor.customer.app.client.config.AuthClientProperties;
import org.dargor.customer.app.dto.TokenResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthClient {

    private final AuthClientProperties properties;
    private final WebClient.Builder webClientBuilder;
    private final String baseUrl = properties.getHost() + ":" + properties.getPort() + properties.getUrl();

    public TokenResponseDto getTokenResponseDto() {
        final String url = baseUrl + properties.getTokenUrl();
        return webClientBuilder.build()
                .get()
                .uri(url)
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(RuntimeException::new))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(Exception::new))
                .bodyToMono(TokenResponseDto.class).block();
    }

}
