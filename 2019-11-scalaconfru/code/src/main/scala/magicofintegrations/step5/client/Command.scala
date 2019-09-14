package magicofintegrations.step5.client

import magicofintegrations.model.NoteV1

sealed trait Command

object Command {
  case class GetNotes(url: String, port: Int)              extends Command
  case class AddNote(url: String, port: Int, note: NoteV1) extends Command
}
