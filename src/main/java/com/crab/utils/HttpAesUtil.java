package com.crab.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class HttpAesUtil {

    private static final String charSet = "UTF-8";

    /**
     * 加密
     *
     * @param _content 需要加密的内容
     * @param _key     加密密码
     * @param md5Key   是否对key进行md5加密
     * @param _iv      加密向量
     * @return 加密后的字节数据
     */
    public static String encrypt(String _content, String _key, boolean md5Key, String _iv) {
        try {
            byte[] content = _content.getBytes(charSet);
            byte[] key = _key.getBytes(charSet);
            byte[] iv = _iv.getBytes(charSet);

            if (md5Key) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                key = md.digest(key);
            }
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding"); //"算法/模式/补码方式"
            IvParameterSpec ivps = new IvParameterSpec(iv);//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivps);
            byte[] bytes = cipher.doFinal(content);
            return new BASE64Encoder().encode(bytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 解密
     *
     * @param _content
     * @param _key
     * @param md5Key
     * @param _iv
     * @return
     */
    public static String decrypt(String _content, String _key, boolean md5Key, String _iv) {
        try {
            if (StringUtils.isBlank(_content) || StringUtils.isBlank(_key) || StringUtils.isBlank(_iv)) {
                return "";
            }
            byte[] content = new BASE64Decoder().decodeBuffer(_content);
            byte[] key = _key.getBytes(charSet);
            byte[] iv = _iv.getBytes(charSet);

            if (md5Key) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                key = md.digest(key);
            }
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding"); //"算法/模式/补码方式"
            IvParameterSpec ivps = new IvParameterSpec(iv);//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivps);
            byte[] bytes = cipher.doFinal(content);
            return new String(bytes, charSet);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 测试，key要满足16字节长度
     *
     * @param args
     */
    /*public static void main(String[] args) {

        String key = "uH)%:5AGVSgyL1@h";

        String sec = encrypt("admin", key, false, key);
        System.out.println(sec);

        String unSec = decrypt("gwGtzCbEIWqqU5w1paVElQ==", key, false, key);
        System.out.println(unSec);
    }*/
}