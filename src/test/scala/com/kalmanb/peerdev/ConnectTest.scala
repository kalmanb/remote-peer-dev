package com.kalmanb.peerdev

import com.kalmanb.test.TestSpec
import spray.json._
import DefaultJsonProtocol._

class ConnectTest extends TestSpec {

  describe("connection test") {

    it("should support connnction") {
      val request = """{
        "username": "user", 
        "platform": "linux2", 
        "secret": "xxx", 
        "client": "unix", 
        "room": "room1", 
        "version": "0.11", 
        "supported_encodings": ["utf8", "base64"], 
        "room_owner": "user", 
        "api_key": "xxx"}""".parseJson

      val response = """{
        "anon_perms":["get_buf","ping","pong"],
        "bufs":{
          "1":{"path":"README.md","id":1,"md5":"abc","encoding":"utf8"}
        },
          "max_size":52428800,
          "owner":"user",
          "room_name":"main-room",
          "secret":false,
          "server_id":"main-server",
          "temp_data":{},
          "terms":{},
          "tree":{"README.md":1},
          "users":{
            "178":{
              "client":"client",
              "user_id":178,
              "is_anon":false,
              "perms":["get_buf","ping","pong","kick","pull_repo","perms","request_perms","patch","set_buf","create_buf","delete_buf","rename_buf","set_temp_data","delete_temp_data","highlight","msg","datamsg","create_term","term_stdin","delete_term","update_term","term_stdout","saved"],
              "platform":"linux2",
              "username":"user",
              "version":0.11 }
          },
          "perms":["get_buf","ping","pong","kick","pull_repo","perms","request_perms","patch","set_buf","create_buf","delete_buf","rename_buf","set_temp_data","delete_temp_data","highlight","msg","datamsg","create_term","term_stdin","delete_term","update_term","term_stdout","saved"],
          "user_id":1,
          "name":"main-room"}""".parseJson

      object Connection {

        case class Buffer(
          id: Int,
          path: String,
          md5: String,
          encoding: String = "utf8")

        case class Tree(
          filename: String,
          id: Int)

        val perms = List("get_buf", "ping", "pong", "kick", "pull_repo", "perms", "request_perms", "patch", "set_buf", "create_buf", "delete_buf", "rename_buf", "set_temp_data", "delete_temp_data", "highlight", "msg", "datamsg", "create_term", "term_stdin", "delete_term", "update_term", "term_stdout", "saved")

        val anon_perms = List("get_buf", "ping", "pong")

        case class User(
          user_id: Int,
          username: String,
          client: String = "client",
          is_anon: Boolean = false,
          platform: String = "unix",
          version: Double = 0.11,
          perms: List[String] = perms)

        case class Response(
          bufs: Map[String, Buffer],
          owner: String,
          tree: List[Tree],
          users: Map[String, User],
          room_name: String = "main-room",
          max_size: Int = 52428800,
          secret: Boolean = false,
          server_id: String = "main-server",
          temp_data: List[String] = List.empty,
          terms: List[String] = List.empty,
          perms: List[String] = perms,
          anon_perms: List[String] = anon_perms)

        object MyJsonProtocol extends DefaultJsonProtocol {
          implicit val bufferFormat = jsonFormat4(Buffer)
          implicit val treeFormat = jsonFormat2(Tree)
          implicit val userFormat = jsonFormat7(User)
          implicit val connectionResponseFormat = jsonFormat12(Response)
        }
      }
      import Connection._
      import Connection.MyJsonProtocol._

      val test = Connection.Response(
        bufs = Map("1" -> Buffer(1, "README.md", "abc")),
        owner = "user",
        tree = List(Tree(filename = "README.md", id = 1)),
        users = Map("1" -> User(user_id = 1, username = "user"))
      )

      println("---------------------------")
      println(test.toJson)

    }
  }
}
