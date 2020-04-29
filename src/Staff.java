import java.sql.*;
import java.util.Scanner;

public class Staff {
    private static int operation;
    public static int welcomeUI() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:退出管理系统");
        System.out.println("1:查看个人信息");
//        System.out.println("2:修改电话号码");
        operation = s.nextInt();
        switch (operation){
            case 0:return 0;
            case 1:
                personalInfoUI();
//            break;
//            case 2:updateUI();
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
        switch (operation){
            case 0:welcomeUI();break;
            case 1:
                DataBaseTools.updatePhoneNumber();
                personalInfoUI();
        }
        return;
    }
    //    private static void showInfo() throws SQLException {
//        PreparedStatement staffInfo = HRSystem.conn.prepareStatement("SELECT * FROM staffs WHERE staff_id = ?");
//        staffInfo.setString(1,HRSystem.userName);
//        ResultSet result = staffInfo.executeQuery();
//        if(result.next()){
//            System.out.println("staff_id :"+result.getString(1));
//            System.out.println("name: "+result.getString(2)+" "+result.getString(3));
//            System.out.println("email: "+result.getString(4));
//            System.out.println("phone number: "+result.getString(5));
//            System.out.println("hire date: "+result.getString(6));
//            System.out.println("employment ID: "+result.getString(7));
//            System.out.println("salary: "+result.getString(8));
//            //System.out.println("COMMISSION_PCT: "+result.getString(9));
//            System.out.println("manager ID: "+result.getString(10));
//            System.out.println("section ID: "+result.getString(11));
//            //System.out.println("GRADUATED_NAME: "+result.getString(12));
//            System.out.println("password: "+result.getString(13));
//        }
//        staffInfo.close();
//        return;
//    }
//    private static void updatePhoneNumber() throws SQLException {
//        Scanner scan = new Scanner(System.in);
//        String newNumber;
//        System.out.print("请输入新的电话号码:");
//        newNumber = scan.nextLine();
//        PreparedStatement pstmt1 = HRSystem.conn.prepareStatement("UPDATE staffs SET PHONE_NUMBER=? WHERE staff_id = ?");
//        pstmt1.setString(1,newNumber);
//        pstmt1.setString(2,HRSystem.userName);
//        PreparedStatement pstmt2 = HRSystem.conn.prepareStatement("SELECT phone_number FROM staffs WHERE staff_id = ?");
//        pstmt2.setString(1,HRSystem.userName);
//        pstmt1.executeUpdate();
//        ResultSet result = pstmt2.executeQuery();
//        if(result.next()){
//            System.out.println("电话号码修改为： "+result.getString(1));
//        }
//        pstmt1.close();
//        pstmt2.close();
//        return;
//    }
}
