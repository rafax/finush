package com.gajdulewicz.finush.modules

import com.gajdulewicz.finush.services.{MemoryUrlStorage, UrlStorage}
import com.twitter.inject.TwitterModule

object StorageModule extends TwitterModule {

  bind[UrlStorage].to[MemoryUrlStorage]
}
