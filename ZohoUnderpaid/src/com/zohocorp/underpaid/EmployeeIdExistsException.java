package com.zohocorp.underpaid;

public class EmployeeIdExistsException extends Exception
{
	EmployeeIdExistsException(String msg)
	{
		super(msg);
	}
}
