import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Derrick Thai
 * @version October 10, 2014
 */
public class Boggle
{
	private static final String[] FILE_NAMES = { "board3x3.txt", "board5x5",
			"boggle3x3.txt", "boggle5x5.txt" };
	private static int fileNum = 2;

	public static void main(String[] args) throws IOException
	{
		// long start = System.nanoTime();
		// for (int i = 0; i < 1; i++)
		// {

		// Read and store the list of words
		BufferedReader inFile = new BufferedReader(new FileReader(
				"boggleWords.txt"));
		ArrayList<String> wordList = new ArrayList<String>();
		String line;
		while ((line = inFile.readLine()) != null)
			wordList.add(line);
		inFile.close();

		//
		System.out.println("= = = = = BOGGLE = = = = =");

		// Load up and display the BoggleBoard
		System.out.println("File Name: " + FILE_NAMES[fileNum]);
		BoggleBoard board = new BoggleBoard(FILE_NAMES[fileNum]);
		System.out.println(board);

		// Display the max score for this board
		System.out.println("Max Score: " + board.getMaxScore(wordList));

		// }
		// System.out.println((System.nanoTime() - start) / 1000000);
	}

}
