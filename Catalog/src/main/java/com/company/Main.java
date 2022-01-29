package com.company;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) {
       userInterface();
    }

    public static void userInterface() {

        String kindOfUser;
        String usageLibrarian;

        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Please type in just the alphabet 'a', 'b', or 'q'");
            System.out.println("Are you a ...");
            System.out.println("    a. Librarian");
            System.out.println("    b. Member");
            System.out.println("    q. Neither, I want to exit this program!");
            kindOfUser = input.next();
            if ("a".equalsIgnoreCase(kindOfUser)) {
                do{
                    System.out.println("What would you like to use the program for, librarian?");
                    System.out.println("    a. Add a new book");
                    System.out.println("    b. Change status of a book");
                    System.out.println("    c. View database");
                    System.out.println("    q. I want to exit this program!");
                    usageLibrarian = input.next();
                    if(usageLibrarian.equalsIgnoreCase("a")) {
                        System.out.println("What is the ISBN number of the book?");
                        String ISBN = input.next();
                        System.out.println("What is the name of the book?");
                        input.nextLine();
                        String bookName = input.nextLine();
                        System.out.println("What is the last name of the author of the book?");
                        String authorLastName = input.next();
                        System.out.println("What is the first name of the author of the book?");
                        String authorFirstName = input.next();
                        System.out.println("What is the name of the publisher of the book?");
                        String publisher = input.next();
                        System.out.println("What is the genre of the book?");
                        String genre = input.next();
                        System.out.println("What type of cover does the book have? It can be either a hardcover, paperback or e-book.");
                        String coverType = input.next();
                        System.out.println("Is this book currently being borrowed by even one person? If so, type in 1. Else, type in 0.");
                        int onLoan = input.nextInt();
                        System.out.println("How many copies of this book does the library have in total, including the ones that are being currently borrowed?");
                        int numberOfCopiesTotal = input.nextInt();
                        System.out.println("How many copies of this book does the library have currently? This would be the amount of total copies it has minus the ones that are being currently borrowed.");
                        int numberOfCopiesCurrent = input.nextInt();
                        System.out.println("How many copies of this book have been borrowed from the library as of now? This would be the amount of total copies it has minus the ones that it has currently.");
                        int numberOfCopiesOnLoan = input.nextInt();
                        insertBookInfo(ISBN, bookName, authorLastName, authorFirstName, publisher, genre, coverType, onLoan, numberOfCopiesTotal, numberOfCopiesCurrent, numberOfCopiesOnLoan);
                        insertMemberBookInfo(ISBN, bookName, authorLastName, authorFirstName, publisher, genre, coverType);
                    }
                    else if(usageLibrarian.equalsIgnoreCase("b")) {
                        System.out.println("What do you want to change? Please type in just the alphabet 'a', 'b', 'c', or 'd'");
                        System.out.println("    a.Number of total copies");
                        System.out.println("    b.Number of current copies");
                        System.out.println("    c.Number of copies on loan");
                        System.out.println("    d.Whether or not one of the books is on loan");
                        String changeStatus = input.next();

                        if(changeStatus.equalsIgnoreCase("a"))
                        {
                            int onLoan;
                            System.out.println("What is the ISBN number of the book that you want to update?");
                            String updateISBN = input.next();
                            System.out.println("How many total copies are there now?");
                            int totalCopies = input.nextInt();
                            System.out.println("How many current copies are there now?");
                            int currentCopies = input.nextInt();
                            System.out.println("How many copies are being borrowed now?");
                            int onLoanCopies = input.nextInt();
                            if(onLoanCopies >= 1){
                                onLoan = 1;
                            }
                            else {
                                onLoan = 0;
                            }
                            changeStatus(updateISBN, onLoan, totalCopies, currentCopies,onLoanCopies);
                        }
                        else if(changeStatus.equalsIgnoreCase("b"))
                        {
                            int onLoan;
                            System.out.println("What is the ISBN number of the book that you want to update?");
                            String updateISBN = input.next();
                            System.out.println("How many current copies are there now?");
                            int currentCopies = input.nextInt();
                            System.out.println("How many total copies are there now?");
                            int totalCopies = input.nextInt();
                            System.out.println("How many copies are being borrowed now?");
                            int onLoanCopies = input.nextInt();
                            if(onLoanCopies >= 1){
                                onLoan = 1;
                            }
                            else {
                                onLoan = 0;
                            }
                            changeStatus(updateISBN, onLoan, totalCopies, currentCopies,onLoanCopies);
                        }
                        else if(changeStatus.equalsIgnoreCase("c"))
                        {
                            int onLoan;
                            System.out.println("What is the ISBN number of the book that you want to update?");
                            String updateISBN = input.next();
                            System.out.println("How many copies are being borrowed now?");
                            int onLoanCopies = input.nextInt();
                            System.out.println("How many current copies are there now?");
                            int currentCopies = input.nextInt();
                            System.out.println("How many total copies are there now?");
                            int totalCopies = input.nextInt();
                            if(onLoanCopies >= 1){
                                onLoan = 1;
                            }
                            else {
                                onLoan = 0;
                            }
                            changeStatus(updateISBN, onLoan, totalCopies, currentCopies,onLoanCopies);
                        }
                        else if(changeStatus.equalsIgnoreCase("d"))
                        {
                            System.out.println("What is the ISBN number of the book that you want to update?");
                            String updateISBN = input.next();
                            System.out.println("Is this book currently being borrowed by even one person? If so, type in 1. Else, if no one is borrowing it, type in 0.");
                            int onLoan = input.nextInt();
                            changeStatusForOnLoan(updateISBN, onLoan);
                        }
                    }
                    else if(usageLibrarian.equalsIgnoreCase("c")) {
                        view();
                    }
                    else if(usageLibrarian.equalsIgnoreCase("q")) {
                        System.exit(1);
                    }
                }while(!usageLibrarian.equals("a") || !usageLibrarian.equals("b") ||usageLibrarian != "c"||usageLibrarian != "q");
            }
            else if ("b".equalsIgnoreCase(kindOfUser)) {
                System.out.println("Please sign in.");
                System.out.println("What is your last name?");
                String lastname = input.next();
                System.out.println("What is your first name?");
                String firstname = input.next();
                System.out.println("What is your email address?");
                String email = input.next();
                System.out.println("What is your library card number?");
                String cardNumber = input.next();
                insertMemberInfo(cardNumber, lastname, firstname, email);
                System.out.println("Where do you want to go now?");
                System.out.println("    a. Look for books to read");
                System.out.println("    b. My info");
                String infoOrBooks = input.next();

                if("a".equalsIgnoreCase(infoOrBooks)){
                    viewMember();
                    System.out.println("Choose your book. Once you have done so, type in the ISBN.");
                    String memberInputISBN = input.next();
                    //increaseNumberOfBooksBorrowed(memberInputISBN);
                    printReceipt(memberInputISBN, cardNumber);
                }
                else if("b".equalsIgnoreCase(infoOrBooks)){
                    System.out.println("First Name: " + firstname);
                    System.out.println("Last Name: " + lastname);
                    System.out.println("Full Name: " + firstname + " " + lastname);
                    System.out.println("Email Address: " + email);
                    System.out.println("Library Card #: " + cardNumber);
                    System.out.println("Please type in just the alphabet 'a' or 'q'");
                    System.out.println("    a. Look for books to borrow.");
                    System.out.println("    q. Exit this program.");
                    String booksOrQuit = input.next();
                    if(booksOrQuit.equalsIgnoreCase("a")){
                        viewMember();
                        System.out.println("Choose your book. Once you have done so, type in the ISBN.");
                        String memberInputISBN = input.next();
                        //increaseNumberOfBooksBorrowed(memberInputISBN);
                        printReceipt(memberInputISBN, cardNumber);
                    }
                    else if(booksOrQuit.equalsIgnoreCase("q")){
                        System.exit(1);
                    }
                }
            }
            else if ("q".equalsIgnoreCase(kindOfUser)){
                System.exit(1);
            }

        }while(!kindOfUser.equals("a") || !kindOfUser.equals("b") || !kindOfUser.equals("q"));

    }

    public static void view(){
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "admin";
        try {
            Connection myConn = DriverManager.getConnection(url,user,password);
            Statement myStmt = myConn.createStatement();
            String sql = "select * from library.bookInfo";
            ResultSet rs = myStmt.executeQuery(sql);
            System.out.printf("%-1s %14s %-35s %1s %-10s %1s %-25s %-28s %-30s %-20s %-20s %-15s %-20s %-10s ", "ISBN", "    ", "Book Name", "    " , "Author Last Name", "    ", "Author First Name", "Publisher", "Genre", "Cover Type", "Borrowed?", "Total Copies", "Current Copies", "Borrowed Copies");
            System.out.println();
            while(rs.next()){
                System.out.printf("%-1s %5s %-40s %1s %-20s %1s %-15s %-30s %-30s %-20s %-20s %-20s %-20s %-20s ", rs.getString("ISBN"), "    ", rs.getString("bookName"), "    " , rs.getString("authorLastName"), "    ", rs.getString("authorFirstName"), rs.getString("publisher"), rs.getString("genre"), rs.getString("coverType"), rs.getString("onLoan"), rs.getString("numberOfCopiesTotal"), rs.getString("numberOfCopiesCurrent"), rs.getString("numberOfCopiesOnLoan"));
                System.out.println();
                // System.out.print(rs.getString("authorLastName"));
            }
            System.out.println();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void viewMember(){
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "admin";
        try {
            Connection myConn = DriverManager.getConnection(url,user,password);
            Statement myStmt = myConn.createStatement();
            String sql = "select * from library.memberviewbooks";
            ResultSet rs = myStmt.executeQuery(sql);
            System.out.printf("%-1s %14s %-35s %1s %-10s %1s %-25s %-28s %-30s %-20s", "ISBN", "    ", "Book Name", "    " , "Author Last Name", "    ", "Author First Name", "Publisher", "Genre", "Cover Type");
            System.out.println();
            while(rs.next()){
                System.out.printf("%-1s %5s %-40s %1s %-20s %1s %-15s %-30s %-30s %-20s ", rs.getString("ISBN"), "    ", rs.getString("bookName"), "    " , rs.getString("authorLastName"), "    ", rs.getString("authorFirstName"), rs.getString("publisher"), rs.getString("genre"), rs.getString("coverType"));
                System.out.println();
                // System.out.print(rs.getString("authorLastName"));
            }
            System.out.println();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertBookInfo(String ISBN, String bookName, String authorLastName, String authorFirstName, String publisher, String genre, String coverType, int onLoan, int numberOfCopiesTotal, int numberOfCopiesCurrent, int numberOfCopiesOnLoan) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "admin";
        try
        {
            Connection Conn = DriverManager.getConnection(url,user,password);
            Statement myStmt = Conn.createStatement();
            myStmt.executeUpdate( "insert into bookInfo" + "(ISBN, bookName, authorLastName, authorFirstName, publisher, genre, coverType, onLoan, numberOfCopiesTotal, numberOfCopiesCurrent, numberOfCopiesOnLoan) " + " values('"+ISBN+"', '"+bookName+"', '"+authorLastName+"', '"+authorFirstName+"', '"+publisher+"', '"+genre+"', '"+coverType+"', '"+onLoan+"', '"+numberOfCopiesTotal+"', '"+numberOfCopiesCurrent+"', '"+numberOfCopiesOnLoan+"')");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void changeStatus(String ISBN, int onLoan, int numberOfCopiesTotal, int numberOfCopiesCurrent, int numberOfCopiesOnLoan){
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "admin";
        try
        {
            Connection Conn = DriverManager.getConnection(url,user,password);
            Statement myStmt = Conn.createStatement();
            //String test="insert into bookInfo" + "(ISBN, bookName, authorLastName, authorFirstName, publisher, genre, coverType, onLoan, numberOfCopiesTotal, numberOfCopiesCurrent, numberOfCopiesOnLoan) " + " values('"+ISBN+"', '"+bookName+"', '"+authorLastName+"', '"+authorFirstName+"', '"+publisher+"', '"+genre+"', '"+coverType+"', '"+onLoan+"', '"+numberOfCopiesTotal+"', '"+numberOfCopiesCurrent+"', '"+numberOfCopiesOnLoan+"')";
            // System.out.println(test);

            myStmt.executeUpdate( "update bookInfo" + " set onLoan = '"+onLoan+"'" + "where ISBN = '"+ISBN+"'");
            myStmt.executeUpdate( "update bookInfo" + " set numberOfCopiesTotal = '"+numberOfCopiesTotal+"'" + "where ISBN = '"+ISBN+"'");
            myStmt.executeUpdate( "update bookInfo" + " set numberOfCopiesCurrent = '"+numberOfCopiesCurrent+"'" + "where ISBN = '"+ISBN+"'");
            myStmt.executeUpdate( "update bookInfo" + " set numberOfCopiesOnLoan = '"+numberOfCopiesOnLoan+"'" + "where ISBN = '"+ISBN+"'");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void changeStatusForOnLoan(String ISBN, int onLoan) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "admin";
        try
        {
            Connection Conn = DriverManager.getConnection(url,user,password);
            Statement myStmt = Conn.createStatement();
            //String test="insert into bookInfo" + "(ISBN, bookName, authorLastName, authorFirstName, publisher, genre, coverType, onLoan, numberOfCopiesTotal, numberOfCopiesCurrent, numberOfCopiesOnLoan) " + " values('"+ISBN+"', '"+bookName+"', '"+authorLastName+"', '"+authorFirstName+"', '"+publisher+"', '"+genre+"', '"+coverType+"', '"+onLoan+"', '"+numberOfCopiesTotal+"', '"+numberOfCopiesCurrent+"', '"+numberOfCopiesOnLoan+"')";
            // System.out.println(test);

            myStmt.executeUpdate( "update bookInfo" + " set onLoan = '"+onLoan+"'" + "where ISBN = '"+ISBN+"'");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertMemberInfo(String libraryCardNumber, String lastName, String firstName, String email) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "admin";
        try
        {
            Connection Conn = DriverManager.getConnection(url,user,password);
            Statement myStmt = Conn.createStatement();
            String test =  "insert into member " + " (libraryCardNumber, memberLastName, memberFirstName, memberEmail) " + " values('"+libraryCardNumber+"', '"+lastName+"', '"+firstName+"', '"+email+"')";
          //  System.out.println(test);
            myStmt.executeUpdate( "insert into member " + " (libraryCardNumber, memberLastName, memberFirstName, memberEmail) " + " values('"+libraryCardNumber+"', '"+lastName+"', '"+firstName+"', '"+email+"')");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void deleteData(){
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "admin";
        try
        {
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            int rowsaffected = stmt.executeUpdate( "delete from city" + " where city_id = 4");
            System.out.print("rows changed " + rowsaffected);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public static void insertMemberBookInfo(String ISBN, String bookName, String authorLastName, String authorFirstName, String publisher, String genre, String coverType) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "admin";
        try
        {
            Connection Conn = DriverManager.getConnection(url,user,password);
            Statement myStmt = Conn.createStatement();
            myStmt.executeUpdate( "insert into memberviewbooks" + "(ISBN, bookName, authorLastName, authorFirstName, publisher, genre, coverType) " + " values('"+ISBN+"', '"+bookName+"', '"+authorLastName+"', '"+authorFirstName+"', '"+publisher+"', '"+genre+"', '"+coverType+"')");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*public static void increaseNumberOfBooksBorrowed(String memberInputISBN){
       String url = "jdbc:mysql://localhost:3306/library";
       String user = "root";
       String password = "admin";
       try
       {

           Connection Conn = DriverManager.getConnection(url,user,password);
           Statement myStmt = Conn.createStatement();
           String sql = "select onLoan from library.bookinfo";
           String sql1 = "select numberOfCopiesOnLoan from library.bookinfo";
           String sql2 = "select numberOfCopiesCurrent from library.bookinfo";
           ResultSet rs = myStmt.executeQuery(sql);
           ResultSet rs1 = myStmt.executeQuery(sql1);
           ResultSet rs2 = myStmt.executeQuery(sql2);
           //String test="insert into bookInfo" + "(ISBN, bookName, authorLastName, authorFirstName, publisher, genre, coverType, onLoan, numberOfCopiesTotal, numberOfCopiesCurrent, numberOfCopiesOnLoan) " + " values('"+ISBN+"', '"+bookName+"', '"+authorLastName+"', '"+authorFirstName+"', '"+publisher+"', '"+genre+"', '"+coverType+"', '"+onLoan+"', '"+numberOfCopiesTotal+"', '"+numberOfCopiesCurrent+"', '"+numberOfCopiesOnLoan+"')";
           // System.out.println(test);

           int x;
           if(rs.getInt("onLoan") == 1)
           {
               x = 0;
           }
           else{
               x = 1;
           }
           while(rs.next()) {
               myStmt.executeUpdate("update bookInfo" + " set onLoan = '" + rs.getInt("onLoan") + x + "'" + "where ISBN = '" + memberInputISBN + "'");
              }
           while(rs1.next()){
               myStmt.executeUpdate("update bookInfo" + " set numberOfCopiesOnLoan = '" + rs1.getInt("numberOfCopiesOnLoan") + 1 + "'" + "where ISBN = '" + memberInputISBN + "'");
           }
           while(rs2.next()){
               myStmt.executeUpdate("update bookInfo" + " set numberOfCopiesCurrent = '" + rs2.getInt("numberOfCopiesCurrent") + x + "'" + "where ISBN = '" + memberInputISBN + "'");
           }
       }
       catch (SQLException throwables) {
           throwables.printStackTrace();
       }
    }*/

    public static void printReceipt(String memberInputISBN, String cardNumber){
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "admin";
        try {
            Connection myConn = DriverManager.getConnection(url,user,password);
            Statement myStmt = myConn.createStatement();
            Statement myStmt1 = myConn.createStatement();
            String sql = "select * from library.bookinfo where ISBN = '"+memberInputISBN+"'" ;
            String sql1 = "select * from library.member where libraryCardNumber = '"+cardNumber+"'" ;

            ResultSet rs = myStmt.executeQuery(sql);
            ResultSet rs1 = myStmt1.executeQuery(sql1);

            System.out.println("");
            System.out.println("");
            System.out.println("RECIEPT");
            System.out.println("");
            while(rs.next()){
                System.out.println("ISBN: " + memberInputISBN);
                System.out.println("Title: " + rs.getString("bookName"));
                System.out.print("Date: ");
                date();
                System.out.print("Due Date: ");
                dueDate();
                System.out.println();
                System.out.println();
                System.out.println("_________________________________________________________________");
                System.out.println();

                // System.out.print(rs.getString("authorLastName"));
            }
            while(rs1.next()){
                System.out.println("Name: " + rs1.getString("memberLastName") + ", " + rs1.getString("memberFirstName"));
                System.out.println("Library Card No.: " + cardNumber);
            }
            System.out.println();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
    public static void dueDate(){
        //Getting the current Date value
        LocalDate currentDate = LocalDate.now();
        //Adding one week to the current date
        LocalDate result = currentDate.plus(3, ChronoUnit.WEEKS);
        System.out.println(result);
    }

}
