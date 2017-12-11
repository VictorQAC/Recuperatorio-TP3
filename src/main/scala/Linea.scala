case object Linea {

  def create(x:Double, y:Double, xFin: Double, yFin: Double) : Linea[Double] = {
    var l = new Linea[Double]
    l.x = x
    l.y = y
    l.xFin = xFin
    l.yFin = yFin
    return l

  }

}

class Linea[T] extends Figura [T] {

  var xFin: Double = 0
  var yFin: Double = 0

  def trasladar(x: Double, y: Double, figura: Linea[Double]): Linea[Double] = {
    val l = Linea.create(figura.x + x, figura.y + y, figura.xFin + x, figura.yFin + y)
    return l
  }

  def escalar(prop: Double): Unit ={
    this.x = (this.x * prop)
    this.y = (this.y * prop)
    this.xFin = (this.xFin * prop)
    this.yFin = (this.yFin * prop)
  }

}
