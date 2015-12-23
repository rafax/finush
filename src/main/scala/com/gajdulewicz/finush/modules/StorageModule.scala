package com.gajdulewicz.finush.modules

import com.gajdulewicz.finush.services.{MemoryUrlStorage, UrlStorage}
import com.twitter.inject.TwitterModule

object StorageModule extends TwitterModule {
  override def configure: Unit = {
    bind[UrlStorage].to[MemoryUrlStorage]
  }

}
