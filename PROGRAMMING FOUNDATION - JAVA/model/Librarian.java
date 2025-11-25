package model;

import java.util.ArrayList;

public class Librarian {
	private ArrayList<Book> BookList;
    private ArrayList<Member> MemberList;
    private ArrayList<Transaction> TransactionList;

    public Librarian() {
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

    public void registerMember(Member m) {
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
