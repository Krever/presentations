
## [code_smarter => test_less]
### functional guide to reasonable development

<span style="color:gray; font-size:0.6em;">Wojciech Pituła @ Nordea Common Platforms</span>

----

## Understand the code

```javascript
class Point {
    constructor(height, width) {
        this.height = height;
        this.width = width;
    }

    divide(factor) {
        if(factor != 0) {
            this.height /= factor;
            this.width /= factor;
            return this;
        }
    }
}
```

++++

## Understand the code

- Variables in scope
  - Arguments, class members, global scope
  - What are they?
  - Can their values be modified? By other threads?
  - Can they be reassigned?
- Return value
  - Is there always one? 
  - What is it?
  - Can exception be thrown?
- External side effects(IO)


++++

## All the questions are recursive!


++++


## Let's do some math

30 lines per method

5 invocations per method

7 levels of nesting

\>2 300 000 lines of code


++++


## Thread-unsafety propagates

You need to find usages of your data in the whole codebase(your code, dependencies, transitive dependencies, JVM)


++++


## Tests to the rescue

notes:
Tests are equivalent to existential qualifier

----

## Types
universal qualifiers that constrain your code and make it safer


++++

### Exception handling

```java
public String decode(String data)
```
vs
```java
public Try<String> decode(String data)

public Either<Exception, String> decode(String data)
```

++++

### Nullability

```java
public String get(String key)
```
vs
```java
public Option<String> get(String key)
```

++++

### Refined types

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

public Integer divide(Integer a, NonZeroInt b)

```

++++

### Unknown type is better than any type

```java
public <T> T identity(T x)

public <A, B> B apply(A a, Function<A, B> func)
```

----

## Immutability
let's contraint us even more


++++

### Forbid reassignment

```
final int a = 1; // Java
const int a = 1; // C++
val a: Int = 1 //Scala
```

++++

### Data still can be modified

++++

Immutable data

Immutable collections

++++

### I still need to modify some state

----

### Everything has it costs

* Performance
* Learning curve/simplicity

----

## Summary

++++

### Guidelines

* put as much constrains as you can into the signatures
* make everything you can immutable
* remember about the costs

++++

### Go and try new langs
#### See what you can bring back home

* Scala
* F#
* Haskell
* OCaml
* Clojure, Lisp, Erlang, Elixir


----

## Thanks

Wojciech Pituła

@Krever01



