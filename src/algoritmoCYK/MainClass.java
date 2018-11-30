package algoritmoCYK;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String lenguajeChomsky= "S=AB|SS|AC|BD,A=a,B=b,C=SB,D=SA";
		
		//Lenguaje en forma normal de Chomsky
		String [][] matrix= {{"S","AB"},
							 {"S","SS"},
							 {"S","AC"},
							 {"S","BD"},
							 {"A","a"},
							 {"B","b"},
							 {"C","SB"},
							 {"D","SA"}};
		Integer colLength= 6;
		
		
		/*String s = "AB|SS|AC|BD";
		String a = "a";
		String b = "b";
		String c = "SB";
		String d = "SA";
		
		String cadena="aabbab";*/
		
		
		MatrizCYK matriz = new MatrizCYK();
/*
		matriz.SetLenguajeChom("S", s);
		matriz.SetLenguajeChom("A", a);
		matriz.SetLenguajeChom("B", b);
		matriz.SetLenguajeChom("C", c);
		matriz.SetLenguajeChom("D", d);
		*/
		matriz.setMatriz(matrix, colLength);
		

	}

}
