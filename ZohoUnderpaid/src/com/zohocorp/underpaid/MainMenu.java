package com.zohocorp.underpaid;

import java.util.Scanner;


public class MainMenu 
{
	
	public static Scanner scan=new Scanner(System.in);
	private static EmployeeActions employeeAction=new EmployeeActions();
	
	public static void main(String[] args) 
	{
		int choice;
		char c='Y';
		
		int rootId=-1;
		Employee rootEmployee=null;
		
		do
		{
			System.out.println("\n Main Menu \n 1.Add Employee \n 2.Build Tree \n 3.Find Underpaid Workers \n 4.Quit");
			System.out.println("Enter your choice: ");
			choice = scan.nextInt();
			
			switch(choice)
			{
			case 1:
				rootId=addEmployee();
				if(rootId!=-1)
					rootEmployee=employeeAction.getRoot(rootId);
					
				break;
			case 2:
				employeeAction.buildHierarchyTree(rootEmployee);
				break;
			case 3:
				employeeAction.findUnderpaidWorkers();
				break;
				
			}
		}while(c=='Y');

	}

	private static int addEmployee() 
	{
		int id, managerId;
		String name;
		int salary;
		System.out.println("Enter employee id: ");
		id=scan.nextInt();
		System.out.println("Enter employee name: ");
		name=scan.next();
		System.out.println("Enter employee's manager id: ");
		managerId=scan.nextInt();
		System.out.println("Enter employee salary: ");
		salary=scan.nextInt();
		if (employeeAction.employeeAdd(id, name, managerId, salary))
		{
			System.out.println("Employee Added");
			System.out.println(employeeAction.displayEmployeeDetails(id));
		}
		else
			System.out.println("Admission No. already present");
		if(managerId==0)
			return id;
		else
			return -1;
		
	}
	
}
