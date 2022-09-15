public class OutputOperations {
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
        for (int i = 0; i <= msg.length(); i++) {
            System.out.print("\b");
            Thread.sleep(75);
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
}

