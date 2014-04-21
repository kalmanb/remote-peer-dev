package com.kalmanb.peerdev

import com.kalmanb.test.AkkaSpec
import spray.json._
import DefaultJsonProtocol._
import akka.actor._
import akka.testkit._

class RequestRouterTest extends AkkaSpec with ImplicitSender {

  describe("request router") {
    it("should route connection requests") {

      val request = """{"room":"test", "data":"xxx"}"""

      val connectionActor = TestActorRef(new ConnectionActor)
      val getBufActor = TestActorRef(new GetBufActor)
      val router = TestActorRef(new RequestRouter(connectionActor, getBufActor))

      router ! request

      expectMsgClass(classOf[Connection.Response])
    }

    it("should route get buf requests") {
      val request = """{"name":"get_buf", "data":"xxx"}"""

      val connectionActor = TestActorRef(new ConnectionActor)
      val getBufActor = TestActorRef(new GetBufActor)
      val router = TestActorRef(new RequestRouter(connectionActor, getBufActor))

      router ! request

      expectMsgClass(classOf[GetBuf.Response])
    }
  }
}

import akka.actor.Actor
import spray.json.JsString

class RequestRouter(connectionActor: ActorRef, getBufActor: ActorRef) extends Actor {
  def receive = {
    case json: String ⇒
      val request = json.parseJson
      val room = request.asJsObject.getFields("room") // Initial connction
      room.map(_ ⇒ connectionActor ! (request, sender()))

      val name = request.asJsObject.getFields("name") // all others
      name map (nameValue ⇒ nameValue match {
        case JsString("get_buf") ⇒ getBufActor ! (request, sender())
        case notHandled          ⇒ println(s"Could not find $notHandled")
      })

    case _ ⇒ connectionActor ! "h"
  }
}

class ConnectionActor extends Actor {
  def receive = {
    case (request: JsValue, originalSender: ActorRef) ⇒ originalSender ! Connection.Response(
      user_id = 1,
      bufs = null,
      owner = "",
      tree = null,
      users = null
    )
  }
}

class GetBufActor extends Actor {
  def receive = {
    case (request: JsValue, originalSender: ActorRef) ⇒
      originalSender ! GetBuf.Response(
        id = 999,
        path = "",
        md5 = "",
        buf = ""
      )
  }
}
