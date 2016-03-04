package Assignment1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Operation {

	public CDRecord getRecordInfo(){
		try{
			System.out.print("Enter Album Title: ");
			Scanner sc1 = new Scanner(System.in);
			String title = sc1.nextLine().toLowerCase();
			System.out.print("Enter Artist Name: ");
			Scanner sc2 = new Scanner(System.in);
			String artist = sc2.nextLine().toLowerCase();
			System.out.print("Enter Number Of Tracks: ");
			Scanner sc3 = new Scanner(System.in);
			int numberOfTracks = Integer.parseInt(sc3.nextLine().toLowerCase());
			System.out.print("Enter Library Tracking Number: ");
			Scanner sc4 = new Scanner(System.in);
			long trackingNumber = Long.parseLong(sc4.nextLine().toLowerCase());
			System.out.print("Enter Number of Copies: ");
			Scanner sc5 = new Scanner(System.in);
			int numberOfCopies = Integer.parseInt(sc5.nextLine().toLowerCase());

			CDRecord newCDRecord = new CDRecord(title, artist, numberOfTracks, trackingNumber, numberOfCopies);

			if(trackingNumber < 0 || numberOfCopies <0 || numberOfTracks < 0){
				throw new Exception("Tracking number or Number of copies cannot be negative.\n\n\n");
			}
			return newCDRecord;
		} catch(NumberFormatException nfe){
			System.out.println("Input error, try again.\n\n\n");
		} catch(Exception e){
			System.out.println("Numbers cannot be negative.\n\n\n");
		}
		return null;
	}

	public boolean isValidCd(CDRecord cd, MyLinkedList<CDRecord> cdList){
		//check if it is a valid list
		Iterator<CDRecord> cdItr = cdList.Iterator(); 

		if(cdList.size()==0)
			return true;
		else{
			while (cdItr.hasNext()) {
				CDRecord cdi = cdItr.next(); 
				if(cd.getTrackingNum() == cdi.getTrackingNum()){
					//check if it is addable
					if(cd.getCdAlbumTitle().equals(cdi.getCdAlbumTitle())){
						cdi.setNumOfCopies(cd.getNumOfCopies() + cdi.getNumOfCopies()); //cdi and cd always == 0
						System.out.println("Record added to the existing library.\n\n\n");
						return false;
					}
					//if it is not addable, give error message
					if(!cd.getCdAlbumTitle().equals(cdi.getCdAlbumTitle())){
						System.out.println("Error: Addition not performed. The album title of the CD you are adding does not match the album title of the existing CD in the CD Library\n\n\n");
						return false;
					}
				}
			}
			return true; 
		}
	}

	public String searchByType(MyLinkedList<CDRecord> cdList, String type, String value){
		//search by type
		Iterator<CDRecord> cdItr = cdList.Iterator(); 

		if(cdList.size()==0){
			return ("You don't have any CD Records\n\n\n");
		}else{
			while (cdItr.hasNext()){
				CDRecord cd = cdItr.next(); 
				if(type.equals("title")&&cd.getCdAlbumTitle().equals(value)){
					return cd.toString();
				}else if(type.equals("tracking")&&cd.getNumOfTracks()==Integer.valueOf(value)){
					return cd.toString();
				}
			}
			if(type.equals("title"))
				return "Error: CD not found in the library. A CD with the album title you provided doesn't exist in the CD Library\n\n\n";
			else{
				return "Error: CD not found in the library. A CD with the library tracking number you provided doesn't exist in the CD Library\n\n\n";
			}
		}
	}

	public String printList (MyLinkedList<CDRecord> cdList){
		//prints the entire list if there are entries
		if(cdList.size()==0){
			return "No Record\n\n\n"; 
		}else{
			Iterator<CDRecord> cdItr = cdList.Iterator(); 
			String msg = ""; 
			while(cdItr.hasNext()){
				CDRecord cd = cdItr.next(); 
				msg += cd.toString()+" \n";
			}
			return msg;
		}
	}

	public void deleteAnEntry(MyLinkedList<CDRecord> cdList, long trackingNumber){
		//deletes the entry as specified
		if(cdList.size() == 0){
			System.out.println("No entry.\n\n\n");
			return;
		} else{
			Iterator<CDRecord> cdItr = cdList.Iterator();
			while(cdItr.hasNext()){
				CDRecord cd = cdItr.next();
				if(cd.getTrackingNum() == trackingNumber){
					if(cd.getNumOfCopies() == 1){
						cdItr.remove();
						System.out.println("Deletion completed. \n\n\n");
						break;
					} 
					if(cd.getNumOfCopies() > 1){
						System.out.println("How many copies do you want to delete? (Type 'all' if you want to delte all)");
						Scanner sc = new Scanner(System.in);
						String string = sc.nextLine().toLowerCase();
						if(string.equals("all")){
							cdItr.remove();
							System.out.println("Deletion completed. \n\n\n");
							break;
						}
						else{
							System.out.println("Not a valid input.\n\n\n");
						}
						try{
							int i = Integer.parseInt(string);
							cd.setNumOfCopies((cd.getNumOfCopies()-i));
							System.out.println("Deletion completed. \n\n\n");
							break;
						} catch(NumberFormatException nfe){
							System.out.println("Not a number.\n\n\n");
						}
					}
				}
			}
		}
	}
}
