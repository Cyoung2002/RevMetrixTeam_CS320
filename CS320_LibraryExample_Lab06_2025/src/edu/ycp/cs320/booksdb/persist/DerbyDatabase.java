package edu.ycp.cs320.booksdb.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.BookAuthor;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.model.Session;
import edu.ycp.cs320.booksdb.model.Event;
import edu.ycp.cs320.booksdb.model.Establishment;
import edu.ycp.cs320.booksdb.model.Ball;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	
	// transaction that retrieves a Book, and its Author by Title
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(final String title) {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					stmt.setString(1, title);
					
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + title + "> was not found in the books table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that retrieves a list of Books with their Authors, given Author's last name
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(final String lastName) {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Authors and Books based on Author's last name, passed into query
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from  authors, books, bookAuthors " +
							"  where authors.lastname = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id "   +
							"  order by books.title asc, books.published asc"
					);
					stmt.setString(1, lastName);
					
					// establish the list of (Author, Book) Pairs to receive the result
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					// execute the query, get the results, and assemble them in an ArrayLsit
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that retrieves all Books in Library, with their respective Authors
	@Override
	public List<Pair<Author, Book>> findAllBooksWithAuthors() {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from authors, books, bookAuthors " +
							"  where authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id "   +
							"  order by books.title asc"
					);
					
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					// check if any books were found
					if (!found) {
						System.out.println("No books were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}	
	
	
	// transaction that retrieves all Authors in Library
	@Override
	public List<Author> findAllAuthors() {
		return executeTransaction(new Transaction<List<Author>>() {
			@Override
			public List<Author> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from authors " +
							" order by lastname asc, firstname asc"
					);
					
					List<Author> result = new ArrayList<Author>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						
						result.add(author);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No authors were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// transaction that retrieves all Establishments in Library
	@Override
	public ArrayList<Establishment> findAllEstablishments() {
		return executeTransaction(new Transaction<ArrayList<Establishment>>() {
			@Override
			public ArrayList<Establishment> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from establishments"
					);
					
					ArrayList<Establishment> result = new ArrayList<Establishment>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Establishment establishment = new Establishment();
						loadEstablishment(establishment, resultSet, 1);
						
						result.add(establishment);
					}
					
					// check if any establishments were found
					if (!found) {
						System.out.println("No establishments were found in the database");
					}
					
					return (ArrayList<Establishment>) result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	private void loadEstablishment(Establishment establishment, ResultSet resultSet, int index) throws SQLException {
		resultSet.getString(index++);
		establishment.setLongname(resultSet.getString(index++));
		establishment.setShortname(resultSet.getString(index++));
		establishment.setAddress(resultSet.getString(index++));
	}


	@Override
	public List<Ball> findAllBalls() {
		return executeTransaction(new Transaction<List<Ball>>() {
			@Override
			public List<Ball> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from arsenal " +
							" order by short_name"
					);
					
					List<Ball> result = new ArrayList<Ball>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Ball ball = new Ball();
						loadBall(ball, resultSet, 1);
						
						result.add(ball);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No balls were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public ArrayList<Event> findAllEvents() {
		return executeTransaction(new Transaction<ArrayList<Event>>() {
			@Override
			public ArrayList<Event> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from events "
					);
					
					ArrayList<Event> result = new ArrayList<Event>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Event event = new Event();
						loadEvent(event, resultSet, 1);
						
						result.add(event);
					}
					
					// check if any events were found
					if (!found) {
						System.out.println("No events were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<Establishment> findAllEstablishments() {
		return executeTransaction(new Transaction<List<Establishment>>() {
			@Override
			public List<Establishment> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from establishments "
					);
					
					List<Establishment> result = new ArrayList<Establishment>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Establishment establishment = new Establishment();
						loadEstablishment(establishment, resultSet, 1);
						
						result.add(establishment);
					}
					
					// check if any events were found
					if (!found) {
						System.out.println("No establishments were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// transaction that inserts new Book into the Books table
	// also first inserts new Author into Authors table, if necessary
	// and then inserts entry into BookAuthors junction table
	@Override
	public Integer insertBookIntoBooksTable(final String title, final String isbn, final int published, final String lastName, final String firstName) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet5 = null;				
				
				// for saving author ID and book ID
				Integer author_id = -1;
				Integer book_id   = -1;

				// try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				try {
					stmt1 = conn.prepareStatement(
							"select author_id from authors " +
							"  where lastname = ? and firstname = ? "
					);
					stmt1.setString(1, lastName);
					stmt1.setString(2, firstName);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if Author was found then save author_id					
					if (resultSet1.next())
					{
						author_id = resultSet1.getInt(1);
						System.out.println("Author <" + lastName + ", " + firstName + "> found with ID: " + author_id);						
					}
					else
					{
						System.out.println("Author <" + lastName + ", " + firstName + "> not found");
				
						// if the Author is new, insert new Author into Authors table
						if (author_id <= 0) {
							// prepare SQL insert statement to add Author to Authors table
							stmt2 = conn.prepareStatement(
									"insert into authors (lastname, firstname) " +
									"  values(?, ?) "
							);
							stmt2.setString(1, lastName);
							stmt2.setString(2, firstName);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New author <" + lastName + ", " + firstName + "> inserted in Authors table");						
						
							// try to retrieve author_id for new Author - DB auto-generates author_id
							stmt3 = conn.prepareStatement(
									"select author_id from authors " +
									"  where lastname = ? and firstname = ? "
							);
							stmt3.setString(1, lastName);
							stmt3.setString(2, firstName);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								author_id = resultSet3.getInt(1);
								System.out.println("New author <" + lastName + ", " + firstName + "> ID: " + author_id);						
							}
							else	// really should throw an exception here - the new author should have been inserted, but we didn't find them
							{
								System.out.println("New author <" + lastName + ", " + firstName + "> not found in Authors table (ID: " + author_id);
							}
						}
					}
					
					// now insert new Book into Books table
					// prepare SQL insert statement to add new Book to Books table
					stmt4 = conn.prepareStatement(
							"insert into books (title, isbn, published) " +
							"  values(?, ?, ?) "
					);
					stmt4.setString(1, title);
					stmt4.setString(2, isbn);
					stmt4.setInt(3, published);
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New book <" + title + "> inserted into Books table");					

					// now retrieve book_id for new Book, so that we can set up BookAuthor entry
					// and return the book_id, which the DB auto-generates
					// prepare SQL statement to retrieve book_id for new Book
					stmt5 = conn.prepareStatement(
							"select book_id from books " +
							"  where title = ? and isbn = ? and published = ? "
									
					);
					stmt5.setString(1, title);
					stmt5.setString(2, isbn);
					stmt5.setInt(3, published);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						book_id = resultSet5.getInt(1);
						System.out.println("New book <" + title + "> ID: " + book_id);						
					}
					else	// really should throw an exception here - the new book should have been inserted, but we didn't find it
					{
						System.out.println("New book <" + title + "> not found in Books table (ID: " + book_id);
					}
					
					// now that we have all the information, insert entry into BookAuthors table
					// which is the junction table for Books and Authors
					// prepare SQL insert statement to add new Book to Books table
					stmt6 = conn.prepareStatement(
							"insert into bookAuthors (book_id, author_id) " +
							"  values(?, ?) "
					);
					stmt6.setInt(1, book_id);
					stmt6.setInt(2, author_id);
					
					// execute the update
					stmt6.executeUpdate();
					
					System.out.println("New entry for book ID <" + book_id + "> and author ID <" + author_id + "> inserted into BookAuthors junction table");						
					
					System.out.println("New book <" + title + "> inserted into Books table");					
					
					return book_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	@Override
	public Integer insertEstablishmentIntoEstablishmentsTable(final String longName, final String shortName, final String address) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet5 = null;				
				
				Integer establishment_id = -1;
				try {
					
					// now insert new Establishment into Establishments table
					// prepare SQL insert statement to add new Establishment to Establishments table
					stmt4 = conn.prepareStatement(
							"insert into establishments (longname, shortname, address) " +
							"  values(?, ?, ?) "
					);
					stmt4.setString(1, longName);
					stmt4.setString(2, shortName);
					stmt4.setString(3, address);
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New establishment <" + longName + "> inserted into Establishments table");					

					// now retrieve establishment_id for new Establishment, so that we can set up Establishment entry
					// and return the establishment_id, which the DB auto-generates
					// prepare SQL statement to retrieve establishment_id for new Book
					stmt5 = conn.prepareStatement(
							"select establishment_id from establishments " +
							"  where longName = ? and shortName = ? and address = ? "
									
					);
					stmt5.setString(1, longName);
					stmt5.setString(2, shortName);
					stmt5.setString(3, address);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						establishment_id = resultSet5.getInt(1);
						System.out.println("New establishment <" + longName + "> ID: " + establishment_id);						
					}
					else	// really should throw an exception here - the new establishment should have been inserted, but we didn't find it
					{
						System.out.println("New establishment <" + longName + "> not found in Establishments table (ID: " + establishment_id);
					}
					
//					// now that we have all the information, insert entry into Establishments table
//					// prepare SQL insert statement to add new establishment to Establishments table
//					stmt6 = conn.prepareStatement(
//							"insert into establishments (establishment_id) " +
//							"  values(?) "
//					);
//					stmt6.setInt(1, establishment_id);
//					
//					// execute the update
//					stmt6.executeUpdate();
//					
//					System.out.println("New entry for establishment ID <" + establishment_id + "> inserted into Establishments table");						
//					
//					System.out.println("New establishment <" + longName + "> inserted into Establishments table");					
					
					return establishment_id;
				} finally {				
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	
	@Override
	public Integer insertBallIntoArsenal(final String longname, final String shortname, final String brand, final String type, final String core, final String cover, final String color, final String surface, final String year, final String serialNumber, final String weight, final String mapping) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet5 = null;				
				
				// for saving ball_id
				Integer ball_id   = -1;

				// try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				try {
					// now insert new ball into Arsenal table
					// prepare SQL insert statement to add new Book to Books table
					stmt4 = conn.prepareStatement(
							"insert into arsenal (long_name, short_name, brand, type, core, cover, color, surface, ball_year, serial_number, weight, mapping) " +
							"  values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
					);
					stmt4.setString(1, longname);
					stmt4.setString(2, shortname);
					stmt4.setString(3, brand);
					stmt4.setString(3, type);
					stmt4.setString(3, core);
					stmt4.setString(3, cover);
					stmt4.setString(3, color);
					stmt4.setString(3, surface);
					stmt4.setString(3, year);
					stmt4.setString(3, serialNumber);
					
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New ball <" + longname + "> inserted into Books table");					

					// now retrieve book_id for new Book, so that we can set up BookAuthor entry
					// and return the book_id, which the DB auto-generates
					// prepare SQL statement to retrieve book_id for new Book
					stmt5 = conn.prepareStatement(
							"select ball_id from arsenal " +
							"  where short_name = ? and serial_number = ? "
									
					);
					stmt5.setString(1, shortname);
					stmt5.setString(2, serialNumber);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						ball_id = resultSet5.getInt(1);
						System.out.println("New book (shortname)< " + shortname + "> ID: " + ball_id);						
					}
					else	// really should throw an exception here - the new book should have been inserted, but we didn't find it
					{
						System.out.println("New ball <" + shortname + "> not found in Books table (ID: " + ball_id);
					}
					
					// now that we have all the information, insert entry into BookAuthors table
					// which is the junction table for Books and Authors
					// prepare SQL insert statement to add new Book to Books table
					/*stmt6 = conn.prepareStatement(
							"insert into bookAuthors (book_id, author_id) " +
							"  values(?, ?) "
					);
					stmt6.setInt(1, book_id);
					stmt6.setInt(2, author_id);
					
					// execute the update
					stmt6.executeUpdate();
					
					System.out.println("New entry for book ID <" + book_id + "> and author ID <" + author_id + "> inserted into BookAuthors junction table");						
					
					System.out.println("New book <" + title + "> inserted into Books table");					
					*/
					return ball_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	@Override
	public Integer insertEvent(final String longname, final String shortname, final String establishmentShort, final String weeknight, final String start, final String end, final Integer gamesPerSession) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;				
				
				// for saving event ID
				Integer event_id = -1;

				// try to retrieve event_id (if it exists) from DB, for Event's long and short names
				try {
					stmt1 = conn.prepareStatement(
							"select event_id from events " +
							"  where longname = ? and shortname = ? "
					);
					stmt1.setString(1, longname);
					stmt1.setString(2, shortname);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if Event was found then save event_id					
					if (resultSet1.next())
					{
						event_id = resultSet1.getInt(1);
						System.out.println("Event <" + longname + ", " + shortname + "> found with ID: " + event_id);						
					}
					else
					{
						System.out.println("Event <" + longname + ", " + shortname + "> not found");
				
						// if the Event is new, insert new Event into Events table
						if (event_id <= 0) {
							// prepare SQL insert statement to add Event to Events table
							stmt2 = conn.prepareStatement(
									"insert into events (longname, shortname, establishmentShort, weeknight, start, end, gamesPerSession) " +
									"  values(?, ?, ?, ?, ?, ?, ?) "
							);
							stmt2.setString(1, longname);
							stmt2.setString(2, shortname);
							stmt2.setString(3, establishmentShort);
							stmt2.setString(4, weeknight);
							stmt2.setString(5, start);
							stmt2.setString(6, end);
							stmt2.setInt(7, gamesPerSession);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New Event <" + longname + ", " + shortname + "> inserted in Events table");						
						
							// try to retrieve event_id for new Event - DB auto-generates event_id
							stmt3 = conn.prepareStatement(
									"select event_id from events " +
									"  where longname = ? and shortname = ? "
							);
							stmt3.setString(1, longname);
							stmt3.setString(2, shortname);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								event_id = resultSet3.getInt(1);
								System.out.println("New Event <" + longname + ", " + shortname + "> ID: " + event_id);						
							}
							else	// really should throw an exception here - the new event should have been inserted, but we didn't find them
							{
								System.out.println("New event <" + longname + ", " + shortname + "> not found in Events table (ID: " + event_id);
							}
						}
					}				
					
					return event_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
				}
			}
		});
	}
	
	@Override
	public Integer insertEstablishment(final String longname, final String shortname, final String address) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;				
				
				// for saving establishment ID
				Integer establishment_id = -1;

				// try to retrieve establishment_id (if it exists) from DB, for Establishment's long and short names
				try {
					stmt1 = conn.prepareStatement(
							"select establishment_id from establishments " +
							"  where longname = ? and shortname = ? "
					);
					stmt1.setString(1, longname);
					stmt1.setString(2, shortname);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if Establishment was found then save establishment_id					
					if (resultSet1.next())
					{
						establishment_id = resultSet1.getInt(1);
						System.out.println("Establishment <" + longname + ", " + shortname + "> found with ID: " + establishment_id);						
					}
					else
					{
						System.out.println("Establishment <" + longname + ", " + shortname + "> not found");
				
						// if the Establishment is new, insert new Establishment into Establishments table
						if (establishment_id <= 0) {
							// prepare SQL insert statement to add Establishment to Establishments table
							stmt2 = conn.prepareStatement(
									"insert into establishments (longname, shortname, address) " +
									"  values(?, ?, ?) "
							);
							stmt2.setString(1, longname);
							stmt2.setString(2, shortname);
							stmt2.setString(3, address);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New Establishment <" + longname + ", " + shortname + "> inserted in Establishments table");						
						
							// try to retrieve establishment_id for new Establishment - DB auto-generates establishment_id
							stmt3 = conn.prepareStatement(
									"select establishment_id from establishments " +
									"  where longname = ? and shortname = ? "
							);
							stmt3.setString(1, longname);
							stmt3.setString(2, shortname);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								establishment_id = resultSet3.getInt(1);
								System.out.println("New Establishment <" + longname + ", " + shortname + "> ID: " + establishment_id);						
							}
							else	// really should throw an exception here - the new establishment should have been inserted, but we didn't find them
							{
								System.out.println("New establishment <" + longname + ", " + shortname + "> not found in Establishments table (ID: " + establishment_id);
							}
						}
					}				
					
					return establishment_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
				}
			}
		});
	}
	@Override
	public Integer insertSession(final String league, final String bowled, final int week, final int series) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet5 = null;				
				
				// for saving author ID and book ID
				//do event id and session id instead
				Integer session_id = -1;
				Integer event_id   = -1;

				// try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				try {
					stmt1 = conn.prepareStatement(
							"select event_id from events " +
							"  where league = ? "
					);
					stmt1.setString(1, league);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if event was found then save event_id					
					if (resultSet1.next())
					{
						event_id = resultSet1.getInt(1);
						System.out.println("Event/League <" + league +  "> found with ID: " + event_id);						
					}
					else
					{
						System.out.println("Event/League <" + league +  "> not found");
						//Kinda unsure what to do if the event/league isnt found. Simple solution
						//for while I was working was to just print that they should first insert the new event
						//and return to this page later, should probably be another way.
						System.out.println("Please insert the new event from the events page, then try again.");
						/*
						// if the Author is new, insert new Author into Authors table
						if (event_id <= 0) {
							// prepare SQL insert statement to add Author to Authors table
							stmt2 = conn.prepareStatement(
									"insert into events (lastname, firstname) " +
									"  values(?, ?) "
							);
							stmt2.setString(1, lastName);
							stmt2.setString(2, firstName);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New author <" + lastName + ", " + firstName + "> inserted in Authors table");						
						
							// try to retrieve author_id for new Author - DB auto-generates author_id
							stmt3 = conn.prepareStatement(
									"select author_id from authors " +
									"  where lastname = ? and firstname = ? "
							);
							stmt3.setString(1, lastName);
							stmt3.setString(2, firstName);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								author_id = resultSet3.getInt(1);
								System.out.println("New author <" + lastName + ", " + firstName + "> ID: " + author_id);						
							}
							else	// really should throw an exception here - the new author should have been inserted, but we didn't find them
							{
								System.out.println("New author <" + lastName + ", " + firstName + "> not found in Authors table (ID: " + author_id);
							}
						}
						*/
						return session_id; //(?) not sure if I should do this but temp idea
					}
					
					
					// now insert new Session into Sessions table
					// prepare SQL insert statement to add new Session to Sessions table
					stmt4 = conn.prepareStatement(
							"insert into sessions (bowled, week, series) " +
							"  values(?, ?, ?) "
					);
					stmt4.setString(1, bowled);
					stmt4.setInt(2, week);
					stmt4.setInt(3, series);
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New session <" + week + "> inserted into Sessions table");					

					// now retrieve session_id for new Session, so that we can set up SessionEvent entry
					// and return the session_id, which the DB SHOULD NOT-auto-generate. THE REASON-
					// User entered the week, so can't have two id's for the same thing
					// prepare SQL statement to retrieve book_id for new Book
					stmt5 = conn.prepareStatement(
							"select session from sessions " +
							"  where bowled = ? and week = ? and series = ? "
									
					);
					stmt5.setString(1, bowled);
					stmt5.setInt(2, week);
					stmt5.setInt(3, series);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						session_id = resultSet5.getInt(1);
						System.out.println("New book <" + title + "> ID: " + book_id);						
					}
					else	// really should throw an exception here - the new book should have been inserted, but we didn't find it
					{
						System.out.println("New book <" + title + "> not found in Books table (ID: " + book_id);
					}
					
					// now that we have all the information, insert entry into BookAuthors table
					// which is the junction table for Books and Authors
					// prepare SQL insert statement to add new Book to Books table
					stmt6 = conn.prepareStatement(
							"insert into bookAuthors (book_id, author_id) " +
							"  values(?, ?) "
					);
					stmt6.setInt(1, book_id);
					stmt6.setInt(2, author_id);
					
					// execute the update
					stmt6.executeUpdate();
					
					System.out.println("New entry for book ID <" + book_id + "> and author ID <" + author_id + "> inserted into BookAuthors junction table");						
					
					System.out.println("New book <" + title + "> inserted into Books table");					
					
					return book_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	// transaction that deletes Book (and possibly its Author) from Library
	@Override
	public List<Author> removeBookByTitle(final String title) {
		return executeTransaction(new Transaction<List<Author>>() {
			@Override
			public List<Author> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;							
				
				ResultSet resultSet1    = null;			
				ResultSet resultSet2    = null;
				ResultSet resultSet5    = null;
				
				try {
					// first get the Author(s) of the Book to be deleted
					// just in case it's the only Book by this Author
					// in which case, we should also remove the Author(s)
					stmt1 = conn.prepareStatement(
							"select authors.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					
					// get the Book's Author(s)
					stmt1.setString(1, title);
					resultSet1 = stmt1.executeQuery();
					
					// assemble list of Authors from query
					List<Author> authors = new ArrayList<Author>();					
				
					while (resultSet1.next()) {
						Author author = new Author();
						loadAuthor(author, resultSet1, 1);
						authors.add(author);
					}
					
					// check if any Authors were found
					// this shouldn't be necessary, there should not be a Book in the DB without an Author
					if (authors.size() == 0) {
						System.out.println("No author was found for title <" + title + "> in the database");
					}
										
					// now get the Book(s) to be deleted
					// we will need the book_id to remove associated entries in BookAuthors (junction table)
				
					stmt2 = conn.prepareStatement(
							"select books.* " +
							"  from  books " +
							"  where books.title = ? "
					);
					
					// get the Book(s)
					stmt2.setString(1, title);
					resultSet2 = stmt2.executeQuery();
					
					// assemble list of Books from query
					List<Book> books = new ArrayList<Book>();					
				
					while (resultSet2.next()) {
						Book book = new Book();
						loadBook(book, resultSet2, 1);
						books.add(book);
					}
					
					// first delete entries in BookAuthors junction table
					// can't delete entries in Books or Authors tables while they have foreign keys in junction table
					// prepare to delete the junction table entries (bookAuthors)
					stmt3 = conn.prepareStatement(
							"delete from bookAuthors " +
							"  where book_id = ? "
					);
					
					// delete the junction table entries from the DB for this title
					// this works if there are not multiple books with the same name
					stmt3.setInt(1, books.get(0).getBookId());
					stmt3.executeUpdate();
					
					System.out.println("Deleted junction table entries for book(s) <" + title + "> from DB");									
					
					// now delete entries in Books table for this title
					stmt4 = conn.prepareStatement(
							"delete from books " +
							"  where title = ? "
					);
					
					// delete the Book entries from the DB for this title
					stmt4.setString(1, title);
					stmt4.executeUpdate();
					
					System.out.println("Deleted book(s) with title <" + title + "> from DB");									
					
					// now check if the Author(s) have any Books remaining in the DB
					// only need to check if there are any entries in junction table that have this author ID
					for (int i = 0; i < authors.size(); i++) {
						// prepare to find Books for this Author
						stmt5 = conn.prepareStatement(
								"select books.book_id from books, bookAuthors " +
								"  where bookAuthors.author_id = ? "
						);
						
						// retrieve any remaining books for this Author
						stmt5.setInt(1, books.get(i).getAuthorId());
						resultSet5 = stmt5.executeQuery();						

						// if nothing returned, then delete Author
						if (!resultSet5.next()) {
							stmt6 = conn.prepareStatement(
								"delete from authors " +
								"  where author_id = ?"
							);
							
							// delete the Author from DB
							stmt6.setInt(1, authors.get(i).getAuthorId());
							stmt6.executeUpdate();
							
							System.out.println("Deleted author <" + authors.get(i).getLastname() + ", " + authors.get(i).getFirstname() + "> from DB");
							
							// we're done with this, so close it, since we might have more to do
							DBUtil.closeQuietly(stmt6);
						}
						
						// we're done with this, so close it since we might have more to do
						DBUtil.closeQuietly(resultSet5);
						DBUtil.closeQuietly(stmt5);
					}
					return authors;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);					
				}
			}
		});
	}
	
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	// TODO: Here is where you name and specify the location of your Derby SQL database
	// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/CS320-2025-LibraryExample-DB/library.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	// retrieves Author information from query result set
	private void loadAuthor(Author author, ResultSet resultSet, int index) throws SQLException {
		author.setAuthorId(resultSet.getInt(index++));
		author.setLastname(resultSet.getString(index++));
		author.setFirstname(resultSet.getString(index++));
	}
	
	// retrieves Book information from query result set
	private void loadBook(Book book, ResultSet resultSet, int index) throws SQLException {
		book.setBookId(resultSet.getInt(index++));
//		book.setAuthorId(resultSet.getInt(index++));  // no longer used
		book.setTitle(resultSet.getString(index++));
		book.setIsbn(resultSet.getString(index++));
		book.setPublished(resultSet.getInt(index++));
	}
	
	// retrieves WrittenBy information from query result set
	private void loadBookAuthors(BookAuthor bookAuthor, ResultSet resultSet, int index) throws SQLException {
		bookAuthor.setBookId(resultSet.getInt(index++));
		bookAuthor.setAuthorId(resultSet.getInt(index++));
	}
	
	private void loadBall(Ball ball, ResultSet resultSet, int index) throws SQLException {
		
		ball.setLongname(resultSet.getString(index++));
		ball.setShortname(resultSet.getString(index++));
		ball.setBrand(resultSet.getString(index++));
		ball.setType(resultSet.getString(index++));
		ball.setCore(resultSet.getString(index++));
		ball.setCover(resultSet.getString(index++));
		ball.setColor(resultSet.getString(index++));
		ball.setSurface(resultSet.getString(index++));
		ball.setYear(resultSet.getString(index++));
		ball.setSerialNumber(resultSet.getString(index++));
		ball.setWeight(resultSet.getString(index++));
		ball.setMapping(resultSet.getString(index++));
	}
	
	private void loadEvent(Event event, ResultSet resultSet, int index) throws SQLException {
		event.setEventID(resultSet.getString(index++));
		event.setLongname(resultSet.getString(index++));
		event.setShortname(resultSet.getString(index++));
		event.setEstablishmentShort(resultSet.getString(index++));
		event.setWeeknight(resultSet.getString(index++));
		event.setStart(resultSet.getString(index++));
		event.setEnd(resultSet.getString(index++));
		event.setGamesPerSession(Integer.parseInt(resultSet.getString(index++)));
	}
	
	private void loadEstablishment(Establishment establishment, ResultSet resultSet, int index) throws SQLException {
		establishment.setLongname(resultSet.getString(index++));
		establishment.setShortname(resultSet.getString(index++));
		establishment.setAddress(resultSet.getString(index++));
	}
	
	private void loadSession(Session session, ResultSet resultSet, int index) throws SQLException{
		session.setLeague(resultSet.getString(index++));
		session.setBowled(resultSet.getString(index++));
		session.setWeek(resultSet.getInt(index++));
		session.setSeries(resultSet.getInt(index++));
	}
	
	//  creates the Authors and Books tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;	
				PreparedStatement stmt4 = null;	
				PreparedStatement stmt5 = null;	
				PreparedStatement stmt6 = null;	
				PreparedStatement stmt7 = null;
				
			
				try {
					
					stmt1 = conn.prepareStatement(
						"create table authors (" +
						"	author_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	lastname varchar(40)," +
						"	firstname varchar(40)" +
						")"
					);	
					stmt1.executeUpdate();					
					System.out.println("Authors table created");
					
					
					stmt2 = conn.prepareStatement(
							"create table books (" +
							"	book_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
//							"	author_id integer constraint author_id references authors, " +  	// this is now in the BookAuthors table
							"	title varchar(70)," +
							"	isbn varchar(15)," +
							"   published integer" +
							")"
					);
					stmt2.executeUpdate();					
					System.out.println("Books table created");					
					
					
					stmt3 = conn.prepareStatement(
							"create table bookAuthors (" +
							"	book_id   integer constraint book_id references books, " +
							"	author_id integer constraint author_id references authors " +
							")"
					);
					stmt3.executeUpdate();
					System.out.println("BookAuthors table created");	
					
					
					stmt4 = conn.prepareStatement(
							"create table establishments (" +
									"	establishment_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	longname varchar(60), " +
									"	shortname varchar(30), " +
									"	address varchar(60) " +
							")"
					);	
					stmt4.executeUpdate();
					System.out.println("Establishment table created");
					
					
					stmt5 = conn.prepareStatement(
							"create table events (" +
									"	event_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									//"	establishment_id integer, " +
									"	longname varchar(60), " +
									"	shortname varchar(30), " +
									"	establishment varchar(30), " +
									"	weeknight varchar(10), " +
									"	start_date varchar(10), " +
									"	end_date varchar(10), " +
									"	games_per_session integer " +
							")"
					);	
					stmt5.executeUpdate();
					System.out.println("Events table created");
					
					
					stmt6 = conn.prepareStatement(
							"create table arsenal (" +
									"	ball_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	long_name varchar(60), " +
									"	short_name varchar(30), " +
									"	brand varchar(30), " +
									"	type varchar(30), " +
									"	core varchar(30), " +
									"	cover varchar(30), " +
									"	color varchar(30), " +
									"	surface varchar(30), " +
									"	ball_year varchar(10), " +
									"	serial_number varchar(20), " +
									"	weight varchar(10), " +
									"	mapping varchar(30) " +
							")"
					);	
					stmt6.executeUpdate();
					System.out.println("Arsenal table created");
					
					stmt7 = conn.prepareStatement(
							"create table session (" +
									"	session_id integer primary key " +
									"		generated always as identity (starts with 1, increment by 1), " +
									"	league varchar(30), " +
									"	date_bowled varchar(10), " +
									"	week integer, " +
									"	series integer " +
									")"
					);
					stmt7.executeUpdate();
					System.out.println("Session table created");
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Author> authorList;
				List<Book> bookList;
				List<BookAuthor> bookAuthorList;
				List<Establishment> establishmentList;		// new
				List<Event> eventList;						// new
				List<Ball> arsenal;
				List<Session> sessionList;						// new
				
				try {
					authorList     = InitialData.getAuthors();
					bookList       = InitialData.getBooks();
					bookAuthorList = InitialData.getBookAuthors();	
					establishmentList = InitialData.getEstablishments();
					eventList 	   = InitialData.getEvents();
					arsenal 	   = InitialData.getArsenal();
					sessionList		   = InitialData.getSessions();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAuthor     = null;
				PreparedStatement insertBook       = null;
				PreparedStatement insertBookAuthor = null;
				
				PreparedStatement insertEstablishment 	= null;		// new
				PreparedStatement insertEvent       	= null;		// new
				PreparedStatement insertBall       		= null;		// new
				PreparedStatement insertSession 		= null;

				try {
					// must completely populate Authors table before populating BookAuthors table because of primary keys
					insertAuthor = conn.prepareStatement("insert into authors (lastname, firstname) values (?, ?)");
					for (Author author : authorList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertAuthor.setString(1, author.getLastname());
						insertAuthor.setString(2, author.getFirstname());
						insertAuthor.addBatch();
					}
					insertAuthor.executeBatch();
					System.out.println("Authors table populated");
					
					
					// must completely populate Books table before populating BookAuthors table because of primary keys
					insertBook = conn.prepareStatement("insert into books (title, isbn, published) values (?, ?, ?)");
					for (Book book : bookList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
//						insertBook.setInt(1, book.getAuthorId());	// this is now in the BookAuthors table
						insertBook.setString(1, book.getTitle());
						insertBook.setString(2, book.getIsbn());
						insertBook.setInt(3, book.getPublished());
						insertBook.addBatch();
					}
					insertBook.executeBatch();
					System.out.println("Books table populated");					
					
					
					// must wait until all Books and all Authors are inserted into tables before creating BookAuthor table
					// since this table consists entirely of foreign keys, with constraints applied
					insertBookAuthor = conn.prepareStatement("insert into bookAuthors (book_id, author_id) values (?, ?)");
					for (BookAuthor bookAuthor : bookAuthorList) {
						insertBookAuthor.setInt(1, bookAuthor.getBookId());
						insertBookAuthor.setInt(2, bookAuthor.getAuthorId());
						insertBookAuthor.addBatch();
					}
					insertBookAuthor.executeBatch();	
					System.out.println("BookAuthors table populated");	
					
					
					// must completely populate Establishment table before events
					insertEstablishment = conn.prepareStatement("insert into establishments (longname, shortname, address) values (?, ?, ?)");
					for (Establishment establishment : establishmentList) {
						insertEstablishment.setString(1, establishment.getLongname());
						insertEstablishment.setString(2, establishment.getShortname());
						insertEstablishment.setString(3, establishment.getAddress());
						insertEstablishment.addBatch();
					}
					insertEstablishment.executeBatch();
					System.out.println("Establishment table populated");
					
					
					// must completely populate Establishment table before events
					insertEvent = conn.prepareStatement("insert into events (longname, shortname, establishment, weeknight, start_date, end_date, games_per_session) values (?, ?, ?, ?, ?, ?, ?)");
					for (Event event : eventList) {
						//insertEvent.setInt(1, event.getEstablishmentId());
						insertEvent.setString(1, event.getLongname());
						insertEvent.setString(2, event.getShortname());
						insertEvent.setString(3, event.getEstablishmentShort());
						insertEvent.setString(4, event.getWeeknight());
						insertEvent.setString(5, event.getStart());
						insertEvent.setString(6, event.getEnd());
						insertEvent.setInt(7, event.getGamesPerSession());
						insertEvent.addBatch();
					}
					insertEvent.executeBatch();
					System.out.println("Event table populated");
					
					
					// must completely populate Establishment table before events
					insertBall = conn.prepareStatement("insert into arsenal (long_name, short_name, brand, type, core, cover, color, surface, ball_year, serial_number, weight, mapping) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Ball ball : arsenal) {
						//insertEvent.setInt(1, event.getEstablishmentId());
						insertBall.setString(1, ball.getLongname());
						insertBall.setString(2, ball.getShortname());
						insertBall.setString(3, ball.getBrand());
						insertBall.setString(4, ball.getType());
						insertBall.setString(5, ball.getCore());
						insertBall.setString(6, ball.getCover());
						insertBall.setString(7, ball.getColor());
						insertBall.setString(8, ball.getSurface());
						insertBall.setString(9, ball.getYear());
						insertBall.setString(10, ball.getSerialNumber());
						insertBall.setString(11, ball.getWeight());
						insertBall.setString(12, ball.getMapping());
						insertBall.addBatch();
					}
					insertBall.executeBatch();
					System.out.println("Arsenal table populated");
					
					insertSession = conn.prepareStatement("insert into session (league, date_bowled, week, series) values (?, ?, ?, ?)");
					for(Session session : sessionList) {
						insertSession.setString(1, session.getLeague());
						insertSession.setString(2, session.getBowled());
						insertSession.setInt(3, session.getWeek());
						insertSession.setInt(4, session.getSeries());
						insertSession.addBatch();
					}
					insertSession.executeBatch();
					System.out.println("Session table populated!");
					//YIPPEE
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertBook);
					DBUtil.closeQuietly(insertAuthor);
					DBUtil.closeQuietly(insertBookAuthor);	
					DBUtil.closeQuietly(insertEstablishment);
					DBUtil.closeQuietly(insertEvent);	
					DBUtil.closeQuietly(insertBall);
					DBUtil.closeQuietly(insertSession);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}
}
