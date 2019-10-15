package start;


import start.Runnable.dobles;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;

public class openCamara {

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("titulo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("APP OPEN");
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        String path= FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        path=path.replaceAll("Documents","Pictures");
        path=path+"\\camara";

/*
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        }); */


        camaraController camara = new camaraController();
        camara.camaraOpen(path,"0");

        dobles d= new dobles();
        d.ejecutar(path);

    }
}

