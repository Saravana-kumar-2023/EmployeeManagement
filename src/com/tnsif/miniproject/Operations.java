package com.tnsif.miniproject;
import java.sql.*;
import java.util.*;
public class Operations {
	public static Scanner sc = new Scanner(System.in);
	public static Connection con;
	static
	{
		sc = new Scanner(System.in);
		try {
			 con = DbConnection.getConnection();
			
		}
		catch(Exception e) {
			 System.err.println("SQLException occurred while connecting to the database:");
			 System.err.println(e.getMessage());
		     // Print the stack trace (optional)
		     System.err.println("Stack trace:");
		     e.printStackTrace();
			
		}
	}
	//Inserting operation
	public static int insert() throws SQLException{
		Connection con = DbConnection.getConnection();
		String emp_name , email , designation;
		int emp_id,salary;
		System.out.println("Inserting....");
		System.out.println("Employee Id : ");
		emp_id =sc.nextInt();
		sc.nextLine();
		System.out.println("Employee Name : ");
		emp_name = sc.nextLine();
		System.out.println("Email :");
		email = sc.nextLine();
		System.out.println("Designation : ");
		designation = sc.nextLine();
		System.out.println("Salary : ");
		salary = sc.nextInt();
		//Insert vlaues into database 
		String query = "INSERT INTO employee(emp_id,emp_name,email,designation,salary) VALUES(?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, emp_id);
		pst.setString(2, emp_name);
		pst.setString(3, email);
		pst.setString(4, designation);
		pst.setInt(5, salary);
		int rows_affected = pst.executeUpdate();
		return rows_affected;
		
		
	}
	//Deletion operation
	public static int delete() throws SQLException{
		System.out.println("Deleting Employee....");
	    System.out.println("Enter Employee ID to delete:");
	    int emp_id = sc.nextInt();//enter the employee id which id want to delete 
	    String query = "DELETE FROM employee WHERE emp_id = ?";
	    PreparedStatement pst = con.prepareStatement(query);
	    pst.setInt(1, emp_id);
	    
	    int rows_affected = pst.executeUpdate();
	    return rows_affected;
	}
	//show operation
	public static void show() throws SQLException {
        System.out.println("Fetching Employee Details....");
        System.out.println("1. Show All Employees");
        System.out.println("2. Show Specific Employee by ID");
        int choice = sc.nextInt();
        //Show all employee details
        if (choice == 1) {
            String query = "SELECT * FROM employee";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            //checks whether the database is empty 
            if (!rs.isBeforeFirst()) {
                System.out.println("No employees found in the database.");
            } else {
                while (rs.next()) {
                    int emp_id = rs.getInt("emp_id");
                    String emp_name = rs.getString("emp_name");
                    String email = rs.getString("email");
                    String designation = rs.getString("designation");
                    int salary = rs.getInt("salary");
                    
                    System.out.println("Employee ID: " + emp_id);
                    System.out.println("Employee Name: " + emp_name);
                    System.out.println("Email: " + email);
                    System.out.println("Designation: " + designation);
                    System.out.println("Salary: " + salary);
                    System.out.println("--------------------");
                }
                
            }
            
        } else if (choice == 2) {
            System.out.println("Enter Employee ID:");
            int emp_id = sc.nextInt();
            
            String query = "SELECT * FROM employee WHERE emp_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, emp_id);
            ResultSet rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) { 
                System.out.println("Employee with ID " + emp_id + " not found.");
            } else {
                if (rs.next()) {
                    String emp_name = rs.getString("emp_name");
                    String email = rs.getString("email");
                    String designation = rs.getString("designation");
                    int salary = rs.getInt("salary");
                    
                    System.out.println("Employee ID: " + emp_id);
                    System.out.println("Employee Name: " + emp_name);
                    System.out.println("Email: " + email);
                    System.out.println("Designation: " + designation);
                    System.out.println("Salary: " + salary);
                }
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }
	public static int update() throws SQLException {
		Connection con = DbConnection.getConnection();
	    System.out.println("Updating Employee Details.....");
	    String checkQuery = "SELECT COUNT(*) AS count FROM employee";
	    PreparedStatement checkPst = con.prepareStatement(checkQuery);
	    ResultSet rsCheck = checkPst.executeQuery();
	    
	    if (rsCheck.next() && rsCheck.getInt("count") == 0) {
	        System.out.println("The database is empty. No employees to update.");
	        return 0;
	    }
	    else {
	    System.out.println("Enter Employee ID to update:");
	    int emp_id = sc.nextInt();
	    
	    System.out.println("Enter Updated Employee Name:");
	    String emp_name = sc.nextLine(); 
	    emp_name = sc.nextLine(); 
	    
	    System.out.println("Enter Updated Email: ");
	    String email = sc.nextLine();
	    
	    System.out.println("Enter Updated Designation:");
	    String designation = sc.nextLine();
	    
	    System.out.println("Enter Updated Salary:");
	    int salary = sc.nextInt();
	    
	    String query = "UPDATE employee SET emp_name = ?, email = ?, designation = ?, salary = ? WHERE emp_id = ?";
	    PreparedStatement pst = con.prepareStatement(query);
	    pst.setInt(1, emp_id);
	    pst.setString(2, emp_name);
	    pst.setString(3, email);
	    pst.setString(4, designation);
	    pst.setInt(5, salary);
	    	    
	    int rows_affected = pst.executeUpdate();
	    return rows_affected;
	    }
	    }
}

	