import scala.collection.mutable.ArrayBuffer

object Observable2 {

  var subj1:Subject[Int] = new Subject[Int]
  var subj2:Subject[Int] = new Subject[Int]

  def merge(s1: Subject[Int], s2: Subject[Int]): Observable2[Int] = {
      this.subj1 = s1
      this.subj2 = s2
    var obs = new Observable2[Int]
    obs.function = s1 => ()
    obs
  }

  def create(f: (Subscriber2[Any] => Unit)): Observable2[Any] = {
    //var subscriber2 = new Subscriber2[Any] {}
    val observable = new Observable2[Any]
    //observable.subscribers.append(subscriber2)
    //f.apply(subscriber2)
    observable.function = f
    observable
  }

  def from(params: Any*): Observable2[Any] = {

    val o = Observable2.create({ subscriber =>
      try {

        params.foreach { p =>
          // se emite el elemento
          subscriber.onNext(p)
        }
        // se completa exitosamente
        subscriber.onCompleted()
      } catch {
        //si hubo un fallo se emite el error
        case e: Exception => subscriber.onError(e)
      }
    })

    /*o.subscribe({ n =>
      println(n)
    })*/

    params.foreach { p =>
      o.elementosEmitidos.append(p)
    }

    return o

  }

  def just(arg: Any): Observable2[Any] = {
    val o = Observable2.create ({ subscriber =>
      try {
        subscriber.onNext(arg)

        // se completa exitosamente
        subscriber.onCompleted()
      } catch {
        //si hubo un fallo se emite el error
        case e: Exception => subscriber.onError(e)
      }
    })
    o.subscribe({ n =>
      println(n)
    })
    return o
  }


}
class Observable2[param] {

  var function: (Subscriber2[Any] => Unit) = null
  var elementosEmitidos: ArrayBuffer[Any] = new ArrayBuffer[Any]()

  def subscribe(f: (Any => Unit)): Unit = {
    Observable2.subj1.subscribe(f)
    Observable2.subj2.subscribe(f)
    val s = new Subscriber2[Any] {
        override def onNext(n: Any): Unit = {
          f.apply(n)
        }

        override def onError(t: Throwable): Unit = {}
        override def onCompleted(): Unit = {}
    }

    function.apply(s)
  }

  def map(f: (Any => Any)): Observable2[Any] = {

    var res = new ArrayBuffer[Any]()

    this.elementosEmitidos.foreach { p =>
      res.append(f.apply(p))
    }

    val obs = Observable2.create { subscriber =>

      try {
        res.foreach { num =>
          subscriber.onNext(num)
        }
        subscriber.onCompleted()
      } catch {
        case e:Exception => subscriber.onError(e)
      }
    }

    return obs

  }

  def scan(f: ((Any,Any) => Any)): Observable2[Any] = {

    var elementosAEmitir = new ArrayBuffer[Any]()

    var indice:Int = 0

    if(this.elementosEmitidos.length == 1){

      elementosAEmitir.append(elementosEmitidos(0))

    } else {

      while(indice <= (this.elementosEmitidos.length)-1){

        if(indice == 0){

          var fristY = this.elementosEmitidos(indice)

          elementosAEmitir.append(f.apply(fristY,fristY))

          indice = indice + 1

        } else {

          var x = elementosAEmitir.last
          var y = this.elementosEmitidos(indice)

          elementosAEmitir.append(f.apply(x, y))

          indice = indice + 1
        }
      }

    }

    val obs = Observable2.create { subscriber =>

      try {
        elementosAEmitir.foreach { num =>
          subscriber.onNext(num)
        }
        subscriber.onCompleted()
      } catch {
        case e:Exception => subscriber.onError(e)
      }
    }

    return obs

  }

  def distinct() : Observable2[Any] = {

    var elementosAEmitir = this.elementosEmitidos.distinct

    val obs = Observable2.create { subscriber =>

      try {
        elementosAEmitir.foreach { num =>
          subscriber.onNext(num)
        }
        subscriber.onCompleted()
      } catch {
        case e:Exception => subscriber.onError(e)
      }
    }

    return obs

  }

