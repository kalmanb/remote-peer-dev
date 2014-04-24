package com.kalmanb.peerdev

import java.io.File

object FileUtils {
  def getContent(file: File): String = {
    val source = io.Source.fromFile(file)
    val content = source.mkString 
    source.close
    content
  }

  def md5(content: String): String = 
    org.apache.commons.codec.digest.DigestUtils.md5Hex(content)
}
