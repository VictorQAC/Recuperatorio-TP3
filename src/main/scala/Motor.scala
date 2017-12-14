import scala.collection.mutable.ArrayBuffer

case class Motor(figuras: ArrayBuffer[Figura]) {

  def agregarFigura(figura: Figura ): Unit ={
    this.figuras.append(figura)
  }

}
