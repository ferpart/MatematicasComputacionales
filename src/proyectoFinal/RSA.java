/***************************************************************************************************
Esta clase esta engargada de genera la llabe publica, privada y el modulo. Esta tambien se
encarga de la encriptacion y desencriptacion de los mensajes. 



Descripcion Basica de las metodos dentro de la clase RSA
	
	Los Siguientes metodos son los Geters y setters.
	
		*isRecievedPublic(): 							regresa receivedPublic
		*setReceivedPublic(boolean receivedPublic): 	establece receivedPublic
		*isRecievedModulus(): 							regresa receivedModulus
		*setRecievedModulus(boolean receivedModulus): 	establece recievedModulus
		*getPublicKey():								regresa publicKey
		*getModulus():									regresa modulus
		*getGivenPublicKey():							regresa PublicKey
		*setGivenPublicKey(BigInteger givenPublicKey):	establece setGivenPublicKey
		*getGivenModulus():		regresa GivenModulus:	regresa GivenModulus
		*setGivenModulus(BigInteger givenModulus):		establece GivenModulus
	
	Estos son los metodos importantes.
	
		*RSA(Integer n):								Es el constructor de la clase, recive
		n y con esta genera el modulus, la public key, y la private key.
		
		*encrypt(String msg):							Este metodo se encarga de hacer la
		encriptacion de cualquier mensaje recibido transformandolo a bytes, y usando la funcion
		modPow la cual saca el modulo (usando el modulo establecido en el constructor) de el 
		mensaje transformado en bytes el cual esta elevado a una x cantidad (la cual es la llave
		publica tambien establecida en el constructor de la clase). Finalmente este mensaje es 
		regresado en string. llamando al metodo toString() en la clase.
		
		*decrypt(String msg):							Este metodo se encarga de desencriptar
		el mensaje recibido haciendo uso de la funcion modPow, que recibe un exponente (privateKey)
		y un modulo (modulus). Como explicado previamente, modPow eleva el numero original a el
		valor de la exponente insertada y saca el modulo de dicho numero. el numero se regresa
		despues de haber terminado las operaciones.
		
		*toString():									Este metodo almacena en un string s la llave
		publica, privada y el modulo. para despues regresarlo compuesto de dichos elementos.
		
		*convertStringToASCII(String msg):				Este metodo combierte un string a formado
		ASCII por medio de la funcion getBytes(), y regresa dicho numero
		
		*convertByteArrToString(byte[] bytes):			Este metodo combierte un array de bytes a un
		String. y regresa dicho String.
	

***************************************************************************************************/

package proyectoFinal;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA{
	private final static BigInteger one = new BigInteger("1");
	private final static SecureRandom random = new SecureRandom();
	
	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger modulus;
	
	private BigInteger givenPublicKey;
	private BigInteger givenModulus;
	
	private boolean receivedPublic = false;
	private boolean receivedModulus = false;
	
	
	
	
	public boolean isReceivedPublic() {
		return receivedPublic;
	}

	public void setReceivedPublic(boolean receivedPublic) {
		this.receivedPublic = receivedPublic;
	}

	public boolean isReceivedModulus() {
		return receivedModulus;
	}

	public void setReceivedModulus(boolean receivedModulus) {
		this.receivedModulus = receivedModulus;
	}
	
	public BigInteger getPublicKey() {
		return publicKey;
	}

	public BigInteger getModulus() {
		return modulus;
	}
	
	public BigInteger getGivenPublicKey() {
		return givenPublicKey;
	}

	public void setGivenPublicKey(BigInteger givenPublicKey) {
		this.givenPublicKey = givenPublicKey;
	}

	public BigInteger getGivenModulus() {
		return givenModulus;
	}

	public void setGivenModulus(BigInteger givenModulus) {
		this.givenModulus = givenModulus;
	}

	public RSA(Integer n)
	{
		BigInteger p = BigInteger.probablePrime(n/2, random);
		BigInteger q = BigInteger.probablePrime(n/2, random);
		BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
		
		modulus = p.multiply(q);
		publicKey = new BigInteger("65537");
		privateKey = publicKey.modInverse(phi);
	}
	
	String encrypt (String msg)
	{
		
		BigInteger e = new BigInteger(msg.getBytes());
		e = e.modPow(getGivenPublicKey(), getGivenModulus());
		return e.toString();
	}
	
	BigInteger decrypt (String msg)
	{
		BigInteger d = new BigInteger(msg); /*El mensaje ya esta en bytes por lo que no se tiene que
transformar*/
		d = d.modPow(privateKey, modulus);
		return d;
	}
	
	public String toString()
	{
		String s = "";
		s += "public = " + publicKey + "\n";
		s += "private = " + privateKey + "\n";
		s +=  "modulus = " + modulus;
		return s;
	}
	
	public byte[] convertStringToASCII(String msg)
	{
		return msg.getBytes();
	}
	
	public String convertByteArrToString(byte[] bytes)
	{
		return new String(bytes);
	}
}
