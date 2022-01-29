# library-cli
This program uses a database to hold the inventory of a library. This inventory can be accessed by a librarian and a library member. The librarian and member have varying levels of access.

Due to the COVID-19 pandemic, libraries had closed down or were having difficulty functioning as they used to. To help with such a problem, we came up with the idea to create a command line interface library system.

First, we created the database using the MySQL Workbench. In the database, we had multiple tables: `bookinfo`, `member`, `memberviewbooks`, and `receipt`. Once we had constructed the tables with their respective columns and data types, we moved onto the next step.

Next, we created a POM (Project Object Model), an XML file, using Maven, to connect our MySQL database to a Java file.

Finally, we implemented our CLI through our `Main.java` file.

The system can allow multiple users, such as librarians and members, to access it. It allows users to create an account for themselves using their email address and library card information. Librarians also have password protected accounts.
Once the user is logged in, depending on the role of the user (librarian or member), they have a variety of capabilities available. The librarian can add books, update books, and view the entire database of books. The member can choose a book from a catalog of available books and 'take it out' for themselves. They can also view their user information.

Sehaj Munot (munotsehaj) and Saakshi Munot (munotsaakshi) worked on this project together for HackMCST VI in January 2021.