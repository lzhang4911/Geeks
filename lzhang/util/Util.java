package lzhang.util;

public class Util {

	public static String generateRadomString(int length) throws IllegalArgumentException {
		if(length <= 0) {
			throw new IllegalArgumentException("The length must be greater than 0");
		}
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < length; i++) {
			int c = (int)'a' + (int)(Math.random() * (1 + (int)'z' - (int)'a'));
			
			sb.append( (char)c );
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String temp = generateRadomString(100);
		
		System.out.println("Temp: " + temp);
	}
}
