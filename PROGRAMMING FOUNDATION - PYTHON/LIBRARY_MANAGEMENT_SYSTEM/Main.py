from Library import Library
from Book import Book
from Member import Member

class Main(Library):

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