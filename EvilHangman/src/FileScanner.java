import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileScanner {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		try{
			BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
			String word=reader.readLine();
			int max = 0;
			while(word != "zyzzyvas"){
				if(word.length()>max){
					max=word.length();
					word = reader.readLine();
				}
				else{
					word = reader.readLine();
				}
			}
			System.out.println(max);
		}
		catch (FileNotFoundException e){
		    System.out.println(e);
		}
		catch (IOException e){
		    System.out.println(e);
		}
		
	}

}
