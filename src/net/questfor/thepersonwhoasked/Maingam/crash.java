package net.questfor.thepersonwhoasked.Maingam;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class crash {
    public static void main(Exception e) {
        JOptionPane.showMessageDialog(null, "An unexpected error has occured! save your data as your game might not function after leaving this msg \n please go to https://discord.gg/GA8tJwSNYm and message @bruhkid2345 your error message, check the error report!");
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
        } catch (IOException ex) {
            main(ex);
        }

    }
}
