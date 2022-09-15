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
    public static void loadingProcess(String loadMsg, int loops){
        System.out.print(loadMsg+"\t");
        try {
            Thread.sleep(500);
            System.out.print("|"); Thread.sleep(200);System.out.print("\b");
            for (int i = 1; i <= loops; i++) {
                System.out.print("/"); Thread.sleep(200);System.out.print("\b");
                System.out.print("-"); Thread.sleep(250);System.out.print("\b");
                System.out.print("\\");Thread.sleep(200);System.out.print("\b");
                System.out.print("|"); Thread.sleep(200);System.out.print("\b");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        slowClearMsg(loadMsg);
    }
    public static void loadingProcess(String loadMsg){
        loadingProcess(loadMsg, 4);
    }
    public static void endingProcess(String finishMsg){
        slowClearMsgAfterPrint(finishMsg);
    }
    public static void invalid(String invalidMsg){
        slowClearMsgAfterPrint(invalidMsg);
    }
    public static void printThenClear(String msg){
        System.out.print(msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String repeatedClear = new String(new char[msg.length()]).replace('\0', '\b');
        System.out.print(repeatedClear);
    }
    public static void slowClearMsgAfterPrint(String msg) {
        System.out.print(msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        slowClearMsg(msg);
    }
    public static void slowClearMsg(String msg){
        int speed = (msg.length()<20) ? 75:30;
        try {
        for (int i = 0; i <= msg.length(); i++) {
            System.out.print("\b");
                Thread.sleep(speed);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void display(TypePrint TP, String msg){
        switch (TP){
            case TITLE -> printTitle("<#- "+msg+" -#>");
            case LOADING -> loadingProcess("\t\t<:- " + msg + " -:>");
            case FINISH -> endingProcess("<%- " + msg + " -%>");
            case INVALID -> invalid("<!- " + msg + " -!>");
        }
    }
    public static void display(TypePrint TP, String msg, int speed){
        switch (TP){
            case TITLE -> printTitle("<#- "+msg+" -#>");
            case LOADING -> loadingProcess("\t\t<:- " + msg + " -:>", speed);
            case FINISH -> endingProcess("<%- " + msg + " -%>");
            case INVALID -> invalid("<!- " + msg + " -!>");
        }
    }
    public static void displayMenuOptions(String[] optionsLabel){
        System.out.println("<!> Choose an Option Between 1 and " + optionsLabel.length);
        for (int i = 0; i < optionsLabel.length; i++) {
            System.out.println((i+1)+". "+ optionsLabel[i]);
        }
    }

    public static Page decideBetweenOptions(Page[] optionsPages){
        System.out.print("-> Choose: ");
        String decision_wait;
        int decision;
        try {
            decision_wait = input.nextLine();
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
    public static int decideBetweenOptions(int options){
        System.out.print("-> Choose: ");
        String decision_wait;
        int decision;
        try {
            decision_wait = input.nextLine();
            decision = Integer.parseInt(decision_wait);
        }
        catch (NumberFormatException e)
        {
            display(TypePrint.INVALID, "Input should be number in range [1"+","+options+"] or -1");
            return decideBetweenOptions(options);
        }
        if (decision != -1 && (decision < 1 || decision > options))
        {
            display(TypePrint.INVALID, "Input should be number in range [1"+","+options+"] or -1");
            return decideBetweenOptions(options);
        }
        return decision;
    }
}

