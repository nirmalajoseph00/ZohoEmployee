package com.zohocorp.underpaid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class EmployeeActions 
{
	private Map<Integer,Employee> employees=new HashMap<>();
	public List<Employee> subsTotal = new ArrayList<Employee>();
	
	
	public boolean employeeAdd(int eid,String ename,int mid,int esalary)
	{
		if (employees.containsKey(eid)) 
			return false;
		else
	    {
			employees.put(eid, new Employee(eid, ename, mid, esalary));
			return true;
	    }
	}
	public Map<Integer,Employee> display()
	{
		return employees;
	}
	public Employee displayEmployeeDetails(int eid) 
	{
			return employees.get(eid);
	}
	public Employee getRoot(int root)
	{
		return employees.get(root);
	}
	public void getTree()
	{
		employees.forEach((k, v) -> {
            System.out.println(k+":"+v.getSubordinates());
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
		 
		 getTree();
	 }	 
	 
	private List<Employee> getSubsById(int managerId) 
	{
		int totalSalary=0;
		 List<Employee> subs = new ArrayList<Employee>();
		 for (Employee em : employees.values()) 
		 {
			 if (em.getManagerId() == managerId) 
			 {
				 subs.add(em);
				 totalSalary+=em.getSalary();
			 }
		 }
		 Employee e=employees.get(managerId);
		 e.setTotalDirectSalary(totalSalary);
		 e.setNoDirectSubordinates(subs.size());
		 return subs;
	}
	
	public void findUnderpaidWorkers()
	{
		List<Employee> underpaidWorkers = new ArrayList<Employee>();
		int totalSubordinateSalary=0, totalSubordinateNumber=0;
		int count=0;
		
		for(Employee emp :employees.values()) 
	    {
			transverse(emp);
			emp.setTotalSubordinates(subsTotal);
			subsTotal.clear();
	    }
		for(Employee emp :employees.values()) 
	    {
			for(Employee e:emp.getTotalSubordinates())
			{
				totalSubordinateSalary+=e.getSalary();
			}
			int l=emp.getTotalSubordinates().size();
			if(l!=0)
			{
				if (emp.getSalary()<(totalSubordinateSalary/l))
				{
					underpaidWorkers.add(emp);
					count++;
				}
	    	}
	    }
		System.out.println(underpaidWorkers);
		System.out.println("Count:"+count);
		
		
	}
	
		public void transverse(Employee e)
		{
			//List<Employee> subsTotal=null;
			if (e.getSubordinatesNo()!=0)
			{
				for(Employee emp:e.getSubordinates())
				{
					subsTotal.add(emp);
					transverse(emp);	
				}
			}
			//e.setTotalSubordinates(subsTotal)
			
			
		}
	
	


}
