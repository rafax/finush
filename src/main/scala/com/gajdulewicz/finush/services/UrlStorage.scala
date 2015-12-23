package com.gajdulewicz.finush.services

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.{Inject, Singleton}
import com.gajdulewicz.finush.model.Url
import Url.Id
import com.gajdulewicz.finush.model.Url

import scala.collection.JavaConversions._

trait UrlStorage {
  def get(id: Id): Url

  def create(long: String): Id

  def getAll(): Seq[Url]
}

@Singleton
class MemoryUrlStorage @Inject()() extends UrlStorage {

  val cnt = new AtomicInteger(0)
  val store = new ConcurrentHashMap[String, Url]

  override def get(id: String): Url = {
    store.get(id)
  }

  override def create(long: String): Id = {
    val id = cnt.incrementAndGet()
    val url = Url(id.toString, long)
    store.put(url.id, url)
    url.id
  }

  override def getAll(): Seq[Url] = {
    store.values().toSeq
  }
}
