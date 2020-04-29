import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HRSystem {
    public static Connection conn;
    public static String userName;
    public static int login(Connection conn,String name, String password) throws SQLException {
        int loginCode;
        if(name.equals("hr001")){
            if(password.equals("Abcd1234"))
                loginCode = 3;
            else
                loginCode = 0;
            return loginCode;
        }
        PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM staffs WHERE staff_id = ?");
        PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM staffs WHERE manager_id = ?");
        pstmt1.setString(1,name);
        pstmt2.setString(1,name);
        ResultSet rs1 = pstmt1.executeQuery();
        ResultSet rs2 = pstmt2.executeQuery();
        if (!rs1.next())
            loginCode = -1;
        else {
            if (rs1.getString("password").equals(password)){
                if(!rs2.next())
                    loginCode = 1;
                else
                    loginCode = 2;
            }
            else
                loginCode = 0;
        }
        pstmt1.close();
        return loginCode;
    }

    public static void showMessage(int code){
        switch (code){
            case -1:
                System.out.println("该用户不存在");
                break;
            case 0:
                System.out.println("密码错误");
                break;
            case 1:
                System.out.println("员工登陆成功");
                break;
            case 2:
                System.out.println("部门经理登陆成功");
                break;
            case 3:
                System.out.println("人事经理登陆成功");
        }
    }

    public static void main(String[] args) throws SQLException {
        conn = DataBaseTools.getConnection();
        int tag,operation=100;
        while(operation!=-1) {
            System.out.println("--欢迎进入人事管理系统--");
            Scanner s = new Scanner(System.in);
            System.out.println("请输入用户名");
            userName = s.nextLine();
            System.out.println("请输入密码");
            String userPassword = s.nextLine();
            tag = login(conn, userName, userPassword);
            showMessage(tag);
            if (tag == 1) {
                operation = Staff.welcomeUI();
            } else if (tag == 2) {
                operation = Manager.welcomeUI();
            } else if (tag == 3) {
                operation = HR.welcomeUI();
            }
        }
    }
}
