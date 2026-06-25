import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuFrame extends JFrame {

    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Tic-Tac-Toe - Main Menu");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 200, 100));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        panel.add(lblTitle, gbc);

        JLabel lblWelcome = new JLabel("Halo, " + currentPlayer.getUsername() + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 14));
        lblWelcome.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 1;
        panel.add(lblWelcome, gbc);

        btnStartGame = createButton("Mulai Game", new Color(0, 150, 200));
        gbc.gridy = 2;
        panel.add(btnStartGame, gbc);

        btnStatistics = createButton("Statistik Saya", new Color(150, 100, 200));
        gbc.gridy = 3;
        panel.add(btnStatistics, gbc);

        btnTopScorers = createButton("Top 5 Scorers", new Color(200, 150, 0));
        gbc.gridy = 4;
        panel.add(btnTopScorers, gbc);

        btnExit = createButton("Keluar", new Color(200, 50, 50));
        gbc.gridy = 5;
        panel.add(btnExit, gbc);

        add(panel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah kamu yakin ingin keluar?",
                "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(250, 40));
        return btn;
    }
}