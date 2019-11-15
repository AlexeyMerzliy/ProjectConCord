package ConCord.Service;

import ConCord.Models.JsonRequest;
import ConCord.Models.JsonResponse;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;


@Service
public class MyService {

    private static final Logger logger = Logger.getLogger(MyService.class);
    private SecretKey secretKey;


    //генерация ключа шифрования
    public SecretKey GeterKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            logger.info(e.getMessage());
        }
        return secretKey;
    }


    public JsonResponse encryption(JsonRequest jsonRequest) {

        if (jsonRequest.getId() != null) {
            JsonResponse jsonResponse = new JsonResponse();
            logger.info("REQUEST: " + jsonRequest.toString());

            secretKey = GeterKey();

            //encription request
            byte[] encriptRequest = makeAes(jsonRequest.toString().getBytes(), Cipher.ENCRYPT_MODE);
            String encript = new String(Base64.encodeBase64(encriptRequest));
            logger.info("=== encryption:" + encript);

            //decription request
            byte[] decriptRequest = makeAes(encriptRequest, Cipher.DECRYPT_MODE);
            String decript = new String(decriptRequest);
            logger.info("=== decryption:" + decript);

            //===========
            if (jsonRequest.getId() == 1) {
                jsonResponse.setFio("Test Testov");

                logger.info("RESPONSE: " + jsonResponse.toString());

                //encription response
                byte[] encriptResponse = makeAes(jsonResponse.toString().getBytes(), Cipher.ENCRYPT_MODE);
                String encript2 = new String(Base64.encodeBase64(encriptResponse));
                logger.info("=== encryption:" + encript2);

                //decription response
                byte[] decriptResponse = makeAes(encriptResponse, Cipher.DECRYPT_MODE);
                String decript2 = new String(decriptResponse);
                logger.info("=== decryption:" + decript2);
                //==========

                return jsonResponse;
            } else {
                logger.info("RESPONSE: NULL");
                return null;
            }
        } else {
            logger.info("Request parameters are empty!");
            return null;
        }

    }

    public byte[] makeAes(byte[] rawMessage, int cipherMode) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);
            byte[] output = cipher.doFinal(rawMessage);
            return output;
        } catch (Exception e) {
            logger.error("ERROR: " + e.getMessage());
            return null;
        }
    }
}
