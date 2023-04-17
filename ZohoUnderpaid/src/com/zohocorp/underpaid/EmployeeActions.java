package com.zohocorp.underpaid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class EmployeeActions 
{
	private Map<Integer,Employee> employees=new HashMap<>();
	public List<Employee> subsTotal = new ArrayList<Employee>();
	
	public static Scanner scan=new Scanner(System.in);
	
	
	public boolean employeeAdd(int eid,String ename,int mid,int esalary)
	{
		try
		{
			if (employees.containsKey(eid)) 
			{
				throw new EmployeeIdExistsException("Employee with id "+ eid + " already exists");
			}
			else if (!employees.containsKey(mid) && mid!=0)
			{
				throw new ManagerNotFoundException("Manager with id "+ mid + " does not exist");
			}
			else
		    {
				employees.put(eid, new Employee(eid, ename, mid, esalary));
				return true;
		    }
		}
		catch(ManagerNotFoundException m)
		{
			System.out.println(m.getMessage());
			System.out.println("Enter employee's manager id: ");
			int managerId = scan.nextInt();
			employees.put(eid, new Employee(eid, ename, managerId, esalary));
			return true;
		} 
		catch (EmployeeIdExistsException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public Map<Integer,Employee> display()
	{
		return employees;
	}
	
	public void displayEmployeeDetails(int eid) 
	{
			Employee e = employees.get(eid);
			System.out.println("ID: "+ e.getId());
			System.out.println("Name: "+ e.getName());
			System.out.println("Manager ID: "+ e.getManagerId());
			System.out.println("Salary: "+ e.getSalary());
	}
	
	public Employee getRoot(int root)
	{
		return employees.get(root);
	}
	
	public void getOrganizationTree()
	{
		System.out.println("ORGANIZATION TREE");
		employees.forEach((k, v) -> {
            System.out.print(employees.get(k).getName()+": ");//get name of employee with id 'k'
            for(Employee e:v.getSubordinates())					//get name of all subordinates of 'k'
            {
            	System.out.print(e.getName()+"\t");
            }
            System.out.println();
        });
	}
	
	public void buildHierarchyTree(Employee root) 
	{
		 Employee employee = root ;
		 List<Employee> subs = getSubsById(employee.getId());
		 employee.setSubordinates(subs);   
		 if (subs.size() == 0)
			 return;
		 for (Employee em : subs) 
	    	buildHierarchyTree(em);
	 }	 
	 
	private List<Employee> getSubsById(int managerId) 
	{
		 List<Employee> subs = new ArrayList<Employee>();
		 for (Employee em : employees.values()) 
		 {
			 if (em.getManagerId() == managerId) 
			 {
				 subs.add(em);
			 }
		 }
		 return subs;
	}
	
	public int totalSubordinateSalaray(Employee e)
	{
		if (e.getSubordinates().isEmpty())
		{
			return 0;
		}
		int total=0;
		for(Employee emp:e.getSubordinates())
		{
			total+=emp.getSalary();
			total+=totalSubordinateSalaray(emp);	
		}
		return total;
		
	}
	public int totalSubordinateNo(Employee e)
	{
		if (e.getSubordinates().isEmpty())
		{
			return 0;
		}
		int totalCount=e.getSubordinates().size();
		for(Employee emp:e.getSubordinates())
		{
			totalCount+=totalSubordinateNo(emp);	
		}
		return totalCount;
	}
	public boolean isUnderPaid(Employee emp) 
	{
		try
		{
			if(emp.getSalary()<(totalSubordinateSalaray(emp)/totalSubordinateNo(emp))) 
			{
				return true;
			}
		}
		catch(ArithmeticException ae)
		{
			return false; //divide by 0 means leaf node so no children so can't be underpaid
		}
		return false;
	}
	
	public void findUnderpaidWorkers() 
	{
		List<Employee> underpaidWorkers = new ArrayList<Employee>();
		
		employees.forEach((employeeId, emp) ->{
			if(isUnderPaid(emp)) 
			{
				underpaidWorkers.add(emp);
			}
		});
		
		System.out.println("Under Paid Employees are:");
		for(Employee e:underpaidWorkers)
		{
			System.out.println(e.getName());
		}
		System.out.println("No. of Under Paid Employees: "+ underpaidWorkers.size());
	}

}
