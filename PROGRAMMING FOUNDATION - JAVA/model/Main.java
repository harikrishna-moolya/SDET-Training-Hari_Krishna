package model;

import java.time.LocalDate;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Librarian center = new Librarian("RAM","LBR1");

        while (true) {
            System.out.println("\n===== Library Management System ===== \n1. Add New Book \n2. Remove Book \n3. Add Member"
            		+ " \n4. Issue Book \n5. Return Book \n6. Display Books \n7. Display Members \n8. Show Transactions"
            		+ " \n9. Exit \nChoose an Option: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();
                    center.addBook(new Book(id, title, author));
                    break;

                case 2:
                    System.out.print("Enter Book Title to delete: ");
                    center.removeBook(sc.nextLine());
                    break;

                case 3:
                	System.out.print("Enter Member ID: ");
                    String memId = sc.nextLine();
                    System.out.print("Enter Member Name: ");
                    String memName = sc.nextLine();
                    center.registerMember(new Member(memId, memName));
                    break;

                case 4:
                    System.out.print("Enter Member ID: ");
                    Member m = center.searchUser(sc.nextLine());
                    if (m == null) {
                        System.out.println("Member Not Found.");
                        break;
                    }

                    System.out.print("Enter Book Title to Issue: ");
                    Book b = center.searchBook(sc.nextLine());
                    if (b == null) {
                        System.out.println("Book Not Found.");
                        break;
                    }

                    if (b.isAvailable()==false) {
                            System.out.println("Book is already issued to another member.");
                            break;
                        }
                    // create transaction
                    String txnId = "TXN" + System.currentTimeMillis();
                    Transaction txn = new Transaction(txnId, b.getBookId(), m.getMemberId(), LocalDate.now());
                    center.addTransaction(txn);

                    m.issueBook(b);
                    System.out.println("Transaction Created: " + txnId);
                    break;
                    
                    

                case 5:
                    System.out.print("Enter Member ID: ");
                    Member m1 = center.searchUser(sc.nextLine());
                    if (m1 == null) {
                        System.out.println("Member Not Found.");
                        break;
                    }

                    System.out.print("Enter Book Title to Return: ");
                    Book b1 = center.searchBook(sc.nextLine());
                    if (b1 == null) {
                        System.out.println("Book Not in issued List.");
                        break;
                    }

                    // update transaction
                    for (Transaction t : center.getTransactionList()) {
                        if (t.getBookId().equalsIgnoreCase(b1.getBookId()) &&
                                t.getMemberId().equalsIgnoreCase(m1.getMemberId()) &&
                                t.getReturnDate() == null) {

                            t.markReturned(LocalDate.now());
                            System.out.println("Fine: ₹" + t.calculateFine());
                            break;
                        }
                    }

                    m1.returnBook(b1);
                    break;

                case 6:
                    center.printBooksList();
                    break;

                case 7:
                    center.printMemberList();
                    break;

                case 8:
                    center.showTransactions();
                    break;

                case 9:
                    System.out.println("Exiting Application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Option! Try Again.");
            }
        }
    }
}
