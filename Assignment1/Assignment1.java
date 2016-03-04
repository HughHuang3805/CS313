//Xiaoquan Huang
//CS313 Assignment1
package Assignment1;

import java.util.Scanner;

public class Assignment1 {

	public static void main(String[] args) {
		MyLinkedList<CDRecord> CDList = new MyLinkedList<CDRecord>(); 
		Operation opt = new Operation(); 
		
		while(true){
			System.out.print("****************************************\n Welcome to the Queens Library\nCD Database System!\n****************************************\n\n");
			System.out.println("Choose from the following:");
			System.out.printf("   " + "%-7s%s", "ADD",":" + "Add a new Record to the list." + "\n");
			System.out.printf("   " + "%-7s%s", "DELETE", ":" + "Delete a Record from the list." + "\n");
			System.out.printf("   " + "%-7s%s", "FIND", ":" + "Find an existing Record in the list." + "\n");
			System.out.printf("   " + "%-7s%s", "LIST", ":" + "Print all current Records to the screen." + "\n");
			System.out.printf("   " + "%-7s%s", "QUIT", ":" + "Quit to the CD Database System." + "\n");
			Scanner sc = new Scanner(System.in);
			System.out.print("Type your choice: ");
			String choice = sc.nextLine().toLowerCase();
			
			switch(choice){
			
			case "add":
				CDRecord cd = opt.getRecordInfo();
				if(cd != null){
					if(opt.isValidCd(cd, CDList)){
						CDList.add(cd);
						System.out.println("Addition Complete. \n\n\n");
					}
				}
				break;
				
			case "delete":
				System.out.println("Enter tracking Number to delete: ");
				Scanner scanner = new Scanner(System.in);
				String trackingNumber = scanner.nextLine().toLowerCase();
				try{
					long trackingNumber1 = Long.parseLong(trackingNumber);
					opt.deleteAnEntry(CDList, trackingNumber1);
				} catch(NumberFormatException nfe){
					System.out.println("Wrong Tracking Number.\n\n\n");
				}
				break;
				
			case "find":
				System.out.print("Search By Type (Title or Tracking): ");
				Scanner sc1 = new Scanner(System.in);
				String type = sc1.nextLine().toLowerCase();
				System.out.print("Please Enter Your Value: ");
				Scanner sc2 = new Scanner(System.in);
				String value = sc2.nextLine().toLowerCase();
				if(!type.equals("title")&&!type.equals("tracking")){
					System.out.println("Invalid Type.\n\n\n");
				}else{
					String msg = opt.searchByType(CDList, type, value);
					System.out.println(msg);
				}
				break;
				
			case "list":
				System.out.println(opt.printList(CDList));
				break;
				
			case "quit":
				sc.close();
				System.out.println("Goodbye!");
				System.exit(0);
				break;
				
			default: 
				System.out.println("Invalid input, try again.\n\n\n");
				break;
			}
		}
	}

}
