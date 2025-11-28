
class Person:
    def __init__(self, name):
        self._name = name

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
