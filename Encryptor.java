public class Encryptor {
    public static void main(String[] args) {

        String mode = "enc"; // По умолчанию mode в режиме enc. Даже если параметр не передан.
        int shift = 0;       // По умолчанию ключ сдвига ноль, даже если параметр не передан.
        String buf = "";     // По умолчанию строка шифрования пустая, даже если параметр не передан.

        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-mode")){
                mode = args[i + 1];
            }
            if(args[i].equals("-key")){
                shift = Integer.parseInt(args[i + 1]);
            }
            if(args[i].equals("-data")){
                buf = buf + args[i + 1];
            }
        }
        // Полученный аргумент переводим в массив символов
        char[] chars = buf.toCharArray();

        switch (mode) {
            case "enc":
                encrypt(chars, shift);
                break;
            case "dec":
                decrypt(chars, shift);
                break;
            default:
                System.out.println("Unknown operation");
                break;
        }
    }

    // Шифруем
    public static void encrypt(char[] chars, int shift) {
        char start = 32;    // Первый символ ASCII
        char finish = 127;  // Последний символ ASCII
        int size = 96;      // К-во символов в таблице ASCII

        for (int i = 0, charsLength = chars.length; i < charsLength; i++) {
            char item = chars[i];
            if (item >= start && item <= finish) {
                char shiftItem = (char) (((item - start + shift) % size) + start);
                System.out.print(shiftItem);
            } else {
                System.out.print(item);
            }
        }
    }

    // Расшифровываем
    private static void decrypt(char[] chars, int shift) {
        char start = 32;        // Первый символ ASCII
        char finish = 127;      // Последний символ ASCII
        int size = 96;          // К-во символов в таблице ASCII

        for (int i = 0, charsLength = chars.length; i < charsLength; i++) {
            char item = chars[i];
            if (item >= start && item <= finish) {
                char shiftItem = (char) (finish - (finish - item + shift) % size);
                System.out.print(shiftItem);
            } else {
                System.out.print(item);
            }
        }
    }

}
