package com.hfad.tokopediaclone.util

fun String?.defaultError(): String {
return this ?: Constants.DEFAULT_ERROR // CEK STRING NYA NULL ATAU GA KALAU DULL NGEMBALIIN DEFAULT ERROR
}