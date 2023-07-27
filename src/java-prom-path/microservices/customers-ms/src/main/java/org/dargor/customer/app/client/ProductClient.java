package org.dargor.customer.app.client;

import lombok.RequiredArgsConstructor;
import org.dargor.customer.app.client.config.ProductClientProperties;
import org.dargor.customer.app.dto.ProductDto;
import org.dargor.customer.app.dto.WishListDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final ProductClientProperties properties;
    private final WebClient.Builder webClientBuilder;
    private final String baseUrl = properties.getHost() + ":" + properties.getPort() + properties.getUrl();

    public List<ProductDto> getWishList(UUID customerId) {
        final String url = baseUrl + properties.getWishlistUrl() + "/" + customerId;
        return webClientBuilder.build()
                .get()
                .uri(url)
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(RuntimeException::new))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(Exception::new))
                .bodyToMono(new ParameterizedTypeReference<List<ProductDto>>() {
                }).block();
    }


    public WishListDto createProducts(WishListDto products, String token) {
        final String url = baseUrl + properties.getCreateUrl();
        return webClientBuilder.build()
                .post()
                .uri(url)
                .headers(h -> h.setBearerAuth(token))
                .bodyValue(products)
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(RuntimeException::new))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(Exception::new))
                .bodyToMono(WishListDto.class)
                .block();
    }
}
