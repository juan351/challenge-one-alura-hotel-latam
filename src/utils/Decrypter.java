package utils;

public class Decrypter {

public static String simpleJeringosoDecrypt(String password) {
		
		// Declaración de array de caracteres para iterar password
		char[] contra = password.toCharArray();
		
		// Declaración de variable para devolver contraseña desencriptada
		String decryptedPass = "";
		
		// Uso de StringBuilder para concatenar los caracteres en un String
		StringBuilder sb = new StringBuilder();

		/*Controlador del último índice recorrido. El último índice recorrido no coincide
		 * neceariamente con passowrd.lenght()-2, ya que en el código se producen saltos i+=2 */
		int ultimoIndiceRecorrido = 0;
		
		
		/*Para cada caracter, se examina la cadena que forman con los dos siguientes.
		 * Si esta cadena es igual a apa, epe, ipi, opo, o upu, entonces se adiciona
		 * únicamente el primer caracter a decryptedPassword y se saltean 2 caracteres
		 * en el recorrido de password. De lo contrario, se adiciona el primera caracter
		 * sin saltos en el loop*/
		
		for (int i = 0; i < password.length() - 2; i++) {
			sb.append(contra[i]);			
			sb.append(contra[i+1]);
			sb.append(contra[i+2]);
			String str = sb.toString();	
			/*El vaciado del StringBuilder es necesario para seguir examinando únicamente
			 * tríos de caracteres */
			sb.setLength(0);
			
			
			
			if(str.equals("apa")
					|| str.equals("epe")
					|| str.equals("ipi")
					|| str.equals("opo")
					|| str.equals("upu")) {
				
				decryptedPass += contra[i];	
				i+=2;
				
			}else {
				decryptedPass += contra[i];
				
			}	
			/*Actualización del controlador del último índice*/
			ultimoIndiceRecorrido = i;
				
		}
		
		/*Finalmente, se agregan al decryptedPass los caracteres que quedaron por fuera del for.
		 * En caso de que ya hayan sido todos recorridos, password.substring(ultimoIndiceRecorrido + 1)
		 * devuelve una cadena vacía*/
	
		
		decryptedPass += password.substring(ultimoIndiceRecorrido + 1);
	

		return decryptedPass; 
	}
}
