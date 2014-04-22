package com.kalmanb.peerdev

import com.kalmanb.test._
import akka.testkit._
import org.mockito.Matchers._
import java.io.File

class FileHandlerTest extends AkkaSpec {

  describe("file handler") {
    it("should apply patches") {
      def create(patch: String) = Patch.Request(
        user_id = 1,
        username = "",
        md5_before = "",
        md5_after = "",
        id = 1,
        path = "test.txt",
        patch = patch
      )
      //val fileUtils = mock[FileUtils]
      //when(fileUtils.read(any[File])).thenReturn("123\n")

      val fileHandler = TestActorRef(new FileHandler())

      fileHandler.underlyingActor.current = "123\n"

      fileHandler ! create("@@ -1,4 +1,5 @@\n 123%0A\n+4\n")
      fileHandler ! create("@@ -1,5 +1,6 @@\n 123%0A4\n+5\n")

      fileHandler.underlyingActor.current should be("123\n45")
    }

    it("should write changes to disk") {
    }
  }
}

import akka.actor._
class FileHandler() extends Actor {
  var current =  "" //fileUtils.read(new File(""))
  println(current)
  def receive = {
    case r: Patch.Request ⇒ current = PatchUtils.applyPatches(r.patch, current)
    case _                ⇒
  }
}

class FileUtils {
  def read(file: File): String = ""
}

case class FileDetails(
  path: String,
  id: Int)

object Patch {
  case class Request(
    user_id: Int,
    username: String,
    md5_before: String,
    md5_after: String,
    id: Int,
    path: String,
    patch: String,
    name: String = "patch")
}
