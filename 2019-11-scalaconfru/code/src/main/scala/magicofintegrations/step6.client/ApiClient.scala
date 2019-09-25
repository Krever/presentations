package magicofintegrations.step6.client

import magicofintegrations.model.Note

trait ApiClient[F[_]] {
  def addNote(note: Note): F[Unit]
  def getNotesStream(): fs2.Stream[F, Note]
}

object ApiClient {
  trait Factory[F[_]] {
    def get(host: String, port: Int): ApiClient[F]
  }
}
