package magicofintegrations.client.step2

import cats.effect.{Console, Sync}
import magicofintegrations.step5.NotesCommand

class ApiCmdHandler[F[_]: Sync](apiClientFactory: ApiClient.Factory[F], console: Console[F]) {
  def handle(cmd: NotesCommand): F[Unit] = cmd match {
    case NotesCommand.GetNotes(url, port) =>
      val apiClient = apiClientFactory.get(url, port)
      apiClient
        .getNotesStream()
        .evalMap(note => console.putStrLn(note.toString))
        .compile
        .drain
    case NotesCommand.AddNote(url, port, note) =>
      val apiClient = apiClientFactory.get(url, port)
      apiClient.addNote(note)
  }
}
