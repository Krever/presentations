package magicofintegrations.server.step0

import cats.effect.ConcurrentEffect
import cats.syntax.all._
import org.http4s.HttpRoutes

trait NotesController[F[_]] {
  def saveNote: HttpRoutes[F]
  def getNotes: HttpRoutes[F]

  def allRoutes(implicit c: ConcurrentEffect[F]): HttpRoutes[F] = saveNote <+> getNotes
}
