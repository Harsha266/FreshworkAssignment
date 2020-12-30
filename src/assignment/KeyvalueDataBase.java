package assignment;
import java.io.*;
import java.util.*;

import org.json.simple.JSONObject;

public class KeyvalueDataBase {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		FileOperation fo = new FileOperation();
		Scanner s = new Scanner(System.in);
		
	Thread t1 = new Thread();
	Thread t2 = new Thread();
	Thread t3 = new Thread();
	
		
		while(true) {
			System.out.println("1. Insert to a database");
			System.out.println("2. Read from a database ");
			System.out.println("3. delete from a database");
			
			System.out.println("Enter your choice");
			int choice = s.nextInt();
			if(choice == 1) {
				  t1 = new Thread(new Runnable() {
					public void run() {
						fo.insertion();
					}
				});
				t1.start();
			
			}else if(choice == 2) {
				t2 = new Thread(new Runnable() {
					public void run() {
						fo.read();
					}
				});
				t2.start();
			}else if(choice == 3) {
				t3 = new Thread(new Runnable() {
					public void run() {
						fo.delete();
					}
					
				});
				t3.start();
			}else {
				System.out.println("Enter a perfect choice!!!!!!!");
			}
		
			
			t1.join();
			t2.join();
			t3.join();
			
		}

	}

}
class FileOperation{
	String displayString="";
	File f = new File("KeyValuedataBase.txt");
	Scanner s = new Scanner(System.in);
	JSONObject jo = new JSONObject();
	
	public synchronized void insertion() {
		double len = f.length();
		len = len/Math.pow(10, 9);
		if(len > 1) {
			return;
		}
		System.out.println("Enter your student id:");
		String id = 00000+s.next();
	
		if(!duplictae(id)) {
		System.out.println("enter student name:");
		String name = s.next();
		System.out.println("enter Lastname:");
		String ln = s.next();
		System.out.println("enter location:");
		String loc = s.next();
		
		jo.put("name", name);
		jo.put("lastname", ln);
		jo.put("location", loc);
		
		try {
			//System.out.print(b);
			PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
			pw.append(id+"->"+jo+"\n");
			pw.close();
			
		}catch(Exception e) {
			System.out.print(e);
		}
		}
		else {
			System.out.println("duplicate Id !!!!!!!!!!!!!!!!!!");
			return;	
		}
		
	}
	private synchronized boolean duplictae(String id) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader("KeyValuedataBase.txt"));
			String s = "";
			while((s=br.readLine()) != null) {
				String data[] = new String[2];
				data = s.split("->");
				if(data[0].equals(id)) {
					displayString=data[1];
					br.close();
					return true;
				}
				
			}
			br.close();
			
		}catch(Exception e) {
			System.out.print(e);
			
		}
		displayString = "No id Matched!!";
		return false;
	}
	public synchronized void read() {
		// TODO Auto-generated method stub
	System.out.println("Enter the Student id you need to read");
	String id = 00000+s.next();
	
	duplictae(id);
	System.out.println(displayString);
	displayString = "";
	
		
	}
	public  void delete() {
		// TODO Auto-generated method stub
	File tempFile = new File("tempFile.txt");

	System.out.println("Enter the id that you want to Delete");
	String id = 00000+s.next();
	
	try {
		BufferedReader br = new BufferedReader(new FileReader(f));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile, true));
		String curr_Line;
		while((curr_Line = br.readLine())!= null) {
			String data[] = new String[2];
			data = curr_Line.split("->");
			
			if(!(data[0].equals(id) )) {
				bw.write(curr_Line+"\n");
				bw.flush();
			}
		}
		bw.close();
		br.close();
		File dump = new File("KeyValuedataBase.txt");
		f.delete();
	    tempFile.renameTo(dump);
	
		
		
	}catch(Exception e) {
		System.out.print(e);
	}
		
	}
}
