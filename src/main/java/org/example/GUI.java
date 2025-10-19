package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private AppManager manager;
    private JTextArea displayArea;

    public GUI(AppManager manager) {
        this.manager = manager;
        setupGUI();
    }

    private void setupGUI() {
        setTitle("Game Shop Inventory");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JButton showInventoryBtn = new JButton("Show Inventory");
        showInventoryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayInventory();
            }
        });

        add(showInventoryBtn, BorderLayout.SOUTH);
    }

    private void displayInventory() {
        StringBuilder sb = new StringBuilder();
        for (Game g : manager.getInventory().getGames()) {
            sb.append(g.toString()).append("\n");
        }
        displayArea.setText(sb.toString());
    }
}
