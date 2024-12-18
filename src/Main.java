
package com.mycompany.main; // This makes errors when comment it!


import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

    static Scanner input = new Scanner(System.in);
    static ArrayList<HashMap<String, String>> bookList = new ArrayList<>();
    static int idCount = 1;
    static int boxes;

    public static void main(String[] args) {


        System.out.println("Enter number of boxes you have ");
        boxes = input.nextInt();
        input.nextLine();

        while (true) {
            System.out.println("""
            
            ---------------------------------------------------
            Welcome to the Library Management System!
            1. Add a book
            2. Search for a book (by Title or ID)
            3. Issue a book (by ID)
            4. Return a book (by ID) 
            5. Delete a book (by ID)
            6. Edit book details (Title/Description by ID)
            7. View All books
            8. Exit
            9. Help
            """);
            System.out.println("Enter your choice");
            boolean full = false;
            int choice = input.nextInt();
            input.nextLine();

            // Add a book
            if (choice == 1) {
                // check if the library is full
                if (boxes == 0) {
                    System.out.println("the library is full");
                    full = true;
                }
                if (!full) {
                    addBook();
                }

            }
            // Search for book
            else if (choice == 2) {
                searchBook();
            }
            // Issue a book
            else if (choice == 3) {
                issueBook();
            }
            // Retrun a book
            else if (choice == 4) {
                returnBook();
            }
            // delete a book
            else if (choice == 5) {

            }
            // edit book details
            else if (choice == 6) {

            }
            // display all books
            else if (choice == 7) {
                for (HashMap<String, String> book : bookList) {
                    printBookInfo(book);
                }
            }
            else if (choice == 9) {
                System.out.println("""
                Welcome to the Library Management System!
                1. Add a book
                2. Search for a book (by Title or ID)
                3. Issue a book (by ID)
                4. Return a book (by ID) 
                5. Delete a book (by ID)
                6. Edit book details (Title/Description by ID)
                7. View All books
                8. Exit
                9. Help
                """);
            }
            else {
                System.out.println("please Enter a valid choice");
            }


        }
    }

    static void printBookInfo(HashMap<String ,String> book) {
        System.out.println("Title: " + book.get("title"));
        System.out.println("Description: " + book.get("description"));
        System.out.println("id: " + book.get("id"));
        System.out.println("availability: " + book.get("availability") + "\n--------------------------------");
    }

    static void addBook() {
        // add book title
        System.out.println("Enter book title");
        String title = input.nextLine();
        while (title.trim().isEmpty()) {
            System.out.println("please enter the name of the book ");
            title = input.nextLine();
        }

        // add book description
        System.out.println("Enter book description");
        String description = input.nextLine();
        while (description.trim().isEmpty()) {
            System.out.println("please enter the name of the book ");
            description = input.nextLine();
        }

        // Add the book to the book list
        HashMap<String, String> book = new HashMap<>();
        book.put("title", title);
        book.put("description", description);
        book.put("id", String.valueOf(idCount));
        book.put("availability", "yes");

        idCount++;
        bookList.add(book);
        boxes--;
    }

    static void searchBook() {
        System.out.println("""
                1. Search by title
                2. Search by id        
                """);
        String searchType = input.nextLine();


        // search by book title
        if (searchType.equals("1")) {
            boolean found = false;
            System.out.println("Enter book title you want");
            String bookTitle = input.nextLine();

            for (HashMap<String, String> book : bookList) {
                if (book.get("title").equals(bookTitle)) {
                    found = true;
                    printBookInfo(book);
                }
            }

            if (!found) {
                System.out.println("the book is not in the library");
            }
        }
        // search by id
        else if (searchType.equals("2")) {
            boolean found = false;
            System.out.println("Enter book id you want");
            String bookId = input.nextLine();

            for (HashMap<String, String> book : bookList) {
                if (book.get("id").equals(bookId)) {
                    found = true;
                    printBookInfo(book);
                }
            }
            if (!found) {
                System.out.println("the book is not in the library");
                searchBook();
            }
        }
        else {
            System.out.println("Enter a right command");
            searchBook();
        }

    }
    
    static void x(String msg) {
        //System.out.println(msg);
    }
    
    static int getBookIndex(String bookId) {
        x("getBookIndex("+bookId+")");
        
        int bookListLength = bookList.size();
        x("bookListLength = "+bookListLength);
        
        x("Start for");
        for (int bookListIndex = 0; bookListIndex < bookListLength; bookListIndex++) {
            HashMap<String, String> thisBook = bookList.get(bookListIndex);    
            x("thisBook: "+ thisBook);
            
            x("thisBook.get(\"id\") = "+thisBook.get("id"));
            if (thisBook.get("id").equals(bookId)) {
                x("thisBook.get(\"id\") == bookId");
                return bookListIndex;
            }
        }
        x("End for and return -1");
                
        return -1; // not found
        
    }
    
    static void issueBook() {
        
        
        while (true) {
            
            System.out.print("Enter book id: ");
            String bookId = input.nextLine();
            System.out.println("");
            
            int bookIndex = getBookIndex(bookId);
            if (bookIndex == -1 /* not found */) {
                System.out.println("This is not valid id!");
                continue;
            } 
            
            
            HashMap<String, String> book = bookList.get(bookIndex); // Get old book
            if ("no".equals(book.get("availability"))) {
                System.out.println("Sorry this book is already issued.");
                break;
                
            } else {
                
                
                book.put("availability","no"); // change its features
                bookList.set(bookIndex,book); // replace old by new book
                System.out.println("\"" + book.get("title") + "\" book has issued successfully.");
                
                break;
                
            }
            
        }
        
        
        
    }

    static void returnBook() {
        while (true) {
            
            System.out.print("Enter book id: ");
            String bookId = input.nextLine();
            System.out.println("");
            
            int bookIndex = getBookIndex(bookId);
            if (bookIndex == -1 /* not found */) {
                System.out.println("This is not valid id!");
                continue;
            } 
            
            
            HashMap<String, String> book = bookList.get(bookIndex); // Get old book
            if ("yes".equals(book.get("availability"))) {
                System.out.println("This book is already exist!");
                break;
                
            } else {
                
                
                book.put("availability","yes"); // change its features
                bookList.set(bookIndex,book); // replace old by new book
                System.out.println("\"" + book.get("title") + "\" book has returned successfully.");

                break;
                
            }
        }
    }
}