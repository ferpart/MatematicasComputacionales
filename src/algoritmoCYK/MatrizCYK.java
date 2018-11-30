package algoritmoCYK;

import java.util.HashMap;
import java.util.Set;

public class MatrizCYK {


	private String [][] matrix;
	private String [][] chomsky;
	private HashMap<String, String[]> lenguajeChom= new HashMap<String, String[]>();
	private String [] cadenaFin;
	private Integer colLength;
	
	
	
	public MatrizCYK () {
	}
	
	public void SetLenguajeChom(String a, String [] b) {
		this.lenguajeChom.put(a, b);
		
	}
	
	public void setMatriz(/*String cadena*/ String[][] matrix, Integer length) {

		this.chomsky=matrix;
		this.colLength=length;
	}
	
	
	
	public void CYKchecker() {
		
		Integer length = cadenaFin.length;
		String temp;
		Integer l = 0;
		
		
		
		for (Integer i = 0; i>=length ; i++) {
			String [] tempStore={};
			Integer j = i+1;
			temp=matrix[i][i]+matrix[j][j];
			
			for (int k=0; k>=colLength; k++) {
				if (chomsky[k][1]==temp) {	
					tempStore[l]=chomsky[k][0];
					l++;
				}	
			}
			if (tempStore.length>0) {
				matrix[i][j]=tempStore.toString();
			}
			
			
			
		}
	}
		
	
	public void CYKchecker(Integer j) {
		
		CYKchecker(j++);
	}
	
	
	
	
}
