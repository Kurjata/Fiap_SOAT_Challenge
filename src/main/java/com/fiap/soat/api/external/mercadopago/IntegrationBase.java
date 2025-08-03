package com.fiap.soat.api.external.mercadopago;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.RetryBackoffSpec;

import static java.time.Duration.ofSeconds;
import static java.time.temporal.ChronoUnit.SECONDS;
import static reactor.util.retry.Retry.fixedDelay;
import static io.netty.handler.ssl.util.InsecureTrustManagerFactory.INSTANCE;

@Slf4j
public abstract class IntegrationBase {
  @Value("${app.connection.retry.seconds-interval}")
  private Long retrySeconds;

  @Value("${app.connection.retry.quantity}")
  private Long retryQuantity;

  @Value("${app.connection.seconds-timeout}")
  protected Long timeout;

  protected RetryBackoffSpec retry() {
    return fixedDelay(retryQuantity, ofSeconds(retrySeconds, SECONDS.ordinal()))
        .doBeforeRetry(
            retrySignal ->
                log.info(
                    "[{}] Retry connection. Attempt: {}. Cause: {}",
                    this.getClass().getName(),
                    retrySignal.totalRetries(),
                    retrySignal.failure().getMessage()));
  }

  @SneakyThrows
  protected WebClient getWebClient() {
    SslContext context = SslContextBuilder.forClient().trustManager(INSTANCE).build();

    HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

    return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
  }
}
