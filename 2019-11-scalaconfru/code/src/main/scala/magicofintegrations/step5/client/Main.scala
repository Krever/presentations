package magicofintegrations.step5.client

import cats.syntax.all._
import com.monovore.decline._
import magicofintegrations.model.Note

object Main
    extends CommandApp(
      name   = "notes",
      header = "Notes manager",
      main = {
        NotesCommand.opts.map({
          case NotesCommand.GetNotes(url, port) =>
            println(s"Get from $url $port")
          case NotesCommand.AddNote(url, port, note) =>
            println(s"Add $note at $url $port")
        })
      },
    )
