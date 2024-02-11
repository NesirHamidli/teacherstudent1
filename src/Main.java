import service.TeacherService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

       TeacherService t=new TeacherService();


         Scanner s= new Scanner(System.in);

        System.out.println(" Menu ucun 1i basin ------> 1");
        System.out.println(" Exit ucun 0i basin ------> 0");
        int i = s.nextInt();
        System.out.println("Menu"
                + "\n New -------- 1 " +
                "\n Update ------- 2 " +
                "\n Find --------- 3" +
                "\n Delete ------- 4 " +
                "\n New Add  ----- 5" +
                "\n Print ALl------6" +
                "\n Exit --------- 0");

        while (i!=0) {
            System.out.println("Menunu sec:");
            int a = s.nextInt();
            switch (a) {

                case 1:
                    t.initialize();
                    break;
                case 2:
                    t.update();
                    break;
                case 3:
                    t.find();
                    break;
                case 4:
                    t.delete();
                    break;
                case 5:
                    t.initializeNew();
                    break;
                case 6:


                    t.get();

                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Errror");

            }
        }
        t.printAll();
        }

    }
