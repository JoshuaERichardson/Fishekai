package com.fishekai.engine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays the information on how to play the game
 */
public class HelpPopup {
    private String helpMessage;

    public HelpPopup(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public JDialog createHelpDialog() {
        JDialog helpDialog = new JDialog();
        helpDialog.setTitle("Help");
        helpDialog.setModalityType(Dialog.ModalityType.MODELESS); // makes the dialog non-modal
        helpDialog.setSize(300, 450);

        JTextArea textArea = new JTextArea(helpMessage);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        helpDialog.add(scrollPane);

        return helpDialog;
    }
}