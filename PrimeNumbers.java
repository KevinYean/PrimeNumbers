import java.io.*;
import java.util.*;

//https://primes.utm.edu/howmany.html#pi_def
//https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Algorithm_and_variants

class PrimeNumbers {

	private long additionTotal;
	private long multiplicationTotal;
	private long start; //Time Variable
	private ArrayList<Double> arrPrime;	
	private double realNumber;//Size of N

	public static void main(String[] args) {
		double N =(double) Integer.parseInt(args[0]);
		PrimeNumbers prime = new PrimeNumbers(N);
	}
	//Constructor
	public PrimeNumbers(double N){
		realNumber = N;
		additionTotal=0;
		multiplicationTotal=0;
		start = System.nanoTime();
		PrimeSieve(N);
	}
	//Version 3:Sieve
	//N:100,	Time=0.001,	Add:370,	Mul:0
	//N:1000,	Time=0.001,	Add:4125,	Mul:0
	//N=10000,	Time=0.003,	Add:44299,	Mul:0
	//N=100000,	Time=0.008,	Add:466399,	Mul:0
	//N=1000000,	Time=0.081,	Add:4853707,	Mul:0
	//N=10000000,	Time=0.397,	Add:50130316,	Mul:0
	//N=100000000, 	Time=3.380,	Add:515037280,	Mul:0
	//N=1000000000,	Time=63.444,	Add:5271067967,	Mul:0
	//Anything after Array too small
	public void PrimeSieve(double N){
		int range = (int) N;
		arrPrime = new ArrayList<Double>();
		int[] sieve = new int[range+1];//+1 is due to me using index location as number
		additionTotal+=N;//Counting adding value in each cell;
		Arrays.fill(sieve,0);//Put 0 in all cells
		for(int i = 2; i < sieve.length;i++){
			additionTotal++;//Going through all cells
			if(sieve[i]==0){ //If zero must be prime
			//	System.out.println(i);
				arrPrime.add((double)i);//Add to list of prime numbers
				additionTotal++;//Adding to array
				for(int l = i+i; l < sieve.length;l = l+i){ //Eliminate all numbers divisible y that prime number
					additionTotal++;//Loop
					sieve[l]=1;//Not prime
				}		
			}
		}
		PrintResults();
	}
			
	///Prints Results
	public void PrintResults(){
		long end = System.nanoTime();
		long finalTime = end-start;
		double seconds = (double)finalTime / (double) 1000000000.0;//COnvert to seconds
		double theta = realNumber/Math.log(realNumber);
		System.out.println("The Number of Primes less than or equal to N="+arrPrime.size());
		if(realNumber <=100){
			for(int i = 0 ; i < arrPrime.size();i++){
				System.out.print(arrPrime.get(i)+",");
			}
			System.out.print("\n");
		}
		System.out.println("Theta="+theta);
		System.out.println("The Real Number:"+arrPrime.size()/theta);
		System.out.println("Total number of Additions="+additionTotal);
		System.out.println("The number of Multiplication="+multiplicationTotal);
		System.out.println("Real Time=" + seconds);
	}
}
