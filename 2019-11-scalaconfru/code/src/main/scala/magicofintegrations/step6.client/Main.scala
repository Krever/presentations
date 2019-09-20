package magicofintegrations.step6.client

import cats.syntax.all._
import com.monovore.decline._
import magicofintegrations.model.NoteV1
import magicofintegrations.step5
object Main
    extends CommandApp(
      name   = "magic",
      header = "Notes manager",
      main = {
        step5.client.Command.opts.map({
          case step5.client.Command.GetNotes(url, port) =>
            println(s"Get from $url $port")
          case step5.client.Command.AddNote(url, port, note) =>
            println(s"Add $note at $url $port")
        })
      },
    )
