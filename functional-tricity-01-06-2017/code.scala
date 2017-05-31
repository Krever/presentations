import scala.language.higherKinds
import scala.util.Try

object code {

  type ~~>[-F[_], +G[_]] = NaturalTransformation[F, G]


  trait Monad[F[_]] {
    def unit[A](value: A): F[A]

    def bind[A, B](instance: F[A], f: A => F[B]): F[B]
  }

  trait Monoid[F] {
    def zero: F

    def combine(a: F, b: F): F
  }

  trait Category {
    type Morphism[F, G]
  }

  trait MonoidalCategory extends Category {
    type MonoidalProduct[F, G]
    type IdentityObject
  }

  trait MonoidInCategory[F] {

    type Category <: MonoidalCategory

    def zero: Category#Morphism[Category#IdentityObject, F]

    def combine: Category#Morphism[Category#MonoidalProduct[F, F], F]

  }

  trait MonoidInCategoryOfSets[T] extends MonoidInCategory[T] {
    type Category = CategoryOfSets.type

    def zero: Unit => T

    def combine: ((T, T)) => T
  }

  trait Functor[F[_]] {
    def map[A, B](f: A => B): F[A] => F[B]
  }

  trait Product[F[_], G[_]] {
    type Out[T]
  }

  trait MonoidalCategoryK2 {
    type Morphism[F[_], G[_]]
    type MonoidalProduct[F[_], G[_]] <: Product[F, G]
    type IdentityObject[T]
  }

  trait NaturalTransformation[-F[_], +G[_]] {
    def apply[A](fa: F[A]): G[A]
  }

  trait Compose[F[_], G[_]] extends Product[F, G] {
    type Out[T] = F[G[T]]
  }

  trait CategoryOfEndofunctors extends MonoidalCategoryK2 {

    type Morphism[F[_], G[_]] = NaturalTransformation[F, G]

    type MonoidalProduct[F[_], G[_]] = Compose[F, G]

    type IdentityObject[T] = Id[T]
  }

  trait MonoidInCategoryK2[F[_]] {

    type Category <: MonoidalCategoryK2

    def zero: Category#Morphism[Category#IdentityObject, F]

    def combine: Category#Morphism[Category#MonoidalProduct[F, F]#Out, F]

  }

  trait MonoidInCategoryOfEndofunctors[F[_]] extends MonoidInCategoryK2[F] {
    type Category = CategoryOfEndofunctors

    def zero: Id ~~> F

    def combine: Compose[F, F]#Out ~~> F

    def functor: Functor[F]
  }

  case class Id[T](value: T)

  object OptionMonad extends Monad[Option] {
    def unit[A](value: A) = Some(value)

    def bind[A, B](instance: Option[A], f: A => Option[B]): Option[B] =
      instance match {
        case Some(value) => f(value)
        case None => None
      }
  }

  object IntAddMonoid extends Monoid[Int] {
    def zero: Int = 0

    def combine(a: Int, b: Int): Int = a + b
  }

  object IntMultiplyMonoid extends Monoid[Int] {
    def zero: Int = 1

    def combine(a: Int, b: Int): Int = a * b
  }

  object OptionFunctor extends Functor[Option] {
    def map[A, B](f: A => B): Option[A] => Option[B] = {
      case Some(value) => Some(f(value))
      case None => None
    }
  }

  object CategoryOfSets extends MonoidalCategory {
    type Morphism[F, G] = F => G
    type MonoidalProduct[F, G] = (F, G)
    type IdentityObject = Unit
  }

  object IntAddMonoidInCategoryOfSets extends MonoidInCategoryOfSets[Int] {
    def zero: Unit => Int = _ => 0

    def combine: ((Int, Int)) => Int = {
      case (a, b) => a + b
    }
  }

  object TryToOption extends NaturalTransformation[Try, Option] {
    def apply[T](t: Try[T]): Option[T] = t.toOption
  }

  object OptionMonoid extends MonoidInCategoryOfEndofunctors[Option] {
    override def zero: Id ~~> Option = new NaturalTransformation[Id, Option] {
      def apply[T](id: Id[T]): Option[T] =
        Some(id.value)
    }

    override def combine: Compose[Option, Option]#Out ~~> Option =
      new NaturalTransformation[Compose[Option, Option]#Out, Option] {
        def apply[T](opt: Option[Option[T]]): Option[T] =
          opt.flatten
      }

    override def functor: Functor[Option] = OptionFunctor
  }

  object Proof {
    def fromMonoidToMonad[M[_]](monoid: MonoidInCategoryOfEndofunctors[M]): Monad[M] = {
      new Monad[M] {
        override def unit[A](v: A): M[A] = monoid.zero(Id(v))

        override def bind[A, B](m: M[A], f: (A) => M[B]): M[B] =
          monoid.combine(monoid.functor.map(f)(m))
      }
    }

    def fromMonadToMonoid[M[_]](monad: Monad[M]): MonoidInCategoryOfEndofunctors[M] = {
      new MonoidInCategoryOfEndofunctors[M] {
        override def zero: Id ~~> M = new (Id ~~> M) {
          override def apply[A](a: Id[A]): M[A] = monad.unit(a.value)
        }

        override def combine: Compose[M, M]#Out ~~> M = new (Compose[M, M]#Out ~~> M) {
          override def apply[A](m: M[M[A]]): M[A] = monad.bind(m, identity[M[A]])
        }

        override def functor: Functor[M] = new Functor[M] {
          override def map[A, B](f: (A) => B): (M[A]) => M[B] = (m: M[A]) => {
            monad.bind(m, f.andThen(monad.unit))
          }
        }
      }
    }
  }

}