import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingBannerApp extends JFrame {
    private JTextField inputTextField;
    private JButton startButton;

    public MovingBannerApp() {
        setTitle("Banner");
        setSize(500, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputTextField = new JTextField(25);
        startButton = new JButton("Cetak");

        setLayout(new FlowLayout());

        // Atur warna latar belakang frame menjadi biru
        getContentPane().setBackground(new Color(0x68f3f8));

        add(inputTextField);
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextField.getText();
                if (!inputText.isEmpty()) {
                    createBannerWindow(inputText);
                }
            }
        });
    }

    private void createBannerWindow(String text) {
        JFrame bannerWindow = new JFrame("Banner");
        JLabel bannerLabel = new JLabel();

        bannerWindow.setLayout(new FlowLayout());
        bannerWindow.add(bannerLabel);
        bannerWindow.setSize(400, 100);

        // Atur warna latar belakang jendela banner menjadi biru
        bannerWindow.getContentPane().setBackground(new Color(104, 243, 248));

        bannerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bannerWindow.setVisible(true);

        Thread animationThread = new Thread(() -> {
            while (true) {
                try {
                    for (int i = 0; i < text.length(); i++) {
                        String bannerText = text.substring(i) + " " + text.substring(0, i);
                        bannerLabel.setText(bannerText);
                        Thread.sleep(1000); // Menghentikan thread selama 1000 milidetik (1 detik)
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        animationThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovingBannerApp app = new MovingBannerApp();
            app.setVisible(true);
        });
    }
}
