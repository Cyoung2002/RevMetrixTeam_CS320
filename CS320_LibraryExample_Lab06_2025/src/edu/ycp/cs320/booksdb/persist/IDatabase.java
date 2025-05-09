package edu.ycp.cs320.booksdb.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Establishment;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.model.Session;
import edu.ycp.cs320.booksdb.model.Shot;
import edu.ycp.cs320.booksdb.model.Ball;
import edu.ycp.cs320.booksdb.model.Event;
import edu.ycp.cs320.booksdb.model.Establishment;

public interface IDatabase {
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title);
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(String lastName);
	public Integer insertBookIntoBooksTable(String title, String isbn, int published, String lastName, String firstName);
	public Integer insertBallIntoArsenal(final String longname, final String shortname, final String brand, final String type, final String core, final String cover, final String color, final String surface, final String Year, final String serialNumber, final String weight, final String mapping);
	public Integer insertEvent(final String longname, final String shortname, final String type, final String establishment, final String season, final Integer team, final String composition, final String day, final String time, final String start, final String end_date, final Integer gamesPerSession, final Integer weeks, final Integer playoffs);
	public Integer insertEstablishment(final String longname, final String shortname, final String address, final String phone, final Integer lanes, final String type);
	public List<Pair<Author, Book>> findAllBooksWithAuthors();
	public List<Author> findAllAuthors();
	
	public ArrayList<Session> findAllSessions();
	public Integer insertSession(final String league, final String bowled, final String ball, final String startLane,final String week, final String series);
	public ArrayList<Event> findAllEvents();
	public ArrayList<Ball> findAllBalls();
	public List<Author> removeBookByTitle(String title);
	
	public ArrayList<Establishment> findAllEstablishments();
	public Integer insertEstablishmentIntoEstablishmentsTable(final String longname, final String shortname, final String address, final String phone, final Integer lanes, final String type);	
	public ArrayList<Shot> findAllShotsInGame(String gameID);
	public ArrayList<Session> findGameswithEventDate(final String longname);
	public ArrayList<Session> findGamesWithSessionDate(final String date);
	Double findOverallAverageForEvent(final String eventShortname);
	public ArrayList<Session> findGamesForSessionLeague(final String eventShortname);
}
