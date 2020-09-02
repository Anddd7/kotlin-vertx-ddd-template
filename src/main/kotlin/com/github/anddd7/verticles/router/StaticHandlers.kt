package com.github.anddd7.verticles.router

import io.vertx.core.json.Json
import io.vertx.ext.web.Router

object StaticHandlers {
  fun Router.injectCommonRoutes(): Router {
    get("/profile").handler {
      it.response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(
          Json.encode(
            mapOf(
              "build.env" to System.getProperty("APP_ENV", "local"),
              "image.version" to System.getProperty("image.version", "development")
            )
          )
        )
    }
    get("/hello").handler { it.response().end("Hello, this is kotlin-spring-webflux!") }
    get("/ping").handler { it.response().end("pong") }

    return this
  }
}
