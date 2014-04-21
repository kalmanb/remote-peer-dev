package com.kalmanb.peerdev

import com.kalmanb.test.TestSpec
import spray.json._
import DefaultJsonProtocol._

class SyncTest extends TestSpec {

  describe("remote synconisation") {
    it("should accept buffer requests") {
      val request = """{"name": "get_buf", "id": 1}""".toJson
      val response = """{
        "path":"numbers.txt",
        "id":3,
        "md5":"ba1f2511fc30423bdbb183fe33f3dd0f",
        "encoding":"utf8",
        "buf":"123\n",
        "name":"get_buf"}"""
    }
  }
}
