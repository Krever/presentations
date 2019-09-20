package magicofintegrations.step6.client

import magicofintegrations.model.NoteV1

trait ApiClient[F[_]] {
  def addNote(note: NoteV1): F[Unit]
  def getNotes(): F[List[NoteV1]]
}
