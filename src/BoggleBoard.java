import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Derrick Thai
 * @version October 10, 2014
 */
public class BoggleBoard
{
	private static final int[] DROW = { -1, -1, 0, 1, 1, 1, 0, -1 };
	private static final int[] DCOL = { 0, 1, 1, 1, 0, -1, -1, -1 };

	private char[][] board;
	private int dimension;

	public BoggleBoard(String fileName)
	{
		try
		{
			BufferedReader inFile = new BufferedReader(new FileReader(fileName));
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			while ((line = inFile.readLine()) != null)
				lines.add(line);

			board = new char[lines.size()][];
			dimension = 0;
			for (String nextLine : lines)
				board[dimension++] = nextLine.toCharArray();
			inFile.close();
		}
		catch (IOException exp)
		{
			board = new char[][] { { 'B', 'A', 'D' }, { 'F', 'I', 'L', 'E' },
					{ 'N', 'A', 'M', 'E' } };
		}
	}

	public boolean search(String word)
	{
		word = word.toUpperCase();
		char firstLetter = word.charAt(0);

		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				if (board[row][col] == firstLetter
						&& searchAround(row, col, word))
					return true;
		return false;
	}

	private boolean searchAround(int row, int col, String wordLeft)
	{
		if (wordLeft.length() == 0)
			return true;
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
				|| board[row][col] != wordLeft.charAt(0))
			return false;

		char saveLetter = board[row][col];
		board[row][col] = '#';
		String reducedWord = wordLeft.substring(1);
		boolean x = false;
		for (int dir = 0; dir < DROW.length; dir++)
			if (searchAround(row + DROW[dir], col + DCOL[dir], reducedWord))
				x = true;
		board[row][col] = saveLetter;
		return x;

	}

	public int getMaxScore(List<String> wordList)
	{
		int score = 0;
		for (String word : wordList)
			if (search(word))
				score += wordPoints(word.length());
		return score;
	}

	private int wordPoints(int length)
	{
		if ((length == 3 && dimension < 5) || length == 4)
			return 1;
		if (length == 5)
			return 2;
		if (length == 6)
			return 3;
		if (length == 7)
			return 5;
		if (length >= 8)
			return 11;
		return 0;
	}

	public String toString()
	{
		StringBuilder strBuild = new StringBuilder(2 * board.length
				* board[0].length + board.length);
		for (char[] row : board)
		{
			for (char ch : row)
				strBuild.append(ch).append(' ');
			strBuild.append("\n");
		}

		// Remove the extra new line (\n) and return the board
		strBuild.deleteCharAt(strBuild.length() - 1);
		return strBuild.toString();
	}
}
