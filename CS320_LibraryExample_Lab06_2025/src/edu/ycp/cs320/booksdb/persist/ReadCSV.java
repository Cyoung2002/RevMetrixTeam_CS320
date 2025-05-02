package edu.ycp.cs320.booksdb.persist;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ReadCSV implements Closeable {
	private BufferedReader reader;
	
	public ReadCSV(String resourceName) throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("edu/ycp/cs320/booksdb/persist/res/" + resourceName);
		
		if (in == null) {
			throw new IOException("Couldn't open " + resourceName);
		}
		this.reader = new BufferedReader(new InputStreamReader(in));
	}
	
	public List<String> next() throws IOException {
		String line = reader.readLine();
		if (line == null) {
			return null;
		}
		List<String> tuple = new ArrayList<String>();
		StringTokenizer tok = new StringTokenizer(line, "|");
		while (tok.hasMoreTokens()) {
			tuple.add(tok.nextToken().trim());
		}
		return tuple;
	}
	
	public ArrayList<ArrayList<String>> allRows() throws IOException {
		
		String line; 
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>(); 
		
		/*while ((line = reader.readLine()) != null) {
			
			ArrayList<String> tuple = new ArrayList<String>();
			StringTokenizer tok = new StringTokenizer(line, "|");
			
			while (tok.hasMoreTokens()) {
				tuple.add(tok.nextToken().trim());
			}  
			rows.add(tuple);
        }*/
		
		while ((line = reader.readLine()) != null) {
	        String[] parts = line.split("\\|", -1);  // preserve empty fields
	        ArrayList<String> tuple = new ArrayList<>(Arrays.asList(parts));
	        rows.add(tuple);
	    }

        return rows;
	}
	
	public void close() throws IOException {
		reader.close();
	}
}
