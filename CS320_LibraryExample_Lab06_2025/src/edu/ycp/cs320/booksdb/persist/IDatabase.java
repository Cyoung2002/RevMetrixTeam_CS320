package edu.ycp.cs320.booksdb.persist;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Establishment;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.model.Ball;

public interface IDatabase {
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title);
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(String lastName);
	public Integer insertBookIntoBooksTable(String title, String isbn, int published, String lastName, String firstName);
	public Integer insertBallIntoArsenal(final String longname, final String shortname, final String brand, final String type, final String core, final String cover, final String color, final String surface, final String Year, final String serialNumber, final String weight, final String mapping);
	public List<Pair<Author, Book>> findAllBooksWithAuthors();
	public List<Author> findAllAuthors();
	public List<Ball> findAllBalls();
	public List<Author> removeBookByTitle(String title);
	public ArrayList<Establishment> findAllEstablishments();		
}
