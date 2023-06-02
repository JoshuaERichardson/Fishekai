package com.fishekai.engine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPopup {
    private String helpMessage;
    private JButton helpButton;
    private JPanel buttonPanel;

    public HelpPopup(String helpMessage) {
        this.helpMessage = helpMessage;
        this.helpButton = new JButton("Help");
        this.buttonPanel = new JPanel();

        this.helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog helpDialog = createHelpDialog();
                helpDialog.setVisible(true);
            }
        });

        this.buttonPanel.add(this.helpButton);
    }

    private JDialog createHelpDialog() {
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

    public JPanel getButtonPanel() {
        return this.buttonPanel;
    }
}