  def first() : Observable2[Any] = {

    var elem = this.elementosEmitidos(0)

    val obs = Observable2.create { subscriber =>

      try {
        subscriber.onNext(elem)
        subscriber.onCompleted()
      } catch {
        case e:Exception => subscriber.onError(e)
      }
    }

    return obs
  }

  def last() : Observable2[Any] = {

    var elem = this.elementosEmitidos.last

    val obs = Observable2.create { subscriber =>

      try {
        subscriber.onNext(elem)
        subscriber.onCompleted()
      } catch {
        case e:Exception => subscriber.onError(e)
      }
    }

    return obs
  }

  def skip(n:Int) : Observable2[Any] = {

    var elementosAEmitir = this.elementosEmitidos.drop(n)

    val obs = Observable2.create { subscriber =>

      try {
        elementosAEmitir.foreach { num =>
          subscriber.onNext(num)
        }
        subscriber.onCompleted()
      } catch {
        case e:Exception => subscriber.onError(e)
      }
    }

    return obs
  }

  def take(n:Int) : Observable2[Any] = {

    var elementosAEmitir = this.elementosEmitidos.take(n)

    val obs = Observable2.create { subscriber =>

      try {
        elementosAEmitir.foreach { num =>
          subscriber.onNext(num)
        }
        subscriber.onCompleted()
      } catch {
        case e:Exception => subscriber.onError(e)
      }
    }

    return obs
  }


  def filter(p: (Any) ⇒ Boolean) : Observable2[Any] = {

    var elementosAEmitir = this.elementosEmitidos.filter(p)

    val obs = Observable2.create { subscriber =>

      try {
        elementosAEmitir.foreach { num =>
          subscriber.onNext(num)
        }
        subscriber.onCompleted()
      } catch {
        case e:Exception => subscriber.onError(e)
      }
    }

    return obs
  }

    //def from(params: Any*):Observable2[param] ={

    /*this.create({ subscriber =>
      try {

        val paramsSize = params.length

        params.foreach { p =>
          // se emite el elemento
          subscriber.onNext(p)
        }
        // se completa exitosamente
        subscriber.onComplete()
      } catch {
        //si hubo un fallo se emite el error
        case e:Exception => subscriber.onError(e)
      }
    })
    this.subscribe({ n =>
      println(n)
    })
    this

    */
    /*
  def just(arg: param): Observable2[param] = {
    this.create({ subscriber =>
      try {
        subscriber.onNext(arg)

        // se completa exitosamente
        subscriber.onComplete()
      } catch {
        //si hubo un fallo se emite el error
        case e: Exception => subscriber.onError(e)
      }
    })
    this.subscribe({ n =>
      println(n)
    })
    this
  }

  def empty(): Unit = {
    this.create({ subscriber =>
      try {
        subscriber.onNext()
        // se completa exitosamente
        subscriber.onComplete()
      } catch {
        //si hubo un fallo se emite el error
        case e: Exception => subscriber.onError(e)
      }
    })
    this.subscribe({ n =>
      println(n)
    })
  }

  def never(): Unit = {
    this.create({ subscriber =>
      try {
        while (true) {
          println("Nunca Termina,termine el proceso por favor")
          subscriber.onNext()
          // se completa exitosamente
        }
        subscriber.onComplete()
      } catch {
        //si hubo un fallo se emite el error
        case e: Exception => subscriber.onError(e)
      }
    })
    this.subscribe({ n =>
      println(n)
    })
  }

  def error(throwable: Throwable): Unit = {
    this.create({ subscriber =>
      try {
        throwable
        subscriber.onNext()
        // se completa exitosamente
        subscriber.onComplete()
      } catch {
        //si hubo un fallo se emite el error
        case throwable: Throwable => subscriber.onError(throwable)
      }
    })
    this.subscribe({ n =>
      println(n)
    })
  }

  def repeat(param: Int): Observable2[param] = {
    this.create({ subscriber =>
      try {

        1 to param foreach {
          subscriber.onNext(_)
        }

        /*var indice = 0;
        while (indice < param){
          this.from()
          indice = indice + 1
        }*/

        // se completa exitosamente
        //subscriber.onComplete()
      } catch {
        //si hubo un fallo se emite el error
        case e: Exception => subscriber.onError(e)
      }
    })
    this.subscribe({ n =>
      println(n)
    })
    this
  }*/


}

