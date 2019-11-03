package magicofintegrations.client.step3

import magicofintegrations.model.Note

trait StreamApiClient[F[_]] {
  def getNotesStream(): F[List[Note]]
}
