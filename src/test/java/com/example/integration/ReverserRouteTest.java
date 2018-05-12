package com.example.integration;

import com.example.*;
import com.example.model.ReverserResult;
import com.example.serializer.ReverserResultSerializer;
import com.fasterxml.jackson.databind.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest
@AutoConfigureWebClient
@Import({SerializerConfig.class, ReverserResultSerializer.class, ReverserRoutes.class, ReverseHandler.class, ReverserConfig.class})
public class ReverserRouteTest {
    @MockBean
    public ReverserService mockReverserService;

    @Autowired
    @Qualifier("specificSerializers")
    public Module jacksonModule;

    @Autowired
    public WebTestClient client;

    @Test
    public void testSerializerBeanIsPresent() {
        assertNotNull(jacksonModule);
    }

    @Test
    public void testRouteAcceptsCall() {
        given(mockReverserService.reverse(anyString())).willReturn(new ReverserResult("foo", "bar"));

        client.get().uri("/reverse/FooBar").exchange().expectStatus().isOk();
    }

    @Test
    public void testRouteReturnsMockedResult() {
        given(mockReverserService.reverse(anyString())).willReturn(new ReverserResult("foo", "bar"));

        client.get().uri("/reverse/somethingcompletelydifferent")
                .exchange()
                .expectBody().json("{\"result\":\"foo|bar\"}");
    }
}
