import java.io.*;
import java.util.*;

class PrimeNumbers {

	private int additionTotal;
	private int multiplicationTotal;
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
		arrPrime = new ArrayList<Integer>();
			
		int loopNumber = (int) Math.ceil(N/loopSize);
		System.out.println(Math.ceil(N/loopSize));
		for(int l = 0; l < loopNumber; l++){
			ArrayList<Integer>  arrInts = new ArrayList<Integer>();
			if(l == 0){ //Skip Zero and 1
				for(int i = 2; i < l*loopSize+loopSize; i++){
					arrInts.add(i);
					//System.out.println(i);
					additionTotal++;
				}
			}
			else if(l < loopNumber-1){
				for(int i =  l* (int)loopSize; i < l*loopSize+loopSize; i++){
					arrInts.add(i);
					//System.out.println(i);
					additionTotal++;
				}
			}
			else{
				for(int i =  l* (int)loopSize; i <= N; i++){
					arrInts.add(i);
					//System.out.println(i);
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
				boolean isPrime = false;
				for(int l = 0; l < arrPrime.size(); l++){
					double pPrime = (double) arrInts.get(i)/ (double) arrPrime.get(l);						
					//System.out.println(pPrime);
					if(pPrime % 1 == 0){ //isPrime
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
					//System.out.println(arrInts.get(i));
				}
			}
		}
	}
			
	///Prints Results
	public void PrintResults(){
		System.out.println("Additions="+additionTotal);
	}
}
