import java.util.Scanner;

public class VendingMachine {

    enum Coin {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String[] args) {

        int price = 15;
        int total = 0;
        Scanner sc = new Scanner(System.in);

        while (total < 15) {

            System.out.print("Enter coin: ");
            Coin coin = Coin.valueOf(sc.nextLine().toUpperCase());

            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            total += value;

            System.out.println("Total so far: " + total);
        }

        System.out.println("Paid. Change: " + (total - price));

        sc.close();
    }
}
