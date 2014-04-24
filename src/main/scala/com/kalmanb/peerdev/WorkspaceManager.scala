package com.kalmanb.peerdev

import java.io.File
import akka.actor.Actor

class WorkspaceManager(workspaceDir: File) extends Actor {

  val files: List[Buf] = workspaceDir.listFiles().toList map (file ⇒
    Buf(file.getName, FileUtils.getContent(file))
  )

  def receive = {
    case _ ⇒
  }
}

object Buf {
  def apply(path: String, content: String) = new Buf(path, content)
}
case class Buf(
    path: String,
    content: String,
    md5: String) {
  def this(path: String, content: String) = this(path, content, FileUtils.md5(content))
}
