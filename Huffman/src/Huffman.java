import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
	
	HuffmanNode root;
	HashMap<Character, String> encodeding = new HashMap<Character, String>();
	
								//TAKE INPUT AS WORD
	
	//a method that takes words *NO SPACES ALLOWED
	public void input(String word) {
		
		ArrayList<Character> lettersList = new ArrayList<Character>();
		HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
		HashMap<Character, Boolean> hasLetter = new HashMap<Character, Boolean>();
		
		int count = 0;
		while(count < word.length()) {
			
			char letter = word.charAt(count);
			
			if(!letters.containsKey(letter)) {
				
				letters.put(letter, 1);
				
			}else {
				
				int occ = letters.get(letter);
				letters.put(letter, occ+1);
			
			}
			
			if(!hasLetter.containsKey(letter)) {
				
				lettersList.add(letter);
				hasLetter.put(letter, true);
				
			}
			
			count++;
			
		}
		
		int[] occur = new int[lettersList.size()];
		char[] let = new char[lettersList.size()];
		
		count = 0;
		while(count < lettersList.size()) {
			
			let[count] = lettersList.get(count);
			occur[count] = (letters.get(lettersList.get(count)));
			count++;
			
		}
		
		
		takeInput(let, occur);
		
	}
	
									//TAKE INPUT
	
	//this method takes an array of letters and an array of containing count of occurrence of those letters
	private void takeInput(char[] letters, int[] occurances) {
		
		int length = letters.length;
		
		PriorityQueue<HuffmanNode> queueOfNodes = new PriorityQueue<HuffmanNode>(length,new MyComparator());
		
		for( int count = 0; count < length; count++) {
			
			HuffmanNode tempNode = new HuffmanNode();
			
			tempNode.letter = letters[count];
			tempNode.occurence = occurances[count];
			
			tempNode.left = null;
			tempNode.right = null;
			
			queueOfNodes.add(tempNode);
			
		}
		
		root = null;
		
		while(queueOfNodes.size() > 1) {
			
			HuffmanNode nodeOne = queueOfNodes.poll();
			HuffmanNode nodeTwo = queueOfNodes.poll();
			
			HuffmanNode currentNode = new HuffmanNode();
			
			currentNode.occurence = nodeOne.occurence + nodeTwo.occurence;
			currentNode.letter = '-';
			
			currentNode.left = nodeOne;
			currentNode.right = nodeTwo;
			
			root = currentNode;
			queueOfNodes.add(currentNode);
			
		}
		
		setCodes(root, "");
		
	}
	
	class MyComparator implements Comparator<HuffmanNode> { 
	    public int compare(HuffmanNode node1, HuffmanNode node2) 
	    { 
	  
	        return node1.occurence - node2.occurence; 
	    } 
	}
	
	private void setCodes(HuffmanNode root, String bitStream) {
		if(root.left == null && root.right == null && Character.isLetter(root.letter)) {
			
			encodeding.put(root.letter, bitStream);
			return;
		
		}
		
		setCodes(root.left,bitStream + "0");
		setCodes(root.right,bitStream + "1");
		
		
	}
	
	
							//PRINT CODES FOR RESPECTIVE LETTERS
	
	
	public void printCodes() {
		
		for(Map.Entry<Character, String> entry: encodeding.entrySet()) {
			
			System.out.println(entry.getKey() + " : " + entry.getValue());
			
		}
		
	}
	
	
				//PRINT DECODED MESSAGE FOR GIVEN BITSTREAM (THIS BITSTREAM MUST USE ABOVE ENCODING BITS AND LETTERS ONLY)
	
	
	public void decode(String bitstream) {
		
		while(!bitstream.isEmpty()) {
			
			bitstream = decode(root,bitstream);
			
		}
		
	}

	
	private String decode(HuffmanNode root,String bitStream) {
		
		if(root.left == null && root.right == null) {
			
			System.out.print(root.letter);
			return bitStream;
			
		}
		
		if(bitStream.charAt(0) == '0') {
			
			bitStream = decode(root.left,bitStream.substring(1));
			
		}else {
			
			bitStream = decode(root.right,bitStream.substring(1));
			
		}
		
		return bitStream;
	}
	
	
//PRINT A BITSTREAM FOR GIVEN INPUT (INPUT CAN BE GIVEN AS WORD OR IN THE FORM OF ARRAYS OF LEETERS AND THEIR OCCURENCES
	
	
	
	
	public void printTheBitStream(String word) {
		
		for(int count = 0; count < word.length(); count++) {
			
			String ans = encodeding.get(word.charAt(count));
			System.out.print(ans);
			
		}
		
	}
	
	
}
