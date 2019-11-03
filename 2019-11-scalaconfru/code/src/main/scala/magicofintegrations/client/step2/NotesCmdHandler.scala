package magicofintegrations.client.step2

import magicofintegrations.step5.NotesCommand

trait NotesCmdHandler[F[_]] {
  def handle(cmd: NotesCommand): F[Unit]
}
