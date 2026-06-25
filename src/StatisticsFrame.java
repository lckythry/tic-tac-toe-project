import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        this.playerService = new PlayerService();

        Player updatedPlayer = playerService.getPlayerById(player.getId());
        this.currentPlayer = (updatedPlayer != null) ? updatedPlayer : player;

        setTitle("Statistik - " + currentPlayer.getUsername());
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("STATISTIK SAYA", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(150, 100, 200));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        addStatRow(panel, gbc, 1, "Username:", currentPlayer.getUsername(), Color.WHITE);

        addStatRow(panel, gbc, 2, "Menang:", String.valueOf(currentPlayer.getWins()), new Color(0, 200, 100));

        addStatRow(panel, gbc, 3, "Kalah:", String.valueOf(currentPlayer.getLosses()), new Color(200, 50, 50));

        addStatRow(panel, gbc, 4, "Seri:", String.valueOf(currentPlayer.getDraws()), new Color(200, 150, 0));

        addStatRow(panel, gbc, 5, "Total Skor:", String.valueOf(currentPlayer.getScore()), new Color(0, 150, 200));

        JButton btnClose = new JButton("Tutup");
        btnClose.setFont(new Font("Arial", Font.BOLD, 13));
        btnClose.setBackground(new Color(100, 100, 100));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnClose, gbc);

        btnClose.addActionListener(e -> dispose());

        add(panel);
    }

    private void addStatRow(JPanel panel, GridBagConstraints gbc, int row, String label, String value, Color valueColor) {
        JLabel lblKey = new JLabel(label);
        lblKey.setFont(new Font("Arial", Font.PLAIN, 14));
        lblKey.setForeground(Color.LIGHT_GRAY);
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblKey, gbc);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Arial", Font.BOLD, 14));
        lblValue.setForeground(valueColor);
        gbc.gridx = 1; gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblValue, gbc);
    }
}