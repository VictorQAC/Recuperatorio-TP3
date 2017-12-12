object Circulo {

  def create(x:Double, y:Double,radio: Double) : Circulo[Double] = {
    var c = new Circulo[Double]
    c.x = x
    c.y = y
    c.radio = radio
    return c

  }

}

class Circulo[T] extends Figura [T] {

  var radio: Double = 0

  override def escalar(prop: Double): Unit ={
    this.radio = (this.radio * prop)
  }

}
