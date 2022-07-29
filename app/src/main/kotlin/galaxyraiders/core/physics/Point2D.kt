@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

data class Point2D(val x: Double, val y: Double) {
  operator fun plus(p: Point2D): Point2D {
    val novoPonto: Point2D = Point2D(this.x+p.x, this.y+p.y)
    return novoPonto
  }

  operator fun plus(v: Vector2D): Point2D {
    val novoPonto: Point2D = Point2D(this.x+v.dx, this.y+v.dy)
    return novoPonto
  }

  override fun toString(): String {
    return "Point2D(x=$x, y=$y)"
  }

  fun toVector(): Vector2D {
    val novoVetor: Vector2D = Vector2D(this.x, this.y)
    return novoVetor
  }

  fun impactVector(p: Point2D): Vector2D {
    val vetorImpacto: Vector2D = Vector2D(Math.abs(this.x - p.x), Math.abs(this.y - p.y))
    return vetorImpacto
  }

  fun impactDirection(p: Point2D): Vector2D {
    return this.impactVector(p).unit
  }

  fun contactVector(p: Point2D): Vector2D {
    return this.impactVector(p).normal
  }

  fun contactDirection(p: Point2D): Vector2D {
    return this.impactVector(p).normal.unit
  }

  fun distance(p: Point2D): Double {
    val dx: Double = this.x - p.x
    val dy: Double = this.y - p.y
    val dist: Double = Math.sqrt(dx*dx + dy*dy)
    return dist
  }

}
