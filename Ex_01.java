import java.util.Scanner;
import java.util.ArrayList;

public class Ex_01 {
    public static void main(String args[]) {
        
        int number = readNumber();
        while (number != -1){
            //Collatz.getSet(number);
            
            ArrayList collatzSet = Collatz.getSet(number);
            System.out.println(collatzSet);
            number = readNumber();
        }
    }
    
    public static int readNumber(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el numero, o -1 para salir: ");
        int n = sc.nextInt();
        if (n == -1){
            System.out.println("Fin");
        }
        return n;
    }
}

class Collatz {
    
    public static int collatzFunctionN(int n){
        if (n % 2 == 0){
            return n / 2; 
        }
        else {
            return 3 * n + 1;
        }
    }
    public static ArrayList getSet(int n){
        ArrayList collatzSetN = new ArrayList();
        collatzSetN.add(n);
        int i = n;
        while (i > 1){
            i = collatzFunctionN(i);
            collatzSetN.add(i);
        }
        return collatzSetN;
    }
}
