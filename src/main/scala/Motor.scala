import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

class Motor {

  var figuras = new ArrayBuffer[Figura]()

  def agregarFigura(figura: Figura): Unit ={
    this.figuras.append(figura)
  }

  def transformar(tras: Figura => Figura): Unit ={

    var resCam = new ArrayBuffer[Figura]()

    this.figuras.foreach{ f =>
      resCam.append(tras.apply(f))
    }

    this.figuras = resCam
  }

  def getFiguras() : String = {
    var res = "["

    this.figuras.foreach {
      case c@Circulo(x, y, r) => res = res + "[Circulo(" + x + "," + y + "," + r + ")]"
      case r@Rectangulo(x, y, alto, ancho) => res = res + "[Rectangulo(" + x + "," + y + "," + alto + ")]"
      case l@Linea(x, y, xFin, yFin) => res = res + "[Linea(" + x + "," + y + "," + xFin + ")]"
    }

    return res
  }

}
