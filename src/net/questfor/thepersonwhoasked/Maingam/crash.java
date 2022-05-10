package net.questfor.thepersonwhoasked.Maingam;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class crash {
    //if the System counters a error, it will call this function to create a crash report for you to report it to @bruhkid2345 at https://discord.gg/tRva2AM2Gk
    public static void main(Exception e) {
        try {
            BufferedWriter CrashWriter = new BufferedWriter(new FileWriter("latestcrashreport.txt"));
            CrashWriter.write("crash report!");
            CrashWriter.newLine();
            CrashWriter.write("oh no? what happened?");
            CrashWriter.newLine();
            CrashWriter.write("----------------------------------------------------");
            CrashWriter.newLine();
            CrashWriter.write(Arrays.toString(e.getStackTrace()));
            CrashWriter.newLine();
            CrashWriter.write(e.getMessage());
            CrashWriter.newLine();
            CrashWriter.write(String.valueOf(e.getCause()));
            CrashWriter.close();
            JOptionPane.showMessageDialog(null, "An unexpected error has occured! save your data as your game might not function after leaving this msg \n please go to https://discord.gg/tRva2AM2Gk and message @bruhkid2345 your error message, check the error report!");
        } catch (IOException ex) {
            main(ex);
        }

    }
}
