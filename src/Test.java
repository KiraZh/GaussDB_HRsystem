import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException {
        System.out.println("--欢迎进入人事管理系统--");
        HRSystem.conn = DataBaseTools.getConnection();
        int tag, operation = 100;
        System.out.println("请输入测试内容，1员工，2M，3HR");
        Scanner s = new Scanner(System.in);
        int testTag = s.nextInt();
        String userPassword = "123";
        switch (testTag) {
            case 1:
                HRSystem.userName = "199";
                userPassword = "DGRANT199";
                break;
            case 2:
                HRSystem.userName = "100";
                userPassword = "SKING100";
                break;
            case 3:
                HRSystem.userName = "hr001";
                userPassword = "Abcd1234";
        }
        tag = HRSystem.login(HRSystem.conn, HRSystem.userName, userPassword);
        HRSystem.showMessage(tag);
        while (operation != 0) {
            if (tag == 1) {
                operation = Staff.welcomeUI();
            }
            else if(tag == 2) {
                operation = Manager.welcomeUI();
            }
            else if(tag == 3) {
                operation = HR.welcomeUI();
            }
        }
    }
}
