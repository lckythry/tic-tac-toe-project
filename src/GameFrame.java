import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private JLabel lblScore;
    private boolean gameOver;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();
        this.gameOver = false;

        setTitle("Tic-Tac-Toe - Game");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        // Panel atas - info pemain
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(30, 30, 30));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblPlayer = new JLabel("Pemain: " + currentPlayer.getUsername() + "  (X)", SwingConstants.CENTER);
        lblPlayer.setFont(new Font("Arial", Font.BOLD, 16));
        lblPlayer.setForeground(new Color(0, 200, 100));
        topPanel.add(lblPlayer);

        lblStatus = new JLabel("Giliran kamu! Klik sel untuk bermain.", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 13));
        lblStatus.setForeground(Color.LIGHT_GRAY);
        topPanel.add(lblStatus);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel papan game - 3x3 grid
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBackground(new Color(50, 50, 50));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].setBackground(new Color(60, 60, 60));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }

        mainPanel.add(boardPanel, BorderLayout.CENTER);

        // Panel bawah - skor dan tombol
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        lblScore = new JLabel(
            "Menang: " + currentPlayer.getWins() +
            "  Kalah: " + currentPlayer.getLosses() +
            "  Seri: " + currentPlayer.getDraws() +
            "  Skor: " + currentPlayer.getScore(),
            SwingConstants.CENTER
        );
        lblScore.setFont(new Font("Arial", Font.PLAIN, 13));
        lblScore.setForeground(Color.LIGHT_GRAY);
        bottomPanel.add(lblScore);

        JButton btnBack = new JButton("Kembali ke Menu");
        btnBack.setFont(new Font("Arial", Font.BOLD, 13));
        btnBack.setBackground(new Color(100, 100, 100));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> {
            MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
            menuFrame.setVisible(true);
            dispose();
        });
        bottomPanel.add(btnBack);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    // Handle klik tombol oleh pemain
    private void handlePlayerMove(int index) {
        if (gameOver) return;

        // Coba lakukan move pemain (X)
        boolean moved = gameLogic.makeMove(index, 'X');
        if (!moved) {
            JOptionPane.showMessageDialog(this, "Sel sudah terisi! Pilih sel lain.", "Tidak Valid", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Update tampilan tombol
        buttons[index].setText("X");
        buttons[index].setForeground(new Color(0, 200, 100));
        buttons[index].setEnabled(false);

        // Cek pemain menang
        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        // Cek seri
        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        // Giliran komputer (O)
        lblStatus.setText("Komputer sedang berpikir...");
        int compIndex = gameLogic.computerMove();
        gameLogic.makeMove(compIndex, 'O');
        buttons[compIndex].setText("O");
        buttons[compIndex].setForeground(new Color(200, 50, 50));
        buttons[compIndex].setEnabled(false);

        // Cek komputer menang
        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        // Cek seri setelah komputer
        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Giliran kamu! Klik sel untuk bermain.");
    }

    // Selesaikan game dan update database
    private void finishGame(String result) {
        gameOver = true;

        // Nonaktifkan semua tombol
        for (JButton btn : buttons) {
            btn.setEnabled(false);
        }

        // Update database
        playerService.updateStatistics(currentPlayer, result);

        // Ambil data terbaru dari database
        Player updatedPlayer = playerService.getPlayerById(currentPlayer.getId());
        if (updatedPlayer != null) {
            currentPlayer = updatedPlayer;
        }

        // Tampilkan hasil
        String pesan = "";
        if (result.equals("WIN")) {
            pesan = "🎉 Selamat! Kamu MENANG! (+10 poin)";
            lblStatus.setText("Kamu menang!");
        } else if (result.equals("LOSE")) {
            pesan = "😢 Kamu KALAH! Komputer menang.";
            lblStatus.setText("Kamu kalah!");
        } else {
            pesan = "🤝 SERI! Permainan berakhir imbang. (+3 poin)";
            lblStatus.setText("Seri!");
        }

        // Update label skor
        lblScore.setText(
            "Menang: " + currentPlayer.getWins() +
            "  Kalah: " + currentPlayer.getLosses() +
            "  Seri: " + currentPlayer.getDraws() +
            "  Skor: " + currentPlayer.getScore()
        );

        int pilihan = JOptionPane.showConfirmDialog(this,
            pesan + "\n\nMau main lagi?",
            "Hasil Game", JOptionPane.YES_NO_OPTION);

        if (pilihan == JOptionPane.YES_OPTION) {
            // Reset game
            gameLogic.resetBoard();
            gameOver = false;
            for (JButton btn : buttons) {
                btn.setText("");
                btn.setEnabled(true);
                btn.setForeground(Color.WHITE);
            }
            lblStatus.setText("Giliran kamu! Klik sel untuk bermain.");
        } else {
            // Kembali ke menu
            MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
            menuFrame.setVisible(true);
            dispose();
        }
    }
}