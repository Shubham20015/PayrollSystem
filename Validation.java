package com.employee.manage;

import java.util.regex.*;
import java.text.*;

public class Validation {
	
	public boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";      
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }
	
	public boolean isValidMobileNo(String str)  
	{  
		//(0/91): number starts with (0/91)  
		//[7-9]: starting of the number may contain a digit between 0 to 9  
		//[0-9]: then contains digits 0 to 9  
		Pattern ptrn = Pattern.compile("[7-9][0-9]{9}");  
		Matcher match = ptrn.matcher(str);  
		
		return (match.find() && match.group().equals(str));  
	}  
	
	public boolean isValidateDate(String strDate)
	   {
			/* Check if date is 'null' */
			if (strDate.trim().equals(""))
			{
			    return true;
			}
	
			else
			{
			    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
			    sdfrmt.setLenient(false);
			    try
			    {
			        sdfrmt.parse(strDate); 
			    }
			    
			    catch (ParseException e)
			    {
			        return false;
			    }
			    return true;
			}
	   }
}
