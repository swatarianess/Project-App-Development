package simpleModel;

import java.util.Scanner;

public class Population {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);

		System.out.println("Enter initial size of population:");  // size of starting population
		
		double Size = input.nextInt();
		
		System.out.println("Enter births per time unit:");		// Births per time unit used(could be day, week, month or year, based on species)
		double B = input.nextInt();
	
		System.out.println("Enter deaths per time unit:");		// Births per time unit used
		double D = input.nextInt();
		
		System.out.println("Enter timeframe:");						// Timeframe to show the population growth/decline	
		double t = input.nextInt();
		

		
		double b = B / Size;		// caculating instateneous birth rate
		
		double d = D / Size;		// caculating instateneous death rate
		
		double r = b - d;			// instateneous rate of increase
		
		
		
		for(int i=0; i<=t; i++){
			
			double Nt = Size * Math.pow(Math.E, (r*i)) ;  // calculating exponential population growth 
		
		System.out.printf("The population at time unit %d is %.0f animals", i, Nt );
		System.out.println();
		}
	}

}
