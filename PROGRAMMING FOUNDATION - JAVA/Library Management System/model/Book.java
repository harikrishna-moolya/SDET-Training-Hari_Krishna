package model;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // false = available
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

    public void markAvailable() { 
    	this.isAvailable = true; 
    }  
    public void markUnavailable() {
    	this.isAvailable = false; 
    	}

    public void printDetails() {
        System.out.println("BookId: " + bookId + " | Title: " + title + ", Writer: " + author +
                (isAvailable ?  " (Available)": " (Issued)"));
    }
}

