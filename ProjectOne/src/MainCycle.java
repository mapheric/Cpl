import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;


public class MainCycle {

	public static void main(String[] args) {
		
		//Create objects
		List<String> lines = new ArrayList<String>();
		List<String> letters = new ArrayList<String>();
		List<String> store = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		CheckComments comments = new CheckComments();
		CheckKeywords keywords = new CheckKeywords();
		CheckSymbols symbols = new CheckSymbols();
		Letters l = new Letters();
		Digits d = new Digits();
		Uno uno = new Uno();
		What what = new What();
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
		boolean E = false;
		boolean period = false;
		String str = "";
		String str2 = "";
		String temp = "";
		char aChar = ' ';
		char[] idUno = {'*', '+', '-', ';', '.', '(', ')', '{', '}', '[', ']'};
		
		//System.out.println("----------------------------------------------------------------------------------------------------------------------");
		//System.out.println("i    j    last    c    Word                              Tokens  in Line                                                  ");
		//System.out.println("----------------------------------------------------------------------------------------------------------------------");
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		Scanner scan = new Scanner(System.in);
	    String choice= null;
	    //int j = 0;
	    //int a = 0;
	    String a = "-input";
	    String b = "";
	    String r = "";
	    for(int zz = 1; zz<28; zz++){
	    	if(zz ==1) {
		    	b = Integer.toString(zz);
		    	r = a + b + ".txt";
	    	}else {
	    		System.out.println("Test file " + r + " just ran. Press any key to run next test file.");
	    		choice = scan.next();
	    		b = Integer.toString(zz);
		    	r = a + b + ".txt";
	    	}
	    	//y	    	zz++;
	    	System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
	    //}
	    //choose.close();	
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		//get file data
		Scanner file = null;
		try {
			//file = new Scanner(new File(args[0]));
			file = new Scanner(new File(r));
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
		
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		//for loop for finding tokens
				for(int i = 0; i<arr.length; i++) {
					System.out.println("INPUT: " + arr[i].toString());

					char[] charArr = arr[i].toCharArray();//create array of chars from string
					for(int j = 0; j< charArr.length; j++) {
						c = charArr[j];							//get first character in line
						entered = false;						//test, for changing badStar
						slashCheck = false;						//test, for changing badSlash
						
						//System.out.print(i + "    " +  j + "    " + last + "       " + c + "    ");
						//System.out.println("-------------------------------------This is c: " + c + " This is char[j]" + charArr[j]);
						if(charArr[j] == '/') {
							//System.out.println("*****************************This is char[j] " + charArr[j] + " This is c: " + c);
						}
				
						if(c == '/' && last == '/' && count == 0) {							//if comment..
							//then it's a line comment, skip to next line
							j = charArr.length;
							last = ' ';
							//System.out.println("In double slash loop. " + c);
							
						}else if(last == '/' && c == '*' && badSlash == false) {
							//if this is the start of a newline comment, increment count and skip over to next char
							count++;
							badStar = true;
							entered = true;
							
						}else if(last != '/' && last != '*' && count != 0) {
							//if we're in the middle of a multi line comment, do nothing, cycle to next char
							
						}
						
						else if(last == '*' && c == '/' && badStar == true) {
							//if this is a false end comment, ignore it 
							//System.out.println("In the badStar == true if statement...");
							//System.out.println("---------------------------------This is c: " + charArr[j]);
							//System.out.println("---------------------------------This is c: " + charArr[j]);
						}else if(last == '*' && c == '/' && badStar == false && count != 0) {
							//if this is a true end comment, and there are still more end comments
							count--;
							badSlash = true;
							slashCheck = true;
							//System.out.println("Hmmm. c: " + c + "  +++ Count: " + count);
						}else if(last != '/' && last != '*' && count == 0 && c == '/') {
							//if the current char is a '/', the previous aren't notable, do nothing.
							//Might be start of comment, but might be division sign
							if(j == (charArr.length - 1)) {					// if the '/' is the last char on the line
								temp += c;
								temp += ' ';
								letters.add(temp);
								temp = "";
								last = ' ';
								name.add("Symbol    ");
								//System.out.println("------------------------------hmmmmm");
							}else if((j < charArr.length - 1)){
								if((charArr[j +1 ] != '*') && (charArr[j +1 ] != '/')){
									temp += c;
									temp += ' ';
									letters.add(temp);
									temp = "";
									//last = '/';
									name.add("Symbol    ");
								}
							}
							
						}else if(c >= 'a' && c <= 'z' && count == 0){
							//cycle through all the letters that make up a term; store somewhere
							//System.out.print("~~" + charArr[j]);
							//System.out.print("Words: ");
							temp = "";
							whileCheck = true;
							while(j < charArr.length && whileCheck == true) {
								if(charArr[j] >= 'a' && charArr[j] <= 'z') {
									temp += charArr[j];
									j++;
								}else {
									whileCheck = false;
								}
							}
							temp += ' ';
							letters.add(temp);
							if(what.check(temp)){
								name.add("Keyword   ");
							}else {
								name.add("ID        ");
							}
							temp = "";
							j--;	
						}else if(Character.isDigit(c) && count == 0) {
							//cycle through all the numbers that make up a term; store somewhere
							temp = "";
							E = false;
							period = false;
							whileCheck = true;
							while(j < charArr.length && whileCheck == true) {
								if(charArr[j] >= '0' && charArr[j] <= '9') {
									temp += charArr[j];
									j++;
								}else if(charArr[j] == '.' && ((j+1) < charArr.length) && period == false && E == false){
									//if the next char is a period, and could be followed by a digit
									//...check if the period is followed by a digit...if so, float
									if(charArr[j+1] >= '0' && charArr[j+1] <= '9') {
										temp += charArr[j];
										j++;
									}else {
										whileCheck = false;
									}
									period = true;
								}else if(E == false && charArr[j] == 'E' && ((j+1) < charArr.length)) {
									//System.out.println("---------------------------charArr[] " + c);
									if((charArr[j + 1] == '+' || charArr[j+1] == '-') && ((j+2) < charArr.length)) {
										//check if next is digit; add if true;
										if(charArr[j+2] >= '0' && charArr[j+2] <= '9') {
											temp += charArr[j];
											temp += charArr[j+1];
											temp += charArr[j+2];
											j+=3;
											//System.out.println("---------------------------Temp: " + temp + " C: " + c + " J+1: " + charArr[j+1] /* + " J+2: " + charArr[j+2]*/);
										}else {
											whileCheck = false;
										}
									}else if(charArr[j+1] >= '0' && charArr[j+1] <= '9') {
										//add E if next is digit
										temp += charArr[j];
										j++;
									}
									E = true;
								}
								else {
									whileCheck = false;
								}
							}//end numcheck while loop
							//temp += ' ';
							boolean czec = false;
							letters.add(temp);
							int m = 0;
							while(m<temp.length() && czec == false) {
								//System.out.println("--------------------------Inside..Temp: " + temp);
								if(temp.charAt(m) == '.' || temp.charAt(m) == 'E') {
									name.add("FLOAT     ");
									czec = true;
								}else if((m == temp.length() -1) && czec == false) {
									name.add("NUM       ");
								}
								m++;
							}
							//name.add("NUM       ");
							temp = "";
							j--;	//because while loop doesn't exit until j is incremented to a false num			
						}
						//+++++++++++++++++++++++++++++++++++++++++++Took out below late....be aware, possibly needed
//						else if(last == '/' && c != '*' && count == 0 && badSlash == false){					
//							//if last is a slash, and next isn't a comment start, then '/' is a division sign
//								temp = "";
//								temp += last;
//								//temp +=  ' ';
//								letters.add(temp);
//								name.add("Symbol    ");
//								j--;
//								
//						}
						else if(count == 0) {
									//System.out.println("-------------------------------------This is c: " + c + " This is char[j]" + charArr[j]);
									if(count == 0 && c == ' ' && last != ' ') {		//add whitespace to demark for future parsing
										temp = "";
										temp += ' ';
										letters.add(temp);
										name.add("Whitespace");
									}else if(count == 0 && c == ' ' && last == ' ') {		//add whitespace to demark for future parsing
										//no need for multiple whitespaces, do nothing..
										//although I could strip them out later...
									}else if(uno.check(c) && count == 0) {			//if singular symbol, add to array
										temp = "";
										temp += c;
										//temp += ' ';
										letters.add(temp);
										name.add("Symbol    ");
										temp = "";
										//System.out.println("---------------------------------This is char[j]: " + charArr[j] + " This is c: " + c);
										//System.out.println("------------------------------This is c: " + c);
									}else if(uno.dos(c) && j == (charArr.length - 1) && count == 0) {	//only 1 symbol if at end
										temp = "";
										temp += c;
										//temp += ' ';
										letters.add(temp);
										name.add("Symbol    ");
										temp = "";
										//System.out.println("---------------------------------This is char[j]: " + charArr[j] + " This is c: " + c);
									}else if((uno.dos(c) && j < (charArr.length - 1) && count == 0) || (c == '!' && count == 0 && j < (charArr.length - 1))) {
													//could be compound symbol
													//System.out.println("---------------------------------J: " + j + " This is char[j]: " + charArr[j] + " This is c: " + c);
													if(c == '!' && charArr[j + 1] == '=') {
														temp = "!=";
														j+=1;
														letters.add(temp);
														name.add("Symbol    ");
														temp = "";
													}else if(c == '!' && count == 0 && charArr[j+1] != '=') {
														temp = "!";
														//j++;
														letters.add(temp);
														name.add("Error     ");
														temp = "";
													}else if(uno.tres(c, charArr[j+1])) {
														temp = "";
														temp += c;
														temp += charArr[j +1];
														//temp += ' ';
														letters.add(temp);
														name.add("Symbol    ");
														temp = "";
														j++;
														//System.out.println("############################ UNO else if loop..");
													}else if(!(uno.tres(c, charArr[j+1])) && (uno.dos(c))) {
														temp = "";
														temp += charArr[j];
														//temp += ' ';
														letters.add(temp);
														name.add("Symbol    ");
														temp = "";
														//j++;
														//ystem.out.println("############################in final else if loop..");
													}	
									}else if(c == '!' && j == charArr.length) {
										temp = "!";
										//j++;
										letters.add(temp);
										name.add("Error     ");
										temp = "";
										//System.out.println("############################in a loop..");
									}else if(count == 0){
										//System.out.println("IN STATEMENT. c: " + c + "--- COUNT: " + count + " Last: " + last);
										temp = "";
										temp += c;
										//temp += ' ';
										letters.add(temp);
										name.add("Error     ");
										temp = "";
									}
							
						}//final else statement
						//System.out.println("---------------------------------This is char[j]: " + charArr[j] + " This is c: " + c);
						
						if(entered == false) {
							badStar = false;//set badStar to false if w
						}
						if(slashCheck == false) {
							badSlash = false;//set badSlash to false if single iteration is over
						}
						//System.out.println();
						last = c;
						//System.out.println("                                   ~~~~~~~~~>>> charArr.length:" + charArr.length + " j: " + j + " count: " + count);
						temp = "";
					}//end for loop, chars
						
					//System.out.print("-------- Length of Array: " + letters.size() + ". ");
					ListIterator<String> itr = letters.listIterator();
					ListIterator<String> nameItr = name.listIterator();
					if(count == 0 && !(letters.isEmpty()) && itr.hasNext()) { //System.out.println("TOKENS:==" + itr.nextIndex());
					while(itr.hasNext() && nameItr.hasNext()) {
						str = (itr.next()).replaceAll("\\s","");
						str2 = nameItr.next();
						//System.out.println("```````````````````String is:" + str + "``````");
						if(str != "" && str2 != "Whitespace" && !(str.isEmpty())) {
							System.out.println("-----" + str2 + ": " + str);
							//System.out.println(nameItr + " " + itr.next());
							//System.out.println(str);
						}
						
						//System.out.print(itr.next());				
					}
					//System.out.print("LENGTH == " + letters.size());
					//System.out.println("");
					store.addAll(letters);//add the contents of 'letters' array, for storage
					letters.clear();
					name.clear();
					if(str2 != "Whitespace") {System.out.println();}
					}
					
					if(count == 0) {last = ' ';}//delimiter to demark end of line
				}//end for loop, string array
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    }//end of test scan loop
		
		
		
		
		
	}//end of main

	
	public static void cycle() {
		
	}
	
}//end of class
