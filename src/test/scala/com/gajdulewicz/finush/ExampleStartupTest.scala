package com.gajdulewicz.finush

import com.google.inject.Stage
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.Test

class ExampleStartupTest extends Test {

  val server = new EmbeddedHttpServer(
    stage = Stage.PRODUCTION,
    twitterServer = new FinushServer)

  "server" in {
    server.assertHealthy()
  }
}
