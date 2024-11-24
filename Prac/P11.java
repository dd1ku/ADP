import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class P11{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        Library library = new Library(); //библиотека

        Author author = new Author(1, "George Orwell");
        Book book1 = author.writeBook("Cars", "11111", 1949);
        Book book2 = author.writeBook("PC", "22222", 1945);

        Librarian librarian1 = new Librarian(1, "Jane Monica", "jane@gmail.com", "qwerty"); // библиотекарь
        librarian1.manageBooks(library, book1, true);
        librarian1.manageBooks(library, book2, true);

        Reader reader1 = new Reader(1, "John Tom", "john@gmail.com", "password123");

        User.Register(reader1);

        User loggedInUser = User.Login("john@gmail.com", "password123");

        Book borrowBook1 = reader1.BorrowBook(library);
        Book borrowBook2 = reader1.BorrowBook(library);

        if (borrowBook1 != null){
            Loan loan1 = new Loan(borrowBook1, reader1);
            loan1.ReturnBook();
        }

        List<Loan> loans = new ArrayList<>();
        Loan loan2 = new Loan(borrowBook1, reader1);
        loans.add(loan2);
        System.out.println(Report.totalLoans(loans));

        librarian1.manageBooks(library, book1, false);

        System.out.println("Books in the library:");
        for (Book book : library.getBooks()){
            System.out.println("- " + book.getTitle());
        }


        in.close();
    }
}

class Author{
    private int id;
    private String name;

    public Author(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Book writeBook(String title, String ISBN, int year){
        return new Book(title, ISBN, this, year);
    }
    public String getName(){
        return name;
    }
}
class Book{
    private String title;
    private String ISBN;
    private Author author;
    private int publicationYear;
    private boolean availability;

    public Book(String title, String ISBN, Author author, int publicationYear){
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.publicationYear = publicationYear;
        this.availability = true;
    }

    public void changeAvailability(){
        this.availability = !this.availability;
    }

    public boolean isAvailable(){
        return availability;
    }

    public String getInfo(){
        return "Title: " + title + ", ISBN: " + ISBN + ", Author: " + author.getName() + ", Year: " + publicationYear;
    }
    public String getTitle(){
        return title;
    }
}
abstract class User{
    protected int id;
    protected String name;
    protected String email;
    protected String password;

    private static List<User> usersDatabase = new ArrayList<>();

    public User(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static boolean Register(User user){
        for (User existingUser : usersDatabase){
            if (existingUser.email.equals(user.email)){
                System.out.println("User with this email already exists!");
                return false;
            }
        }
        usersDatabase.add(user);
        System.out.println("User registered successfully!");
        return true;
    }
    public static User Login(String email, String password){
        for (User user : usersDatabase) {
            if (user.email.equals(email) && user.password.equals(password)){
                System.out.println("Login successful! Welcome, " + user.name);
                return user;
            }
        }
        System.out.println("Invalid login or password.");
        return null;
    }
}
class Reader extends User{
    public Reader(int id, String name, String email, String password){
        super(id, name, email, password);
    }
    public boolean ReturnBook(Book book){
        if (!book.isAvailable()){
            book.changeAvailability();
            System.out.println(name + " returned: " + book.getTitle());
            return true;
        }
        System.out.println("This book was not borrowed or already returned.");
        return false;
    }
    public Book BorrowBook(Library library){
        for (Book book : library.getBooks()){
            if (book.isAvailable()){
                book.changeAvailability();
                System.out.println(name + " borrowed: " + book.getTitle());
                return book;
            }
        }
        System.out.println("No available books to borrow.");
        return null;
    }
}
class Librarian extends User{
    public Librarian(int id, String name, String email, String password){
        super(id, name, email, password);
    }
    public void manageBooks(Library library, Book book, boolean add){
        if (add){
            library.addBook(book);
            System.out.println("Book added by librarian: " + book.getTitle());
        }
        else{
            library.removeBook(book);
            System.out.println("Book removed by librarian: " + book.getTitle());
        }
    }
}
class Loan{
    private Book book;
    private Reader reader;
    private Date loanDate;
    private Date returnDate;

    public Loan(Book book, Reader reader){
        this.book = book;
        this.reader = reader;
        this.loanDate = new Date();
    }
    public boolean issueLoan(){
        if (book.isAvailable()){
            book.changeAvailability();
            return true;
        }
        return false;
    }
    public boolean ReturnBook(){
        return reader.ReturnBook(book);
    }
    public Date getReturnDate(){
        return returnDate;
    }
}
class Report{
        public static String totalLoans(List<Loan> loans){
        return "Total Loans: " + loans.size();
    }
}
class Library{
    private List<Book> books;
    public Library(){
        this.books = new ArrayList<>();
    }
    public void addBook(Book book){
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }
    public boolean removeBook(Book book){
        if (books.remove(book)){
            System.out.println("Book removed: " + book.getTitle());
            return true;
        }
        System.out.println("Book not found in the library.");
        return false;
    }
    public List<Book> getBooks(){
        return books;
    }
}
