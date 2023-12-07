package wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@WireMockTest
public class First_Test {
  @RegisterExtension
  static WireMockExtension wm1 = WireMockExtension.newInstance()
      .options(wireMockConfig().withRootDirectory("path/to/root"))
      .build();

  @Test
  public void exactUrlOnly(WireMockRuntimeInfo wmRuntimeInfo) throws URISyntaxException, IOException, InterruptedException {
    stubFor(get(urlEqualTo("/some/thing"))
        .willReturn(aResponse()
            .withHeader("Content-Type", "text/plain")
            .withBody("Hello world!")
            .withStatus(400)));

    final HttpClient client = HttpClient.newBuilder().build();
    final HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(wmRuntimeInfo.getHttpBaseUrl() + "/some/thing"))
//        .header("Content-Type", "text/xml")
        .GET().build();

    assertEquals(client.send(request, HttpResponse.BodyHandlers.ofString()).statusCode(), 200);
//    assertThat(testClient.get("/some/thing/else").statusCode(), is(404));
  }
}
