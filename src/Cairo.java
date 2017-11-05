import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.nio.CharBuffer;

public class Cairo {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner lineScanner = new Scanner(new File("cairo.dat"));
        char[][] pyramids = new char[12][60];
        StringBuilder reversedInput = new StringBuilder();
        
        while (lineScanner.hasNext()) {
            String line = lineScanner.nextLine();
            
            if (line.equals("0 0")) {
                reversedInput.append("0 0");
                
                break;
            }
            
            reversedInput.insert(0, line + "\n");
        }
        
        lineScanner = new Scanner(reversedInput.toString());
        
        for (char[] row: pyramids)
            Arrays.fill(row, ' ');
        
        while (lineScanner.hasNext()) {
            Scanner scanner = new Scanner(lineScanner.nextLine());
            int xCenter = scanner.nextInt();
            int height = scanner.nextInt() - 1;
            
            if (xCenter == 0 && height == 0) {
                break;
            }
            
            for (int y=height; y>=0; y--) {
                int width = (height - y);
                
                pyramids[y][xCenter - width - 1] = '/';
                pyramids[y][xCenter + width] = '\\';
                
                for (int x = xCenter - width; x < xCenter + width; x++) {
                    pyramids[y][x] = ' ';
                }
            }
        }
        
        for (int i=pyramids.length-1; i>=0; i--) {
            if (!CharBuffer
                .wrap(pyramids[i])
                .chars()
                .filter(character -> character != ' ')
                .findAny()
                .isPresent())
                continue;
            
            for (char character: pyramids[i])
                System.out.print(character);
            System.out.println();
        }
        
        for (int i=0; i<3; i++) {
            for (int j=0; j<10; j++) {
                System.out.print("" + (j + 1) % 10);
            }
        }
        
        System.out.println();
    }
}
