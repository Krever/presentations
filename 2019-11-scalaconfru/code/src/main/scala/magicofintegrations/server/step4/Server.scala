package magicofintegrations.server.step4

import cats.effect._
import magicofintegrations.server.step0
import magicofintegrations.server.step2
import org.http4s.HttpRoutes
import cats.syntax.all._

trait Server[F[_]] {
  def serve: F[Unit]
}

class ServerImpl[F[_]: ConcurrentEffect: Timer](cfg: Config.Api, routes: HttpRoutes[F]) extends Server[F] {

  def serve: F[Unit] = {
    import org.http4s.implicits._
    import org.http4s.server.Router
    import org.http4s.server.blaze._
    val httpApp       = Router("/" -> routes).orNotFound
    val serverBuilder = BlazeServerBuilder[F].bindHttp(cfg.port, cfg.address).withHttpApp(httpApp)
    serverBuilder.serve.compile.drain
  }
}
