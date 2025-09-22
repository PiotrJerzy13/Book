# Book Library — Composite Pattern (Java 21 + Maven)

A tiny library domain that demonstrates the **Composite** design pattern.  
Content is organized as a hierarchy:

`AllBooks → Book → Part → Verse`

Each layer implements a common `Text` interface, so operations (like counting words or finding verses) can be applied to the whole tree or any subtree.

> Demo data is seeded in `TestDataCreator` (e.g., two sample books). Swap it for your own content at any time.

---

## Why Composite here?

- You can call the **same operations** on a single `Verse`, a `Part`, one `Book`, or **all books at once**.
- Higher-level nodes **delegate** the work to their children and **aggregate** the results.

---

## Domain model

- **AllBooks** — root collection of books  
- **Book** — has a title, contains parts  
- **Part** — identified by a number, contains verses  
- **Verse** — numbered unit of text; exposes `format()` for nice printing

All of them implement:

```java
interface Text {
  int getNumberOfWords();
  List<Verse> getVersesContainingWord(String word);
}
classDiagram
  Text <|.. AllBooks
  Text <|.. Book
  Text <|.. Part
  Text <|.. Verse

  AllBooks "1" o--> "*" Book
  Book "1" o--> "*" Part
  Part "1" o--> "*" Verse

  class Text {
    +getNumberOfWords() int
    +getVersesContainingWord(word String) List<Verse>
  }
  class AllBooks
  class Book {
    +title: String
  }
  class Part {
    +number: int
  }
  class Verse {
    +number: int
    +content: String
    +format() String
  }
```
## Prerequisites

JDK 21

Maven 3.8+
Check:
```java
java -version
mvn -v
```
## Run and Test
```java
mvn clean compile exec:java -Dexec.mainClass="book.Application"
mvn test
```
## Javadoc
```java
mvn -DskipTests javadoc:javadoc
# macOS:
open target/site/apidocs/index.html
# Depending on your plugin
open target/reports/apidocs/index.html
# Linux:
xdg-open target/site/apidocs/index.html
# Windows:
start target\site\apidocs\index.html
```
