package com.sample;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class is for providing input to the DroolsTest class. 
 * It is a placeholder for interfacing with the natural language group.
 * 
 * Since this is for testing, gene constants are not realistic, reflect relevance to this pathway
 * http://cgap.nci.nih.gov/Genes/PathGeneQuery?PAGE=1&ORG=Hs&PATH=h_At1rPathway
 * 
 * @author Massoud Maher
 * @version 0.1
 */
public class GeneListInputAgent {
	
	// Reads in file
	private BufferedReader reader = null;
	
	// Array of each line in file
	private ArrayList<String> fileLines;
	
	// Name of input file
	private String fileName;
	
	// Genes involved in epidermal growth factor receptor
	public static final List<String> GENE_EPIDERMAL_GROWTH_FACTOR_RECEPTOR = 
			Arrays.asList("NM_005228", "NM_201284", "NM_201282", "NM_201283");

	// Genes involved in myocyte enhancer 2c 
	public static final List<String> GENE_MYOCYTE_ENHANCER_2C =
			Arrays.asList("NM_002397", "NM_001193350", "NM_001193347", "NM_001131005", "NM_001193348", "NM_001193349");
	
	// Genes involved in calmodulin 1
	public static final List<String> GENE_CALMODULIN_1 =
			Arrays.asList("NM_006888");
	
	/**
	 * Creates an GeneListInputAgent for reading in a text file.
	 * 
	 * @param fileName Filename of file wanted to be read in
	 */
	public GeneListInputAgent(String fileName) {
		this.fileName = fileName;
		try {
			File filePath = new File( this.getClass().getResource(fileName).toURI());
			System.err.println(filePath.toString() + ": FILEPATH");
			reader = new BufferedReader( new FileReader(filePath) );
		}
		catch (FileNotFoundException fileException) {
			// TODO add log statement
			System.err.println("File " + fileName + " not found!");
		}
		catch(URISyntaxException URIException) {
			System.err.println(URIException.toString());
		}
		fileLines = toArray();
	}
	
	/**
	 * Reads a line from the file this Agent is handling
	 * 
	 * @return line that was just read, returns null if there is no more to read
	 */
	public String readLine() {
		try {
			return reader.readLine();
		}
		catch(IOException iOException) {
			// TODO add log statement
			System.err.print(iOException.getMessage());
			return null;
		}
	}
	
	/**
	 * Reads in the file and stores each line in the file array
	 * 
	 * @return ArrayList<String> that contains each line of the file as elements
	 */
	private ArrayList<String> toArray() {
		ArrayList<String> output = new ArrayList<String>();

		String line = readLine();
		while(line != null) {
			// if line isn't already in array, add it
			if(!output.contains(line)) {
				output.add(line);
			}
			
			line = readLine();
		}
		
		return output;
	}

	/**
	 * Returns array of file lines
	 * 
	 * @return ArrayList<String> that contains each line of file, no duplicates
	 */
	public ArrayList<String> getArray() {
		return fileLines;
	}
	
	/**
	 * Returns name of input file
	 * 
	 * @return String that is name of input file
	 */
	public String getFileName() {
		return fileName;
	}
}




