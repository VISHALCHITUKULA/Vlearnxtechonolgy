package Vilearnx;
import java.util.*;

public class TASK1 {
	public static void main (String args[])
	{
		Scanner sc=new Scanner(System.in);
		try {
			System.out.println("Enter First Number");
			double num1=sc.nextDouble();
			System.out.println("Enter Sceound Number");
			double num2=sc.nextDouble();
			System.out.println("Choose an operation;+,-,*,/");
			char s=sc.next().charAt(0);
			
			double result;
			 switch (s) {
             case '+':
                 result = num1 + num2;
                 break;
             case '-':
                 result = num1 - num2;
                 break;
             case '*':
                 result = num1 * num2;
                 break;
             case '/':
                 if (num2 != 0) {
                     result = num1 / num2;
                 } else {
                     System.out.println("Error: Division by zero is not allowed.");
                     return;
                 }
                 break;
             default:
                 System.out.println("Error: Invalid operation.");
                 return;
         }
			 System.out.println("The Result is: "+result);
			 
			}
		catch(Exception e){
			System.out.println("Error : Invalid input , Please enter numerical values.");
		}
		finally {
			sc.close();
		}
		
	}

}
