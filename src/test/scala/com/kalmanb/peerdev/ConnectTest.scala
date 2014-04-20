package com.kalmanb.peerdev

import com.kalmanb.test.TestSpec
import spray.json._
import DefaultJsonProtocol._

class ConnectTest extends TestSpec {

  describe("connection test") {

    it("should create a valid response based off samples") {

      val demoResponse = """{
        "name":"room_info",
        "user_id":1,
        "bufs":{
          "1":{"id":1, "path":"README.md","md5":"abc","encoding":"utf8"}
        },
        "owner":"user",
        "tree":{"README.md":1},
        "users":{
          "1":{
            "username":"user",
            "user_id":1,
            "client":"client",
            "is_anon":false,
            "platform":"unix",
            "version":0.11,
            "perms":["get_buf","ping","pong","kick","pull_repo","perms","request_perms","patch","set_buf","create_buf","delete_buf","rename_buf","set_temp_data","delete_temp_data","highlight","msg","datamsg","create_term","term_stdin","delete_term","update_term","term_stdout","saved"]
          }
        },
        "room_name":"main-room",
        "max_size":52428800,
        "secret":false,
        "server_id":"main-server",
        "temp_data":{},
        "terms":{},
        "perms":["get_buf","ping","pong","kick","pull_repo","perms","request_perms","patch","set_buf","create_buf","delete_buf","rename_buf","set_temp_data","delete_temp_data","highlight","msg","datamsg","create_term","term_stdin","delete_term","update_term","term_stdout","saved"],
        "anon_perms":["get_buf","ping","pong"]
      }""".parseJson

      import Connection._
      import Connection.MyJsonProtocol._

      val response = Connection.Response(
        name = "room_info",
        bufs = Map("1" -> Buffer(1, "README.md", "abc")),
        owner = "user",
        tree = Map("README.md"-> 1),
        user_id = 1,
        users = Map("1" -> User(user_id = 1, username = "user"))
      )

      response.toJson should be(demoResponse)
    }

    it("should return the correct values based on input") {
      val demoRequest = """{
        "username": "user", 
        "platform": "linux2", 
        "secret": "xxx", 
        "client": "unix", 
        "room": "room1", 
        "version": "0.11", 
        "supported_encodings": ["utf8", "base64"], 
        "room_owner": "user", 
        "api_key": "xxx"}""".parseJson
      // TBC
    }
  }
}
