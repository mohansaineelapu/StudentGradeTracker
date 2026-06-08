import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTrackerGUI extends JFrame {

    private JTextField nameField;
    private JTextField gradeField;
    private JTextArea displayArea;

    private ArrayList<Student> students;

    public StudentGradeTrackerGUI() {

        students = new ArrayList<>();

        setTitle("Student Grade Tracker");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Student Name:");
        JLabel gradeLabel = new JLabel("Grade:");

        nameField = new JTextField(15);
        gradeField = new JTextField(15);

        JButton addButton = new JButton("Add Student");
        JButton summaryButton = new JButton("Show Summary");

        displayArea = new JTextArea(15, 35);
        displayArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(gradeLabel);
        panel.add(gradeField);

        panel.add(addButton);
        panel.add(summaryButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String name = nameField.getText();
                    double grade = Double.parseDouble(gradeField.getText());

                    students.add(new Student(name, grade));

                    displayArea.append(
                            "Name: " + name +
                            " | Grade: " + grade + "\n");

                    nameField.setText("");
                    gradeField.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid grade!"
                    );
                }
            }
        });

        summaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (students.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No student data available!"
                    );
                    return;
                }

                double total = 0;
                double highest = students.get(0).grade;
                double lowest = students.get(0).grade;

                for (Student s : students) {

                    total += s.grade;

                    if (s.grade > highest)
                        highest = s.grade;

                    if (s.grade < lowest)
                        lowest = s.grade;
                }

                double average = total / students.size();

                displayArea.append("\n===== SUMMARY =====\n");
                displayArea.append("Average Score: " + average + "\n");
                displayArea.append("Highest Score: " + highest + "\n");
                displayArea.append("Lowest Score: " + lowest + "\n\n");
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentGradeTrackerGUI().setVisible(true);
            }
        });
    }
}