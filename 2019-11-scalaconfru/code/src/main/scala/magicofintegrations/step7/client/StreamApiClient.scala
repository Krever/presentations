package magicofintegrations.step7.client

import magicofintegrations.model.Note

trait StreamApiClient[F[_]] {
  def getNotesStream(): F[List[Note]]
}
