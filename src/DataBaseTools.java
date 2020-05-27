import java.sql.*;
import java.util.Scanner;

public class DataBaseTools {
    public static Connection getConnection(String userName, String userPassword) {
        String driver = "com.huawei.gauss.jdbc.ZenithDriver";
        String sourceURL = "jdbc:zenith:@192.168.3.15:1888?useSSL=true";
        Connection con = null;
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            con = DriverManager.getConnection(sourceURL, userName, userPassword);
            //System.out.println("---连接中---");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return con;
    }
    public static Connection getConnection() {
        String userName = "hr";
        String userPassword = "Abcd1234";
        String driver = "com.huawei.gauss.jdbc.ZenithDriver";
        String sourceURL = "jdbc:zenith:@192.168.3.15:1888?useSSL=true";
        Connection con = null;
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            con = DriverManager.getConnection(sourceURL, userName, userPassword);
            //System.out.println("---连接中---");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return con;
    }
    public static void showPersonalInfo() throws SQLException {
        PreparedStatement sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs WHERE staff_id = ?");
        sql.setString(1,HRSystem.userName);
        ResultSet result = sql.executeQuery();
        if(result.next()){
            System.out.println("staff_id :"+result.getString(1));
            System.out.println("name: "+result.getString(2)+" "+result.getString(3));
            System.out.println("email: "+result.getString(4));
            System.out.println("phone number: "+result.getString(5));
            System.out.println("hire date: "+result.getString(6));
            System.out.println("employment ID: "+result.getString(7));
            System.out.println("salary: "+result.getString(8));
            //System.out.println("COMMISSION_PCT: "+result.getString(9));
            System.out.println("manager ID: "+result.getString(10));
            System.out.println("section ID: "+result.getString(11));
            //System.out.println("GRADUATED_NAME: "+result.getString(12));
            System.out.println("password: "+result.getString(13));
        }
        sql.close();
        return;
    }
    public static void updatePhoneNumber() throws SQLException {
        Scanner s = new Scanner(System.in);
        String newNumber;
        System.out.print("请输入新的电话号码:");
        newNumber = s.nextLine();
        PreparedStatement pstmt1 = HRSystem.conn.prepareStatement("UPDATE staffs SET PHONE_NUMBER=? WHERE staff_id = ?");
        pstmt1.setString(1,newNumber);
        pstmt1.setString(2,HRSystem.userName);
        PreparedStatement pstmt2 = HRSystem.conn.prepareStatement("SELECT phone_number FROM staffs WHERE staff_id = ?");
        pstmt2.setString(1,HRSystem.userName);
        pstmt1.executeUpdate();
        ResultSet result = pstmt2.executeQuery();
        if(result.next()){
            System.out.println("电话号码修改为： "+result.getString(1));
        }
        pstmt1.close();
        pstmt2.close();
        return;
    }
    public static void showStaffsInfo(int userID) throws SQLException {
        System.out.println("1:员工编号升序排列");
        System.out.println("2:员工工资降序排列");
        Scanner s = new Scanner(System.in);
        int op = s.nextInt();
        PreparedStatement sql = null;
        if(userID == 2) {
            if (op == 1) {
                sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs WHERE section_id IN" +
                        "(SELECT section_id FROM staffs WHERE staff_id = ?)" +
                        "ORDER BY staff_id");
            } else if (op == 2) {
                sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs WHERE section_id IN" +
                        "(SELECT section_id FROM staffs WHERE staff_id = ?)" +
                        "ORDER BY salary DESC");
            } else return;
            sql.setString(1, HRSystem.userName);
        }
        else if(userID == 3){
            if (op == 1) {
                sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs ORDER BY staff_id");
            } else if (op == 2) {
                sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs ORDER BY salary DESC");
            } else return;
        }
        ResultSet result = sql.executeQuery();
        while(result.next()){
            System.out.println("staff_id :"+result.getString(1));
            System.out.println("name: "+result.getString(2)+" "+result.getString(3));
            System.out.println("email: "+result.getString(4));
            System.out.println("phone number: "+result.getString(5));
            System.out.println("hire date: "+result.getString(6));
            System.out.println("employment ID: "+result.getString(7));
            System.out.println("salary: "+result.getString(8));
            //System.out.println("COMMISSION_PCT: "+result.getString(9));
            System.out.println("manager ID: "+result.getString(10));
            System.out.println("section ID: "+result.getString(11));
            //System.out.println("GRADUATED_NAME: "+result.getString(12));
            //System.out.println("password: "+result.getString(13));
            System.out.println("---------------------");
        }
        sql.close();
        return;
    }
    public static void showStaffInfo(int userID) throws SQLException {
        System.out.println("1:根据编号查询");
        System.out.println("2:根据名字查询");
        Scanner s = new Scanner(System.in);
        int op = s.nextInt();
        PreparedStatement sql = null;
        if (userID==2) {
            if (op == 1) {
                System.out.println("请输入要查询的员工的ID");
                sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs WHERE section_id IN" +
                        "(SELECT section_id FROM staffs WHERE staff_id = ?)" +
                        "AND staff_id = ?");
                sql.setString(1, HRSystem.userName);
                s = new Scanner(System.in);
                String id = s.nextLine();
                sql.setString(2, id);
            } else if (op == 2) {
                System.out.println("请输入要查询的员工的名字");
                sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs WHERE section_id IN" +
                        "(SELECT section_id FROM staffs WHERE staff_id = ?)" +
                        "AND (first_name = ? OR last_name = ?)");
                sql.setString(1, HRSystem.userName);
                s = new Scanner(System.in);
                String name = s.nextLine();
                sql.setString(2, name);
                sql.setString(3, name);
            } else return;
        }
        else if (userID==3){
            if (op == 1) {
                System.out.println("请输入要查询的员工的ID");
                sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs WHERE staff_id = ?");
                s = new Scanner(System.in);
                String id = s.nextLine();
                sql.setString(1, id);
            } else if (op == 2) {
                System.out.println("请输入要查询的员工的名字");
                sql = HRSystem.conn.prepareStatement("SELECT * FROM staffs WHERE (first_name = ? OR last_name = ?)");
                s = new Scanner(System.in);
                String name = s.nextLine();
                sql.setString(1, name);
                sql.setString(2, name);
            } else return;
        }
        ResultSet result = sql.executeQuery();
        if(result.next()) {
            System.out.println("staff_id :" + result.getString(1));
            System.out.println("name: " + result.getString(2) + " " + result.getString(3));
            System.out.println("email: " + result.getString(4));
            System.out.println("phone number: " + result.getString(5));
            System.out.println("hire date: " + result.getString(6));
            System.out.println("employment ID: " + result.getString(7));
            System.out.println("salary: " + result.getString(8));
            //System.out.println("COMMISSION_PCT: "+result.getString(9));
            System.out.println("manager ID: " + result.getString(10));
            System.out.println("section ID: " + result.getString(11));
            //System.out.println("GRADUATED_NAME: "+result.getString(12));
            //System.out.println("password: "+result.getString(13));
            System.out.println("---------------------");
        }
        else
            System.out.println("未查询到相关员工信息");
        sql.close();
        return;
    }
    public static void showSalaryInfo() throws SQLException {
        PreparedStatement sql = null;
        sql = HRSystem.conn.prepareStatement(
                "SELECT max(salary),min(salary),avg(salary) FROM staffs " +
                        "where section_id IN " +
                        "(SELECT section_id FROM staffs WHERE staff_id = ?)" +
                        "GROUP BY section_id");
        sql.setString(1,HRSystem.userName);
        ResultSet result = sql.executeQuery();
        while(result.next()){
            System.out.println("maxim salary: " + String.format("%.2f",result.getDouble(1)));
            System.out.println("minim salary: " + String.format("%.2f",result.getDouble(2)));
            System.out.println("average salary: " + String.format("%.2f",result.getDouble(3)));
        }
        sql.close();
        return;
    }
    public static void showSalaryInfo(int userID) throws SQLException {
        if (userID != 3)
            return;
        PreparedStatement sql = null;
        //max salary
        sql = HRSystem.conn.prepareStatement(
                "SELECT max(salary),min(salary),avg(salary),section_id " +
                "FROM staffs GROUP BY section_id");
        ResultSet result = sql.executeQuery();
        while(result.next()){
            System.out.println("section ID: " + result.getString(4));
            System.out.println("maxim salary: " + String.format("%.2f",result.getDouble(1)));
            System.out.println("minim salary: " + String.format("%.2f",result.getDouble(2)));
            System.out.println("average salary: " + String.format("%.2f",result.getDouble(3)));
            System.out.println("---------------------");
        }
        sql.close();
        return;
    }
    public static void showSectionInfo() throws SQLException {
        System.out.println("请输入需要查看的部门的ID");
        Scanner s = new Scanner(System.in);
        String sectionID = s.nextLine();
        PreparedStatement sql =null;
        sql = HRSystem.conn.prepareStatement(
                "SELECT * FROM sections WHERE section_id = ?");
        sql.setString(1,sectionID);
        ResultSet result = sql.executeQuery();
        if(result.next()){
            System.out.println("section ID: " + result.getString(1));
            System.out.println("section name: " + result.getString(2));
            System.out.println("manager id: " + result.getString(3));
            System.out.println("place id: " + result.getString(4));
            System.out.println("---------------------");
        }
        else {
            System.out.println("部门不存在，请检查输入");
        }
        sql.close();
        return;
    }
    public static void showSectionsInfo() throws SQLException {
        PreparedStatement sql =null;
        sql = HRSystem.conn.prepareStatement(
                "SELECT * FROM sections");
        ResultSet result = sql.executeQuery();
        while(result.next()){
            System.out.println("section ID: " + result.getString(1));
            System.out.println("section name: " + result.getString(2));
            System.out.println("manager id: " + result.getString(3));
            System.out.println("place id: " + result.getString(4));
            System.out.println("---------------------");
        }
        sql.close();
        return;
    }
    public static void updateSectionsName() throws SQLException {
        System.out.println("请输入需要修改的部门的ID");
        Scanner s = new Scanner(System.in);
        String sectionID = s.nextLine();
        System.out.println("请输入修改后的部门名称");
        s = new Scanner(System.in);
        String sectionName = s.nextLine();
        PreparedStatement sql = null;
        sql = HRSystem.conn.prepareStatement(
                "UPDATE sections SET section_name=? WHERE section_id = ?");
        sql.setString(1,sectionName);
        sql.setString(2,sectionID);
        sql.executeUpdate();
        sql.close();
        sql = HRSystem.conn.prepareStatement(
                "SELECT section_name FROM sections WHERE section_id = ?");
        sql.setString(1,sectionID);
        ResultSet result = sql.executeQuery();
        while (result.next()){
            System.out.println("部门名称已修改为: "+result.getString(1));
        }
        sql.close();
        return;
    }
    public static void showPlaceInfo() throws SQLException {
        System.out.println("请输入需要查看的工作地点的ID");
        Scanner s = new Scanner(System.in);
        String sectionID = s.nextLine();
        PreparedStatement sql =null;
        sql = HRSystem.conn.prepareStatement(
                "SELECT * FROM places WHERE place_id = ?");
        sql.setString(1,sectionID);
        ResultSet result = sql.executeQuery();
        if(result.next()){
            System.out.println("place ID: " + result.getString(1));
            System.out.println("address: " + result.getString(2));
            System.out.println("postal code: " + result.getString(3));
            System.out.println("city: " + result.getString(4));
            System.out.println("state province: " + result.getString(5));
            System.out.println("state id: " + result.getString(6));
            System.out.println("---------------------");
        }
        else {
            System.out.println("地点不存在，请检查输入");
        }
        sql.close();
        return;
    }
    public static void showPlacesInfo() throws SQLException {
        PreparedStatement sql =null;
        sql = HRSystem.conn.prepareStatement(
                "SELECT * FROM places");
        ResultSet result = sql.executeQuery();
        while(result.next()){
            System.out.println("place ID: " + result.getString(1));
            System.out.println("address: " + result.getString(2));
            System.out.println("postal code: " + result.getString(3));
            System.out.println("city: " + result.getString(4));
            System.out.println("state province: " + result.getString(5));
            System.out.println("state id: " + result.getString(6));
            System.out.println("---------------------");
        }
        sql.close();
        return;
    }
    public static void insertPlace() throws SQLException {
        PreparedStatement sql =null;
        Scanner s = new Scanner(System.in);
        System.out.println("please enter info in the following order:");
        System.out.println("place ID\naddress\npostal code\ncity\nstate province\nstate id");
        String placeID = s.nextLine();
        String address = s.nextLine();
        String postalCode = s.nextLine();
        String city = s.nextLine();
        String state = s.nextLine();
        String stateID = s.nextLine();
        sql = HRSystem.conn.prepareStatement(
                "INSERT INTO places values(?,?,?,?,?,?)");
        sql.setString(1,placeID);
        sql.setString(2,address);
        sql.setString(3,postalCode);
        sql.setString(4,city);
        sql.setString(5,state);
        sql.setString(6,stateID);
        if(sql.executeUpdate()==1){
            System.out.println("增加数据成功");
        }
        else{
            System.out.println("增加数据失败");
        }
        sql.close();
        return;
    }
    public static void showEmploymentsInfo() throws SQLException {
        System.out.println("请输入需要查看的员工的ID");
        Scanner s = new Scanner(System.in);
        String staffID = s.nextLine();
        PreparedStatement sql =null;
        sql = HRSystem.conn.prepareStatement(
                "SELECT * FROM employment_history " +
                        "WHERE staff_id = ?");
        sql.setString(1,staffID);
        ResultSet result = sql.executeQuery();

        if(result.next()){
            System.out.println("staff ID: " + result.getString(1));
            System.out.println("time : " + result.getString(2) +
                    " to " + result.getString(3));
            System.out.println("employment ID: " + result.getString(4));
            System.out.println("section id: " + result.getString(5));
            System.out.println("---------------------");
        }
        else {
            System.out.println("信息不存在，请检查输入");
        }
        sql.close();
        return;
    }
}
