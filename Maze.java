import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Maze {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {

        //Ask for file path
        Scanner user_input = new Scanner(System.in);
        System.out.print("Enter maze file : ");
        String filename = user_input.nextLine();
        user_input.close();

        //Open file
        File fd = new File(filename);
        Scanner sc = null;
        try {
            sc = new Scanner(fd);
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Application terminated\n" + e.toString());
            return;
        }


        int width = 0, height = 0;
        Position srt_pos, end_pos;
        int[][] maze;

        width = sc.nextInt();
        height = sc.nextInt();
        srt_pos = new Position(sc.nextInt(), sc.nextInt());
        end_pos = new Position(sc.nextInt(), sc.nextInt());
        maze = new int[height][width];
        for(int i=0; i<height; i++)
            for(int j=0; j<width; j++)
                maze[i][j] = sc.nextInt();
        sc.close();

        MazeSolver ms = new MazeSolver(maze, srt_pos, end_pos);

        //Call solver method to find a path through the maze
        char succ = ms.solver();
        ms.print_result();

        if(succ != 'X')
            System.out.println("Maze can not be solved.");
        else
            System.out.println("Maze solved.");
    }
}