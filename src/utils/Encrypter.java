package utils;

public class Encrypter {
	
public static String simpleJeringosoEncrypt(String password) {
		
		// Declaración de array de caracteres para iterar password
		char[] contra = password.toCharArray();
		
		// Declaración de variable para devolver contraseña encriptada
		String encryptedPass = "";
		
		/* Se recorre cada elemento del array de caracteres. Si el elemento es vocal, se agrega a encryptedPass vocal+p+vocal*/
		
		for (int i = 0; i < password.length(); i++) {
			if(contra[i] == 'a'
					|| contra[i] == 'e'
					|| contra[i] == 'i'
					|| contra[i] == 'o'
					|| contra[i] == 'u') {
				
				encryptedPass += contra[i]+"p"+contra[i];				
			}else {
				encryptedPass += contra[i];
			}
				
		}
		return encryptedPass; 
	}

}
