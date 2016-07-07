import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


public class RealHangMan {
	//private String _dictionary = "C:\\Users\\Joshua\\dictionary.txt";
	private final int NUMGUESS = 6;
	private ArrayList<String> _activeList;
	private int _L;
	private String[] _currentAnswer;
	private int _shownLetters;
	private int _triesRemaining;
	private boolean _gameOver;
	private ArrayList<String> _guessed;
	private List<String> alphabet = Arrays.asList("abcdefghijklmnopqrstuvwxyz".split(""));
	public RealHangMan(int length, String dictionary) throws IOException {
		_L = length;
		_gameOver = false;
		_triesRemaining = NUMGUESS;
		_guessed = new ArrayList<String>(NUMGUESS);
		_currentAnswer = new String[_L];
		for (int i = 0; i < _L; i++) {
			_currentAnswer[i] = "_";
		}
		_shownLetters = 0;
		_activeList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(dictionary));
		try {
			String line = br.readLine();

			while (line != null) {
				if(line.length() == _L) {
					String[] chars = line.split("");
					List<String> charlist = Arrays.asList(chars);
					HashSet s = new HashSet(charlist);
					if (s.size() == _L) {
						_activeList.add(line);					
					}	
				}
				line = br.readLine();
			}			
		} finally {
			br.close();
		}
	}
	
	public void printSortedGuesses() {
		Collections.sort(_guessed);
		if(_guessed.size() <= 13) {
			System.out.println("Guessed " + _guessed);
		} else {
			ArrayList<String> rest = new ArrayList<String>();
			for (String s : alphabet) {
				if(! _guessed.contains(s)) {
					rest.add(s);
				}
			}
			System.out.println("Not Guessed " + rest);
			
		}
		
	}
	public boolean isGameOver() {
		return _gameOver;
	}
	public void displayAnswer() {
		StringBuilder sb = new StringBuilder();
		for (String s : _currentAnswer) {
			sb.append(s);
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public void showActiveList() {
		System.out.println("Active list has " + _activeList.size() + " words:");
		for (String w : _activeList) {
			System.out.println(w);
		}
		System.out.println();
	}
	public void update(String letter) {
		_guessed.add(letter);
		ArrayList<ArrayList<String>> candidateSets = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < _L+1; i++) {
			candidateSets.add(new ArrayList<String>());
		}
		//System.out.println("Pre: Size of activelist: " + _activeList.size());
		//logic
		for( String word : _activeList) {
			String[] wordArray = word.split("");
			// 0th is always empty because of split so we will use 0th to index notfound set
			boolean notFound = true;
			for (int i = 0; i < _L; i++) {
				if (wordArray[i].equals(letter)) {
					notFound = false;
					candidateSets.get(i).add(word);
				}
			}
			if (notFound) {
				candidateSets.get(_L).add(word);
			}
		}
		
		int maxIndex = 0;
		int max = -1;
		for (int i = 0; i < _L + 1; i++) {
			int len = candidateSets.get(i).size();
			// System.out.println("Candidate Set " + i + " len " + len);
			if (len > max) {
				max = len;
				maxIndex = i;
			}
		}
		if (maxIndex == _L) {
			_triesRemaining--;
			if (_triesRemaining == 0){
				System.out.println("You lose");
				showActiveList();
				_gameOver = true;
			} else {
				System.out.print("Tries:" + _triesRemaining + "   ");
			}
		} else {
			_currentAnswer[maxIndex] = letter;
			displayAnswer();
			_shownLetters++;
			if (_shownLetters == _L) {
				System.out.println("You win");
				_gameOver = true;
			}
		}
		_activeList = candidateSets.get(maxIndex);
		System.out.print("Uncertainty: " + _activeList.size() + "  ");
		printSortedGuesses();
	}

	public static void main(String[] args) throws Exception {
		String dict = args[0];
		System.out.println("dict " + dict);

		Scanner in = new Scanner(System.in);
		System.out.println("Please input a wordlength");
		int wordlength = in.nextInt();
		RealHangMan game = new RealHangMan(wordlength , dict);
		while (! game.isGameOver()) {
			game.displayAnswer();
			System.out.print("Please guess a letter.  ");
			String letter = in.nextLine();
			game.update(letter);
		}
		in.close();
	}

}
