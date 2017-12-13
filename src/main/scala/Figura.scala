object Figura {

/*  def create(x:Double, y:Double) : Figura[Double] = {
    var f = new Figura[Double]
    f.x = x
    f.y = y
    return f

  }*/

  def trasladarParcial(x:Double) (y:Double) (figura: Figura): Figura = figura match {
    case c@Circulo(_, _, _) => c.copy(x,y)


  }
  /*

  def moverParcial(x:Double) (y:Double) (figura: Figura[Double]) = {
    figura.mover(x,y)
  }

  def escalarParcial(prop: Double) (figura: Figura[Double]) = {
    figura.escalar(prop)
  }

  def moverX(x:Double) = {
    moverParcial(x) _
  }

  /*def moverY(y:Double) = {
    moverParcial _ (y)
  }*/

  def trasladarXeY(n: Double) = {
    trasladarParcial(n)(n) _
  }

  def duplicar() = {
    escalarParcial(2) _
  }

  def cuadruplicar() = {
    escalarParcial(4) _
  }*/

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

