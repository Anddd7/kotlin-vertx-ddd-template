package com.github.anddd7.verticles

import com.github.anddd7.verticles.router.StaticHandlers.injectCommonRoutes
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.ext.web.Router
import org.slf4j.LoggerFactory

class MainVerticle : AbstractVerticle() {
  private val log = LoggerFactory.getLogger(this.javaClass)
  private val port = 8888

  override fun start(startPromise: Promise<Void>) {
    vertx
      .createHttpServer()
      .requestHandler(
        Router.router(vertx).injectCommonRoutes()
      )
      .listen(port) { http ->
        if (http.succeeded()) {
          startPromise.complete()

          log.debug("HTTP server started on port {}", port)
        } else {
          val cause = http.cause()
          startPromise.fail(cause)

          log.error("server is dead", cause)
        }
      }
  }
}
