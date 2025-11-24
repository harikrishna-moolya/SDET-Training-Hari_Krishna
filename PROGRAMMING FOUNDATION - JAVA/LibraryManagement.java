package fundamentals;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = false; // false = available
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void updateStatus(boolean status) {
        this.isAvailable = status;
    }

    public void printDetails() {
        System.out.println("BookId: " + bookId + " | Title: " + title + ", Writer: " + author +
                (isAvailable ? " (Issued)" : " (Available)"));
    }
}

// TRANSACTION MODULE 

class Transaction {
    private String transactionId;
    private String bookId;
    private String memberId;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private double fineAmount;

    public Transaction(String transactionId, String bookId, String memberId, LocalDate issueDate) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = null;
        this.fineAmount = 0;
    }

    public void markReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
        calculateFine();
    }

    // Fine calculation (₹2 per day after 15 days)
    public double calculateFine() {
        if (returnDate == null) return 0;

        long days = ChronoUnit.DAYS.between(issueDate, returnDate);
        fineAmount = (days > 15) ? (days - 15) * 2 : 0;

        return fineAmount;
    }

    public void printTransaction() {
        System.out.println(
                "TransactionID: " + transactionId +
                " -->BookID: " + bookId +
                " -->MemberID: " + memberId +
                " -->Issued: " + issueDate +
                " -->Returned: " + returnDate +
                " -->Fine: ₹" + fineAmount
        );
    }

    public String getBookId() { return bookId; }
    public String getMemberId() { return memberId; }
    public LocalDate getReturnDate() { return returnDate; }
}

// Member Module

class Member {
    private String memberId;
    private String name;
    private ArrayList<Book> issuedBooks;
    private ArrayList<LocalDate> issuedDates;

    public Member( String memberId,String name) {
        this.memberId = memberId;
        this.name = name;
        issuedBooks = new ArrayList<>();
        issuedDates = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public void issueBook(Book b) {
        if (!b.isAvailable()) {
            issuedBooks.add(b);
            issuedDates.add(LocalDate.now());
            b.updateStatus(true);
            System.out.println(name + " issued: " + b.getBookTitle());
        } else {
            System.out.println(b.getBookTitle() + " is not available right now.");
        }
    }

    public void returnBook(Book b) {
        int idx = issuedBooks.indexOf(b);
        if (idx >= 0) {
            LocalDate takenDate = issuedDates.get(idx);
            long diff = ChronoUnit.DAYS.between(takenDate, LocalDate.now());
            double penalty = diff > 14 ? (diff - 14) * 5 : 0;

            issuedBooks.remove(idx);
            issuedDates.remove(idx);
            b.updateStatus(false);

            System.out.println("'"+b.getBookTitle() + "' returned by : " + name + " | Fine: ₹" + penalty);
        } else {
            System.out.println(name + " did not take this book.");
        }
    }

    public void displayUser() {
        System.out.println("User: " + name + " | MemberID: " + memberId);
        System.out.println("Issued Books:");
        for (Book b : issuedBooks)
            System.out.println("• " + b.getBookTitle());
    }
}

//Book & Member Collection Module

class CollectionCenter {
    private ArrayList<Book> BookList;
    private ArrayList<Member> MemberList;
    private ArrayList<Transaction> TransactionList;

    public CollectionCenter() {
        BookList = new ArrayList<>();
        MemberList = new ArrayList<>();
        TransactionList = new ArrayList<>();
    }

    public void addBook(Book b) {
        BookList.add(b);
    }

    public void removeBook(String name) {
        boolean flag = BookList.removeIf(v -> v.getBookTitle().equalsIgnoreCase(name));
        System.out.println(flag ? "Book removed successfully." : "Book not found.");
    }

    public void addMember(Member m) {
        MemberList.add(m);
    }

    public Book searchBook(String name) {
        for (Book b : BookList)
            if (b.getBookTitle().equalsIgnoreCase(name))
                return b;
        return null;
    }

    public Member searchUser(String code) {
        for (Member m : MemberList)
            if (m.getMemberId().equalsIgnoreCase(code))
                return m;
        return null;
    }

    // Add transaction when issuing a book
    public void addTransaction(Transaction t) {
        TransactionList.add(t);
    }

    public void showTransactions() {
        System.out.println("===== TRANSACTION HISTORY =====");
        for (Transaction t : TransactionList)
            t.printTransaction();
    }

    public void printBooksList() {
        System.out.println("===== List of Books =====");
        for (Book b : BookList)
            b.printDetails();
    }

    public void printMemberList() {
        System.out.println("===== Registered Members =====");
        for (Member m : MemberList)
            m.displayUser();
    }

    public ArrayList<Transaction> getTransactionList() {
        return TransactionList;
    }
}

// ----------------------------------------------------------------------

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CollectionCenter center = new CollectionCenter();

        while (true) {
            System.out.println("\n===== Library Management System ===== \n1. Add New Book \n2. Remove Book \n3. Add Member"
            		+ " \n3. Add Member \n4. Issue Book \n5. Return Book \n6. Display Books \n7. Display Members \n8. Show Transactions"
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
                    center.addMember(new Member(memId, memName));
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
