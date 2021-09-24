import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.FormatterClosedException;


public class Calculator {
    public static void main(String[] args) throws IOException, ArithmeticException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
    try {
        String[] namber = input.split(" ");
        if (namber.length>3){
            throw new NumberFormatException();
        }
         if (new AraOrRom().arab(namber[0]) && new AraOrRom().arab(namber[2])) {
         int a = Integer.parseInt(namber[0]);
         int b = Integer.parseInt(namber[2]);

            if (a <= 10 && b <= 10 && a >= -10 && b >= -10) {
                System.out.println(new Accountant().accountant(a,namber[1],b));
            }

        } else if (new AraOrRom().rom(namber[0]) && new AraOrRom().rom(namber[2])) {
            int a = Integer.parseInt(new ConvertRomToAra().romTo(namber[0]));
            int b = Integer.parseInt(new ConvertRomToAra().romTo(namber[2]));
            if (a <= 10 && b <= 10 && a >= -10 && b >= -10) {
                if ( new Accountant().accountant(a,namber[1],b) >= 1) {
                    System.out.println(new ConvertAraToRom().convert(new Accountant().accountant(a,namber[1],b)));
                }else {
                    throw new FormatterClosedException();
                }
            }
         }else if (new AraOrRom().rom(namber[0]) && new AraOrRom().arab(namber[2]) || new AraOrRom().arab(namber[0]) && new AraOrRom().rom(namber[2])){
            throw new ExceptionInInitializerError();
        }else throw new ArrayStoreException();

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Строка не является математической операцией");
            }catch (ArithmeticException e){
                System.out.println("На ноль делить нельзя");
            } catch (NumberFormatException e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } catch (ArrayStoreException e){
                System.out.println("Не допустимое значение");
            } catch (Exception e) {
                System.out.println("В римской системе нет отрицательных чисел");
            }catch (ExceptionInInitializerError e){
                System.out.println("Используются одновременно разные системы счисления");
            }
    }


    static class ConvertRomToAra {
        public String romTo(String a) {
            String[] romnamber = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            String[] arabnamber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            String romA = null;
            for (int i = 0; i < romnamber.length; i++) {
                if (a.equals(romnamber[i])) {
                    romA = arabnamber[i];
                }
            }
            return romA;
        }
    }

    static class AraOrRom {

        public boolean arab(String a) {
            String[] arabnamber = new String[21];
            for (int i = -10; i<11;i++){
                arabnamber[i+10] = String.valueOf(i);
            }
            boolean q = false;
            for (int i = 0; i < arabnamber.length; i++) {
                if (a.equals(arabnamber[i])) {
                    q = true;
                }
            }
            return q;
        }

        public boolean rom(String a) {
            String[] romnamber = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            boolean w = false;
            for (int i = 0; i < romnamber.length; i++) {
                if (a.equals(romnamber[i])) {
                    w = true;
                }
            }
            return w;
        }
    }

    static class ConvertAraToRom {
        String[] rom10 = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] romnamber = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        public String convert(int a) {
            String result = null;
            int b = a / 10;
            int c = a % 10;
            if (b == 0) {
                result = romnamber[a - 1];
            } else if (b > 0) {
                for (int i = 1; i < rom10.length + 1; i++) {
                    if (b == i) {
                        result = rom10[i - 1];
                    }
                }
                if (c != 0) {
                    for (int i = 1; i < romnamber.length; i++) {
                        if (c == i) {
                            result = result + romnamber[i - 1];
                        }
                    }
                }
            }
            return result;
        }
    }
    static class Accountant {
        public int accountant(int a, String q, int b){
            int result = 0;
            if (q.equals("+")) {
                result = a + b;
            }
            if (q.equals("-")) {
                result = a - b;
            }
            if (q.equals("*")) {
                result = a * b;
            }
            if (q.equals("/")) {
                result = a / b;
            }else throw new NumberFormatException();
            return result;
        }
    }
}


