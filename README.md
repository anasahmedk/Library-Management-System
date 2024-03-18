**Library Management Project using Design pattern**

As part of this assignment, you are required to use at least one design pattern from each Design
Pattern sections namely `Creational`, `Structural` and `Behavioural` respectively.
********************************************************

**Requirements:**

1. Library Management Software is "Menu driver" program which can use standard input and output (
   terminal) to operate.
2. Inventory management of books.
    - listing / add / remove books in library
3. Crud operation for members of library.
    - add/remove/edit members
4. Assign books to library member.
5. Validation
    - Do not allow duplicate assignment.
6. Fine calculator for late submissions of books.
    - R10 per book per day for late submissions.
7. Wish list of books for a member.

********************************************************

**Helpful tips to implement Design pattern:**

1. Design classes and objects keeping different design patterns in mind:
2. Creational design pattern to create and add objects (books/members) in system.
3. Structural design pattern to establish relationship between objects and classes
    - Eg Proxy books to save loading time
4. Behavioral while establishing communication between components
    - Eg. Observer for wish list component
5. Write Dry code (Do not repeat yourself - have reusable components)
6. Write Loosely coupled and cohesive classes
7. Please Program against interface.

********************************************************

**Persistence Layer:**

1. Java collections with some seed data (call in init block).
2. Persist all data into various flat files (basic read and write operations into TXT files) **OR**
   If you are comfortable
   to use a PostgreSQL DB then make basic JDBC connection calls to it and perform require CRUD
   operations

********************************************************

**Design Patterns Used:**

1. Proxy design pattern used for books.
2. Observer for the wish list component.