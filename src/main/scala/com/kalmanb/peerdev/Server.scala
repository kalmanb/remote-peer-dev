package com.kalmanb.peerdev

import akka.actor._
import akka.util.ByteString
import akka.io.IO
import akka.io.Tcp
import java.net.InetSocketAddress

class Server(handler: ActorRef) extends Actor {

  import akka.io.Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8888))

  def receive = {
    case b @ Bound(localAddress) ⇒
      println(s"Server running on $localAddress")

    case CommandFailed(_: Bind)  ⇒ context stop self

    case c @ Connected(remote, local) ⇒
      val connection = sender()
      connection ! Register(handler)
  }
}
