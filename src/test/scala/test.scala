package pg

import shapeless._, record._
import org.specs2.mutable.Specification


class Test extends Specification {
  "test case" should {
    "test1" >> {
      val a = A(0)
      val map = H.iter(a)
      println(s"TM: ${map}")
      1 must_== 1
    }
    "test2" >> {
      case class B(c:Int)
      val b = LabelledGeneric[B]
      val a = B(0)
      println(s"BT: ${b.to(a)} ${b.to(a).toMap}")
      1 must_== 1
    }
    "test3" >> {
      val a = A(0)
      val map = H.iter2(a)
      println(s"TM3: ${map}")
      1 must_== 1
    }
    "test4" >> {
      val a = A(0)
      val map = H.iter3(a)
      println(s"TM3: ${map}")
      1 must_== 1
    }
    "test5" >> {
      val a = A(0)
      val map = H.iter4(a)
      println(s"TM5 iter4: ${map}")
      1 must_== 1
    }
  }
}
