
package com.mycompany.main; // This makes errors when comment it!


import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int maxBooks;
        System.out.println("Enter max number of books ");
        maxBooks = scanner.nextInt();
        scanner.nextLine();
        String[] bookTitles = new String[maxBooks];
        String[] bookDescriptions = new String[maxBooks];
        boolean[] bookIssued = new boolean[maxBooks];
        int bookCount = 0;

        // Main menu loop
        while (true) {
            System.out.println("""
                Library Management System
                1. Add a Book
                2. Search for a Book
                3. Issue a Book
                4. Return a Book
                5. Delete a Book
                6. Edit Book Details
                7. View All Books
                8. Exit
                Choose an option: 
            """);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) { // Add a Book
                if (bookCount < maxBooks) {
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter book description: ");
                    String description = scanner.nextLine();

                    bookTitles[bookCount] = title;
                    bookDescriptions[bookCount] = description;

                    bookIssued[bookCount] = false;
                    bookCount++;

                    System.out.println("Book added successfully.");
                } else {
                    System.out.println("Library is full.");
                }
            } else if (choice == 2) { // Search for a Book
                System.out.println("Search by: 1. Title 2. ID");
                int searchChoice = scanner.nextInt();
                scanner.nextLine();

                if (searchChoice == 1) {
                    System.out.print("Enter book title: ");
                    String searchTitle = scanner.nextLine();
                    boolean found = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (bookTitles[i] != null && bookTitles[i].equalsIgnoreCase(searchTitle)) {
                            System.out.println("Book ID: " + (i + 1) + ", Title: " + bookTitles[i] +
                                    ", Description: " + bookDescriptions[i] +
                                    ", Status: " + (bookIssued[i] ? "Issued" : "Available"));
                            found = true;
                        }
                    }
                    if (!found) System.out.println("Book not found.");
                }
                else if (searchChoice == 2) {
                    System.out.print("Enter book ID: ");
                    int bookID = scanner.nextInt();
                    if (bookID > 0 && bookID <= bookCount && bookTitles[bookID - 1] != null) {
                        System.out.println("Book ID: " + bookID + ", Title: " + bookTitles[bookID - 1] +
                                ", Description: " + bookDescriptions[bookID - 1] +
                                ", Status: " + (bookIssued[bookID - 1] ? "Issued" : "Available"));
                    } else {
                        System.out.println("Invalid book ID.");
                    }
                }
            }
            else if (choice == 3) { // Issue a Book
                System.out.print("Enter book ID to issue: ");
                int bookID = scanner.nextInt();
                if (bookID > 0 && bookID <= bookCount && bookTitles[bookID - 1] != null) {
                    if (!bookIssued[bookID - 1]) {
                        bookIssued[bookID - 1] = true;
                        System.out.println("Book issued successfully.");
                    } else {
                        System.out.println("Book is already issued.");
                    }
                } else {
                    System.out.println("Invalid book ID.");
                }
            }
            else if (choice == 4) { // Return a Book
                System.out.print("Enter book ID to return: ");
                int bookID = scanner.nextInt();
                if (bookID > 0 && bookID <= bookCount && bookTitles[bookID - 1] != null) {
                    if (bookIssued[bookID - 1]) {
                        bookIssued[bookID - 1] = false;
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book is already available.");
                    }
                } else {
                    System.out.println("Invalid book ID.");
                }
            }
            else if (choice == 5) { // Delete a Book
                System.out.print("Enter book ID to delete: ");
                int bookID = scanner.nextInt();
                if (bookID > 0 && bookID <= bookCount && bookTitles[bookID - 1] != null) {
                    for (int i = bookID - 1; i < bookCount - 1; i++) {
                        bookTitles[i] = bookTitles[i + 1];
                        bookDescriptions[i] = bookDescriptions[i + 1];
                        bookIssued[i] = bookIssued[i + 1];
                    }
                    bookCount--;
                    System.out.println("Book deleted successfully.");
                } else {
                    System.out.println("Invalid book ID.");
                }
            }
            else if (choice == 6) { // Edit Book Details
                System.out.print("Enter book ID to edit: ");
                int bookID = scanner.nextInt();
                scanner.nextLine();
                if (bookID > 0 && bookID <= bookCount && bookTitles[bookID - 1] != null) {
                    System.out.println("Edit: 1. Title 2. Description");
                    int editChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (editChoice == 1) {
                        System.out.print("Enter new title: ");
                        bookTitles[bookID - 1] = scanner.nextLine();
                        System.out.println("Title updated.");
                    } else if (editChoice == 2) {
                        System.out.print("Enter new description: ");
                        bookDescriptions[bookID - 1] = scanner.nextLine();
                        System.out.println("Description updated.");
                    }
                } else {
                    System.out.println("Invalid book ID.");
                }
            }
            else if (choice == 7) { // View All Books
                for (int i = 0; i < bookCount; i++) {
                    if (bookTitles[i] != null) {
                        System.out.println("Book ID: " + (i + 1) + ", Title: " + bookTitles[i] +
                                ", Description: " + bookDescriptions[i] +
                                ", Status: " + (bookIssued[i] ? "Issued" : "Available"));
                    }
                }
            } else if (choice == 8) { // Exit
                System.out.println("Exiting the system.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}