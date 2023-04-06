package ex00;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Analyzer {
    public class Signature {
        final private String ext;
        final private String hex;

        Signature(String in_ext, String in_hex) {
            ext = in_ext;
            hex = in_hex;
        }

        public String getHex() {
            return hex;
        }

        public String getExt() {
            return ext;
        }

        @Override
        public String toString() {
            return "Signature{" +
                    "ext='" + ext + '\'' +
                    ", hex='" + hex + '\'' +
                    '}';
        }
    }

    private List<Signature> signature;
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    Analyzer() {
        signature = new ArrayList<>();
    }

    public void fillSignature() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("src/ex00/signatures.txt"));
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String arr[] = str.split(",");
            String hex = arr[1].replaceAll("\\s+","");
            signature.add(new Signature(arr[0], hex));
        }
    }

    public void GetExtensions() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/ex00/result.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        System.out.print("->");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("42")) {
            FileInputStream fin = new FileInputStream(str);
            byte[] bytes = new byte[8];
            fin.readNBytes(bytes, 0, 8);
            String hex = bytesToHex(bytes);
            for (Analyzer.Signature signature : signature)
                if (hex.contains(signature.getHex())) {
//                    bw.write(signature.getExt());
                    System.out.println(signature.getExt());
                }
            System.out.print("PROCESSED\n->");
            str = sc.nextLine();
        }
    }


    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public void printSignature() {
        for (int i = 0; i < signature.size(); i++) {
            System.out.println(signature.get(i).toString());
        }
    }
}