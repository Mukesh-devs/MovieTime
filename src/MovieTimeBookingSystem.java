import features.employee.EmployeeView;
import util.Util;

public class MovieTimeBookingSystem {
    public void init() {

    }
    void askRegisterLogin() {
        System.out.println("1. Employee");
        System.out.println("2. User");
        System.out.println("3. Exit");
        int homeChoice = Util.choice();
        switch (homeChoice) {
            case 1 -> {
                new EmployeeView().init();
            }
            case 2 -> {
                init();
            }
            case 3 -> {
                System.exit(0);
            }
            default -> {
                Util.printError("Enter the Correct the choice");
                askRegisterLogin();
            }
        }
    }
}
