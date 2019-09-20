package magicofintegrations.step5.client

import com.monovore.decline.Opts
import magicofintegrations.model.NoteV1
import cats.syntax.all._

sealed trait Command

object Command {
  case class GetNotes(url: String, port: Int)              extends Command
  case class AddNote(url: String, port: Int, note: NoteV1) extends Command

  val opts: Opts[Command] = {
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

    getNotesCmdOpt orElse addNoteCmdOpt
  }

}
