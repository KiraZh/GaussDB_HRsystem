import java.sql.*;
import java.util.Scanner;

public class Staff {
    private static int operation;

    public static int welcomeUI() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:退出管理系统");
        System.out.println("1:查看个人信息");
        operation = s.nextInt();
        switch (operation) {
            case 0:
                return 0;
            case 1:
                personalInfoUI();
        }
        return 0;
    }

    private static void personalInfoUI() throws SQLException {
        DataBaseTools.showPersonalInfo();
        Scanner s = new Scanner(System.in);
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:返回上一级");
        System.out.println("1:修改电话号码");
        operation = s.nextInt();
        switch (operation) {
            case 0:
                welcomeUI();
                break;
            case 1:
                DataBaseTools.updatePhoneNumber();
                personalInfoUI();
        }
        return;
    }
}