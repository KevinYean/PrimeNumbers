import java.io.*;
import java.util.*;

class PrimeNumbers {

	private long additionTotal;
	private long multiplicationTotal;
	private long start;
	private ArrayList<Integer> arrPrime;
	private double loopSize = 10;	

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		PrimeNumbers prime = new PrimeNumbers(N);
	}
	public PrimeNumbers(int N){
		System.out.println("N="+N);

		additionTotal=0;
		multiplicationTotal=0;
		start = System.nanoTime();
		PrimeV2(N);
	}
	//Version 2
	//N:100,     Time:0.001,    Add: 535,        Mul: 485
	//N:1000,    Time:0.005,    Add: 16787,      Mul: 16451
	//N:10000,   Time:0.029,    Add: 787859,     Mul: 785401
	//N:100000,  Time:0.707,    Add: 46424068,   Mul: 46404884
	//N:1000000, Time:37.207,   Add: 3086865210, Mul: 3086708214
	//N:10000000, //Time:??
	//Version 2.1
	//N=100,     Time:0.001,    Add: 441,        Mul: 415
	//N=1000,    Time:0.005,    Add: 10908,      Mul: 10739
	//N=10000,   Time:0.040,    Add: 448784,     Mul: 447554
	//N=100000,  Time:0.450,    Add: 25162132,   Mul: 25152539
	//N=1000000, Time:21.661    Add: 1639679217, Mul: 1639600718
	public void PrimeV2(int N){
		arrPrime = new ArrayList<Integer>();
		for(int i = 2 ; i <= N ; i++){
			boolean isPrime = true;
			additionTotal++;//For loop
			for(int l = 0 ; l < arrPrime.size(); l++){
				additionTotal++;//For loop
				double pPrime = (double) i/ (double) arrPrime.get(l); 
				multiplicationTotal++;
				if(arrPrime.get(l) > i/2){ //Version 2 loop
					multiplicationTotal++;
					break;
				}
				else if(pPrime % 1 == 0){ //isPrime
					multiplicationTotal++;//Comparaison
                                        isPrime = false;
                                        break;
                                }
			}
			if(isPrime == true){
				//System.out.println(i);
				arrPrime.add(i);
				additionTotal++;
			}
		}
		PrintResults();
	}
	//Version 1
	//N:100,    Time: 0.002,   Add: 3079,       Mul :99
	//N:1000,   Time: 0.036,   Add: 185558,     Mul: 999
	//N:10000,  Time: 0.877,   Add: 13060137,   Mul: 9999
	//N:100000, Time: 62.105 , Add: 1002068212, Mul: 99999 
	public void PrimeV1(int N){
		arrPrime = new ArrayList<Integer>();
			
		int loopNumber = (int) Math.ceil(N/loopSize);
		for(int l = 0; l < loopNumber; l++){
			additionTotal++;//For Loop
			ArrayList<Integer>  arrInts = new ArrayList<Integer>();
			if(l == 0){ //Skip Zero and 1
				for(int i = 2; i < l*loopSize+loopSize; i++){
					additionTotal++;//For Loop
					arrInts.add(i);
					additionTotal++;
				}
			}
			else if(l < loopNumber-1){
				for(int i =  l* (int)loopSize; i < l*loopSize+loopSize; i++){
					additionTotal++;//For loop
					arrInts.add(i);
					additionTotal++;
				}
			}
			else{
				for(int i =  l* (int)loopSize; i <= N; i++){
					additionTotal++;//For loop
					arrInts.add(i);
					additionTotal++;
				}
			}
			//arrInts is now compromised of the list of Ints that needs
			// to be check if they are prime or not.
			CheckPrime(arrInts);
		}
		PrintResults();
	}
	public void CheckPrime(ArrayList<Integer> arrInts){
		while(!arrInts.isEmpty()){
			for(int i = 0 ; i < arrInts.size(); i ++){
				additionTotal++;//For Loop
				boolean isPrime = false;
				for(int l = 0; l < arrPrime.size(); l++){
					additionTotal++;//For Loop
					double pPrime = (double) arrInts.get(i)/ (double) arrPrime.get(l);								//System.out.println(pPrime);
					if(pPrime % 1 == 0){ //isPrime
						multiplicationTotal++;//Comparaison
						arrPrime.add(arrInts.get(i));
						arrInts.remove(i);
						i--;
						isPrime = true;
						break;
					}
				}
				if(isPrime == false){
					//Add as prime
					arrPrime.add(arrInts.get(i));
					additionTotal++;
					//System.out.println(arrInts.get(i));
				}
			}
		}
	}
			
	///Prints Results
	public void PrintResults(){
		long time = System.nanoTime();
		long finalTime = time-start;
		double seconds = (double)finalTime / (double) 1000000000.0;
		System.out.println("Time="+seconds);
		System.out.println("PrimeSize="+arrPrime.size());
		System.out.println("Additions="+additionTotal);
		System.out.println("Multiplication="+multiplicationTotal);
	}
}
