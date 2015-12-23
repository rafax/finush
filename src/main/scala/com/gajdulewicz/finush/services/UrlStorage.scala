package com.gajdulewicz.finush.services

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.{Inject, Singleton}

import com.gajdulewicz.finush.model.Url

import scala.collection.JavaConversions._

trait UrlStorage {
  def get(id: String): Url

  def create(long: String): Url

  def getAll(): Seq[Url]
}

@Singleton
class MemoryUrlStorage @Inject()() extends UrlStorage {

  val cnt = new AtomicInteger(0)
  val store = new ConcurrentHashMap[String, Url]

  override def get(id: String): Url = {
    store.get(id)
  }

  override def create(long: String): Url = {
    val id = cnt.incrementAndGet()
    val url = Url(id.toString, long)
    store.put(url.id, url)
    url
  }

  override def getAll(): Seq[Url] = {
    store.values().toSeq
  }
}
