package CommandLine;

public class Help {

    public Help() {
        System.out.println("Commands: ");
        System.out.println("- exit = Stops All Tasks And Closes Program.");
        System.out.println("- stop <taskName> = Stops Specifed Task.");
        System.out.println("- list = Lists Current Running Jobs.");
        System.out.println("- errors = Lists Current Errors.");

    }
}
