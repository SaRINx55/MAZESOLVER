public class MazeSolver {
    private char[][] result;	//The solution
    private Position srt_pos, end_pos;//start and end position
    private int[][] maze;

    public MazeSolver(int[][] maze, Position srt_pos, Position end_pos) {
        this.maze = maze;
        this.srt_pos = srt_pos;
        this.end_pos = end_pos;
        result = new char[maze.length][maze[0].length];
    }


    public char[][] getResult() {
        return result;
    }


    public char solver()
    {
        //Create walls and the start and end points.
        result[srt_pos.getX()][srt_pos.getY()] = 'S';
        result[end_pos.getX()][end_pos.getY()] = 'E';
        for(int i=0; i<maze.length; i++)
            for(int j=0; j<maze[i].length; j++)
                if(maze[i][j] == 1)
                    result[i][j] = '#';

        //Recursively find a correct path from S to E
        char succ = path_finder(maze, srt_pos);

        //Clear the result
        for(int i=0; i<maze.length; i++)
            for(int j=0; j<maze[i].length; j++)
                if(result[i][j] == 'N')
                    result[i][j] = '$';
        result[srt_pos.getX()][srt_pos.getY()] = 'S';
        result[end_pos.getX()][end_pos.getY()] = 'E';

        return succ;
    }


        public void print_result()
    {
        for(int i=0; i<result.length; i++) {
            for(int j=0; j<result[i].length; j++) {
                System.out.print(result[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //recursively finds the path
    private char path_finder(int[][] maze, Position pos)
    {

        if(result[pos.getY()][pos.getX()] == 'E')
            return 'X';	//Found the end.
        else
        {
            char c = 0;
            result[pos.getY()][pos.getX()] = 'V';	//Visited

            int offset = pos.getY() + 1;
            if(offset >= maze.length) offset = 0;

            char south = result[offset][pos.getX()];
            if(south == 0 || south == 'E')
            {
                //Go south
                c = path_finder(maze, new Position(offset, pos.getX()));
                result[offset][pos.getX()] = c;
                if(c == 'X') return 'X';
            }

            offset = pos.getX() + 1;
            if(offset >= maze[0].length) offset = 0;

            char east = result[pos.getY()][offset];
            if(east == 0 || east == 'E')
            {
                //Go east
                c = path_finder(maze, new Position(pos.getY() , offset));
                result[pos.getY()][offset] = c;
                if(c == 'X') return 'X';
            }

            offset = pos.getX() - 1;
            if(offset < 0) offset = maze[0].length - 1;

            char west = result[pos.getY()][offset];
            if(west == 0 || west == 'E')
            {
                //Go west
                c = path_finder(maze, new Position(pos.getY() , offset));
                result[pos.getY()][offset] = c;
                if(c == 'X') return 'X';
            }

            offset = pos.getY() - 1;
            if(offset < 0) offset = maze.length - 1;

            char north = result[offset][pos.getX()];
            if(north == 0 || north == 'E')
            {
                //Go north
                c = path_finder(maze, new Position(offset, pos.getX()));
                result[offset][pos.getX()] = c;
                if(c == 'X') return 'X';
            }

        }

        return 'N';	//Dead-end go back

    }
}