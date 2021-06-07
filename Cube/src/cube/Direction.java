package cube;

public enum Direction {
    RIGHT(1),
    REVERSE(2),
    LEFT(3);

    private int num;

    public int getNum() {
        return num;
    }

    Direction(int num) {
        this.num = num;
    }

    public static Direction getDirection(int index) {
        for (Direction dir : Direction.values())
            if (dir.num == index)
                return dir;

        throw new IllegalArgumentException("index must be from 1 to 3 include");
    }
}
