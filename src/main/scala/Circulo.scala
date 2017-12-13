object Circulo {

/*  def create(x:Double, y:Double,radio: Double) : Circulo = {
    var c = new Circulo
    c.x = x
    c.y = y
    c.radio = radio
    return c

  }*/


}

case class Circulo(x:Double,y:Double,radio:Double) extends Figura {

  //var radio: Double = 0

  /*override def escalar(prop: Double): Unit ={
    this.radio = (this.radio * prop)
  }*/

}
