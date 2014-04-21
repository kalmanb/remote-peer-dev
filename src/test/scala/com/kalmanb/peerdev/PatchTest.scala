package com.kalmanb.peerdev

import com.kalmanb.test.TestSpec

class PatchTest extends TestSpec {

  describe("DiffMatchPatch") {
    it("test it") {
      val start = "123\n"
      val end = "123\n4"

      import com.sksamuel.diffpatch.DiffMatchPatch
      val dmp = new DiffMatchPatch
      println(dmp.patch_make(start, end))
    }
  }
}
