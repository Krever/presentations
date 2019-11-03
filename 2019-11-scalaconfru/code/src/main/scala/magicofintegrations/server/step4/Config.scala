package magicofintegrations.server.step4

import ciris.ConfigValue

case class Config(api: Config.Api, db: Config.Db)

object Config {

  case class Api(address: String, port: Int)

  case class Db(jdbcUrl: String, username: Option[String], password: Option[String])

  def value(): ConfigValue[Config] = {
    import ciris._
    import cats.syntax.all._
    val apiConfig: ConfigValue[Api] = (
      env("MAGIC_API_ADDRESS")
        .or(prop("magic.api.address"))
        .default("localhost"),
      env("MAGIC_API_PORT")
        .or(prop("magic.api.port"))
        .as[Int]
        .default(8080),
    ).parMapN(Config.Api)

    val dbConfig: ConfigValue[Db] = (
      env("MAGIC_DB_JDBCURL")
        .or(prop("magic.db.jdbcUrl"))
        .default("jdbc:h2:mem:MyDatabase;DB_CLOSE_DELAY=-1"),
      env("MAGIC_DB_USERNAME")
        .or(prop("magic.db.username"))
        .option,
      env("MAGIC_DB_PASSWORD")
        .or(prop("magic.db.password"))
        .option,
    ).parMapN(Config.Db)

    (apiConfig, dbConfig).parMapN(Config.apply)
  }

}
