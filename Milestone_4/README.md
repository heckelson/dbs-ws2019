# Milestone 4

Milestone 4 was to write a Java program that inserts rows into the database as a practice.    
Here is my implementation.

## Organisation of files

### Manager class

The Manager.java class handles the DataGenerator and DBAccessor interfaces. It can request a ArrayList of entries and insert these entries into the database.

### DataGenerator interface

The DataGenerator interface enables the Manager to get singular entities with all dependencies satisfied.

### RandomDataGenerator class

The RandomDataGenerator provides the specific types of random values the entities use. It knows how to build the entities.

### RandomSequenceGenerator class

In this class, the pseudorandom sequences are generated in a form specified by the RandomDataGenerator class.

### Entities package

In this package is a class for each table in the SQL database and I tried turning these tables into a Java class. Obviously, the two are not fully compatible.

### DBAccessor interface

The DBAccessor interface provides a way to open and close a connection with a database, as well as executing SQL queries on the database.

## How To Use

1) Get a copy of ojdbc8.jar (download from Oracle)
2) put it into the directory with the Main, Manager, ... classes (not the entities package!) so it looks (about) like this:
```
[heckelson@device src]$ ls
DataGenerator.java  entities              Main.java     ojdbc8.jar                RandomSequenceGenerator.java
DBAccessor.java     JDBC_DBAccessor.java  Manager.java  RandomDataGenerator.java
```
3) Compile:
```bash
$ javac -classpath ojdbc8.jar:. *.java entities/*.java
```
4) Run (Main):
```$ bash
java -classpath ojdbc8.jar:. Main
```
Enjoy!
