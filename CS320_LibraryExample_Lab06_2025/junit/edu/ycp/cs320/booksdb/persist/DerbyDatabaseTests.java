package edu.ycp.cs320.booksdb.persist;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Ball;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Establishment;
import edu.ycp.cs320.booksdb.model.Pair;

public class DerbyDatabaseTests {

	private IDatabase db = null;
	
	ArrayList<Author> authors = null;
	ArrayList<Book>   books   = null;
	List<Pair<Author, Book>> bookAuthorList = null;
	List<Pair<Author, Book>> authorBookList = null;	
	
	ArrayList<Ball> arsenal = null;
	ArrayList<Establishment> establishments = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAuthorAndBookByTitle() {
		System.out.println("\n*** Testing findAuthorAndBookByTitle ***");
		
		String title = "A Briefer History of Time";

		// get the list of (Author, Book) pairs from DB
		authorBookList = db.findAuthorAndBookByTitle(title);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (authorBookList.isEmpty()) {
			System.out.println("No book found in library with title <" + title + ">");
			fail("No book with title <" + title + "> returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {			
			authors = new ArrayList<Author>();
			for (Pair<Author, Book> authorBook : authorBookList) {
				Author author = authorBook.getLeft();
				Book   book   = authorBook.getRight();
				authors.add(author);
				System.out.println(author.getLastname() + "," + author.getFirstname() + ", " + book.getTitle() + "," + book.getIsbn());
			}			
		}
	}

	@Test
	public void testFindAuthorAndBookByAuthorLastName() {
		System.out.println("\n*** Testing findAuthorAndBooksByAuthorLastName ***");

		String lastName = "Hawking";
		
		// get the list of (Author, Book) pairs from DB
		authorBookList = db.findAuthorAndBookByAuthorLastName(lastName);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (authorBookList.isEmpty()) {
			System.out.println("No books found for author <" + lastName + ">");
			fail("No books for author <" + lastName + "> returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			books = new ArrayList<Book>();
			for (Pair<Author, Book> authorBook : authorBookList) {
				Author author = authorBook.getLeft();
				Book book = authorBook.getRight();
				books.add(book);
				System.out.println(author.getLastname() + "," + author.getFirstname() + "," + book.getTitle() + "," + book.getIsbn());
			}			
		}
	}

	@Test
	public void testFindAllBooksWithAuthors() {
		System.out.println("\n*** Testing findAllBooksWithAuthors ***");

		// get the list of (Author, Book) pairs from DB
		bookAuthorList = db.findAllBooksWithAuthors();
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (bookAuthorList.isEmpty()) {
			System.out.println("No books in database");
			fail("No books returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			books = new ArrayList<Book>();
			for (Pair<Author, Book> authorBook : bookAuthorList) {
				Author author = authorBook.getLeft();
				Book book = authorBook.getRight();
				books.add(book);
				System.out.println(book.getTitle() + ", " + book.getIsbn() + ", " + author.getLastname() + ", " + author.getFirstname());
			}			
		}
	}

	@Test
	public void testFindAllAuthors() {

		System.out.println("\n*** Testing findAllAuthors ***");

		// get the list of (Author, Book) pairs from DB
		List<Author> authorList = db.findAllAuthors();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (authorList.isEmpty()) {
			System.out.println("No authors found in library");
			fail("No authors returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			authors = new ArrayList<Author>();
			for (Author author : authorList) {
				authors.add(author);
				System.out.println(author.getLastname() + ", " + author.getFirstname());
			}			
		}
	}

	@Test
	public void testInsertBookIntoBooksTable() {
		System.out.println("\n*** Testing insertBookIntoBooksTable ***");

		String title     = "Wired for War";
		String isbn      = "0-143-11684-3";
		int    published = 2009;
		String lastName  = "Singer";
		String firstName = "P.J.";
  
				
		// insert new book (and possibly new author) into DB
		Integer book_id = db.insertBookIntoBooksTable(title, isbn, published, lastName, firstName);

		// check the return value - should be a book_id > 0
		if (book_id > 0)
		{
			// try to retrieve the book and author from the DB
			// get the list of (Author, Book) pairs from DB
			authorBookList = db.findAuthorAndBookByAuthorLastName(lastName);
			
			if (authorBookList.isEmpty()) {
				System.out.println("No books found for author <" + lastName + ">");
				fail("Failed to insert new book <" + title + "> into Library DB");
			}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
			else {
				System.out.println("New book (ID: " + book_id + ") successfully added to Books table: <" + title + ">");
				
				// now delete Book (and its Author) from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been used
				List<Author> authors = db.removeBookByTitle(title);				
			}
		}
		else
		{
			System.out.println("Failed to insert new book (ID: " + book_id + ") into Books table: <" + title + ">");
			fail("Failed to insert new book <" + title + "> into Library DB");
		}
	}
	
	//test was created on 4/27/25 @ 10pm, this test shows a new establishment being added
	@Test
	public void testInsertEstablishmentIntoEstablishmentsTable() {
		System.out.println("\n*** Testing insertEstablishmentIntoEstablishmentsTable ***");

		String longname    	= "Laser Lanes";
		String shortname    = "LA";
		String address 		= "3905 E. Market St  York  PA  17402";
  
				
		// insert new book (and possibly new author) into DB
		Integer establishment_id = db.insertEstablishmentIntoEstablishmentsTable(longname, shortname, address);

		// check the return value - should be a book_id > 0
		if (establishment_id > 0)
		{
			// try to retrieve the book and author from the DB
			// get the list of (Author, Book) pairs from DB
			establishments = db.findAllEstablishments();
			
			if (establishments.isEmpty()) {
				System.out.println("No establishments found within establishments table <" + establishment_id + ">");
				fail("Failed to insert new establishment <" + longname + "> into DB");
			}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
			else {
				System.out.println("New establishment: " + longname + ") successfully added to establishments table: <" + establishment_id + ">");
				
				// now delete establishment from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been used
				//we do not have this function implemented yet
			}
		}
		else
		{
			System.out.println("Failed to insert new establishment: " + longname + ") into establishments table: <" + establishment_id + ">");
			fail("Failed to insert new establishment <" + longname + "> into DB");
		}
	}
	
	//test was created on 4/27/25 @ 10pm, this test shows a new establishment being added
	@Test
	public void testInsertBallIntoArsenal() {
		System.out.println("\n*** Testing insertBallIntoArsenal ***");
		
		String longname 	= "Blue Pearl Hammer";
		String shortname 	= "BPH"; 
		String brand 		= "Hammer";
		String type 		= "Urethane";
		String core 		= "Symmetric";
		String cover 		= "Solid";
		String color 		= "Blue";
		String surface 		= "?";
		String year 		= "2025";
		String serialNumber = "1BVJ9KL0O";
		String weight 		= "15";
		String mapping 		= "?";
  
					
		// insert new book (and possibly new author) into DB
		Integer ball_id = db.insertBallIntoArsenal(longname, shortname, brand, type, core, cover, color, surface, year, serialNumber, weight, mapping);

		// check the return value - should be a book_id > 0
		if (ball_id > 0)
		{
			// try to retrieve the book and author from the DB
			// get the list of (Author, Book) pairs from DB
			arsenal = db.findAllBalls();
				
			if (arsenal.isEmpty()) {
				System.out.println("No balls found within arsenal with ID: <" + ball_id + ">");
				fail("Failed to insert new ball <" + longname + "> into DB");
			}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
			else {
				System.out.println("New ball: " + longname + ") successfully added to arsenal with this ID: <" + ball_id + ">");
					
				// now delete establishment from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been used
				//we do not have this function implemented yet
			}
		}
		else
		{
			System.out.println("Failed to insert new ball: " + longname + ") into arsenal with ID: <" + ball_id + ">");
			fail("Failed to insert new ball <" + longname + "> into DB");
		}
	}
	
	

	@Test
	public void testRemoveBookByTitle() {
		System.out.println("\n*** Testing removeBookByTitle ***");
		
		String title     = "Outliers";
		String isbn      = "0-316-01793-0";
		int    published = 2010;		
		String lastName  = "Gladwell";
		String firstName = "Malcolm";
				
		// insert new book (and new author) into DB
		Integer book_id = db.insertBookIntoBooksTable(title, isbn, published, lastName, firstName);
		
		// check to see that insertion was successful before proceeding
		if (book_id > 0) {
			// now delete Book (and its Author) from DB
			List<Author> authors = db.removeBookByTitle(title);
			
			if (authors.isEmpty()) {
				System.out.println("Failed to remove Author(s) for book with title <" + title + ">");
				fail("No Author(s) removed from DB for Book with title <" + title + ">");
			}
			else {
				System.out.println("Author <" + authors.get(0).getLastname() + ", " + authors.get(0).getFirstname() + "> removed from Library DB");
			}					
			
			// get the list of (Author, Book) pairs from DB
			authorBookList = db.findAuthorAndBookByTitle(title);
			
			if (authorBookList.isEmpty()) {
				System.out.println("All Books with title <" + title + "> were removed from the Library DB");
			}
			else {
				fail("Book with title <" + title + "> remains in Library DB after delete");			
			}
		}
		else {
			System.out.println("Failed to insert new book (ID: " + book_id + ") into Books table: <" + title + ">");
			fail("Failed to insert new book <" + title + "> into Library DB");			
		}
	}
}
