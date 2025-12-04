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

    //Getters & Setters
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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


