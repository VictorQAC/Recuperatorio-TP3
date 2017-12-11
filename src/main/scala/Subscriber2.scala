import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

trait Subscriber2[-T] {

  /**
    * Recibe un elemento nuevo
    *
    * @param t el elemento emitido
    */
  def onNext(t: Any): Unit = {
    /*
    elements :+= t*/
  }

  /** t
    * Termina con error.
    *
    * @param t el throwable emitido
    */
  def onError(t: Throwable): Unit = {/*println("Ejecución con error")*/}

  /**
    * Termina con success.
    * No se enviarán más eventos
    */
  def onCompleted(): Unit = {
    /*
    println("Se completo exitosamente")*/
  }
}
