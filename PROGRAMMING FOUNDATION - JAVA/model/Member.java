package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

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
            b.markUnavailable();
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
            double penalty = diff > 15 ? (diff - 15) * 2 : 0;

            issuedBooks.remove(idx);
            issuedDates.remove(idx);
            b.markAvailable();

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
