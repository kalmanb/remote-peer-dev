package com.kalmanb.peerdev

object GetBuf {
  case class Request(
    name: String = "get_buf",
    id: Int)

  case class Response(
    id: Int,
    path: String,
    md5: String,
    buf: String,
    encoding: String = "utf8",
    name: String = "get_buf")
}
