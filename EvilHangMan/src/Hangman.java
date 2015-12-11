
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Hangman {
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		Hangman Game = new Hangman();
		System.out.println("Wanna play a game? (y or n)");
		String choice = scan.nextLine();
		while(choice != "y" || choice != "n"){
			System.out.println("I don't understand... (Say y or n.)");
			choice = scan.nextLine();
		}
		if(choice=="y"){
			Game.startGame();
		}
		else {
			System.exit(0);
		}
	}
	public Hangman() {
		
	}
	public void startGame() throws IOException{
		System.out.println("So how long do you want the word to be? \n"
				+ "(Please don't be dumb like Nikhil and enter in a word. \n"
				+ "Just enter in a whole number like a normal person.)");
		int wordLength = scan.nextInt(); //Find longest word later
		findWords(wordLength);
	}
	public void findWords(int length) throws IOException{
		FileReader filereader = new FileReader("dictionary.txt");
		BufferedReader reader = new BufferedReader(filereader);
		String word=reader.readLine();
		ArrayList<String> wordList = new ArrayList();
		while(word != "zyzzyvas"){
			if(word.length()==length){
				wordList.add(word);
			}
			else{
				word = reader.readLine();
			}
		}
	}
}
