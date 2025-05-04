package edu.ycp.cs320.booksdb.persist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.BookAuthor;
import edu.ycp.cs320.booksdb.model.Establishment;
import edu.ycp.cs320.booksdb.model.Event;
import edu.ycp.cs320.booksdb.model.Frame;
import edu.ycp.cs320.booksdb.model.Game;
import edu.ycp.cs320.booksdb.model.Session;
import edu.ycp.cs320.booksdb.model.Shot;
import edu.ycp.cs320.booksdb.model.Ball;

public class InitialData {

	// reads initial Author data from CSV file and returns a List of Authors
	public static List<Author> getAuthors() throws IOException {
		List<Author> authorList = new ArrayList<Author>();
		ReadCSV readAuthors = new ReadCSV("authors.csv");
		try {
			// auto-generated primary key for authors table
			Integer authorId = 1;
			while (true) {
				List<String> tuple = readAuthors.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Author author = new Author();

				// read author ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file				
				Integer.parseInt(i.next());
				// auto-generate author ID, instead
				author.setAuthorId(authorId++);				
				author.setLastname(i.next());
				author.setFirstname(i.next());
				authorList.add(author);
			}
			System.out.println("authorList loaded from CSV file");
			return authorList;
		} finally {
			readAuthors.close();
		}
	}
	
	
	// reads initial Book data from CSV file and returns a List of Books
	public static List<Book> getBooks() throws IOException {
		List<Book> bookList = new ArrayList<Book>();
		ReadCSV readBooks = new ReadCSV("books.csv");
		try {
			// auto-generated primary key for table books
			Integer bookId = 1;
			while (true) {
				List<String> tuple = readBooks.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Book book = new Book();
				
				// read book ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file
				Integer.parseInt(i.next());
				// auto-generate book ID, instead
				book.setBookId(bookId++);				
//				book.setAuthorId(Integer.parseInt(i.next()));  // no longer in books table
				book.setTitle(i.next());
				book.setIsbn(i.next());
				book.setPublished(Integer.parseInt(i.next()));
				
				bookList.add(book);
			}
			System.out.println("bookList loaded from CSV file");			
			return bookList;
		} finally {
			readBooks.close();
		}
	}
	
	
	// reads initial BookAuthor data from CSV file and returns a List of BookAuthors
	public static List<BookAuthor> getBookAuthors() throws IOException {
		List<BookAuthor> bookAuthorList = new ArrayList<BookAuthor>();
		ReadCSV readBookAuthors = new ReadCSV("book_authors.csv");
		try {
			while (true) {
				List<String> tuple = readBookAuthors.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				BookAuthor bookAuthor = new BookAuthor();
				bookAuthor.setBookId(Integer.parseInt(i.next()));				
				bookAuthor.setAuthorId(Integer.parseInt(i.next()));
				bookAuthorList.add(bookAuthor);
			}
			System.out.println("bookAuthorList loaded from CSV file");			
			return bookAuthorList;
		} finally {
			readBookAuthors.close();
		}
	}
	
	
	// reads initial Author data from CSV file and returns a List of Authors
	public static List<Establishment> getEstablishments() throws IOException {
		List<Establishment> establishmentList = new ArrayList<Establishment>();
		ReadCSV readEstablishments = new ReadCSV("establishments.csv");
		try {	
			while (true) {
				List<String> tuple = readEstablishments.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Establishment establishment = new Establishment();

				// read author ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file				
				// Integer.parseInt(i.next());
				// auto-generate author ID, instead
				establishment.setLongname(i.next());				
				establishment.setShortname(i.next());
				establishment.setAddress(i.next());
				establishmentList.add(establishment);
			}
			System.out.println("establishmentList loaded from CSV file");
			return establishmentList;
		} finally {
			readEstablishments.close();
		}
	}
	
	
	public static List<Event> getEvents() throws IOException {
		List<Event> eventList = new ArrayList<Event>();
		ReadCSV readEvents = new ReadCSV("Events.csv");
		try {
			// auto-generated primary key for table books
			// Integer bookId = 1;
			while (true) {
				List<String> tuple = readEvents.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Event event = new Event();
				
				event.setLongname(i.next());
				event.setShortname(i.next());
				event.setType(i.next());
				event.setEstablishment(i.next());
				event.setSeason(i.next());
				event.setTeam(Integer.parseInt(i.next()));
				event.setComposition(i.next());
				event.setDay(i.next());
				event.setTime(i.next());
				event.setStart(i.next());
				event.setEnd(i.next());
				event.setGamesPerSession(Integer.parseInt(i.next()));
				event.setWeeks(Integer.parseInt(i.next()));
				event.setPlayoffs(Integer.parseInt(i.next()));
				
				eventList.add(event);
			}
			System.out.println("eventList loaded from CSV file");			
			return eventList;
		} finally {
			readEvents.close();
		}
	}
	
	
	public static List<Ball> getArsenal() throws IOException {
		
		List<Ball> arsenal = new ArrayList<Ball>();
		ReadCSV readArsenal = new ReadCSV("Arsenal.csv");
		//readArsenal.next();
		
		try {

			while (true) {
				List<String> tuple = readArsenal.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Ball ball = new Ball();
				
				ball.setLongname(i.next());
				ball.setShortname(i.next());
				ball.setBrand(i.next());
				ball.setType(i.next());
				ball.setCore(i.next());
				ball.setCover(i.next());
				ball.setColor(i.next());
				ball.setSurface(i.next());
				ball.setYear(i.next());
				ball.setSerialNumber(i.next());
				ball.setWeight(i.next());
				ball.setMapping(i.next());
				
				arsenal.add(ball);
			}
			System.out.println("Arsenal loaded from CSV file");			
			return arsenal;
		} finally {
			readArsenal.close();
		}
	}
	
// reads initial session data from CSV file and returns a List of Books
		public static List<Session> getSessions() throws IOException {
			List<Session> sessionList = new ArrayList<Session>();
			ReadCSV readSessions = new ReadCSV("sessions.csv");
			try {
				while (true) {
					List<String> tuple = readSessions.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					Session session = new Session();
					
					//WE WILL NEED TO ADD MORE OF THE SETTERS HERE JUST WANTED A BASIS
					
					//set the type of event (/league)
					session.setLeague(i.next());
					String skip = i.next();
					skip = i.next();
					session.setWeek(i.next());
					System.out.println(session.getLeague() + " week: " + session.getWeek());	
					//set the date it was bowled
					session.setBowled(i.next());
					skip = i.next();
					skip = i.next();
					skip = i.next();
					skip = i.next();
					session.setStart(i.next());
					session.setBall(i.next());
					System.out.println(session.getBowled() + " startlane: " + session.getStart() + " ball: " + session.getBall());
					//something will put here for setting the games...eventually
					//session.setGames(getGames);
					skip = i.next();
					skip = i.next();
					skip = i.next();
					session.setSeries(i.next());
					sessionList.add(session);
				}
				System.out.println("sessionList loaded from CSV file");			
				return sessionList;
			} finally {
				readSessions.close();
			}
		}



//reads initial game data from CSV file and returns a List of Books
		public static List<Object> getGames() throws IOException {
			
			List<Object> lists = new ArrayList<Object>();
			
			List<Game> gameList = new ArrayList<Game>();
			List<Frame> frameList = new ArrayList<Frame>();
			List<Shot> shotList = new ArrayList<Shot>();
			
			ReadCSV readGames = new ReadCSV("games.csv");
	        ArrayList<ArrayList<String>> rows = readGames.allRows();	// New ReadCSV method
	        
	        
	        for(int g = 0; g < rows.size(); g+=7) {
	        	
	        	int gameID = (g/7) + 1;
	        	Game game = new Game();
	        	
	        	game.setLeague(rows.get(g).get(1));
	        	game.setSeason(rows.get(g).get(2));
	        	game.setWeek(rows.get(g).get(3));
	        	game.setDate(rows.get(g).get(4));
	        	game.setGame(rows.get(g).get(5));
	        	game.setLane(rows.get(g).get(6));
	        	gameList.add(game);
	        	
	        	
	        	for(int f = 0; f < 12; f++) {
	        		
	        		Frame frame = new Frame();
	        		frame.setGameID(gameID);
	        		frame.setFrame(String.valueOf(f+1));
	        		frameList.add(frame);
	        	}
	        	
	        	
	        	for(int s = 9; s < 32; s++) {
	        		
	        		if(s == 29 && ((rows.get(g).get(s) == null) || (rows.get(g).get(s).equals("")))) {
	        			break;
	        		}
	        		else if(s == 31 && ((rows.get(g).get(s) == null) || (rows.get(g).get(s).equals("")))) {
	        			break;
	        		}
	        		
        			Shot shot = new Shot();
        			shot.setGameID(gameID);	// this attribute will be changed to game ID
        			shot.setFrameNumber((int) Math.ceil((double) (s-8) / 2));
        			shot.setShotNumber(String.valueOf(s-8));
        			
        			shot.setCount(rows.get(g).get(s));
    	        	shot.setLeave(rows.get(g+1).get(s));
    	        	shot.setScore(rows.get(g+2).get(s));
    	        	shot.setType(rows.get(g+3).get(s));
    	        	shot.setBoard(rows.get(g+4).get(s));
    	        	shot.setLane(rows.get(g+5).get(s));
    	        	shot.setBall(rows.get(g+6).get(s));
    	        	shotList.add(shot);
    	        	
    	        	if (s == 29 && (rows.get(g).get(s).equals("X")) && ((rows.get(g).get(31) == null) || (rows.get(g).get(31).equals("")))) {
	        			break;
	        		}
    	        	else if (s == 29 && !(rows.get(g).get(s).equals("X")) && ((rows.get(g).get(30) == null) || (rows.get(g).get(30).equals("")))) {
    	        		break;
    	        	}
        		}
	        }
	        
	        System.out.println("gameList loaded from CSV file");	
	        lists.add(gameList);
	        System.out.println("frameList loaded from CSV file");
	        lists.add(frameList);
	        System.out.println("frameList loaded from CSV file");
	        lists.add(shotList);
	        
	        readGames.close();
	        
	        return lists;
			
			/*ReadCSV readGames = new ReadCSV("games.csv");
			try {
				while (true) {
					List<String> tuple = readGames.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					Game game = new Game();
					
					String skip = i.next();
					
					//WE WILL NEED TO ADD MORE OF THE SETTERS HERE JUST WANTED A BASIS
					
					//skipping unneeded columns and rows
					for (int j = 0; j < 33 ; j++) {
						skip = i.next();
					}
					
					//starts at row 2, column B
					
					
					//have to start this at row 3, column B but still need information prior
					
					//also have to figure out how to skip the blank space between leave, score, etc.. and frames and shots
					
					
					//games
					for (int x = 0; x < 8; x++) {
						game.setLeague(i.next());
						game.setSeason(i.next());
						game.setWeek(i.next());
						game.setLane(i.next());
						game.setStrikeball(i.next());
						game.setSpareball(i.next());
						
						//frames
						for (int y = 0; y < 11; y++) {
							
							game.setFrameone(i.next());
							game.setFrametwo(i.next());
							game.setFramethree(i.next());
							game.setFramefour(i.next());
							game.setFramefive(i.next());
							game.setFramesix(i.next());
							game.setFrameseven(i.next());
							game.setFrameeight(i.next());
							game.setFramenine(i.next());
							game.setFrameten(i.next());
							game.setFrameeleven(i.next());
							game.setFrametwelve(i.next());
							
							//shots
							for (int z = 0; z < 22; z++) {
								
								game.setShotone(i.next());
								game.setShottwo(i.next());
								game.setShotthree(i.next());
								game.setShotfour(i.next());
								game.setShotfive(i.next());
								game.setShotsix(i.next());
								game.setShotseven(i.next());
								game.setShoteight(i.next());
								game.setShotnine(i.next());
								game.setShotten(i.next());
								game.setShoteleven(i.next());
								game.setShottwelve(i.next());
								game.setShotthirteen(i.next());
								game.setShotfourteen(i.next());
								game.setShotfifteen(i.next());
								game.setShotsixteen(i.next());
								game.setShotseventeen(i.next());
								game.setShoteighteen(i.next());
								game.setShotnineteen(i.next());
								game.setShottwenty(i.next());
								game.setShottwentyone(i.next());
								game.setShottwentytwo(i.next());
								game.setShottwentythree(i.next());
								
							}
								
						}
					}
				}
				System.out.println("gameList loaded from CSV file");			
				return gameList;
			} finally {
				readGames.close();
			}*/
		}
}


