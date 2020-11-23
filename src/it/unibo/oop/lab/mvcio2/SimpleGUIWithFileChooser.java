package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * 
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame("My second Java graphical interface");
    private static final int PROPORTION = 3;

    public SimpleGUIWithFileChooser() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.getContentPane().add(panel);
        //Area
        final JTextArea area = new JTextArea();
        panel.add(area, BorderLayout.CENTER);
        //save button
        final JButton saveButton = new JButton("Save");
        panel.add(saveButton, BorderLayout.SOUTH);
        final Controller controller = new Controller();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final int n = JOptionPane.showConfirmDialog(frame,
                        "Do you want to save the file??",
                        "Saving...", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    final String text = area.getText();
                    try {
                        controller.writeToFile(text);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(frame, "Error");
                    }
                }
            }
        });
        frame.add(panel);

        final JPanel panel2 = new JPanel();
        panel.add(panel2, BorderLayout.NORTH);
        panel2.setLayout(new BorderLayout());
        //Path text
        final JTextField path = new JTextField();
        panel2.add(path, BorderLayout.CENTER);
        //Browse button
        final JButton browse = new JButton("Browse...");
        panel2.add(browse, BorderLayout.LINE_END);
        path.setEnabled(false);
        path.setText(controller.getCurrentFile().getPath());
        path.setDisabledTextColor(Color.black);
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser filechooser = new JFileChooser();
                filechooser.setSelectedFile(controller.getCurrentFile());
                final int n = filechooser.showSaveDialog(browse);
                if (n == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(filechooser.getSelectedFile());
                    path.setText(controller.getCurrentFile().getPath());
                }
                if (n == JFileChooser.ERROR_OPTION) {
                    JOptionPane.showMessageDialog(filechooser, "Error");
                }
            }
        });

    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }

}
