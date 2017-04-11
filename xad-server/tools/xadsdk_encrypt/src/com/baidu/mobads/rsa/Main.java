package com.baidu.mobads.rsa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMFynyCDc7tuN2fP6lLGD4kf"
            + "+Ztwp+bt0SNxGpU0AXAbttOkvANCHjRtknOtwEpBZsPqQCtHpM+5BQIxDT3/LnlWTU/Yqru8kYaKwWmOWq/aqWDx2st2FeQtBS39J5l"
            + "UmBP4I3FVu7PEAXrUQR/wzQweLUjZ2bqfqU4Dp+iaIIP3AgMBAAECgYEAkWV/aMakc9nRVvuU2YX4I06fI0+3QwC4UnUuT7p44jRSZB"
            + "zLG3/Uh/9bNgsAHk+t1873UBBXNbGQrCcGFvT3jfjaF2LSAOzXmikpaBKLe2/JZQeBvS1GsZqG2ZsmNEV2Lw/y/ICSzoMFdJKSdyeZ/"
            + "ZNVBWfL8k7TIFY9ODvMDSECQQDh1HoISQFyxmdpUeRhuIEvq/7+lh/Xb78DvMNmNvPVVB2hRKevf/HgEFTapljTeYzWqU8PCaQPmQDe"
            + "GdiwTogPAkEA20qm/lkqBaVf90oIOa49XoPT+HNzjEB5c8lYkOQ9HqtZ5RA4bFSlXpV7yjDnQ3zm2eexwX4ZBa101dKsEkCdmQJAIPy"
            + "teqpXWNGcZ2j76D5QOItyKvP44LpE96HKFRMv2Nh5n4XszJju1uDr1Ch6TyGxLeX7U2IAIfsigk7aNR6zhwJAYWhUq1CB+QYPjbHl23"
            + "FJM02Nf6QQ5HDTQhpi6+FmQNROVxCQb5Zd6pit5w83+uNlS8++Rruzc7Er37r30he/qQJBAMOBYr4jPuDg1/nxrrigoKSesEKxN6HPu"
            + "4FLTUX5wGSJiN6VFaH56NlXdqT2kidp0/dG8eA0gAYAr8sKoy9X2gQ=";

    public static void main(String[] args) throws Exception {
        String fullPath = args[0]; // "build/__pasys_remote_banner.jar"
        // float arg1 = Float.parseFloat(args[1]); // 3.97
        String arg1 = args[1]; // 3.97        
        String urlPath = args[2]; // http://mobads.baidu.com/ads/pa/__pasys_remote_banner.jar
        String outputFileFullPath = args[3];
        
        //  
        String signOutputFileFullPath = null;
        if (args.length > 4) {
            signOutputFileFullPath = args[4];
        }
        
        String plainText = RSAEncrypt.getMD5Checksum(new File(fullPath));

        byte[] cipherData = RSAEncrypt.encrypt(
                RSAEncrypt.loadPrivateKeyByStr(PRIVATE_KEY),
                plainText.getBytes());
        String cipher = Base64.encode(cipherData);
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("{'url':'" + urlPath + "','version':'" + arg1
                + "',");
        sbuilder.append("'sign':'" + cipher + "'}");
        
        System.out.println(sbuilder.toString());
        
        write2(outputFileFullPath, sbuilder.toString());
        if (signOutputFileFullPath != null) {
            write2(signOutputFileFullPath, cipher);
        }
        
        
//        File file = new File(outputFileFullPath);
//        if (file.exists()) {
//            file.delete();
//        }
//        FileWriter fw = new FileWriter(file.getAbsoluteFile());
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(sbuilder.toString());
//        bw.close();
    }
    
    private static void write2(String path, String content) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileWriter fw;
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
