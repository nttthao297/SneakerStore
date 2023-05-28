import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SneakerRetailApp {
    private JFrame frame;
    private JPanel loginPanel;
    private JPanel homePanel;
    private JTabbedPane tabbedPane;

    public SneakerRetailApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sneaker Retail App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        loginPanel = new JPanel();
        homePanel = new JPanel();

        createLoginPage();

        frame.setVisible(true);
    }

    private void createLoginPage() {
        loginPanel.setLayout(new GridLayout(7, 2));

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblLocation = new JLabel("Location:");
        JLabel lblRole = new JLabel("Role (Buyer/Seller):");

        JTextField txtUsername = new JTextField(20);
        JPasswordField txtPassword = new JPasswordField(20);
        JTextField txtEmail = new JTextField(20);
        JTextField txtLocation = new JTextField(20);
        JTextField txtRole = new JTextField(20);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                String email = txtEmail.getText();
                String location = txtLocation.getText();
                String role = txtRole.getText();

                // Perform sign up logic here
                performSignUp(username, password, email, location, role);

                JOptionPane.showMessageDialog(frame, "Account created successfully!");
                clearFields();
            }
        });

        JButton btnLogIn = new JButton("Log In");
        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Perform login logic here
                if (performLogin(username, password)) {
                    createHomePage();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            }
        });

        loginPanel.add(lblUsername);
        loginPanel.add(txtUsername);
        loginPanel.add(lblPassword);
        loginPanel.add(txtPassword);
        loginPanel.add(lblEmail);
        loginPanel.add(txtEmail);
        loginPanel.add(lblLocation);
        loginPanel.add(txtLocation);
        loginPanel.add(lblRole);
        loginPanel.add(txtRole);
        loginPanel.add(btnSignUp);
        loginPanel.add(btnLogIn);

        frame.getContentPane().add(loginPanel);
    }

    private boolean performLogin(String username, String password) {
        // Simulate login logic
        // Replace this code with your actual login implementation
        if (username.equals("admin") && password.equals("password")) {
            return true; // Login successful
        } else {
            return false; // Login failed
        }
    }

    private void createHomePage() {
        homePanel.setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        JLabel lblSearch = new JLabel("Search:");
        JTextField txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = txtSearch.getText();

                // Retrieve filter values
                String brandFilter = ""; // Replace with actual brand filter value
                String sizeFilter = ""; // Replace with actual size filter value
                String colorFilter = ""; // Replace with actual color filter value
                String conditionFilter = ""; // Replace with actual condition filter value

                // Perform search logic here based on searchTerm and filters
                performSearch(searchTerm, brandFilter, sizeFilter, colorFilter, conditionFilter);
            }
        });

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Sneaker Panel
        JPanel sneakerPanel = new JPanel();
        sneakerPanel.setLayout(new GridLayout(3, 3));

        int imageWidth = 400; // Set the desired width of the image
        int imageHeight = 300; // Set the desired height of the image

        String[] sneakerNames = {"Jordan 4 UNC", "Jordan 1 Low Travis Scott", "Nike Dunk Low Panda", "Air Force 1 Off White",
                "Jordan 3 Fire Red", "Jordan 5 PSG", "Yeezy 700", "Yeezy Slide", "Yeezy 350"};

        for (int i = 1; i <= 9; i++) {
            JPanel sneakerInnerPanel = new JPanel(new BorderLayout());
            JButton btnSneaker = new JButton(sneakerNames[i-1]); // Set the desired name from the sneakerNames array
            sneakerInnerPanel.add(btnSneaker, BorderLayout.SOUTH);
            sneakerInnerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

            String imageName = "sneaker" + i + ".jpg"; // Replace with the desired image name format
            ImageIcon originalIcon = new ImageIcon("src/" + imageName); // Replace with the correct image file path
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel lblSneakerImage = new JLabel(scaledIcon);
            sneakerInnerPanel.add(lblSneakerImage, BorderLayout.CENTER);

            sneakerPanel.add(sneakerInnerPanel);
        }

// ...

        // User Account Panel
        JPanel accountPanel = new JPanel();
        JButton btnAccount = new JButton("User Account");
        btnAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(2); // Select the "Profile" tab
            }
        });
        accountPanel.add(btnAccount);

        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sneakers", sneakerPanel); // Sneakers tab
        tabbedPane.addTab("Favorites", new JPanel()); // Favorites tab (empty for now)
        tabbedPane.addTab("Profile", new JPanel()); // Profile tab (empty for now)

        homePanel.add(searchPanel, BorderLayout.NORTH);
        homePanel.add(accountPanel, BorderLayout.SOUTH);
        homePanel.add(tabbedPane, BorderLayout.CENTER);

        frame.getContentPane().remove(loginPanel);
        frame.getContentPane().add(homePanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    private void performSignUp(String username, String password, String email, String location, String role) {
        // Implement your sign up logic here
        // Save the user information to a database or file system
        createHomePage();
        clearFields();
    }

    private void performSearch(String searchTerm, String brandFilter, String sizeFilter, String colorFilter, String conditionFilter) {
        // Implement your search logic here
        // Use the provided searchTerm, brandFilter, sizeFilter, colorFilter, and conditionFilter
        // to retrieve the matching streetwear items
        // Display the results to the user
        JOptionPane.showMessageDialog(frame, "Searching for: " + searchTerm +
                "\nBrand Filter: " + brandFilter +
                "\nSize Filter: " + sizeFilter +
                "\nColor Filter: " + colorFilter +
                "\nCondition Filter: " + conditionFilter);
    }

    private void clearFields() {
        Component[] components = loginPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SneakerRetailApp();
            }
        });
    }
}
