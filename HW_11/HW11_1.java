import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class HW11_1{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        Library library = new Library();
        Librarian librarian = new Librarian("Alice");
        Reader reader = new Reader("Bob");

        Book book1 = new Book("Cars", "Tom Holland", "1234");
        Book book2 = new Book("PC", "Logitech", "4321");

        librarian.manageBooks(library, book1, "add");
        librarian.manageBooks(library, book2, "add");
        library.displayBooks();

        reader.borrowBook(book1);
        reader.borrowBook(book2);
        library.displayBooks();

        reader.returnBook(book1);
        library.displayBooks();

        librarian.manageBooks(library, book2, "remove");
        library.displayBooks();

        in.close();
    }
}
class Book{
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;
    public Book(String title, String author, String ISBN){
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    public String getTitle(){
        return title;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public void changeStatus(boolean status){
        this.isAvailable = status;
    }
    public String toString(){
        return "Book [title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", isAvailable=" + isAvailable + "]";
    }
}
class Reader{
    private String name;
    private List<Book> bookList;
    public Reader(String name){
        this.name = name;
        this.bookList = new ArrayList<>();
    }

    public void borrowBook(Book book){
        if (book.isAvailable()){
            bookList.add(book);
            book.changeStatus(false);
            System.out.println(name + " borrowed " + book.getTitle());
        }
        else{
            System.out.println("Book " + book.getTitle() + " is not available.");
        }
    }

    public void returnBook(Book book){
        if (bookList.remove(book)){
            book.changeStatus(true);
            System.out.println(name + " returned " + book.getTitle());
        }
        else{
            System.out.println(name + " did not borrow " + book.getTitle());
        }
    }
}
class Librarian{
    private String name;
    public Librarian(String name){
        this.name = name;
    }

    public void manageBooks(Library library, Book book, String action){
        switch (action.toLowerCase()){
            case "add":
                library.addBook(book);
                break;
            case "remove":
                library.removeBook(book);
                break;
            default:
                System.out.println("Error! Enter 'add' or 'remove'");
        }
    }
}
class Library{
    private List<Book> books;
    public Library(){
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
        System.out.println("Book " + book.getTitle() + " added to library.");
    }
    public void removeBook(Book book){
        if (books.remove(book)) {
            System.out.println("Book " + book.getTitle() + " removed from library.");
        }
        else{
            System.out.println("Book " + book.getTitle() + " not found in library.");
        }
    }

    public void displayBooks(){
        System.out.println("Books in library:");
        for (Book book : books){
            System.out.println(book);
        }
    }
}