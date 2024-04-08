package CommandLine;

import java.util.List;

public class Errors {
    public Errors(List<String> errors) {

        System.out.println("Errors: ");

        if (!errors.isEmpty()) {
            for (String error : errors) {
                System.out.println("-" + error);
            }
        } else {
            System.out.println("No Errors Found.");
        }
    }
}
