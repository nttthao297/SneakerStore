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
            JButton btnAddToCart = new JButton("Add to Favorite"); // Add button for each sneaker

            JPanel buttonPanel = new JPanel(new BorderLayout());
            buttonPanel.add(btnSneaker, BorderLayout.CENTER);
            buttonPanel.add(btnAddToCart, BorderLayout.EAST);

            sneakerInnerPanel.add(buttonPanel, BorderLayout.SOUTH);
            sneakerInnerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

            String imageName = "sneaker" + i + ".jpg"; // Replace with the desired image name format
            ImageIcon originalIcon = new ImageIcon("src/" + imageName); // Replace with the correct image file path
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel lblSneakerImage = new JLabel(scaledIcon);
            sneakerInnerPanel.add(lblSneakerImage, BorderLayout.CENTER);

            btnSneaker.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String sneakerName = btnSneaker.getText();
                    JFrame sneakerFrame = createSneakerFrame(sneakerName);
                    sneakerFrame.setVisible(true);
                }
            });

            sneakerPanel.add(sneakerInnerPanel);
        }


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

    private JFrame createSneakerFrame(String sneakerName) {
        JFrame sneakerFrame = new JFrame(sneakerName);
        sneakerFrame.setSize(600, 400);

        JPanel sneakerDetailsPanel = new JPanel();
        sneakerDetailsPanel.setLayout(new GridLayout(13, 1));

        JPanel itemPanel = new JPanel(new GridLayout(1, 2));
        JLabel lblItem = new JLabel("Item: ");
        lblItem.setFont(new Font("Arial", Font.BOLD, 18));
        lblItem.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lblItemValue = new JLabel(sneakerName);
        lblItemValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblItemValue.setHorizontalAlignment(SwingConstants.LEFT);
        itemPanel.add(lblItem);
        itemPanel.add(lblItemValue);
        sneakerDetailsPanel.add(itemPanel);

        JPanel brandPanel = new JPanel(new GridLayout(1, 2));
        JLabel lblBrand = new JLabel("Brand: ");
        lblBrand.setFont(new Font("Arial", Font.BOLD, 18));
        lblBrand.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lblBrandValue = new JLabel("Nike"); // Replace with the actual brand value
        lblBrandValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblBrandValue.setHorizontalAlignment(SwingConstants.LEFT);
        brandPanel.add(lblBrand);
        brandPanel.add(lblBrandValue);
        sneakerDetailsPanel.add(brandPanel);

        JPanel sizePanel = new JPanel(new GridLayout(1, 2));
        JLabel lblSize = new JLabel("Size: ");
        lblSize.setFont(new Font("Arial", Font.BOLD, 18));
        lblSize.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lblSizeValue = new JLabel("10"); // Replace with the actual size value
        lblSizeValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSizeValue.setHorizontalAlignment(SwingConstants.LEFT);
        sizePanel.add(lblSize);
        sizePanel.add(lblSizeValue);
        sneakerDetailsPanel.add(sizePanel);

        // Add panels for color, condition, price, seller, original price, retail price, and status

        JPanel conditionPanel = new JPanel(new GridLayout(1, 2));
        JLabel lblCondition = new JLabel("Condition: ");
        lblCondition.setFont(new Font("Arial", Font.BOLD, 18));
        lblCondition.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lblConditionValue = new JLabel("New"); // Replace with the actual condition value
        lblConditionValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblConditionValue.setHorizontalAlignment(SwingConstants.LEFT);
        conditionPanel.add(lblCondition);
        conditionPanel.add(lblConditionValue);
        sneakerDetailsPanel.add(conditionPanel);

        JPanel originalPricePanel = new JPanel(new GridLayout(1, 2));
        JLabel lblOriginalPrice = new JLabel("Original Price: ");
        lblOriginalPrice.setFont(new Font("Arial", Font.BOLD, 18));
        lblOriginalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lblOriginalPriceValue = new JLabel("$200"); // Replace with the actual original price value
        lblOriginalPriceValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblOriginalPriceValue.setHorizontalAlignment(SwingConstants.LEFT);
        originalPricePanel.add(lblOriginalPrice);
        originalPricePanel.add(lblOriginalPriceValue);
        sneakerDetailsPanel.add(originalPricePanel);

        JPanel retailPricePanel = new JPanel(new GridLayout(1, 2));
        JLabel lblRetailPrice = new JLabel("Retail Price: ");
        lblRetailPrice.setFont(new Font("Arial", Font.BOLD, 18));
        lblRetailPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lblRetailPriceValue = new JLabel("$150"); // Replace with the actual retail price value
        lblRetailPriceValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblRetailPriceValue.setHorizontalAlignment(SwingConstants.LEFT);
        retailPricePanel.add(lblRetailPrice);
        retailPricePanel.add(lblRetailPriceValue);
        sneakerDetailsPanel.add(retailPricePanel);

        JPanel sellerPanel = new JPanel(new GridLayout(1, 2));
        JLabel lblSeller = new JLabel("Seller: ");
        lblSeller.setFont(new Font("Arial", Font.BOLD, 18));
        lblSeller.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lblSellerValue = new JLabel("John Doe"); // Replace with the actual seller value
        lblSellerValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSellerValue.setHorizontalAlignment(SwingConstants.LEFT);
        sellerPanel.add(lblSeller);
        sellerPanel.add(lblSellerValue);
        sneakerDetailsPanel.add(sellerPanel);

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        JLabel lblStatus = new JLabel("Status: ");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 18));
        lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lblStatusValue = new JLabel("Available"); // Replace with the actual status value
        lblStatusValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblStatusValue.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(lblStatus);
        statusPanel.add(lblStatusValue);
        sneakerDetailsPanel.add(statusPanel);

        JPanel buyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnBuy = new JButton("Buy");
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(sneakerFrame, "You have purchased " + sneakerName + ".");
            }
        });
        buyPanel.add(btnBuy);
        sneakerDetailsPanel.add(buyPanel);

        sneakerFrame.getContentPane().add(sneakerDetailsPanel);

        return sneakerFrame;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SneakerRetailApp();
            }
        });
    }
}
