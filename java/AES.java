import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {

	private static final String ALGO = "AES";

	public static String encrypt(String Data, String keyWord) throws Exception {
		System.out.println("data at Encrypt : " + Data);

		keyWord = keyWord.substring(0, 16);
		byte[] keyValue = keyWord.getBytes();
		System.out.println("Size : " + keyValue.length);
		Key key = new SecretKeySpec(keyValue, ALGO);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);

		System.out.println("Encrypted value : " + encryptedValue);

		return encryptedValue;
	}

	public static String decrypt(String encryptedData, String keyWord) throws Exception {
		keyWord = keyWord.substring(0, 16);
		byte[] keyValue = keyWord.getBytes();
		Key key = new SecretKeySpec(keyValue, ALGO);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(encryptedData.getBytes());
		byte[] decValue = c.doFinal(decodedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	public static void main(String[] args) {
		String password = "mypassword";
		String keyWord = "ef50a0ef2c3e3a5fdf803ae9752c8c66";

		try {
			String passwordEnc = AES.encrypt(password, keyWord);
			String passwordDec = AES.decrypt(passwordEnc, keyWord);
			System.out.println("Plain Text : " + password);
			System.out.println("Encrypted Text : " + passwordEnc);
			System.out.println("Decrypted Text : " + passwordDec);
		} catch (Exception e) {
			System.out.println("Opps,Exception In AES_EncrypterNdecrypter=>main() :");
			e.printStackTrace();
		}
	}
}