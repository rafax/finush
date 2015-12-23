package com.gajdulewicz.finush

import com.gajdulewicz.finush.model.Url
import com.twitter.finagle.http.Status.{MovedPermanently, Ok}
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class UrlFeatureTest extends FeatureTest {

  override val server = new EmbeddedHttpServer(new FinushServer)

  val link = "http://twitter.github.io/finatra"
  val id = 1
  val expectedUrl = Url(id.toString, link)

  "Server" should {
    "return welcome message for root" in {
      server.httpGet(
        path = "/",
        andExpect = Ok,
        withBody = "Welcome to Finush")
    }

    "post by id" in {
      val url = server.httpPostJson[Url](
        path = "/url",
        andExpect = Ok,
        postBody = s"""{"link":"$link"}"""
      )
      url should equal (expectedUrl)
    }

    "get by id" in {
      server.httpGet(
        path = s"/url/$id",
        andExpect = MovedPermanently,
        withLocation = link)
    }

    "get all" in {
      val urls = server.httpGetJson[Seq[Url]](
        path = "/url",
        andExpect = Ok)
      urls.size should be (1)
      urls should contain(expectedUrl)
    }
  }
}
