package com.kalmanb.peerdev

import spray.json._
import DefaultJsonProtocol._

object Connection {
  case class Buffer(
    id: Int,
    path: String,
    md5: String,
    encoding: String = "utf8")


  val perms = List("get_buf", "ping", "pong", "kick", "pull_repo", "perms", "request_perms", "patch", "set_buf", "create_buf", "delete_buf", "rename_buf", "set_temp_data", "delete_temp_data", "highlight", "msg", "datamsg", "create_term", "term_stdin", "delete_term", "update_term", "term_stdout", "saved")

  val anon_perms = List("get_buf", "ping", "pong")

  case class User(
    username: String,
    user_id: Int,
    client: String = "client",
    is_anon: Boolean = false,
    platform: String = "unix",
    version: Double = 0.11,
    perms: List[String] = perms)

  case class Response(
    user_id: Int,
    bufs: Map[String, Buffer],
    owner: String,
    tree: Map[String, Int],
    users: Map[String, User],
    name: String = "room_info",
    room_name: String = "main-room",
    max_size: Int = 52428800,
    secret: Boolean = false,
    server_id: String = "main-server",
    temp_data: Map[String,String] = Map.empty,
    terms: Map[String,String] = Map.empty,
    perms: List[String] = perms,
    anon_perms: List[String] = anon_perms)

  object MyJsonProtocol extends DefaultJsonProtocol {
    implicit val bufferFormat = jsonFormat4(Buffer)
    implicit val userFormat = jsonFormat7(User)
    implicit val connectionResponseFormat = jsonFormat14(Response)
  }

}
