package com.kalmanb.peerdev

import com.kalmanb.test.AkkaSpec
import java.net.InetSocketAddress
import akka.actor._
import akka.testkit._

class HandlerTest extends AkkaSpec {

  describe("server") {
    it("should accept client connections") {
      val handler = system.actorOf(Props[SimplisticHandler])
      val server = system.actorOf(Props(new Server(handler)))

      val remote = new InetSocketAddress("localhost", 8888)
      val replies = TestActorRef(new AActor)
      val client = system.actorOf(Props(new Client(remote, replies)))

      awaitCondition("wait for it...") {
        replies.underlyingActor.gotIt should be("done")
      }
      println(replies.underlyingActor.gotIt)

      system.shutdown
    }
  }
}

class AActor extends Actor {
  var gotIt = "not yet"
  def receive = {
    case a ⇒
      println("aaaaaaa")
    //gotIt = true
  }
}

import akka.io.IO
import akka.io.Tcp
import akka.util.ByteString

class SimplisticHandler extends Actor {
  import Tcp._
  def receive = {
    case Received(data: ByteString) ⇒
      println(s"Server recieved : $data")
      sender() ! Write(data)
    case PeerClosed ⇒
      context stop self
  }
}

object Client {
  def props(remote: InetSocketAddress, replies: ActorRef) =
    Props(new Client(remote, replies))
}

class Client(remote: InetSocketAddress, listener: ActorRef) extends Actor {

  import Tcp._
  import context.system

  IO(Tcp) ! Connect(remote)

  def receive = {
    case CommandFailed(_: Connect) ⇒
      listener ! "connect failed"
      context stop self

    case c @ Connected(remote, local) ⇒
      listener ! c
      val connection = sender()
      connection ! Register(self)
      context become {
        case data: ByteString ⇒
          connection ! Write(data)
        case CommandFailed(w: Write) ⇒
          // O/S buffer was full
          listener ! "write failed"
        case Received(data) ⇒
          listener ! data
        case "close" ⇒
          connection ! Close
        case _: ConnectionClosed ⇒
          listener ! "connection closed"
          context stop self
      }
  }
}
