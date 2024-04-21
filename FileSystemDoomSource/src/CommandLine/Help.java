package CommandLine;

public class Help {

    public Help() {
        System.out.println("Commands: ");
        System.out.println("- exit = Stops All Tasks And Closes Program.");
        System.out.println("- stop <taskName> = Stops Specified Task.");
        System.out.println("- start <taskName> = Starts Specified Task.");
        System.out.println("- list/ls = Lists Jobs And Their Status.");
        System.out.println("- errors = Lists Current Errors.");
        System.out.println("- force stop <jobName> = Forcibly Kills A Job.");
    }
}
