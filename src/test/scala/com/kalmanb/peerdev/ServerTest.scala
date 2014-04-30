package com.kalmanb.peerdev

import com.kalmanb.test.AkkaSpec
import java.net.InetSocketAddress
import akka.actor._

class ServerTest extends AkkaSpec {

  describe("server") {
    it("should accept client connections") {
      val server = system.actorOf(Props(new Server))

      val remote = new InetSocketAddress("localhost", 8888)
      val replies = system.actorOf(Props(new Actor {
        def receive = {
          case a ⇒ println("aaaaaa " + a)
        }
      }))
      val client = system.actorOf(Props(new Client(remote, replies)))

      Thread sleep 3000
      system.shutdown
    }
  }
}

import akka.actor.Actor
import akka.actor.Props
import akka.util.ByteString
import akka.io.IO
import akka.io.Tcp

class Server extends Actor {

  import akka.io.Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8888))

  def receive = {
    case b @ Bound(localAddress) ⇒
    // do some logging or setup ...

    case CommandFailed(_: Bind)  ⇒ context stop self

    case c @ Connected(remote, local) ⇒
      val handler = context.actorOf(Props[SimplisticHandler])
      val connection = sender()
      connection ! Register(handler)
  }
}

class SimplisticHandler extends Actor {
  import Tcp._
  def receive = {
    case Received(data: ByteString) ⇒
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
