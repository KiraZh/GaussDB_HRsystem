import java.sql.SQLException;
import java.util.Scanner;

public class HR {
    private static int operation;
    private static int userID = 3;
    public static int welcomeUI() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:退出管理系统");
        System.out.println("1:查看所有员工信息");
        System.out.println("2:查看单一员工信息");
        System.out.println("3:查看员工工资信息");
        System.out.println("4:查看部门信息");
        System.out.println("5:查看工作地点信息");
        System.out.println("6:查看员工工作信息");
        operation = scan.nextInt();
        switch (operation){
            case 0:return 0;
            case 1:staffsUI();break;
            case 2:staffUI();break;
            case 3:salaryUI();break;
            case 4:sectionsUI();break;
            case 5:placesUI();break;
            case 6:employmentUI();break;
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
        DataBaseTools.showSalaryInfo(userID);
        System.out.println("0:返回上一级");
        Scanner s = new Scanner(System.in);
        operation = s.nextInt();
        switch (operation){
            case 0:welcomeUI();
        }
        return;
    }
    private static void sectionsUI() throws SQLException {
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:返回上一级");
        System.out.println("1:查询部门信息");
        System.out.println("2:显示所有部门信息");
        System.out.println("3:修改部门名称");
        Scanner s = new Scanner(System.in);
        operation = s.nextInt();
        switch (operation){
            case 0:welcomeUI();break;
            case 1:
                DataBaseTools.showSectionInfo();
                sectionsUI();
                break;
            case 2:
                DataBaseTools.showSectionsInfo();
                sectionsUI();
                break;
            case 3:
                DataBaseTools.updateSectionsName();
                sectionsUI();
        }
        return;
    }
    private static void placesUI() throws SQLException {
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:返回上一级");
        System.out.println("1:查询工作地点信息");
        System.out.println("2:显示所有工作地点信息");
        System.out.println("3:增加新工作地点");
        Scanner s = new Scanner(System.in);
        operation = s.nextInt();
        switch (operation){
            case 0:welcomeUI();break;
            case 1:
                DataBaseTools.showPlaceInfo();
                placesUI();
                break;
            case 2:
                DataBaseTools.showPlacesInfo();
                placesUI();
                break;
            case 3:
                DataBaseTools.insertPlace();
                placesUI();
        }
        return;
    }
    private static void employmentUI() throws SQLException {
        System.out.print("---------------------\n输入数字选择要进行的操作:\n");
        System.out.println("0:返回上一级");
        System.out.println("1:查询员工雇佣信息");
        Scanner s = new Scanner(System.in);
        operation = s.nextInt();
        switch (operation){
            case 0:welcomeUI();break;
            case 1:
                DataBaseTools.showEmploymentsInfo();
                employmentUI();
        }
        return;
    }
}
