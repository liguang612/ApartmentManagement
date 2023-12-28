import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        String data[][] = {{"101", "Tran Van Minh", "6000"}, {"102", "Phan Van Tai", "8000"}, {"101", "Do Cao Hoc", "7000"}};
        String column[] = {"ID", "NAME", "SALARY"};

        JTable table = new JTable(data, column);
        JScrollPane sp = new JScrollPane(table);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println(table.getSelectedRows()[0]);
            }
        });

        frame.add(sp);
        frame.setVisible(true);
    }
}
