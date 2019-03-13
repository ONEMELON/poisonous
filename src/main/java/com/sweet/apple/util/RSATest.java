package com.sweet.apple.util;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author zhujialing
 * @Create 2019-02-18 下午12:25
 * @Description:
 */
public class RSATest {
    private static final String PUBLIC_KEY ="publicKey";
    private static final String PRIVATE_KEY ="privateKey";

    private static final String KEY_ALGORITHM = "RSA";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        try {
            String pre = "http://www.baidu.com?";
            TreeMap<String, Object> map = new TreeMap<>();
            map.put("source", 108);
            map.put("merchant", "3234343");
            map.put("userid", 123323);
            StringBuilder sb = new StringBuilder();
            sb.append(pre);
            for (String temp : map.keySet()) {
                sb.append(temp);
                sb.append("=");
                sb.append(map.get(temp));
                if (!temp.equals(map.lastKey())) {
                    sb.append("&");
                }
            }
            String content = sb.toString();
            System.out.println("====content========"+content);

            Map<String, String> keyMap = genKey();
            RSAPublicKey publicKey = getPublicKey(keyMap.get(PUBLIC_KEY));
            RSAPrivateKey privateKey = getPrivateKey(keyMap.get(PRIVATE_KEY));

            //SHA1withRSA算法进行签名
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign(privateKey);
            byte[] data = content.getBytes("utf-8");
            //更新用于签名的数据
            sign.update(data);
            String signature = Base64.encodeBase64String(sign.sign());
            System.out.println("====signature========"+signature);

            Signature verifySign = Signature.getInstance("SHA1withRSA");
            verifySign.initVerify(publicKey);
            //用于验签的数据
            verifySign.update(data);

            boolean flag = verifySign.verify(Base64.decodeBase64(signature));
            System.out.println("====flag========"+flag);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static Map<String,String> genKey() throws NoSuchAlgorithmException {
            Map<String,String> keyMap = new HashMap<String,String>();
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom random = new SecureRandom();
            // 初始加密，512位已被破解，用1024位,最好用2048位
            keygen.initialize(2048, random);
            // 取得密钥对
            KeyPair kp = keygen.generateKeyPair();
            RSAPrivateKey privateKey = (RSAPrivateKey)kp.getPrivate();
            String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
            RSAPublicKey publicKey = (RSAPublicKey)kp.getPublic();
            String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
            keyMap.put(PUBLIC_KEY, publicKeyString);
            keyMap.put(PRIVATE_KEY, privateKeyString);
            return keyMap;
        }

        /**获取公钥私钥**/
        public static RSAPublicKey getPublicKey(String publicKey) throws Exception{
            byte[] keyBytes = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return (RSAPublicKey) keyFactory.generatePublic(spec);
        }

        public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception{
            byte[] keyBytes = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return (RSAPrivateKey) keyFactory.generatePrivate(spec);
        }

}
