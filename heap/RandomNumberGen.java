package heap;

//import java.io.DataInputStream;
import java.io.DataOutputStream;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class RandomNumberGen {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		float NumberOfNumbersToBeGenerated= (float) Math.pow(10, 5);
		float iterator;
		DataOutputStream out = null;
//		DataInputStream in = null;
//		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("output.txt");
			out = new DataOutputStream(fos);
	 		for(iterator=NumberOfNumbersToBeGenerated;iterator>0;iterator--)
				out.writeFloat(iterator);
	 		out.flush();
	 		System.out.println("Done Baby! :) ");
	 		/*fis = new FileInputStream("output.txt");
	 		in = new DataInputStream(fis);
	 		
	 		while(in.available()>0){
	 			iterator=in.readFloat();
	 			System.out.printf("%.0f\n",iterator);
	 		}*/
	 		
	        
	      }catch (Exception e){
	    	  System.out.println(e.getMessage());
	      }
	      finally {
	         if (out != null) {
	            out.close();
	         }
	         if (fos !=null) {
	        	 fos.close();
	         }
/*			if (fis != null) {
	        	 fis.close();
	         }
	         if (in != null) {
	        	 in.close();
	         }
*/	      }
		long endTime = System.currentTimeMillis();
		System.out.println("Took "+(endTime - startTime) + " ms");

	}

}
