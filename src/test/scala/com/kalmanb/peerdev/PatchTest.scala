package com.kalmanb.peerdev

import com.kalmanb.test.TestSpec

class PatchTest extends TestSpec {

  describe("DiffMatchPatch") {
    it("it should apply patches correctly") {
      val orig = "123\n"
      val patch = "@@ -1,4 +1,5 @@\n 123%0A\n+4\n"
      val expected = "123\n4"

      import com.sksamuel.diffpatch.DiffMatchPatch
      import com.sksamuel.diffpatch.DiffMatchPatch.Patch
      val dmp = new DiffMatchPatch

      val patches = dmp.patch_fromText(patch).asInstanceOf[java.util.LinkedList[Patch]]
      val result = dmp.patch_apply(patches, orig)(0)

      result should be(expected)
    }
  }
}
