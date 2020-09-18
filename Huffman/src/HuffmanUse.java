
public class HuffmanUse {

	public static void main(String[] args) {
		
		Huffman myHuffman = new Huffman();
		
        String word = "helloworld";
        myHuffman.input(word);
        
        System.out.print("Encoded message :");
        myHuffman.printTheBitStream(word);
        System.out.println();
        
        String encodedMessage = "010111010100011110011010011";
        System.out.print("Decoded message :");
        myHuffman.decode(encodedMessage);
        System.out.println();
        
        System.out.println("Codes :");
        myHuffman.printCodes();
	}
}