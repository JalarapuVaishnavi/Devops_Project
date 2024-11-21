import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class StudentGradeManagementSystem extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private JTextField idField, nameField, gradeField, updateIdField, updateGradeField;

    public StudentGradeManagementSystem() {
        // Check if the environment is headless
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Running in headless mode. GUI will not be created.");
            return;  // Exit the constructor if headless
        }

        // Set up the frame only if not in headless mode
        setTitle("Student Grade Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel to hold input fields for adding student
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add a Student"));

        inputPanel.add(new JLabel("ID: "));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name: "));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Grade: "));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Add Student");
        inputPanel.add(addButton);
        addButton.addActionListener(new AddButtonListener());

        JButton saveButton = new JButton("Save Students");
        saveButton.addActionListener(e -> saveStudentsToFile());
        inputPanel.add(saveButton);

        JButton loadButton = new JButton("Load Students");
        loadButton.addActionListener(e -> loadStudentsFromFile());
        inputPanel.add(loadButton);

        // Panel for updating a student's grade
        JPanel updatePanel = new JPanel(new GridLayout(3, 2));
        updatePanel.setBorder(BorderFactory.createTitledBorder("Update Grade"));

        updatePanel.add(new JLabel("Student ID to update: "));
        updateIdField = new JTextField();
        updatePanel.add(updateIdField);

        updatePanel.add(new JLabel("New Grade: "));
        updateGradeField = new JTextField();
        updatePanel.add(updateGradeField);

        JButton updateButton = new JButton("Update Grade");
        updatePanel.add(updateButton);
        updateButton.addActionListener(new UpdateButtonListener());

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(new DeleteButtonListener());
        updatePanel.add(deleteButton);

        // Text area to display students
        JTextArea displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Records"));

        // Panel for displaying students
        JPanel displayPanel = new JPanel(new BorderLayout());
        JButton displayButton = new JButton("Display Students");
        displayButton.addActionListener(e -> {
            displayArea.setText("");  // Clear the display area
            for (Student s : students) {
                displayArea.append(s.toString() + "\n");
            }
        });
        displayPanel.add(displayButton, BorderLayout.NORTH);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(updatePanel, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.SOUTH);
    }

    // Action listener for adding a student
    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String name = nameField.getText();
            try {
                double grade = Double.parseDouble(gradeField.getText());
                if (grade < 0 || grade > 100) {
                    throw new NumberFormatException();
                }
                students.add(new Student(id, name, grade));
                JOptionPane.showMessageDialog(null, "Student Added!");
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid grade (0-100).");
            }
        }
    }

    // Action listener for updating a student's grade
    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String id = updateIdField.getText();
            try {
                double newGrade = Double.parseDouble(updateGradeField.getText());
                for (Student s : students) {
                    if (s.getId().equals(id)) {
                        s.setGrade(newGrade);
                        JOptionPane.showMessageDialog(null, "Grade Updated!");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Student ID not found.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid grade (0-100).");
            }
        }
    }

    // Action listener for deleting a student
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String idToDelete = JOptionPane.showInputDialog("Enter Student ID to delete:");
            boolean found = false;

            for (Student s : students) {
                if (s.getId().equals(idToDelete)) {
                    students.remove(s);
                    JOptionPane.showMessageDialog(null, "Student Deleted!");
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Student ID not found.");
            }
        }
    }

    // Save students to file
    public void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            oos.writeObject(students);
            JOptionPane.showMessageDialog(null, "Students saved to file.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving to file: " + ex.getMessage());
        }
    }

    // Load students from file
    @SuppressWarnings("unchecked")
    public void loadStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
            students = (ArrayList<Student>) ois.readObject();
            JOptionPane.showMessageDialog(null, "Students loaded from file.");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error loading from file: " + ex.getMessage());
        }
    }

    // Helper method to clear input fields
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        gradeField.setText("");
        updateIdField.setText("");
        updateGradeField.setText("");
    }

    // Main method
    public static void main(String[] args) {
        StudentGradeManagementSystem app = new StudentGradeManagementSystem();
        // Only show the GUI if it's not in headless mode
        if (!GraphicsEnvironment.isHeadless()) {
            app.setVisible(true);
        }
    }
}
