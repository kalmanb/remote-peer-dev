package com.kalmanb.peerdev

import com.kalmanb.test.AkkaSpec
import akka.testkit._
import java.io.File

class WorkspaceManagerTest extends AkkaSpec {

  describe("workspace manager") {
    it("should load all files into FileHandlers") {
      // abc.txt and 123.txt
      val workspaceManager = TestActorRef(new WorkspaceManager(new File("./src/test/resources/workspace")))

      val files = workspaceManager.underlyingActor.files
      files should contain value (Buf("abc.txt", "", ""))
      files should contain value (Buf("123.txt", "", ""))
    }
  }
}
import akka.actor.Actor
class WorkspaceManager(workspaceDir: File) extends Actor {

  val listedFiles: List[File] = workspaceDir.listFiles().toList
  val files = listedFiles.foldLeft(List[(Int, Buf)]())((m, file: File) ⇒
    (m.size, Buf(file.getName, "", "")) :: m
  ).toMap
  def receive = {
    case _ ⇒
  }
}

case class Buf(
  path: String,
  content: String,
  md5: String)
