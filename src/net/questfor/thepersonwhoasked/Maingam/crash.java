package net.questfor.thepersonwhoasked.Maingam;
import net.questfor.thepersonwhoasked.Main;
import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

import static net.questfor.thepersonwhoasked.Main.concole;

public class crash {
    //if the System counters a error, it will call this function to create a crash report for you to report it to @bruhkid2345 at https://discord.gg/tRva2AM2Gk
    public static void main(Exception e) {
        try {
            concole.write("crash report!");
            concole.newLine();
            concole.write("oh no? what happened?");
            concole.newLine();
            concole.write("concole logs");
            concole.newLine();
            concole.write(String.valueOf(Main.baos));
            concole.newLine();
            concole.write("Cause");
            concole.newLine();
            concole.write("----------------------------------------------------");
            concole.newLine();
            concole.write(Arrays.toString(e.getStackTrace()));
            concole.newLine();
            concole.write(e.getMessage());
            concole.newLine();
            concole.write(String.valueOf(e.getCause()));
            concole.newLine();
            concole.write(e.getLocalizedMessage());
            concole.newLine();
            concole.write(e.hashCode());
            concole.newLine();
            concole.write(String.valueOf(e.getClass()));
            concole.close();
            JOptionPane.showMessageDialog(null, "An unexpected error has occured! save your data as your game might not function after leaving this msg \n please go to https://discord.gg/tRva2AM2Gk and message @bruhkid2345 your error message, check the error report!");
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("C:\\Windows\\notepad.exe latestcrashreport.txt");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "A Fatal Exception has occured and the writer couldnt make the crash report! \n please go to https://discord.gg/tRva2AM2Gk and message @bruhkid2345 how you got the error");
        }
    }
}
