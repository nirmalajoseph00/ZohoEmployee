package com.zohocorp.underpaid;

import java.util.List;

public class Employee 
{
	private int id, managerId; 
	private String name;
	private int salary;
	private List<Employee> subordinates;
	private int totalDirectSalary;
	private int noDirectSubordinates;
	private List<Employee> totalSubordinates;
	
	
    
    //Constructor, Time O(1), Space O(1)
    public Employee(int id,  String name, int managerId, int salary) 
    {    
	        this.id = id;	        
	        this.name = name;
	        this.managerId = managerId;
	        this.salary=salary;
	        
    }
    public void setTotalDirectSalary(int totalDirectSalary)
    {
    	this.totalDirectSalary=totalDirectSalary;
    }
    public void setNoDirectSubordinates(int noDirectSubordinates)
    {
    	this.noDirectSubordinates=noDirectSubordinates;
    }
    public void setTotalSubordinates(List<Employee> totalSubordinates)
    {
    	this.totalSubordinates=totalSubordinates;
    }
    public void setSubordinates(List<Employee> subordinates)
    {
    	this.subordinates=subordinates;
    }
    
    public int getId()
    {
    	return id;
    }
    public int getManagerId()
    {
    	return managerId;
    }
    public int getSalary()
    {
    	return salary;
    }
    public int getTotalSalary()
    {
    	return totalDirectSalary;
    }
    public int getSubordinatesNo()
    {
    	return noDirectSubordinates;
    }
    public List<Employee> getSubordinates()
    {
    	return this.subordinates;
    }
    public List<Employee> getTotalSubordinates()
    {
    	return this.totalSubordinates;
    }

}
