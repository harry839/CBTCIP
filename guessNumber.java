import javax.swing.*;
import java.util.Random;

public class guessNumber {

    private static int points = 0;
    private static int count = 0;
    private static Random random = new Random();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Number Guessing Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            String x = JOptionPane.showInputDialog(frame, "Enter start of the range:");
            String y = JOptionPane.showInputDialog(frame, "Enter end of the range::");

            while (true) {                
                int randomNumber = random.nextInt(Integer.parseInt(y)-Integer.parseInt(x)+1) + Integer.parseInt(x); // Random number between the given range
                String input = JOptionPane.showInputDialog(frame, "Guess a number between "+x+" and "+y+":");

                if (input == null) {
                    break; // User clicked cancel or closed the dialog
                }

                try {
                    int guessedNumber = Integer.parseInt(input);
                    count++;

                    if (guessedNumber == randomNumber) {
                        points++;
                        JOptionPane.showMessageDialog(frame, "Correct! Your points: " + points+" out of "+count);
                    } else if(guessedNumber < randomNumber){
                        JOptionPane.showMessageDialog(frame, "Wrong! You've guessed a smaller number, the correct guess: "+randomNumber+" Your points: " + points+" out of "+count);
                    }else{
                        JOptionPane.showMessageDialog(frame, "Wrong! You've guessed a greater number, the correct guess: "+randomNumber+" Your points: " + points+" out of "+count);
                    }

                    int option = JOptionPane.showConfirmDialog(frame, "Do you want to play again?", "Continue?", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.NO_OPTION) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                }
            }

            JOptionPane.showMessageDialog(frame, "Game over! Your total points: " + points+" out of "+count);
            frame.dispose();
        });
    }
}