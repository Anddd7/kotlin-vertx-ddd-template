package com.github.anddd7.verticles

import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(VertxExtension::class)
class TestMainVerticle {

  @BeforeEach
  fun `deploy verticle`(vertx: Vertx, testContext: VertxTestContext) {
    vertx.deployVerticle(
      MainVerticle(),
      testContext.succeeding<String> { _ -> testContext.completeNow() })
  }

  @Test
  fun `verticle deployed`(vertx: Vertx, testContext: VertxTestContext) {
    testContext.completeNow()
  }
}
