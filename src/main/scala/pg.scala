package pg

import shapeless._, record._, ops.record._, ops.hlist.ToList,
  labelled.FieldType, ops.hlist.LeftFolder

case class A(b:Int)

object H {
  def iter[B, T <: HList, K <: HList, V <: HList](b:B)
    (implicit
      lgen: LabelledGeneric.Aux[B, T],
      keys: Keys.Aux[T, K],
      values: Values.Aux[T, V],
      ktl: ToList[K, Any],
      vtl: ToList[V, Any]
    ) = {
    val b1 = lgen.to(b)
    val out = ((b1.keys.toList map {s => s.toString.tail}) zip b1.values.toList)
    Map(out.unzip)
  }


  def iter2[T, K <: Symbol, V, G <: HList](a:T)
    (implicit
      g: LabelledGeneric.Aux[T, G],
      tomap: ops.record.ToMap.Aux[G, K, V]
    ) = {
    val l = LabelledGeneric[T]
    val lta = l.to(a)
    println(s"LG: $l")
    println(s"LL: ${lta.toMap}")
    // ERR: could not find implicit value for parameter toMap: shapeless.ops.record.ToMap.Aux[l.Repr,K,V]
    lta.toMap
  }

  def iter3[B, T <: HList, K <: Symbol, V](b:B)
    (implicit
      lgen: LabelledGeneric.Aux[B, T],
      /*keys: Keys.Aux[T, K],
      values: Values.Aux[T, V],*/
      tm: ToMap.Aux[T, K, V]
    ) = {
    val b1 = LabelledGeneric[B].to(b)
    val out = b1.toMap
  }

  object fill extends Poly {
    implicit def hnil = use((out:Map[String, Any], l: HNil) => out)

    implicit def hlist[K <:Symbol, V](implicit wit: Witness.Aux[K]) =
      use((out:Map[String, Any], l: FieldType[K, V]) => {
        out + (wit.value.name -> l)
      })
  }

  def iter4[B, L <: HList](a: B)
  (implicit
    gen: LabelledGeneric.Aux[B, L],
    lf: LeftFolder.Aux[L, Map[String, Any], fill.type, Map[String, Any]]
  ) = {
    val out:Map[String, Any] = Map()
    gen.to(a).foldLeft(out)(fill)
  }

}
