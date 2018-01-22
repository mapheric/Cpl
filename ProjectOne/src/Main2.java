import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;


public class Main2 {

	public static void main(String[] args) {
		
		//Create objects
		List<String> lines = new ArrayList<String>();
		List<String> letters = new ArrayList<String>();
		List<String> store = new ArrayList<String>();
		CheckComments comments = new CheckComments();
		CheckKeywords keywords = new CheckKeywords();
		CheckSymbols symbols = new CheckSymbols();
		Letters l = new Letters();
		Digits d = new Digits();
		Uno uno = new Uno();
		//Iterator itr = letters.iterator();
		//ListIterator<String> itr = letters.listIterator();
		
		
		//variables and such
		char c;
		int start = 0;
		char last = ' ';
		int count = 0;
		boolean badStar = false;
		boolean badSlash = false;
		boolean entered = false;
		boolean whileCheck = false;
		boolean slashCheck = false;
		String temp = "";
		char aChar = ' ';
		char[] idUno = {'*', '+', '-', ';', '.', '(', ')', '{', '}', '[', ']'};
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("i    j    last    c    Word                              Tokens  in Line                                                  ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//get file data
		Scanner file = null;
		try {
			file = new Scanner(new File("text.txt"));
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
			System.out.println("INPUT: " + arr[i].toString());
			
//			if(count == 0) {
//				last = ' ';
//			}
			char[] charArr = arr[i].toCharArray();//create array of chars from string
			for(int j = 0; j< charArr.length; j++) {
				c = charArr[j];							//get first character in line
				entered = false;						//test, for changing badStar
				slashCheck = false;						//test, for changing badSlash
				
				//System.out.print(i + "    " +  j + "    " + last + "       " + c + "    ");
				
				
		
				if(c == '/' && last == '/' && count == 0) {							//if comment..
					//then it's a line comment, skip to next line
					j = charArr.length;
					last = ' ';
					//System.out.println("In double slash loop. ");
					
				}else if(last == '/' && c == '*' && badSlash == false) {
					//if this is the start of a newline comment, increment count and skip over to next char
					count++;
					badStar = true;
					entered = true;
					//System.out.println(">>>>>>>entered count = " + count);
					//System.out.println("Last is / and c is *; in this loop...");
				}else if(last != '/' && last != '*' && count != 0) {
					//if we're in the middle of a multi line comment, do nothing, cycle to next char
					
				}else if(last != '/' && last != '*' && count == 0 && c == '/') {
					//if the current char is a '/', the previous aren't notable, do nothing.
					//Might be start of comment, but might be division sign
					if(j == (charArr.length - 1)) {					// if the '/' is the last char on the line
						temp += c;
						temp += ' ';
						letters.add(temp);
						temp = "";
						last = ' ';
					}
					
				}
//				else if(last == '/' && j == (charArr.length - 1)) {
//					//if last is a '/' and next is a whitespace, then division sign?
//					//what if the space before the '/' is not a whitespace?
//					//this might not be necessary, probably should be dealt with in symbols section
//					
//				}
				else if(last == '*' && c == '/' && badStar == true) {
					//if this is a false end comment, ignore it 
					//System.out.println("In the badStar == true if statement...");
				}
				
//				else if(last == '*' && c == '/' && badStar == false && count == 0) {
//					//if this is a true end comment that's bad (no start comment)
//					//not sure, log this as a bad token?
//					System.out.println("In the dead if-statement");
//					
//				}
				else if(last == '*' && c == '/' && badStar == false && count != 0) {
					//if this is a true end comment, and there are still more end comments
					count--;
					badSlash = true;
					slashCheck = true;
					//System.out.println("Hmmm. c: " + c + "  +++ Count: " + count);
				}else if(c >= 'a' && c <= 'z' && count == 0){
					//cycle through all the letters that make up a term; store somewhere
					//System.out.print("~~" + charArr[j]);
					//System.out.print("Words: ");
					whileCheck = true;
					while(j < charArr.length && whileCheck == true) {
						//System.out.println("This is the charArray: " + charArr[j]);
						if(charArr[j] >= 'a' && charArr[j] <= 'z') {
							//System.out.println("                                   ----->>> charArr j value: " + charArr[j] + ";   c-value: " + c + " ");
							temp += charArr[j];
							//System.out.println("------> In char-loop: " + charArr[j]);
							j++;
						}else {
							whileCheck = false;
						}
					}
					temp += ' ';
					letters.add(temp);
					//System.out.println("Words " + temp);
					temp = "";
					j--;
					//last = ' ';
					//System.out.println("");
					
				}else if(Character.isDigit(c) && count == 0) {
					//cycle through all the numbers that make up a term; store somewhere
					whileCheck = true;
					while(j < charArr.length && whileCheck == true) {
						if(charArr[j] >= '0' && charArr[j] <= '9') {
							temp += charArr[j];
							j++;
						}else {
							whileCheck = false;
						}
					}
					temp += ' ';
					letters.add(temp);
					temp = "";
					j--;				
				}else if(last == '/' && c != '*' && count == 0 && badSlash == false){					
					//if last is a slash, and next isn't a comment start, then '/' is a division sign
						temp = "";
						temp += last;
						temp +=  ' ';
						letters.add(temp);
						j--;
				}
//				else if(count == 0 && c != ' ' && !(Character.isDigit(c)) && !(c >= 'a' && c <= 'z')){
//					whileCheck = true;
//					temp = "";
//					while(j<charArr.length) {
//						if(count == 0 && c != ' ' && !(Character.isDigit(c)) && !(c >= 'a' && c <= 'z')) {
//							temp += charArr[j];
//						}
//					}//end while loop
//
//
//				}//end else if, long
				
				else if(count == 0) {
					//System.out.println("Final Else. c: " + c + " Count: " + count);
					if(count == 0 && c == ' ' && last != ' ') {		//add whitespace to demark for future parsing
						temp = "";
						temp += ' ';
						letters.add(temp);
					}else if(count == 0 && c == ' ' && last == ' ') {		//add whitespace to demark for future parsing
						//no need for multiple whitespaces, do nothing..
						//although I could strip them out later...
					}else if(uno.check(c) && count == 0) {			//if singular symbol, add to array
						temp = "";
						temp += c;
						temp += ' ';
						letters.add(temp);
						temp = "";
					}else if(uno.dos(c) && j == (charArr.length - 1) && count == 0) {	//only 1 symbol if at end
						temp = "";
						temp += c;
						temp += ' ';
						letters.add(temp);
						temp = "";
					}else if((uno.dos(c) && j < (charArr.length - 1) && count == 0) || (c == '!' && count == 0 && j < (charArr.length - 1))) {
						//could be compound symbol
						
						if(c == '!' && charArr[j + 1] == '=') {
							temp = "!= ";
							j++;
							letters.add(temp);
						}else if(uno.tres(c, charArr[j+1])) {
							temp = "";
							temp += c;
							temp += charArr[j +1];
							temp += ' ';
							letters.add(temp);
							temp = "";
							j++;
						}else {
							temp = "";
							temp += c;
							temp += ' ';
							letters.add(temp);
							temp = "";
						}
					}
					else if(count == 0){
						//System.out.println("IN STATEMENT. c: " + c + "--- COUNT: " + count + " Last: " + last);
						temp = "";
						temp += c;
						temp += ' ';
						letters.add(temp);
						temp = "";
					}
					
				}//final else statement
				
				
				
				
				
				if(entered == false) {
					badStar = false;//set badStar to false if w
				}
				if(slashCheck == false) {
					badSlash = false;//set badSlash to false if single iteration is over
				}
				//System.out.println();
				last = c;
				//System.out.println("                                   ~~~~~~~~~>>> charArr.length:" + charArr.length + " j: " + j + " count: " + count);
			}//end for loop, chars
			
			
			//System.out.print("-------- Length of Array: " + letters.size() + ". ");
			ListIterator<String> itr = letters.listIterator();
			if(count == 0 && !(letters.isEmpty())) {System.out.print("TOKENS: ");
			while(itr.hasNext()) {
				System.out.print(itr.next());
			}
			//System.out.print("LENGTH == " + letters.size());
			System.out.println("");
			store.addAll(letters);//add the contents of 'letters' array, for storage
			letters.clear();
			}
			
			if(count == 0) {last = ' ';}//delimiter to demark end of line
		}//end for loop, string array
		
		
	}//end of main

	
	public static void cycle() {
		
	}
	
}//end of class
