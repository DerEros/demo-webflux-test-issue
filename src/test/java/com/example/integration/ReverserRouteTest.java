package com.example.integration;

import com.example.ReverserRoutes;
import com.example.ReverserService;
import com.example.model.ReverserResult;
import com.fasterxml.jackson.databind.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReverserRouteTest {
    @Autowired
    public ReverserRoutes reverserRoutes;

    @MockBean
    public ReverserService mockReverserService;

    @Autowired
    @Qualifier("specificSerializers")
    public Module jacksonModule;

    @Test
    public void testSerializerBeanIsPresent() {
        assertNotNull(jacksonModule);
    }

    @Test
    public void testRouteAcceptsCall() {
        given(mockReverserService.reverse(anyString())).willReturn(new ReverserResult("foo", "bar"));

        WebTestClient client = WebTestClient.bindToRouterFunction(reverserRoutes.createRouterFunction()).build();
        client.get().uri("/reverse/FooBar").exchange().expectStatus().isOk();
    }

    @Test
    public void testRouteReturnsMockedResult() {
        given(mockReverserService.reverse(anyString())).willReturn(new ReverserResult("foo", "bar"));

        WebTestClient client = WebTestClient.bindToRouterFunction(reverserRoutes.createRouterFunction()).build();
        client.get().uri("/reverse/somethingcompletelydifferent")
                .exchange()
                .expectBody().json("{\"result\":\"foo|bar\"}");
    }
}
