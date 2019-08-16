import java.util.Scanner;

public class crypto {

    public static String normalizeText(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char j = s.charAt(i);
            if (".,:;!?()\"\' ".indexOf(j) != -1) {
                continue;
            } else {
                sb.append(j);
            }
        }
        String newStr = sb.toString();
        newStr = newStr.toUpperCase();
        return newStr;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String caesarify(String str, int n) {
        String alphabet = shiftAlphabet(0);
        String shiftedAlphabet = shiftAlphabet(n);
        StringBuilder caeserString = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char k = str.charAt(i);
            int indx = alphabet.indexOf(k);
            char newChar = shiftedAlphabet.charAt(indx);
            caeserString.append(newChar);
        }
        return caeserString.toString();
    }

    public static String groupify(String str, int n) {
        if (n <= 0) {
            return new IllegalArgumentException().toString();
        }
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i += n) {
            int diff = (i + n) - len;
            if (diff > 0) {
                sb.append(str.substring(i));
                for (int j = 0; j < diff; j++) {
                    sb.append("x");
                }
            } else {
                sb.append((str.substring(i, i + n) + " "));
            }
        }
        String newStr = sb.toString();
        return newStr;
    }

    public static String encryptString(String s, int shift, int group) {
        String normalizedText = normalizeText(s);
        String encryptedText = caesarify(normalizedText, shift);
        return groupify(encryptedText, group);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter a sentence you would like to encrypt ");
        String sentence = scan.nextLine();
        System.out.print("Please enter the encryption key 0-26 ");
        int encryptKey = scan.nextInt();
        System.out.print("Please enter a group-by number (it should be less than the length of your sentence) ");
        int groupKey = scan.nextInt();
        System.out.println("Your new message is : " + encryptString(sentence, encryptKey, groupKey));
    }
}
