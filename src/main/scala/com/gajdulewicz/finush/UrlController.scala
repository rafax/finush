package com.gajdulewicz.finush

import javax.inject.{Inject, Singleton}

import com.gajdulewicz.finush.services.UrlStorage
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.validation.NotEmpty
@Singleton
class UrlController @Inject()(urlStorage: UrlStorage) extends Controller {

  get("/") { request: Request =>
    info("ping")
    "Welcome to Finush"
  }

  get("/url/:id") { request: Request =>
    val id = request.params("id")
    info("You looked up " + id)
    val url = urlStorage.get(id)
    response.movedPermanently.location(url.link)
  }

  get("/url") { request: Request =>
    info("You asked for all")
    urlStorage.getAll()
  }

  post("/url") { lr: LinkRequest =>
    info("You wanted to create a link")
    urlStorage.create(lr.link)
  }

}

case class LinkRequest(@NotEmpty link: String)

