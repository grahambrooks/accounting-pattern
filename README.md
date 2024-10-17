# accounting-pattern

Implementation of Martin Fowler's [accounting patterns article](http://martinfowler.com/apsupp/accounting.pdf) and [Accounting Narrative](http://martinfowler.com/eaaDev/AccountingNarrative.html).

## Summary

The accounting pattern is a design pattern used to manage financial transactions and ensure accurate financial reporting. It involves the following key concepts:

- **Transaction**: Represents a financial event that affects accounts.
- **Entry**: A record within a transaction that specifies the amount and the account affected.
- **Account**: Represents a ledger account where entries are recorded.
- **Ledger**: A collection of accounts that tracks all financial transactions.

The pattern ensures that all transactions are balanced, meaning the total debits equal the total credits, which is crucial for maintaining accurate financial records.

# Pattern


```mermaid
graph TD
    A[Application] -->|1.0| B[Command] -->|1.1| C[Command Handler]
    A -->|2.0| D[Event] -->|2.1| E[Event Handler]
    A -->|3.0| F[Query] -->|3.1| G[Query Handler]
    A -->|4.0| H[View]
```

```mermaid
sequenceDiagram
participant Application
participant Command
participant Command Handler
participant Event
participant Event Handler
participant Query
participant Query Handler
participant View

Application->>Command: 1.0
Command->>Command Handler: 1.1
Command Handler->>Event: 2.0
Event->>Event Handler: 2.1
Event Handler->>Query: 3.0
Query->>Query Handler: 3.1
Query Handler->>View: 4.0
    
```
