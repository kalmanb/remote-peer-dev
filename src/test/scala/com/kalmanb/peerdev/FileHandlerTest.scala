package com.kalmanb.peerdev

import com.kalmanb.test._
import akka.testkit._
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

class FileUtils {
  def read(file: File): String = ""
}

