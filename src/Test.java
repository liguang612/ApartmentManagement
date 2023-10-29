import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private JFrame frame;
    private final JComboBox comboBox = new JComboBox();
    private static List<String> listForComboBox = new ArrayList<String>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Test window = new Test();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Thêm các mục vào danh sách
        listForComboBox.add("Lion");
        listForComboBox.add("LionKing");
        listForComboBox.add("Mufasa");
        listForComboBox.add("Nala");
        listForComboBox.add("KingNala");
        listForComboBox.add("Animals");
        listForComboBox.add("Anims");
        listForComboBox.add("Fish");
        listForComboBox.add("Jelly Fish");
        listForComboBox.add("I am the boss");
    }

    public Test() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 412, 165);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        comboBox.setEditable(true);

        for (String detail : listForComboBox) {
            comboBox.addItem(detail);
        }

        final JTextField textfield = (JTextField) comboBox.getEditor().getEditorComponent();
        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (!textfield.getText().isEmpty()) {
                            comboBoxFilter(textfield.getText());
                        }
                    }
                });
            }
        });

        comboBox.setBounds(10, 39, 364, 29);
        frame.getContentPane().add(comboBox);
    }

    public void comboBoxFilter(String enteredText) {
        if (!comboBox.isPopupVisible()) {
            comboBox.showPopup();
        }

        List<String> filterArray = new ArrayList<String>();
        
        for (int i = 0; i < listForComboBox.size(); i++) {
            if (listForComboBox.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
                filterArray.add(listForComboBox.get(i));
            }
        }

        if (filterArray.size() > 0) {
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
            model.removeAllElements();
            
            for (String s : filterArray)
                model.addElement(s);

            JTextField textfield = (JTextField) comboBox.getEditor().getEditorComponent();
            textfield.setText(enteredText);
        }
    }
}
