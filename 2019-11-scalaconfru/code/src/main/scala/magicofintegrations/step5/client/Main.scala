package magicofintegrations.step5.client

import cats.syntax.all._
import com.monovore.decline._
import magicofintegrations.model.NoteV1

object Main
    extends CommandApp(
      name   = "hello-world",
      header = "Says hello!",
      main = {
        val urlOpt = Opts
          .option[String]("url", help = "Server url")
          .orElse(Opts.env[String]("MAGIC_URL", "Server url"))
          .withDefault("localhost")

        val portOpt = Opts
          .option[Int]("port", help = "Server port")
          .orElse(Opts.env[Int]("MAGIC_PORT", "Server port"))
          .withDefault(8080)

        val noteOpt = {
          val titleOpt = Opts.option[String]("title", "Note title")
          val textOpt  = Opts.option[String]("text", "Note text").withDefault("")
          (titleOpt, textOpt).mapN(NoteV1.apply)
        }

        val getNotesCmdOpt =
          Opts.subcommand[Command]("get-notes", "Get notes")((urlOpt, portOpt).mapN(Command.GetNotes.apply))
        val addNoteCmdOpt =
          Opts.subcommand[Command]("add-note", "Add note")((urlOpt, portOpt, noteOpt).mapN(Command.AddNote.apply))

        val allOpts = getNotesCmdOpt orElse addNoteCmdOpt

        allOpts.map({
          case Command.GetNotes(url, port) =>
            println(s"Get from $url $port")
          case Command.AddNote(url, port, note) =>
            println(s"Add $note at $url $port")
        })
      },
    )
