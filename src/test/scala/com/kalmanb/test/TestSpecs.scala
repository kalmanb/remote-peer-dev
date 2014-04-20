package com.kalmanb.test

import org.mockito.Mockito
import org.mockito.verification.VerificationMode
import org.scalatest._
import org.scalatest.matchers._
import org.scalatest.mock.MockitoSugar
import akka.testkit._
import akka.actor._ 

trait TestSpec extends FunSpecLike
  with Matchers
  with MockitoSugar             
  with MockitoWrapper
  with BeforeAndAfter
  with BeforeAndAfterEach
  with BeforeAndAfterAll

trait MockitoWrapper {
  def spy[T](mock: T) = Mockito.spy(mock)
  def verify[T](mock: T) = Mockito.verify(mock)
  def verify[T](mock: T, mode: VerificationMode) = Mockito.verify(mock, mode)
  def when[T](methodCall: T) = Mockito.when(methodCall)
  def never = Mockito.never
  def times(wantedNumberOfInvocations: Int) = Mockito.times(wantedNumberOfInvocations)
  def reset[T](mock: T) = Mockito.reset(mock)
}

trait AkkaTest extends TestKitBase with TestSpec {
  // this must be lazy because TestKitBase requires it to be available BEFORE the actor is run
  implicit lazy val system = ActorSystem("AkkaTestSystem")         
}

// Tagging
import org.scalatest.Tag
object IntTest extends Tag("com.kalmanb.test.IntTest")
