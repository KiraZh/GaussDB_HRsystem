import java.sql.SQLException;
import java.util.Scanner;

public class Manager {
    private static int userID = 2;
    private static int operation;
    public static int welcomeUI() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:退出管理系统");
        System.out.println("1:查看本部门所有员工信息");
        System.out.println("2:查看本部门单一员工信息");
        System.out.println("3:查看本部门员工工资信息");
        System.out.println("4:查看个人信息");
        operation = scan.nextInt();
        switch (operation){
            case 0:return 0;
            case 1:staffsUI();break;
            case 2:staffUI();break;
            case 3:salaryUI();break;
            case 4:
                personalInfoUI();
        }
        return 0;
    }
    private static void staffsUI() throws SQLException {
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        DataBaseTools.showStaffsInfo(userID);
        System.out.println("0:返回上一级");
        Scanner s = new Scanner(System.in);
        operation = s.nextInt();
        if (operation == 0) {
            welcomeUI();
        }
        return;
    }

    private static void staffUI() throws SQLException {
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        DataBaseTools.showStaffInfo(userID);
        System.out.println("0:返回上一级");
        Scanner s = new Scanner(System.in);
        operation = s.nextInt();
        switch (operation){
            case 0:welcomeUI();
        }
        return;
    }
    private static void salaryUI() throws SQLException {
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        DataBaseTools.showSalaryInfo();
        System.out.println("0:返回上一级");
        Scanner s = new Scanner(System.in);
        operation = s.nextInt();
        switch (operation){
            case 0:welcomeUI();
        }
        return;
    }
    private static void personalInfoUI() throws SQLException {
        DataBaseTools.showPersonalInfo();
        Scanner s = new Scanner(System.in);
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:返回上一级");
        System.out.println("1:修改电话号码");
        operation = s.nextInt();
        switch (operation){
            case 0:welcomeUI();break;
            case 1:
                DataBaseTools.updatePhoneNumber();
                personalInfoUI();
        }
        return;
    }
}
