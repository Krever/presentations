
## [code_smarter => test_less]
### functional guide to reasonable development

<span style="color:gray; font-size:0.6em;">Wojciech Pituła @ Nordea Common Platforms</span>

----

## Mission: Understand the code

```javascript
function divide(factor) {
    if(factor != 0) {
        this.height /= factor;
        this.width /= factor;
        return this;
    }
}
```

++++

### Approach 1: Read it!

++++

#### Nope

Reading is recursive

++++

### Approach 2: Read the tests!

++++

#### Nope

* Tests can be more complicated than code itself
* Tests are equivalent to existential qualifiers

++++

### Approach 3: Constrain the code!

----

## Contraint 1: Types

++++

### Step 0: Redefine the semantics, change the expectations

```java
Double divide(Double a, Double b)
```
vs
```scala
def divide(a: Double, b: Double): Double
```

++++

### Step 1: Exception handling

```java
public String decode(String data)
```
vs
```java
public Try<String> decode(String data)

public Either<Exception, String> decode(String data)
```

++++

### Step 2: Nullability

```java
public String get(String key)
```
vs
```java
public Option<String> get(String key)
```

++++

### Step 3: Refined types

```java
public Double calculateVelocity(Double d, Double t)
```
vs.
```java
public Velocity calculate(Distance d, Time t)
```
---

```java
public Integer reduce(NonEmptyList<Integer> list, 
                      BiFunction<Integer, Integer, Integer> func)

public Double divide(Integer a, NonZeroInt b)

```

++++

### Step 4: Unknown type is better than any type

```java
public <T> T identity(T x)

public <A, B> B apply(A a, Function<A, B> func)
```

++++

### Step 5: Remove half of your tests and stop worrying

* you should be able to read most of information from method signatures
* you only need to test business logic

++++

### Step 6: Know the costs

* More complicated  signatures
* Compilation time can grow

----

## Summary

* You can embed a lot of information in methods signatures
* Experiment with new languages, the better the language the more you can do
* [Constraints liberate, liberties constrain](https://www.youtube.com/watch?v=GqmsQeSzMdw)
* Follow me to see the 2nd part :)

----

## Thanks

**Meet me at Nordea stand**

Wojciech Pituła

[@Krever01](https://twitter.com/Krever01)

[w.pitula.me/presentations](http://w.pitula.me/presentations/datamass-2017/index.html)

----

## Constraint 2: Immutability

++++

### Step 1: Forbid the reassignments

```
final int a = 1;    // Java
const int a = 1;    // C++
val a = 1           // Scala
let a = 1           // Swift
```

++++

### Step 2: Data still can be modified

* Immutable data
* Immutable collections

++++

### Step 3: I still need to modify some state!

* Are you sure?
* Bubble of mutability
* Push it to database

++++

### Step 3.1: Bubble of mutability

```scala
val list: mutable.List
```
vs
```scala
var list: immutable.List
```

++++

### Step 4: Stop worrying about thread-safety and rest of your system

* Everything that is immutable is thread-safe automatically
* No one can break your piece from outside

++++

### Step 5: Know the costs 

* Performance
* Learning curve/simplicity
* You need to know what you are doing
* No mainstream language can enforce immutability

----

## Summary

++++

### Guidelines

* put as much constrains as you can into the code
* make everything you can immutable
* remember about the costs
* [Constraints liberate, liberties constrain](https://www.youtube.com/watch?v=GqmsQeSzMdw)

++++

### Go and try new languages
#### See what you can bring back home

* Scala
* F#
* Haskell
* OCaml
* Clojure, Lisp, Erlang, Elixir


----

## Thanks

**Meet me at Nordea stand**

Wojciech Pituła

[@Krever01](https://twitter.com/Krever01)

[w.pitula.me/presentations](http://w.pitula.me/presentations/datamass-2017/index.html)


