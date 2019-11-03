package magicofintegrations.step5

import com.monovore.decline.{Command, Opts}
import magicofintegrations.model.Note
import cats.syntax.all._

sealed trait NotesCommand

object NotesCommand {
  case class GetNotes(url: String, port: Int)            extends NotesCommand
  case class AddNote(url: String, port: Int, note: Note) extends NotesCommand

  val opts: Opts[NotesCommand] = {
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
      (titleOpt, textOpt).mapN(Note.apply)
    }

    val getNotesCmdOpt =
      Opts.subcommand[NotesCommand]("get-notes", "Get notes")((urlOpt, portOpt).mapN(NotesCommand.GetNotes.apply))
    val addNoteCmdOpt =
      Opts.subcommand[NotesCommand]("add-note", "Add note")((urlOpt, portOpt, noteOpt).mapN(NotesCommand.AddNote.apply))

    getNotesCmdOpt orElse addNoteCmdOpt
  }

  val command = Command("notes", "Notes manager")(opts)

}
