import java.util.Arrays;

public class Grid {
    public static final int DEAD = 0;
    private static final int ALIVE = 1;
    private static final int UNDERPOPULATION_LIMIT = 2;
    private static final int OVERPOPULATION_LIMIT = 3;
    public static final int RESURRECTION_COUNT = 3;
    int[][] cells;
    int xLength;
    int yLength;

    public Grid(int xLength, int yLength, int fill) {
        this.xLength = xLength;
        this.yLength = yLength;
        cells = new int[yLength][xLength];

        for(int y = 0; y < yLength; y++){
            for(int x = 0; x < xLength; x++){
                cells[y][x] = fill;
            }
        }
    }

    public int neighborCount(int x, int y) {
        return getCell(x-1, y-1) + getCell(x, y-1 ) + getCell(x+1, y-1 )+
                getCell(x-1, y) + getCell(x+1, y) +
                getCell(x-1, y+1) + getCell(x, y+1) + getCell(x+1, y+1);
    }


    private int getCell(int x, int y) {
        if(x < 0 || x>= this.xLength || y < 0 || y >= yLength){
            return DEAD;
        }
        return cells[y][x];
    }

    public void setCell(int x, int y, int cell) {
        cells[y][x] = cell;
    }

    public int nextCell(int x, int y, int numNeighbors) {
        if (getCell(x, y) == ALIVE && (numNeighbors == UNDERPOPULATION_LIMIT || numNeighbors == OVERPOPULATION_LIMIT)) {
            return ALIVE;
        }else if(getCell(x, y) == DEAD && numNeighbors == RESURRECTION_COUNT){
            return ALIVE;
        }

        return DEAD;
    }

    public Grid nextGrid() {
        Grid grid = new Grid(xLength, yLength, DEAD);

        for(int y = 0; y < yLength; y++){
            for(int x = 0 ; x < xLength; x++){
                grid.setCell(x, y, nextCell(x, y, neighborCount(x, y)));
            }
        }

        return grid;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.cells);
    }

    @Override
    public boolean equals(Object obj) {
        return Arrays.deepEquals(this.cells, ((Grid)obj).cells);
    }
}
