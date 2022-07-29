package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

// O raio de uma explosão entre um míssil e um asteróide será igual ao raio do asteróide
// Uma explosão não terá massa e nem velocidade, ela acontece no momento da colisão entre estes

class Explosion (
  val isTriggered: Boolean,
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double
): SpaceObject("Explosion", '#', initialPosition, initialVelocity, radius, mass)
