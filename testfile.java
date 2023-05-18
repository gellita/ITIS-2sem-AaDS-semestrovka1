import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class testfile {
    public static void main(String[] args) {
        Random random = new Random();
        int minStringLength = 100;
        int maxStringLength = 1000;
        int numberOfLines = 100;

        try {
            FileWriter writer = new FileWriter("text.txt");

            for (int i = 0; i < numberOfLines; i++) {
                int stringLength = random.nextInt(maxStringLength - minStringLength + 1) + minStringLength;
                StringBuilder stringBuilder = new StringBuilder();

                for (int j = 0; j < stringLength; j++) {
                    char randomChar = (char) (random.nextInt(26) + 'a');
                    stringBuilder.append(randomChar);
                }

                String line = stringBuilder.toString();
                writer.write(line + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
