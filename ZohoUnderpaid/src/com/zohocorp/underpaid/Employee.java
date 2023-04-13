package com.zohocorp.underpaid;

import java.util.List;

public class Employee 
{
	private int id, managerId; 
	private String name;
	private int salary;
	private List<Employee> subordinates;
	
    public Employee(int id,  String name, int managerId, int salary) 
    {    
	        this.id = id;	        
	        this.name = name;
	        this.managerId = managerId;
	        this.salary=salary;
	        
    }
    public void setSubordinates(List<Employee> subordinates)
    {
    	this.subordinates=subordinates;
    }
    
    public int getId()
    {
    	return id;
    }
    public String getName()
    {
    	return name;
    }
    public int getManagerId()
    {
    	return managerId;
    }
    public int getSalary()
    {
    	return salary;
    }
    public List<Employee> getSubordinates()
    {
    	return this.subordinates;
    }

}
