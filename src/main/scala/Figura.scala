object Figura {

  /*  def create(x:Double, y:Double) : Figura[Double] = {
      var f = new Figura[Double]
      f.x = x
      f.y = y
      return f

    }*/

  def trasladar(x:Double) (y:Double) (figura: Figura): Figura = figura match {
    case c@Circulo(_, _, _) => c.copy(x,y)
    case r@Rectangulo(_,_,_,_) => r.copy(x,y)
    case l@Linea(_,_,_,_) => l.copy(x,y)
  }

  def mover(x:Double) (y:Double) (figura: Figura): Figura = figura match {
    case c@Circulo(_, _, _) => c.copy(c.x + x, c.y + y)
    case r@Rectangulo(_,_,_,_) => r.copy(r.x + x, r.y + y)
    case l@Linea(_,_,_,_) => l.copy(l.x + x, l.y + y)
  }

  def escalar(prop: Double) (figura: Figura) : Figura = figura match {
    case c@Circulo(_, _, radio) => c.copy(radio * prop)
    case r@Rectangulo(_,_,ancho,alto) => r.copy(ancho * prop, alto * prop)
    case l@Linea(_,_,xFin,yFin) => l.copy(xFin * prop, yFin * prop)
  }

  def moverX(x:Double) = {
    mover(x) _
  }

  def moverY(y:Double) = {
    //mover _ (y)
  }

  def trasladarXeY(n: Double) = {
    trasladar(n)(n) _
  }

  def duplicar() = {
    escalar(2) _
  }

  def cuadruplicar() = {
    escalar(4) _
  }

  def doble(tras: Figura => Figura) (figura: Figura) : Figura = {
    val res1 = tras.apply(figura)
    val res2 = tras.apply(res1)
    return res2
  }


}

abstract class Figura {

  val x: Double
  val y: Double


/*  def trasladar(x:Double, y:Double, figura: Figura): Figura ={
    var f = figura.copy()
    f.x = figura.x + f.x
    f.y = figura.y + f.y
    return f
  }*/

  /*def mover(x:Double, y:Double): Unit ={
    this.x = x
    this.y = y
    // tiene que devolver una instancia nueva
  }

  def escalar(prop: Double): Unit ={
    // tiene que devolver una instancia nueva
  }*/

}

