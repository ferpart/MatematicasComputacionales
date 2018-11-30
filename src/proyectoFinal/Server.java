/***************************************************************************************************
Para hacer uso de este programa, el usuario debe de correr primero la clase "Server" para asi 
levantar un socket de conexion y poder comenzar con el chat. Despues correr la clase client para 
conectarse.

Al enviar un mensaje, se hace la encriptacion en RSA de 1024-bit se hace dentro de la clase "RSA" 
esta despues se almacena y se envia a "Server" el cual llama denuevo a RSA para asi desencriptar y 
mostrar el mensaje.

Al recibir un mensaje encriptado, este se desencripta en la clase de RSA y se regresa el mensaje 
desencriptado para que este pueda ser leido por el usuario.
***************************************************************************************************/

package proyectoFinal;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;

public class Server {
	
	private static String name;
	private static String otherName;	
	
	public static void main (String[] args) throws Exception
	{
		Scanner s = new Scanner(System.in);
		while (name == null || name.isEmpty())
		{
			try
			{
				System.out.println("Escriba su nombre o nickname");
				name = s.nextLine();
				if (name.isEmpty())
				{
					System.out.println("Porfavor inserta un nombre no vacio!");
				}
				
			}
			catch (Exception e)
			{
				System.out.println("Inserta el input correcto.");
			}
		}
		
		System.out.println("Presiona Enter para comenzar el intento de conexión.");
		s.nextLine();
		
		RSA encryptionSystem = new RSA (1024);
		ServerSocket server = new ServerSocket(3000);
		Socket socket = server.accept();
	
		BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
		OutputStream out = socket.getOutputStream();
		PrintWriter write = new PrintWriter(out, true);
	
		InputStream in = socket.getInputStream();
		BufferedReader receiveReader = new BufferedReader(new InputStreamReader(in));
		
		
		String receiveMsg = "", sendMsg = "";
		while(true)
		{
			try
			{
				if ((receiveMsg = receiveReader.readLine()) != null)
				{
					if (receiveMsg.startsWith("public-"))
					{
						encryptionSystem.setGivenPublicKey(new BigInteger(receiveMsg.substring(7)));
						write.println("public-" + encryptionSystem.getPublicKey());
						write.flush();
						receiveMsg = "";
						encryptionSystem.setReceivedPublic(true);
					}
					
					else if (receiveMsg.startsWith("modulus-"))
					{
						encryptionSystem.setGivenModulus(new BigInteger(receiveMsg.substring(8)));
						write.println("modulus-" + encryptionSystem.getModulus());
						write.flush();
						receiveMsg = "";
						encryptionSystem.setReceivedModulus(true);
					}
					
					else if (receiveMsg.startsWith("name-"))
					{
						otherName = receiveMsg.substring(5);
						receiveMsg = "";
						write.println("name-" + name);
						write.flush();
					}
				}
				if (encryptionSystem.isReceivedModulus() && encryptionSystem.isReceivedPublic() && 
						otherName != null)
				{
					break;
				}
			}
		
			catch (SocketException se)
			{
				continue;
			}
		}
	
		System.out.println("Server listo para chatear!");
		receiveMsg = "";
		sendMsg = "";
		System.out.println("public: " + encryptionSystem.getGivenPublicKey());
		System.out.println("modulus: " + encryptionSystem.getGivenModulus());
		System.out.println("\n\n\n\nConectado a " + otherName + "\nDiviertete conversando ya que "
				+ "las conversaciones son encriptadas usando RSA de 1024-bits \n");
		
		while(true)
		{
			if ((receiveMsg = receiveReader.readLine()) != null)
			{
				String message;
				BigInteger decMsg;
				
				decMsg = encryptionSystem.decrypt(receiveMsg);
				message = encryptionSystem.convertByteArrToString(decMsg.toByteArray());
				
				if (!message.equalsIgnoreCase("q!"))
					System.out.println("$" + otherName + ": " + message);
				else
					break;
			}
			
			String encMsg = "";
			
			System.out.print("$" + name + ": ");
			sendMsg = keyboardReader.readLine();			
			encMsg = encryptionSystem.encrypt(sendMsg);		
			write.println(encMsg);
			write.flush();
			
			if (sendMsg.equalsIgnoreCase("q!"))
				break;
		}
		
		if (!sendMsg.equalsIgnoreCase("q!"))
			System.out.println("La otra persona se ha salido del chat!");
		else
			System.out.println("Te haz salido del chat!");
		
		System.out.println("Vuelve a usar este chat en el futuro\\\\nPresiona ENTER para salir");
		keyboardReader.readLine();
		server.close();
		socket.close();
		s.close();
	}
}
