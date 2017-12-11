
object Subject {

  val subj1:Subject[Any] = new Subject[Any]
  val subj2:Subject[Any] = new Subject[Any]

  def create [Any] :Subject[Any] = {
    val s = new Subject[Any]
    return s
  }

  def create (s1: Subject[Any],s2: Subject[Any]) :Subject[Any] = {
    val subj1 = s1
    val subj2 = s2
    val s = new Subject[Any]
    return s
  }

}


class Subject [T] extends Observable2 [T] with Subscriber2 [T] {

  var subs: Subscriber2[Any] = new Subscriber2[Any] {}

  def observable: Subject[T] = {
    return this
  }

  def subscriber: Subscriber2[T] = {
    return this.subs
  }

  override def subscribe(f: (Any => Unit)) {
    var s = new Subscriber2[Any] {
      override def onNext(n: Any): Unit = {
        f.apply(n)
      }

      override def onError(t: Throwable): Unit = {}

      override def onCompleted(): Unit = {}

    }
    this.subs = s
  }

  override def onNext(t: Any): Unit = {
    this.subs.onNext(t)

  }

  override def onCompleted(): Unit = {
    this.subs.onCompleted()

  }

  override def onError(e: Throwable): Unit = {
    this.subs.onError(e)

  }

}





