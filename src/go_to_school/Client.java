package go_to_school;

import java.io.IOException;
import java.util.ArrayList;

public class Client {
	public static Boolean isSendReq = false;
	public static ArrayList<String>  weekDaysArray = new ArrayList<>();
	public static ArrayList<String>  timeLineArray = new ArrayList<>();
	public static ArrayList<String>  subjectArray = new ArrayList<>();
	public static ArrayList<String>  requirementsArray = new ArrayList<>();
	public static void main(String[] args) {
   	 	try {
			new Weather();
		} 
   	 	catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        new Login();
        
	}
}
