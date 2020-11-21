package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
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
     * TODO: Starting from the application in mvcio:
     */
    private final JFrame frame = new JFrame();
    //
    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);
        final JTextArea txt = new JTextArea();
        panel.add(txt);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Button Action
        save.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.write(txt.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
     /* 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     */
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel.add(panel2, BorderLayout.NORTH);
        final JButton browse = new JButton("Browse");
        panel2.add(browse, BorderLayout.LINE_END);
        final JTextField txt2 = new JTextField(controller.getPath());
        panel2.add(txt2, BorderLayout.NORTH);
        frame.setContentPane(panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     /* 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     */
     /* 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     */
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                final Integer result = fc.showSaveDialog(frame);
                if (result.equals(JFileChooser.APPROVE_OPTION)) {
                    controller.setFile(fc.getSelectedFile());
                    txt2.setText(controller.getPath());
                } else if (result.equals(JFileChooser.CANCEL_OPTION)) {
                    return;
                } else {
                    JOptionPane.showMessageDialog(frame, result, "problem occur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
     /* 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
        final JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(txt2, BorderLayout.CENTER);
        upperPanel.add(browse, BorderLayout.LINE_END);
        panel2.add(upperPanel, BorderLayout.NORTH);
        //
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setContentPane(panel2);
        frame.setVisible(true);
    }
    //
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser(new Controller());
    }

}
