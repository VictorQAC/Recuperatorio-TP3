case object Rectangulo {

  /*def create(x:Double, y:Double, ancho: Double, alto: Double) : Rectangulo[Double] = {
    var r = new Rectangulo[Double]
    r.x = x
    r.y = y
    r.ancho = ancho
    r.alto = alto
    return r

  }*/

}

case class Rectangulo(x:Double, y:Double, ancho: Double, alto: Double) extends Figura {

 /* var ancho: Double = 0
  var alto: Double = 0

  override def escalar(prop: Double): Unit ={
    this.ancho = (this.ancho * prop)
    this.alto = (this.ancho * prop)
  }*/

}