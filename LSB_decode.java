import java.awt.image.BufferedImage;
//import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;
public class LSB_decode {

	//STEGIMAGEFILE = "C:\\Users\\Predator\\Desktop\\J-Comp\\steg.png";

	public static String b_msg="";
	public static int len = 0;

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);

		System.out.println("Please provide full path to image to decode:");
		final String filename = input.nextLine();
		File inFile = new File(filename);
		BufferedImage yImage = null;
		yImage = ImageIO.read(inFile);

		DecodeTheMessage(yImage);
		String msg="";
		//System.out.println("len is "+len*8);

		for(int i=0;i<len*8;i=i+8) {
		String sub=b_msg.substring(i,i+8);
		int m=Integer.parseInt(sub,2);
		char ch=(char) m;
		//System.out.println("m "+m+" c "+ch);
		msg+=ch;
		}

		System.out.println("-------------Decoded message is ------------\n\n"+msg);
}



public static void DecodeTheMessage (BufferedImage yImage) throws Exception{

int j=0;
int currentBitEntry=0;
String bx_msg="";
for (int x = 0; x < yImage.getWidth(); x++){
for ( int y = 0; y < yImage.getHeight(); y++){
if(x==0&&y<8){
	int currentPixel = yImage.getRGB(x, y);
	int red = currentPixel>>16;
	red = red & 255;
	int green = currentPixel>>8;
	green = green & 255;
	int blue = currentPixel;
	blue = blue & 255;
	String x_s=Integer.toBinaryString(blue);
	bx_msg+=x_s.charAt(x_s.length()-1);
	len=Integer.parseInt(bx_msg,2);
}
else if(currentBitEntry<len*8){
	int currentPixel = yImage.getRGB(x, y);
	int red = currentPixel>>16;
	red = red & 255;
	int green = currentPixel>>8;
	green = green & 255;
	int blue = currentPixel;
	blue = blue & 255;
	String x_s=Integer.toBinaryString(blue);
	b_msg+=x_s.charAt(x_s.length()-1);
	currentBitEntry++;	
	//System.out.println("curre "+currentBitEntry);
}}}
//System.out.println("bin value of msg hided in img is "+b_msg);
}
}

