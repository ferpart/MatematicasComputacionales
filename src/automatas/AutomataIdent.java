package automatas;

import java.util.Scanner;

public class AutomataIdent {
	
	int count;
	int resCount;
	char [] car;
	char [] comp;
	boolean flag = false;
	boolean accepted;
	String [] reserved = {"program","var","begin","end","if","then","else"};
	String chain;
	

	public static void main(String[] args) {
		AutomataIdent aut = new AutomataIdent();
		Scanner reader = new Scanner(System.in);
		System.out.println("Inserta un Identificador ");
		aut.chain=reader.nextLine();
		reader.close();
		aut.car = aut.chain.toCharArray();
		aut.start();
		if(aut.accepted) {
			System.out.println("Se inserto identificador valido.");
		}else {
			System.out.println("No se inserto un identificador valido.");
		}
		
		
	}

	public void start() {
		count = 0;
		resCount = 0;
		q0();
		
	}
	
	public void q0() {
		//System.out.println("En q0");
		accepted=false;
		if(count<car.length) {
			if(count==0) {
				reservedCheck();
			}
						
			if(car[count]=='_' && flag!=true) {
				count++;
				q0();
			}else if(Character.isLetter(car[count]) && flag!=true){
				count++;
				q1();
			}else{
				count++;
				qError();
			}
		}
	}
	
	public void q1() {
		//System.out.println("En q1");
		accepted = true;
		if(count<car.length) {
			
			if(car[count]=='_' || Character.isLetter(car[count]) || Character.isDigit(car[count])) {
				count++;
				q1();
			}
			else {
				count++;
				qError();
			}
		
		}
		
	}
	
	public void qError() {
		accepted = false;
		return;
	}
	
	public void reservedCheck() {
		for(int i=0; i < reserved.length; i++) {
			if (chain.contains(reserved[i])) {
				flag = true;
			}
		}
	}

}
