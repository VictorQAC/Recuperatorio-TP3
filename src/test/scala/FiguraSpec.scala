import scala.collection.mutable.ArrayBuffer
import org.scalatest
import org.scalatest.FlatSpec

import scala.util.Try

class StackSpec extends FlatSpec {

  val circulo    = new Circulo(2,3,4)
  val rectangulo = new Rectangulo(4,9,3,6)
  val linea      = new Linea(4,7,7,10)
  var figuras    = new ArrayBuffer[Figura]()
  var motor      = new Motor

  /*"Prueba" should "Figura creacion" in {

    val figura = Figura.create(2,3)

    println(figura.x)
  }*/

  "Prueba" should "Figuras se trasladar" in {

    val circuloTras    = Figura.trasladar(5)(6)(circulo)
    val rectanguloTras = Figura.trasladar(9)(15)(rectangulo)
    val lineaTras      = Figura.trasladar(6)(9)(linea)

    assert(circuloTras.x == 5)
    assert(circuloTras.y == 6)
    assert(rectanguloTras.x == 9)
    assert(rectanguloTras.y == 15)
    assert(lineaTras.x == 6)
    assert(lineaTras.y == 9)
  }

  "Prueba" should "Figura mover" in {

    val circuloMover    = Figura.mover(5)(6)(circulo)
    val rectanguloMover = Figura.mover(9)(15)(rectangulo)
    val lineaMover      = Figura.mover(6)(9)(linea)

    assert(circuloMover.x == 7)
    assert(circuloMover.y == 9)
    assert(rectanguloMover.x == 13)
    assert(rectanguloMover.y == 24)
    assert(lineaMover.x == 10)
    assert(lineaMover.y == 16)
  }

  "Prueba" should "Figura moverX" in {

/*
    assert(circulo.x == 2)
    assert(rectangulo.x == 4)
    assert(linea.x == 4)
*/

    val moverAlOrigen = Figura.moverX(1)

    val circuloOrigen = moverAlOrigen(0)(circulo)
    val rectanguloOrigen = moverAlOrigen(0)(rectangulo)
    val lineaOrigen = moverAlOrigen(0)(linea)

    assert(circuloOrigen.x == 3)
    assert(rectanguloOrigen.x == 5)
    assert(lineaOrigen.x == 5)
  }

  "Prueba" should "Figura trasladarXeY" in {

    val trasXeY = Figura.trasladarXeY(3)

    val circuloTrasXeY = trasXeY(circulo)
    val rectanguloTrasXeY = trasXeY(rectangulo)
    val lineaTrasXeY = trasXeY(linea)

    assert(circuloTrasXeY.x == 3)
    assert(circuloTrasXeY.y == 3)
    assert(rectanguloTrasXeY.x == 3)
    assert(rectanguloTrasXeY.y == 3)
    assert(lineaTrasXeY.x == 3)
    assert(lineaTrasXeY.y == 3)
  }

  "Prueba" should "Figura DOBLE" in {

    val tras = Figura.mover(1)(1) _
    val dobleTraslacion = Figura.doble(tras) _
    val circulo2 = dobleTraslacion(circulo)

    assert(circulo2.x == 4)
    assert(circulo2.y == 5)
  }

  "Prueba" should "Motor getFiguras" in {

    motor.agregarFigura(circulo)
    motor.agregarFigura(rectangulo)

    println(motor.getFiguras())
  }

  "Prueba" should "Motor transformar" in {

    motor.agregarFigura(circulo)
    motor.transformar(Figura.trasladar(5)(6)_)
    println(motor.figuras)
    val moverAlOrigen = Figura.mover(-5)(-6)_
    motor.transformar(moverAlOrigen)
    println(motor.figuras)
  }

/*  "Prueba" should "Figura escalar" in {

    val circuloEsc    = Figura.escalar(0.5)(circulo)
    val rectanguloEsc = Figura.escalar(2)(rectangulo)
    val lineaEsc      = Figura.escalar(3)(linea)

    assert(circuloEsc == 2.5)
    assert(rectanguloEsc == 6)
    assert(rectangulo.alto == 12)
    assert(lineaEsc == 21)
    assert(linea.yFin == 30)
  }*/

  /*
  "Prueba" should "Figura mover parcial" in {

    assert(circulo.x == 2)
    assert(rectangulo.x == 4)
    assert(linea.x == 4)

    val moverAlOrigen = Figura.moverParcial(0)(0) _

    moverAlOrigen(circulo)
    moverAlOrigen(rectangulo)
    moverAlOrigen(linea)

    assert(circulo.x == 0)
    assert(rectangulo.x == 0)
    assert(linea.x == 0)
  }

  "Prueba" should "Figura escalar parcial" in {

    assert(circulo.radio == 5)
    assert(rectangulo.ancho == 3)
    assert(rectangulo.alto == 6)
    assert(linea.xFin == 7)
    assert(linea.yFin == 10)

    val duplicar = Figura.escalarParcial(2) _

    duplicar(circulo)
    duplicar(rectangulo)
    duplicar(linea)

    assert(circulo.radio == 10)
    assert(rectangulo.ancho == 6)
    assert(rectangulo.alto == 12)
    assert(linea.xFin == 14)
    assert(linea.yFin == 20)
  }

*/
}

