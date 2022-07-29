@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.sqrt
import kotlin.math.atan2
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos


@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = sqrt(dx * dx + dy * dy)

  val radiant: Double
    get() = atan2(dy, dx)

  val degree: Double
    get() = Math.toDegrees(this.radiant)

  val unit: Vector2D
    get() = Vector2D(dx / this.magnitude, dy / this.magnitude)

  val normal: Vector2D
    get() = Vector2D(this.unit.dy, -1 * this.unit.dx )

  operator fun times(scalar: Double): Vector2D {

    return Vector2D(dx * scalar, dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    val novoVetor: Vector2D = Vector2D(this.dx / scalar, this.dy / scalar)
    return novoVetor
  }

  operator fun times(v: Vector2D): Double {
    val result: Double = dx * v.dx + dy * v.dy
    return result
  }

  operator fun plus(v: Vector2D): Vector2D {
    //soma de vetores
    val novoVetor: Vector2D = Vector2D(this.dx + v.dx, this.dy + v.dy)
    return novoVetor
  }

  operator fun plus(p: Point2D): Point2D {
    val novoPonto: Point2D = Point2D(this.dx + p.x, this.dy + p.y)
    return novoPonto
  }

  operator fun unaryMinus(): Vector2D {
    val novoVetor: Vector2D = Vector2D(this.dx * -1, this.dy * -1)
    return novoVetor
  }

  operator fun minus(v: Vector2D): Vector2D {
    val novoVetor: Vector2D = Vector2D(this.dx - v.dx, this.dy - v.dy)
    return novoVetor
  }

  fun scalarProject(target: Vector2D): Double {
    val ang: Double
    val angThis: Double
    val angTarget: Double

    if (target.dx == 0.0 || target.dy == 0.0){
      val valor: Double
      //colocamos dois if's encaixados pois o linter recomenda que a quantidade de returns
      //de uma função não exceda dois.
      if(target.dx == 0.0){
        valor = this.dy
      }else{
        valor = this.dx
      }
      return valor
    }

    if (this.radiant < 0){
      angThis = this.radiant + 2 * PI
    }else{
      angThis = this.radiant
    }
    if (target.radiant < 0){
      angTarget = target.radiant + 2 * PI
    }else{
      angTarget = target.radiant
    }
    ang = abs(angTarget - angThis)
    return this.magnitude * cos(ang)
  }

  fun vectorProject(target: Vector2D): Vector2D {
    val versor: Vector2D = target.unit
    return versor.times(this.scalarProject(target))
  }

}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(this * v.dx, this * v.dy)
}
