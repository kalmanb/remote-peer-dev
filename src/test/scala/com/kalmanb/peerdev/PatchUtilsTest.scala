package com.kalmanb.peerdev

import com.kalmanb.test.TestSpec

class PatchUtilsTest extends TestSpec {

  describe("patching") {
    it("it should apply patches correctly") {
      val orig = "123\n"
      val patch = "@@ -1,4 +1,5 @@\n 123%0A\n+4\n"
      val expected = "123\n4"

      val result = PatchUtils.applyPatches(patch, orig)

      result should be(expected)
    }
  }
}

