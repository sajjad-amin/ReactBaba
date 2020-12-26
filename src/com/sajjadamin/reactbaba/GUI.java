package com.sajjadamin.reactbaba;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GUI extends JFrame {

    public ReactionEngine engine;
    private JComboBox<String> reactionChooser, limitSetter;
    private JButton startButton;
    private boolean isStarted;
    private boolean running;

    GUI(){
        initComponent();
    }

    private void initComponent(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(this.getClass().getResource("image/icon.png"));
        Container container = this.getContentPane();
        container.setLayout(null);

        //define frame parameters
        this.setSize(370, 150);
        this.setTitle("React Baba 1.0");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setResizable(false);

        // label 1
        JLabel label1 = new JLabel("Choose react : ");
        label1.setBounds(20,20,90,20);
        container.add(label1);
        // label 2
        JLabel label2 = new JLabel("Set limit : ");
        label2.setBounds(20,60,90,20);
        container.add(label2);

        //Combo Box 1
        String[] reacts = {"Like", "Love", "Care", "Haha", "Wow", "Sad", "Angry"};
        reactionChooser = new JComboBox<>(reacts);
        reactionChooser.setBounds(110,20,100,25);
        container.add(reactionChooser);
        // Combo Box 2
        String[] limit = new String[101];
        limit[0] = "Unlimited";
        for (int i = 1; i < 101; i++){
            limit[i] = Integer.toString(i);
        }
        limitSetter = new JComboBox<>(limit);
        limitSetter.setBounds(110,60,100,25);
        container.add(limitSetter);

        //Start Button
        startButton = new JButton("Start");
        startButton.setBounds(230,20,100,25);
        container.add(startButton);

    }

    public void start() {
        this.setVisible(true);
        startButton.addActionListener(e -> {
            startButton.setRequestFocusEnabled(false);
            if (!isStarted){
                isStarted = true;
                running = true;
                startButton.setText("Stop");
                reactionChooser.setEnabled(false);
                limitSetter.setEnabled(false);
                engine = new ReactionEngine(reactionChooser.getSelectedIndex());
                // delay before starting react
                engine.delay(2000);
                // Create new thread and start reacting
                new Thread(() -> {
                    if (Objects.requireNonNull(limitSetter.getSelectedItem()).equals("Unlimited")){
                        while (running){
                            engine.startReact();
                        }
                    } else {
                        int limit = Integer.parseInt(limitSetter.getSelectedItem().toString());
                        for (int i = 1; i <= limit; i++){
                            engine.startReact();
                            if (!running){
                                return;
                            }
                        }
                        isStarted = false;
                        running = false;
                        startButton.setText("Start");
                        reactionChooser.setEnabled(true);
                        limitSetter.setEnabled(true);
                    }
                }).start();
            } else {
                // Stop reacting
                running = false;
                engine.delay(1600);
                isStarted = false;
                startButton.setText("Start");
                reactionChooser.setEnabled(true);
                limitSetter.setEnabled(true);
            }
        });
    }
}
