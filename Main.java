package it.tdt.edu.vn;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ViewApplication viewApplication = new ViewApplication();
        viewApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewApplication.setSize(900, 700);
        viewApplication.setVisible(true);
    }
}
