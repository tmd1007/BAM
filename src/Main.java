import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 프로그램 시작 ==");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("cmd)");
            String cmd = sc.nextLine();

            if (cmd.equals("exit")) {
                break;
            }
        }



        System.out.println("== 프로그램 끝 ==");

        sc.close();
    }
}