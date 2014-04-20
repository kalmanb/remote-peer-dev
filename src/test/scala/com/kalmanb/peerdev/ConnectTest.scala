package com.kalmanb.peerdev

import com.kalmanb.test.TestSpec
import spray.json._
import DefaultJsonProtocol._

class ConnectTest extends TestSpec {

  describe("connection test") {

    it("should support connnction") {
      val request =
        """{ "username": "user", 
      "platform": "linux2", 
      "secret": "xxx", 
      "client": "Sublime Text 2", 
      "room": "room1", 
      "version": "0.11", 
      "supported_encodings": ["utf8", "base64"], 
      "room_owner": "user", 
      "api_key": "xxx"}""".parseJson

      println(request)

      val response = """{"anon_perms":["get_buf","ping","pong"],
      "bufs":{
        "1":{"path":"README.md","id":1,"md5":"abc","encoding":"utf8"}
      },
        "max_size":52428800,
        "owner":"user",
        "room_name":"test1",
        "secret":false,
        "server_id":"server1",
        "temp_data":{},
        "terms":{},
        "tree":{"README.md":1},
        "users":{
          "178":{
            "client":"Sublime Text 2",
            "user_id":178,
            "is_anon":false,
            "perms":["get_buf","ping","pong","kick","pull_repo","perms","request_perms","patch","set_buf","create_buf","delete_buf","rename_buf","set_temp_data","delete_temp_data","highlight","msg","datamsg","create_term","term_stdin","delete_term","update_term","term_stdout","saved"],
            "platform":"linux2",
            "username":"user",
            "version":0.11 }
         },
         "perms":["get_buf","ping","pong","kick","pull_repo","perms","request_perms","patch","set_buf","create_buf","delete_buf","rename_buf","set_temp_data","delete_temp_data","highlight","msg","datamsg","create_term","term_stdin","delete_term","update_term","term_stdout","saved"],
         "user_id":178,
         "name":"room_info"}""".parseJson

      println(response)

      case class Buffer(
        id: Int,
        path: String,
        md5: String,
        encoding: String = "utf8")

      case class Tree(
        filename: String,
        id: Int)

      case class ConnectionResponse(
        bufs: List[Buffer],
        max_size: Int,
        owner: String,
        room_name: String,
        secret: Boolean,
        server_id: String,
        temp_data: List[String] = List.empty,
        terms: List[String] = List.empty,
        tree: List[Tree])

      object MyJsonProtocol extends DefaultJsonProtocol {
        implicit val bufferFormat = jsonFormat4(Buffer)
        implicit val treeFormat = jsonFormat2(Tree)
        implicit val connectionResponseFormat = jsonFormat9(ConnectionResponse)
      }
      import MyJsonProtocol._

      val test = ConnectionResponse(
        List(Buffer(1, "/path", "abc")),
        max_size = 52428800,
        owner = "user",
        room_name = "test1",
        secret = false,
        server_id = "server1",
        tree = List.empty
      )

      println("---------------------------")
      println(test.toJson)

    }
  }
}
