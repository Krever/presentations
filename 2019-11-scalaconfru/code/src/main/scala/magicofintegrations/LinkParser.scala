package magicofintegrations

import atto.Parser
import magicofintegrations.model.Link

object LinkParser {

  def get: Parser[Link] = {
    import atto._, Atto._
    import atto.parser._
    import cats.syntax.all._
    val dot   = char('.')
    val slash = char('/')
    for {
      _            <- opt(string("www") ~ dot)
      hostSegments <- sepBy(stringOf(letterOrDigit), dot)
      pathOpt      <- opt(slash ~> sepBy(stringOf(letterOrDigit), slash))
    } yield Link(hostSegments, pathOpt.getOrElse(List()))
  }

}
