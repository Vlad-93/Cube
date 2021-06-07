package cube;

import cube.blocks.Color;

import static cube.blocks.Color.*;
import static cube.Direction.*;

public enum Move {
    RED_RIGHT(RED, RIGHT), RED_LEFT(RED, LEFT), RED_REVERSE(RED, REVERSE),
    GREEN_RIGHT(GREEN, RIGHT), GREEN_LEFT(GREEN, LEFT), GREEN_REVERSE(GREEN, REVERSE),
    WHITE_RIGHT(WHITE, RIGHT), WHITE_LEFT(WHITE, LEFT), WHITE_REVERSE(WHITE, REVERSE),
    YELLOW_RIGHT(YELLOW, RIGHT), YELLOW_LEFT(YELLOW, LEFT), YELLOW_REVERSE(YELLOW, REVERSE),
    BLUE_RIGHT(BLUE, RIGHT), BLUE_LEFT(BLUE, LEFT), BLUE_REVERSE(BLUE, REVERSE),
    ORANGE_RIGHT(ORANGE, RIGHT), ORANGE_LEFT(ORANGE, LEFT), ORANGE_REVERSE(ORANGE, REVERSE);

    private final Color color;
    private final Direction direction;

    public Color getColor() {
        return color;
    }

    public Direction getDirection() {
        return direction;
    }

    Move(Color color, Direction direction) {
        this.color = color;
        this.direction = direction;
    }

    public static Move getMove(Color color, Direction direction) {
        for (Move move : Move.values())
            if (move.color == color && move.direction == direction)
                return move;

        throw new IllegalArgumentException();
    }

    public Move inverted() {
        switch (this.direction) {
            case RIGHT:
                return getMove(this.color, LEFT);
            case LEFT:
                return getMove(this.color, RIGHT);
            default:
                return this;
        }
    }

}
