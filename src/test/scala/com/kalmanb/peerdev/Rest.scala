package com.kalmanb.peerdev

import com.kalmanb.test._

import akka.actor._
import akka.event.Logging
import scala.concurrent._
import scala.concurrent.duration._
//import spray.util._
//import spray.http._
//import spray.client.pipelining._
import scala.util.{ Failure, Success }

//Server
import akka.actor.Actor
import akka.io.IO
//import spray.can.Http

class Rest extends AkkaSpec {
  //import ExecutionContext.Implicits.global
  ////val log = Logging(system, getClass)
  //var server: ActorRef = _

  //before {
    //server = system.actorOf(Props[Server])
    //Thread sleep 1000
  //}

  //describe("rest test") {
    //it("should accept connections", IntTest) {
      ////client
      //val pipeline: HttpRequest ⇒ Future[HttpResponse] = sendReceive
      //val response: Future[HttpResponse] = pipeline(Get("http://localhost:8080"))

      //val result = Await.result(response, 1 second)
      //result.entity.asString should be("hi")
    //}
  //}

  //after {
    //server ! Server.Stop
  //}
//}
  
//object Server {
  //case object Stop
//}
//class Server extends Actor with MySslConfiguration {
  //import context.dispatcher
  //implicit val system = context.system

  //val handler = system.actorOf(Props[DemoService], name = "handler")
  //IO(Http) ! Http.Bind(handler, interface = "localhost", port = 8080)

  //def receive = {
    //case Server.Stop ⇒
      //sender ! Http.Close
      //system.scheduler.scheduleOnce(1.second) { system.shutdown() }
    //case e ⇒ println(e)
  //}
//}

//import spray.http.HttpMethods._

//class DemoService extends Actor {
  //def receive = {
    //case _: Http.Connected ⇒ sender() ! Http.Register(self)

    //case HttpRequest(GET, Uri.Path("/"), _, _, _) ⇒
      //sender() ! HttpResponse(entity = "hi")

    //case HttpRequest(GET, Uri.Path("/ping"), _, _, _) ⇒
      //sender() ! HttpResponse(entity = "PONG!")

    //case _ ⇒ println("oh")
  //}

//}

//import java.security.{ SecureRandom, KeyStore }
//import javax.net.ssl.{ KeyManagerFactory, SSLContext, TrustManagerFactory }
//import spray.io._

//// for SSL support (if enabled in application.conf)
//trait MySslConfiguration {

  //// if there is no SSLContext in scope implicitly the HttpServer uses the default SSLContext,
  //// since we want non-default settings in this example we make a custom SSLContext available here
  //implicit def sslContext: SSLContext = {
    //val keyStoreResource = "/ssl-test-keystore.jks"
    //val password = ""

    //val keyStore = KeyStore.getInstance("jks")
    //keyStore.load(getClass.getResourceAsStream(keyStoreResource), password.toCharArray)
    //val keyManagerFactory = KeyManagerFactory.getInstance("SunX509")
    //keyManagerFactory.init(keyStore, password.toCharArray)
    //val trustManagerFactory = TrustManagerFactory.getInstance("SunX509")
    //trustManagerFactory.init(keyStore)
    //val context = SSLContext.getInstance("TLS")
    //context.init(keyManagerFactory.getKeyManagers, trustManagerFactory.getTrustManagers, new SecureRandom)
    //context
  //}

  //// if there is no ServerSSLEngineProvider in scope implicitly the HttpServer uses the default one,
  //// since we want to explicitly enable cipher suites and protocols we make a custom ServerSSLEngineProvider
  //// available here
  //implicit def sslEngineProvider: ServerSSLEngineProvider = {
    //ServerSSLEngineProvider { engine ⇒
      //engine.setEnabledCipherSuites(Array("TLS_RSA_WITH_AES_256_CBC_SHA"))
      //engine.setEnabledProtocols(Array("SSLv3", "TLSv1"))
      //engine
    //}
  //}
}
