import org.scalatest
import org.scalatest.FlatSpec
import scala.util.Try

class StackSpec extends FlatSpec {

  "Prueba" should "Nuevo Observable" in {

    val o = Observable2.create { subscriber =>

      try {
        Range(1,5).foreach { num =>
          // se emite el elemento
          subscriber.onNext(num)
        }
        // se completa exitosamente
        subscriber.onCompleted()
      } catch {
        //si hubo un fallo se emite el error
        case e:Exception => subscriber.onError(e)
      }
    }

    o.subscribe { n =>
      println(n)
    }

    }

  "Prueba" should "Metodo From" in {
    val strings:Observable2[Any] = Observable2.from("a", "b", "c")

    val list = List(1,2,3)
    val integers:Observable2[Any] = Observable2.from(list)
  }


  "Prueba" should "Metodo Just" in {
    //val o = new Observable2[String]
    //val oneObject:Observable2[String] = o.just("one object")
    val oneObject:Observable2[Any] = Observable2.just("one object")
  }

  /*
  "Prueba" should "Metodo Empty" in {
    val o = new Observable2
    o.empty()
  }


  "Prueba" should "Metodo Never" in {
    val o = new Observable2
    o.never()
  }

  "Prueba" should "Metodo Error" in {
    val o = new Observable2
    val t: Throwable = (throw new Exception)

    try {
      o.error(t)
    } catch {
      case e:Throwable => e
    }
  }

  "Prueba" should "Metodo Repeat" in {
    val o = new Observable2[Int]

    val listaObservable = o.from(1,2,3)
    val listaRepetible = listaObservable.repeat(3)

    listaRepetible.subscribe { n =>
      println(s"Hola $n!")
    }
  }
*/
  "Prueba" should "Subject" in {

    val subject = Subject.create[Int]
    //val subscriber = subject.subscriber
    //val observable = subject.observable

    subject.subscribe({ n =>
      println(s"Recibí $n")
    })
      /*{
        println(s"Completado sin errores")
      }*/

    subject.onNext(1) //Recibí 1
    subject.onNext(9) //Recibí 9
    subject.onCompleted() //Completado sin errores
  }

  "Prueba" should "Map" in {

    val listaObservable = Observable2.from(1,2,3)

    val listaIncrementada = listaObservable.map({n => n + "ANDA"})
    listaIncrementada.subscribe { n =>
      println(n)
    }

  }

  "Prueba" should "Distinct" in {

    val listaObservable = Observable2.from(1,1,7,2,3,2,4,1,5)
    val listaSumando = listaObservable.distinct()
    listaSumando.subscribe(println(_))
  }

  "Prueba" should "First" in {
    val listaObservable = Observable2.from(1,1,2,7,6,3,9,4,4,5)
    val listaSumando = listaObservable.first()
    listaSumando.subscribe(println(_))
  }

  "Prueba" should "Last" in {
    val listaObservable = Observable2.from(1,1,2,7,6,3,9,4,4,5)
    val listaSumando = listaObservable.last()
    listaSumando.subscribe(println(_))
  }

  "Prueba" should "Skip" in {
    val listaObservable = Observable2.from(1,1,2,7,6,3,9,4,4,5)
    val listaSumando = listaObservable.skip(5)
    listaSumando.subscribe(println(_))
  }

  "Prueba" should "Take" in {
    val listaObservable = Observable2.from(1,1,2,7,6,3,9,4,4,5)
    val listaSumando = listaObservable.take(3)
    listaSumando.subscribe(println(_))
  }

  "Prueba" should "filter" in {
    val listaObservable = Observable2.from(1,1,2,7,6,3,9,4,4,5)
    val listaSumando = listaObservable.filter(n => n==4)
    listaSumando.subscribe(println(_))
  }

  "Prueba" should "Merge" in {

    val s1 = Subject.create[Int]
    val s2 = Subject.create[Int]
    val merged = Observable2.merge(s1, s2) // también puede ser s1.mergeWith(s2)
    merged.subscribe(println(_))
    s1.onNext(1) // 1
    s2.onNext(2) // 2
    s2.onNext(3)
    }



}


