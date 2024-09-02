import javax.swing.*;

public class app {

    public static void main(String[] args) throws Exception {
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flying Cheems");
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        FlyCheems flyCheems = new FlyCheems();
        frame.add(flyCheems);
        frame.pack();
        flyCheems.requestFocus();
        frame.setVisible(true);
    }
}
