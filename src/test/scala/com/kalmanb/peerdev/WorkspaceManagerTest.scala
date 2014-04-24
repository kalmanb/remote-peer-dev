package com.kalmanb.peerdev

import com.kalmanb.test.AkkaSpec
import akka.testkit._
import java.io.File

class WorkspaceManagerTest extends AkkaSpec {

  describe("workspace manager") {
    it("should correctly load all files form workspace") {
      // abc.txt and 123.txt
      val workspaceManager = TestActorRef(new WorkspaceManager(new File("./src/test/resources/workspace")))

      val files = workspaceManager.underlyingActor.files

      files.size should be(2)
      files should contain(Buf("abc.txt", "abc\ndef\n", "f72fe788e136ba9e53518afa8b407eac"))
      files should contain(Buf("123.txt", "123\n456\n", "c010aff9dc6276fdb7efefd1a2757658"))
    }
  }
}

