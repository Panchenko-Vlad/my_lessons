package lessons_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ifelse {
    public static void main(String[] args) throws IOException {
        System.out.println("������� �����: ");
        String log = new BufferedReader(new InputStreamReader(System.in)).readLine();

        System.out.println("������� ������: ");
        String pass = new BufferedReader(new InputStreamReader(System.in)).readLine();

        System.out.println("��������� ������: ");
        String pass2 = new BufferedReader(new InputStreamReader(System.in)).readLine();

        if (pass.equals("password") || pass2.equals("password")) {
            if (pass2.equals(pass)) {
                if (log.equals("user")) {
                    System.out.println("�� ����� �� ���� �������!");
                }
                else {
                    System.out.println("��������� ����� �� �����!");
                }
            } else {
                System.out.println("������ �� ���������!");
            }
        } else {
            System.out.println("������ �� �����!");
        }
    }

}
