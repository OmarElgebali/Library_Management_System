import java.util.Scanner;

public class OutputOperations {
    public static Scanner input = new Scanner(System.in);
    public static void printTitle(String title)
    {
        System.out.println(title);
        for (int i = 0; i < title.length(); i++) {
            System.out.print("#");
        }
        System.out.println();
    }
    public static void loadingProcess(String loadMsg) throws InterruptedException {
        System.out.print(loadMsg+"\t");
        Thread.sleep(500);
        System.out.print("|"); Thread.sleep(200);System.out.print("\b");
        for (int i = 1; i <= 4; i++) {
            System.out.print("/"); Thread.sleep(200);System.out.print("\b");
            System.out.print("-"); Thread.sleep(250);System.out.print("\b");
            System.out.print("\\");Thread.sleep(200);System.out.print("\b");
            System.out.print("|"); Thread.sleep(200);System.out.print("\b");
        }
        slowClearMsg(loadMsg);
    }
    public static void endingProcess(String finishMsg) throws InterruptedException {
        slowClearMsgAfterPrint(finishMsg);
    }
    public static void invalid(String invalidMsg) throws InterruptedException {
        slowClearMsgAfterPrint(invalidMsg);
    }
    public static void printThenClear(String msg) throws InterruptedException {
        System.out.print(msg);
        Thread.sleep(1000);
        String repeatedClear = new String(new char[msg.length()]).replace('\0', '\b');
        System.out.print(repeatedClear);
    }
    public static void slowClearMsgAfterPrint(String msg) throws InterruptedException {
        System.out.print(msg);
        Thread.sleep(1000);
        slowClearMsg(msg);
    }
    public static void slowClearMsg(String msg) throws InterruptedException {
        int speed = (msg.length()<20) ? 75:30;
        for (int i = 0; i <= msg.length(); i++) {
            System.out.print("\b");
            Thread.sleep(speed);
        }
    }

    public static void display(TypePrint TP, String msg) throws InterruptedException {
        switch (TP){
            case TITLE -> printTitle("<#- "+msg+" -#>");
            case LOADING -> loadingProcess("\t\t<:- " + msg + " -:>");
            case FINISH -> endingProcess("<%- " + msg + " -%>");
            case INVALID -> invalid("<!- " + msg + " -!>");
        }
    }
    public static void displayMenuOptions(String[] optionsLabel){
        for (int i = 0; i < optionsLabel.length; i++) {
            System.out.println((i+1)+". "+ optionsLabel[i]);
        }
    }

    public static Page decideBetweenOptions(Page[] optionsPages) throws InterruptedException {
        System.out.print("-> Choose: ");
        String decision_wait;
        int decision;
        try {
            decision_wait = input.next();
            decision = Integer.parseInt(decision_wait);
        }
        catch (NumberFormatException e)
        {
            display(TypePrint.INVALID, "Input should be number in range [1"+","+optionsPages.length+"]");
            return decideBetweenOptions(optionsPages);
        }
        if (decision < 1 || decision > optionsPages.length)
        {
            display(TypePrint.INVALID, "Input should be number in range [1"+","+optionsPages.length+"]");
            return decideBetweenOptions(optionsPages);
        }
        return optionsPages[decision-1];
    }
}

