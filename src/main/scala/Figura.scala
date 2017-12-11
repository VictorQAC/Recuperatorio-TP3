object Figura {

  def create(x:Double, y:Double) : Figura[Double] = {
    var f = new Figura[Double]
    f.x = x
    f.y = y
    return f

  }
}


class Figura [T]{

  var x: Double = 0
  var y: Double = 0

  def trasladar(x:Double, y:Double, figura: Figura[Double]): Figura [Double] ={
    var f = Figura.create(figura.x + x,figura.y + y)
    return f
  }

  def mover(x:Double, y:Double): Unit ={
    this.x = x
    this.y = y
  }

}

