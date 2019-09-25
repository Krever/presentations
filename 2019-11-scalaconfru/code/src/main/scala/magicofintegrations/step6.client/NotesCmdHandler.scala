package magicofintegrations.step6.client

import magicofintegrations.step5.client.NotesCommand

trait NotesCmdHandler[F[_]] {
  def handle(cmd: NotesCommand): F[Unit]
}
