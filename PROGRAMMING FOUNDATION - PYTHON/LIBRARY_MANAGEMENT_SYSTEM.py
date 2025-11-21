
#BOOK CLASS
class Book:
    def __init__(self, title, author, isbn):
        self._title = title
        self._author = author
        self._isbn = isbn
        self._availability = True

   
    def get_title(self):
        return self._title
    
    def get_author(self):
        return self._author

    def get_isbn(self):
        return self._isbn

    def is_available(self):
        return self._availability
    

    def set_availability(self, status):
        self._availability = status

    def __str__(self):
        status = "Available" if self._availability else "Not Available"
        return f"{self._title} by {self._author} | ISBN: {self._isbn} | {status}"
    
    
    
    
    
#PERSON CLASS  
class Person:
    def __init__(self,name):
        self._name=name





#MEMBER CLASS
class Member(Person):
    def __init__(self, name, member_id):
        super().__init__(name)
        self._name=name
        self._member_id = member_id
        self._borrowed_books = [] 
        print(f"Member Name-{self._name} of ID-{self._member_id} added to member list\n")
  
    def borrow_book(self, book):
        if book.get_isbn() in self._borrowed_books:
            raise Exception("Member already borrowed this book.")

        self._borrowed_books.append(book.get_isbn())
        print(f"Book '{book.get_title()}({book.get_isbn()})'  issued to '{self.get_name()}({self.get_member_id()})'.")

    def return_book(self, book):
        if book.get_isbn() not in self._borrowed_books:
            raise Exception("Book not in member's borrowed list.")

        self._borrowed_books.remove(book.get_isbn())
        print(f"Book '{book.get_title()}({book.get_isbn()})'  returned by '{self.get_name()}({self.get_member_id()})'\n")

    def get_member_id(self):
        return self._member_id
    def get_name(self):
        return self._name

    def __str__(self):
        return f"Member: {self._name} | ID: {self._member_id}"
    
    
    
    
    
    


#LIBRARY CLASS
class Library:
    def __init__(self):
        self.books = []     #BOOKS LIST
        self.members = []   #MEMBERS LIST

   #FUNCTION TO ADD BOOK TO LIBRARY
    def add_book(self, book):
        self.books.append(book)
        print(f"BOOK '{book.get_title()}'-'{book.get_isbn()}' written by '{book.get_author()}' added to OUR LIBRARY \n")
   #FUNCTION TO REMOVE BOOK FROM LIBRARY
    def remove_book(self, isbn):
        for b in self.books:
            if b.get_isbn() == isbn:
                self.books.remove(b)
                print(f"Book '{b.get_title()}' (ISBN: {b.get_isbn()}) removed from library.\n")
                return
        
        raise Exception("Book not found.")

    #FUNCTION TO ADD MEMBER
    def add_member(self, member):
        self.members.append(member)

    #FUNCTION TO SEARCH BOOK
    def search_book(self, keyword):
        result = []

        for b in self.books:
            if keyword.lower() in b.get_title().lower() or keyword.lower() in b.get_author().lower() or keyword == b.get_isbn():
                result.append(b)
                return result
        raise Exception("BOOK NOT IN LIBRARY")
    

    # FUNCTION TO LEND BOOK FROM LIBRARY
    def lend_book(self, isbn, member_id):
        try:
            book = next(b for b in self.books if b.get_isbn() == isbn)
        except StopIteration:
            raise Exception("Book not found.")

        try:
            member = next(m for m in self.members if m.get_member_id() == member_id)
        except StopIteration:
            raise Exception("Member not found.")

        if not book.is_available():
            raise Exception("Book is not available.")

        #MEMBERS BORROWING BOOK FROM LIBRARY
        member.borrow_book(book)
        book.set_availability(False)

    #FUNCTION TO RETURN BOOK
    def return_book(self, isbn, member_id):
        try:
            book = next(b for b in self.books if b.get_isbn() == isbn)
        except StopIteration:
            raise Exception("Book not found.")

        try:
            member = next(m for m in self.members if m.get_member_id() == member_id)
        except StopIteration:
            raise Exception("Member not found.")

        #MEMBERS RETURNING BOOK TO LIBRARY
        member.return_book(book)
        book.set_availability(True)
        
        
        
        
        
        

#MAIN CLASS TO EXECUTE BASIC LIBRARY FUNCTIONS
class Main:

    library = Library()
    
    #ADDING BOOKS TO LIBRARY
    b1 = Book("Amma Diarylo Konni Pageelu", "Ravi Mantri", "HK01")
    b2 = Book("Romeo and Juliet", " Shakespeare","HK02")
    b3 = Book("The Last Masterpiece", "Laura Morelli", "HK03")
    print("\n==== ADDING BOOKS TO LIBRARY ===\n     =======================\n  ")
    library.add_book(b1)
    library.add_book(b2)
    library.add_book(b3)
    
    #ADDING MEMBERS TO LIBRARY
    print("\n==== ADDING MEMBER TO LIBRARY ===\n     ========================\n  ")
    m1 = Member("Uji", "MB1")
    m2 = Member("Shyam", "MB2")
    
    library.add_member(m1)
    library.add_member(m2)
    
    #LENDING BOOKS FROM LIBRARY
    print("\n==== LENDING BOOK FROM LIBRARY ===\n     ============================\n  ")
    try:
        library.lend_book("HK01", "MB1")
        library.lend_book("HK01", "MB2")
    except Exception as e:
        print("Error:", e)
    print("\n==== SEARCHING BOOK IN LIBRARY ===\n     =========================\n  ")
    
    #SEARCHING BOOK IN THE LIBRARY
    try:
        results = library.search_book("HK01")
        for r in results:
            print(r)
    except Exception as e:
        print("Error:",e)
   #RETURNING BOOK TO LIBRARY
    print("\n==== RETURNING BOOK TO LIBRARY ===\n     =========================\n  ")
    try:
        library.return_book("HK01", "MB1")
    except Exception as e:
        print("Error:", e)
   #REMOVING BOOK FROM LIBRARY
    print("\n==== REMOVING BOOK TO LIBRARY ===\n     ========================\n  ")
    try:
        library.remove_book("HK01")
    except Exception as e:
        print("Error:",e)
