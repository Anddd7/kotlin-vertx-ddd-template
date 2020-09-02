package com.github.anddd7.verticles

import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.codec.BodyCodec
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(VertxExtension::class)
class TestMainVerticle {

  @BeforeEach
  fun `deploy verticle`(vertx: Vertx, testContext: VertxTestContext) {
    vertx.deployVerticle(
      MainVerticle(),
      testContext.completing()
    )
  }

  @Test
  fun `verticle deployed`(vertx: Vertx, testContext: VertxTestContext) {
    WebClient.create(vertx)
      .get(8888, "localhost", "/hello")
      .`as`(BodyCodec.string())
      .send(testContext.succeeding { response ->
        testContext.verify {
          assertThat(response.body()).isEqualTo("Hello, this is kotlin-spring-webflux!")
          testContext.completeNow()
        }
      })
  }
}
