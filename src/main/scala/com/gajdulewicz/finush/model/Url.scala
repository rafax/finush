package com.gajdulewicz.finush.model

import com.gajdulewicz.finush.model.Url.Id

case class Url(id: Id, longUrl: String)

object Url {
  type Id = String
}