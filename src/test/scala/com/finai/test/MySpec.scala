package com.finai.test

import org.specs2.mutable._

class MySpec extends Specification {

  "HelloWorldUnit" should {
    "contain 11 characters" in {
      "Hello world" must have size(11)
    }
    "start with 'Hello'" in {
      "Hello world" must startWith("Hello")
    }
    "end with 'world'" in {
      "Hello world" must endWith("world")
    }
    "hello is the value" in {
       "hello".equals("hello")
    }
  }
}
