import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class MMMain {

	public static void main(String[] args) {
		
		//Create objects
		List<String> lines = new ArrayList<String>();
		CheckComments comments = new CheckComments();
		CheckKeywords keywords = new CheckKeywords();
		CheckSymbols symbols = new CheckSymbols();
		Letters l = new Letters();
		Digits d = new Digits();
		
		
		//variables and such
		char c;
		int start = 0;
		
		//get file data
		Scanner file = null;
		try {
			file = new Scanner(new File("textt.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//pass contents of file into array, delimiter is new line
		while(file.hasNextLine()) {
			lines.add(file.nextLine());
		}
		//create string array of contents; each string is one line
		String[] arr = lines.toArray(new String[0]);
		
		//for loop for finding tokens
		for(int i = 0; i<arr.length; i++) {
			char[] charArr = arr[i].toCharArray();//create array of chars from string
			for(int j = 0; j< charArr.length; j++) {
				c = charArr[j];							//get first character in line
				if(c == '/') {							//if comment..
					comments.check(arr, charArr, i, j);	
					System.out.println("This means CheckComments() exited succesfully.");
					
				}else if(c >= 'a' && c <= 'z'){
					l.letters();
				}else if(c >= 0 && c <= 9) {
					d.digits();
				}else {
					symbols.checkSymbols();
				}
				System.out.println("Char's for loop. J == " + j);
			}//end for loop
		}
		
		
	}//end of main

	
	public static void cycle() {
		
	}
	
}//end of class
