
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

