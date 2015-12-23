package com.gajdulewicz.finush

import com.fasterxml.jackson.databind.JsonNode
import com.twitter.finagle.http.Status.{Ok, MovedPermanently}
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class UrlFeatureTest extends FeatureTest {

  override val server = new EmbeddedHttpServer(new FinushServer)

  val link = "http://twitter.github.io/finatra"
  val id = 1

  "Server" should {
    "root" in {
      server.httpGet(
        path = "/",
        andExpect = Ok,
        withBody = "Welcome to Finush")
    }

    "post by id" in {
      server.httpPostJson[JsonNode](
        path = "/url",
        andExpect = Ok,
        postBody = s"""{"link":"$link"}""",
        withBody = s"""{"id":"$id", "link":"$link"}"""
      )
    }

    "get by id" in {
      server.httpGet(
        path = s"/url/$id",
        andExpect = MovedPermanently,
        withLocation = link)
    }

    "get all" in {
      server.httpGet(
        path = "/url",
        andExpect = Ok,
        withBody = s"""{"id":"$id", "link":"$link"}""")
    }
  }
}
