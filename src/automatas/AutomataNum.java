package automatas;

import java.util.Scanner;

public class AutomataNum {
	
	int count;
	char[] car;
	boolean accepted;
	String chain;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutomataNum aut = new AutomataNum();
		Scanner reader = new Scanner(System.in);
		System.out.println("Inserta un numero ");
		aut.chain=reader.nextLine();
		reader.close();
		aut.car= aut.chain.toCharArray();
		aut.start();
		if(aut.accepted) {
			System.out.println("El numero es valido.");
		}else {
			System.out.println("Lo que se inserto no es un numero.");
		}
		//System.out.println(aut.accepted);
	}
	
	public void start() {
		count = 0;
		q0();
	}
	public void q0() {
		//System.out.println("q0");
		accepted = false;
		if(count<car.length) {
			
			if (Character.isDigit(car[count]) && car[count]!='0' ) {
				//System.out.println(car[count]);
				count++;
				q1();
			}else if(car[count]=='0' && Character.toLowerCase(car[count+1])=='x') {
				//System.out.println("test");
				count++;
				count++;
				q2();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q1() {
		//System.out.println("q1 " + count);
		accepted = true;
		if(count<car.length) {
			//System.out.println("lol");
			
			if (Character.isDigit(car[count])) {
				count++;
				q1();
			}else if (car[count]=='.') {
				count++;
				q3();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q2() {
		//System.out.println("q2");
		accepted = false;
		if(count<car.length) {
			
			if (Character.isDigit(car[count])|| Character.toLowerCase(car[count])>='a' && Character.toLowerCase(car[count])<='f') {
				//System.out.println("test3");
				count++;
				q9();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q3() {
		//System.out.println("q3 " + count);
		accepted = false;
		if(count<car.length) {
			if (Character.isDigit(car[count])) {
				count++;
				q4();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q4() {
		//System.out.println("q4 "+ count);
		accepted = true;
		if(count<car.length) {
			if (Character.isDigit(car[count])) {
				count++;
				q4();
			}else if(Character.toLowerCase(car[count])=='e') {
				count++;
				q5();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q5() {
		//System.out.println("q5");
		accepted = false;
		if(count<car.length) {
			if (Character.isDigit(car[count])) {
				count++;
				q6();
			}else if(car[count]=='+' || car[count]=='-') {
				count++;
				q7();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q6() {
		//System.out.println("q6");
		accepted = true;
		if(count<car.length) {
			if (Character.isDigit(car[count])) {
				count++;
				q6();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q7() {
		//System.out.println("q7");
		accepted = false;
		if(count<car.length) {
			if (Character.isDigit(car[count])) {
				count++;
				q8();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q8() {
		//System.out.println("q8");
		accepted = true;
		if(count<car.length) {
			if (Character.isDigit(car[count])) {
				count++;
				q8();
			}else {
				count++;
				qError();
			}
		}
	}
	public void q9() {
		//System.out.println("q9");
		accepted = true;
		if(count<car.length) {
			if (Character.isDigit(car[count])|| Character.toLowerCase(car[count])>='a' && Character.toLowerCase(car[count])<='f') {
				count++;
				q9();
			}else {
				count++;
				qError();
			}
		}
	}
	public void qError() {
		//System.out.println("qError");
		accepted=false;
		return;
	}

}
