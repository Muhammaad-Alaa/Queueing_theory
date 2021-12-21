
package queue_project_all;

import java.awt.Toolkit;

/**
 *
 * @author Mohammed Mashal
 */
public class Queue_Project_All {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Form f = new Form();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setTitle("Queue Project");
        f.setIconImage(Toolkit.getDefaultToolkit().getImage("queue.png"));
    }
    
}
