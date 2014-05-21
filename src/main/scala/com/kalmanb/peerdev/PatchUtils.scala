package com.kalmanb.peerdev

import com.sksamuel.diffpatch.DiffMatchPatch
//import com.sksamuel.diffpatch.DiffMatchPatch.Patch

object PatchUtils {
  val dmp = new DiffMatchPatch

  def applyPatches(patches: String, text: String): String = {
    val patchList = dmp.patch_fromText(patches).asInstanceOf[java.util.LinkedList[DiffMatchPatch.Patch]]
    (dmp.patch_apply(patchList, text)(0)).asInstanceOf[String]
  }
}

