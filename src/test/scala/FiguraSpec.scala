import org.scalatest
import org.scalatest.FlatSpec
import scala.util.Try

class StackSpec extends FlatSpec {

  val circulo    = Circulo.create(2,3,5)
  val rectangulo = Rectangulo.create(4,9,3,6)
  val linea      = Linea.create(4,7,7,10)

  "Prueba" should "Figura creacion" in {

    val figura = Figura.create(2,3)

    println(figura.x)
  }

  "Prueba" should "Figuras se trasladar" in {

    val circuloTras    = circulo.trasladar(3,3,circulo)
    val rectanguloTras = rectangulo.trasladar(5,6,rectangulo)
    val lineaTras      = linea.trasladar(2,2,linea)

    assert(circuloTras.x == 5)
    assert(circuloTras.y == 6)
    assert(rectanguloTras.x == 9)
    assert(rectanguloTras.y == 15)
    assert(lineaTras.x == 6)
    assert(lineaTras.y == 9)
  }

  "Prueba" should "Figura escalar" in {

    circulo.escalar(0.5)
    rectangulo.escalar(2)
    linea.escalar(3)

    assert(circulo.radio == 2.5)
    assert(rectangulo.ancho == 6)
    assert(rectangulo.alto == 12)
    assert(linea.xFin == 21)
    assert(linea.yFin == 30)
    //println(circulo.radio)
  }

}

