import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameOfLifeTest {

    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    @Test
    public void neighborCountReturns0Given1x1Grid() {
        int actual = new Grid(1, 1, ALIVE).neighborCount(0, 0);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void neighborCountReturns1Given3x3DeadGridWithTopAlive() {
        Grid grid = new Grid(3, 3, DEAD);
        grid.setCell(1, 0, ALIVE);
        int expected = 1;

        int actual = grid.neighborCount(1, 1);

        assertEquals(expected, actual);
    }

    @Test
    public void neighborCountReturns1Given3x3DeadGridWithAnySurroundingAlive(){

        int expected = 1;

        assertEquals(expected, getDeadGridWith1Alive(0, 0).neighborCount(1, 1));
        assertEquals(expected, getDeadGridWith1Alive(1, 0).neighborCount(1, 1));
        assertEquals(expected, getDeadGridWith1Alive(2, 0).neighborCount(1, 1));
        assertEquals(expected, getDeadGridWith1Alive(0, 1).neighborCount(1, 1));
// center cell
        assertEquals(expected, getDeadGridWith1Alive(2, 1).neighborCount(1, 1));
        assertEquals(expected, getDeadGridWith1Alive(0, 2).neighborCount(1, 1));
        assertEquals(expected, getDeadGridWith1Alive(1, 2).neighborCount(1, 1));
        assertEquals(expected, getDeadGridWith1Alive(2, 2).neighborCount(1, 1));
    }

    @Test
    public void neighborCountReturns8Given3x3AliveGridAtCenter() {
        assertEquals(8, new Grid(3, 3, 1).neighborCount(1, 1));
    }


    @Test
    public void nextCellReturnsDeadGivenAliveAnd0Neighbors() {
        int expected = DEAD;

        int actual   = new Grid(1, 1, ALIVE).nextCell(0, 0, 0);

        assertEquals(expected,actual);
    }
    @Test
    public void nextCellReturnsAliveGivenAliveAnd2Neighbors() {
        int expected = ALIVE;

        int actual   = new Grid(1, 1, ALIVE).nextCell(0, 0, 2);

        assertEquals(expected,actual);
    }
    @Test
    public void nextCellReturnsAliveGivenAliveAnd3Neighbors() {
        int expected = ALIVE;

        int actual   = new Grid(1, 1, ALIVE).nextCell(0, 0, 3);

        assertEquals(expected,actual);
    }

    @Test
    public void nextGridReturnsDeadGridGiven2x1AliveGrid() {
        Grid expected = new Grid(2, 1, DEAD);

        Grid actual = new Grid(2, 1, ALIVE).nextGrid();

        assertEquals(expected, actual);
    }

    @Test
    public void nextGridReturnsAliveGridGiven2x2AliveGrid() {
        Grid expected = new Grid(2,2, ALIVE);

        Grid actual = new Grid(2, 2, ALIVE).nextGrid();

        assertEquals(expected, actual);
    }

    @Test
    public void nextCellReturnsAliveGivenDeadAnd3Neighbors() {
        int actual = new Grid(1, 1, DEAD).nextCell(0, 0, 3);

        assertEquals(ALIVE, actual);
    }

    private Grid getDeadGridWith1Alive(int x, int y) {
        Grid grid = new Grid(3, 3, DEAD);
        grid.setCell(x, y, ALIVE);
        return grid;
    }


}
