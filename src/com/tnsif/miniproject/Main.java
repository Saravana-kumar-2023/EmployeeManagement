package com.tnsif.miniproject;
import java.sql.SQLException;
import java.util.*;
public class Main {
	public static void main(String[] args) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		int option;
		String choice;
		boolean exit = false;
		System.out.println("Employee Management");
		while(!exit)
		{
			System.out.println("Choose the operation did you perform :");
			System.out.println("1.Insert \n2.Delete \n3.Show \n4.Update \n5.Exit");
			option = sc.nextInt();
			switch(option)
			{
			case 1:
				System.out.println("Number of rows affected : "+Operations.insert());
				break;
			case 2:
				System.out.println("Number of rows affected :"+Operations.delete());
				break;
			case 3:
				Operations.show();
				break;
			case 4:
				System.out.println("Number of rows affected :"+Operations.update());
				break;
			case 5:
				exit = true;
				System.out.println("Operations are done...");
				break;
			default:
				System.out.println("Invalid option...Choose above option only");
			}
			System.out.print("Do you want to continue this operation (Y/N)? ");
	        choice = sc.nextLine().trim().toLowerCase();

	        // Check the user's input
	        if (choice.equals("y")) {
	            continue;
	        } else if (choice.equals("n")) {
	            System.out.println("Ending the operation.");
	            break;
	        } else {
	            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
	        }
		}
		sc.close();
	}
}
