package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
