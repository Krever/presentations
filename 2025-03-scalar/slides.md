### Durable Event-Sourced
## <y-ellow>Workflow Monad</y-ellow>
#### Seriously!


<span style="font-size: 0.7em;color: gray">Voytek Pituła</span>

----

<span class="breadcrumb-data">Workflows4s</span>

### A long time ago ( In 2022 )

<img src="resources/2022.png" width="50%">

++++

## My history with Workflows

* 2018 - Camunda @ Sony
* 2019 - Cadence & Temporal @ Sony
* 2020 - workflows through event-sourcing @ SwissBorg
* Q4 2022 - ranting on why everything is broken
* Q1 2024 - Workflows4s prototype

<!--TODO segments -->

----

<span class="breadcrumb-data">Workflows4s4s » What is a workflow?</span>

## What is a <y-ellow>workflow</y-ellow>?

Steps + State + Time

++++

## Different names?

* Saga
* Finite state machine
* Long running process
* Service orchestration

++++

## Rings a bell?

----

<span class="breadcrumb-data">Workflows4s4s » Why everything is broken?</span>

## Why everything is <y-ellow>broken</y-ellow>?

++++

<span class="breadcrumb-data">Workflows4s4s » Why everything is broken?</span>

### What <y-ellow>goes into</y-ellow> a workflow solution?

* Definition
* Execution
* Monitoring
* Management


++++

<span class="breadcrumb-data">Workflows4s4s » Why everything is broken?</span>

### What <y-ellow>REALLY</y-ellow> goes into a workflow solution?

* Definition
* Understandability
* Migrations

++++

<span class="breadcrumb-data">Workflows4s4s » Why everything is broken?</span>

### What's in place?

<img src="resources/solutions.png" width="30%">

<!--TODO wording, self contained is not a good term -->

++++

<span class="breadcrumb-data">Workflows4s4s » Why everything is broken? » What's in place?</span>

#### Data workflows are <y-ellow>not</y-ellow> our workflows

Hence, no Airflow & friends

<!--TODO gray -->

----

<span class="breadcrumb-data">Workflows4s4s » Meet Workflows4s</span>

## Meet <y-ellow>Workflows4s</y-ellow>

* Functional
* Composable
* Scalable (up & down)
* Business-friendly

<y-ellow>LIBRARY</y-ellow>

----

## Not-Demo

----

<span class="breadcrumb-data">Workflows4s</span>

## <y-ellow>Defining</y-ellow> workflows

++++

<span class="breadcrumb-data">Workflows4s » Defining workflows</span>

<pre style="width: fit-content"><code class="hljs scala">sealed trait WIO[In, Err, Out]</code></pre>

++++

<pre style="width: fit-content"><code class="hljs scala">trait WorkflowContext {
  type Event
  type State
}</code></pre>

<pre style="width: fit-content"><code class="hljs scala">sealed trait WIO[In, Err, Out, Ctx <: WorkflowCtx]</code></pre>

++++

<pre style="width: fit-content"><code class="hljs scala">trait WorkflowContext {
  type Event
  type State
}</code></pre>

<pre style="width: fit-content"><code class="hljs scala">sealed trait WIO[In, Err, Out <: WCState[Ctx], Ctx <: WorkflowCtx]</code></pre>

++++

General type projections are gone

++++

<span class="breadcrumb-data">Workflows4s » Defining workflows</span>

## Operations

++++

<span class="breadcrumb-data">Workflows4s » Defining workflows » Operations</span>

### Deterministic code

++++

### Non-deterministic code

++++

### Sequencing steps

++++

### Signals

++++

### Timers

++++

### Interruptions

++++

### Error handling

++++

### More?

* Forks
* Loop
* Embedding sub-workflows

----

<span class="breadcrumb-data">Workflows4s</span>

## <y-ellow>Running</y-ellow> workflows

++++

<span class="breadcrumb-data">Workflows4s » Running workflows</span>

```scala
trait WorkflowRuntime[F[_], Ctx <: WorkflowContext, WorkflowId]{
    def createInstance(id: WorkflowId):
       F[WorkflowInstance[F, WCState[Ctx]]]
}
```

++++

<span class="breadcrumb-data">Workflows4s » Running workflows</span>

```scala [1|3|5-8|10]
trait WorkflowInstance[F[_], State] {

  def queryState(): F[State]

  def deliverSignal[Req, Resp](
    signalDef: SignalDef[Req, Resp], 
    req: Req
  ): F[Either[UnexpectedSignal, Resp]]

  def wakeup(): F[Unit]

}
```

++++

<span class="breadcrumb-data">Workflows4s » Running workflows</span>

```scala 
val myWorkflow: WIO[Any, Nothing, WCState[Ctx], Ctx]
val runtime = SomeRuntime.create(wio)

```

++++

## Available Runtimes

++++

TODO

++++

## Knocker-Uppers

++++

TODO

----


<hr style="width:100%;height:0.15em;background-color: #eee;">
<div style="font-size: 0.7em">
    Thank you!

    Voytek Pituła @ SwissBorg

    [https://w.pitula.me/presentations](https://w.pitula.me/presentations)
</div>
