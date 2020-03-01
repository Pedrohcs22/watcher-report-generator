# watcher-report-generator
Watch a folder and create a report when new files are created.

## Prerequisites
1. You will need [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) installed.

## The application
* Each file is designed for a Thread (4 Max Threads)
* Each file is read, and converted into Java POJOs.
* A Service will take care of executing business logic.
* A new file will be created with the results of the process.

## Running

To start the application, just run the main 'App' class.
The watcher works in a hard-coded loop, so te application must be shutdown manually.
