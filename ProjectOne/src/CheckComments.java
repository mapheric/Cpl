
public class CheckComments {
	String[] arr;
	char[] charArray;
	int i;
	int j;
	int k;
	int check = 0;//check if entered loop
	int count = 0;
	int highest = 0;//for debugging
	
	private void initialize(String[] a, char[] cA, int ii, int jj) {
		arr = a;
		charArray = cA;
		i = ii;
		j = jj;
	}
	
	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}
	
	public int check(String[] arr, char[] charArray, int i, int j) {
		initialize(arr, charArray, i, j);
		char c = charArray[j];
		if(c != '/') {System.out.println("Error: The CheckComment invocation doesn't match.");
			return i;
		}
		System.out.println(" --------   Value of J is " + j + ". Length of charArray is " + charArray.length);
		if((j+1) > charArray.length) {							//if we're at the last char in array and..
			if(i < arr.length) {								//...if there are still lines left in the original file
				i++;
				charArray = arr[i].toCharArray();
				j = 0;
			}else {
				return -2; //end of comments
			}
			if(charArray[j] != '*' && charArray[j] != '/' ) {
				return 0;										//if the character after '/' isn't '*' or a '/' then exit
			}
			if(charArray[j] == '/') {
				return 1;										//if the next character is a '/', then whole line is comment, skip it
			}
			check = 2;
		}else if(charArray[j+1] != '*' && charArray[j+1] != '/') {
			//if the second character isn't '*' or a '/' then exit
			return 0;
		}else if(charArray[j+1] == '/') {
			//if the whole line is a comment, then skip it
			return 1;
		}else if(check == 2) {
			j+=2;//because we already determined that charArr[j] and j+1 is '/' and '*'
		}
		count++;
		highest = 0;
		do {
		for(k = j; k<charArray.length; k++) {
			c = charArray[k];
			if(k+1 <= charArray.length) {						//check to make sure no index out of bounds
				if(c == '/') {									//if next is another comment start, count++
						if(charArray[k+1] == '*') {
							count++;							//increment count, of number of nested comments
							if(count>highest) {highest = count;}//for debugging
						}
				}else if(c == '*') {
					while(c == '*') {//while there's a star, check to see if comment end
						if(charArray[k+1] == '/') {					//if next is a comment end, then count--
								count--;
								if(count == 0) {return 2;}
								//if we're at 0 comments, then we should completely exit all loops here.
								//....probably convert to while loop
						}
						k++;
						c = charArray[k];
						if((k+1) > charArray.length && c =='*') {	//if we're at the last char in array that's a '*'
							if(count>0 && i < arr.length) {			//if there's still an opening comment start and still lines left in the orginal file
								i++;
								charArray = arr[i].toCharArray();
								k = -1;
							}else {
								System.out.println("File ended, but there's an open comment. Open comments: " + count);
							}
						}
						
					}//end while loop
				}//end if c == '*'
			}else if(count>0 && i < arr.length) {			//if there's still an opening comment start and still lines left in the orginal file
				i++;
				charArray = arr[i].toCharArray();
				k = 0;
			}
			System.out.println("K-Loop. Highest is == " + highest + ". " + "K = " + k);
			
		}//End K-Loop
		if(count == 0) {
			j = k;	//set 
			return 4;
		}
		else {
			charArray = arr[i+1].toCharArray();
			i++;
			j = 0;
		}//end else
		}while(count != 0);
		
		return i;
	}//end of check method

}
