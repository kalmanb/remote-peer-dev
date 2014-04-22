package com.kalmanb.peerdev

import akka.actor._

class FileHandler() extends Actor {
  var current = "" 

  def receive = {
    case r: Patch.Request ⇒ current = PatchUtils.applyPatches(r.patch, current)
    case _                ⇒ throw new Exception
  }
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
