package game;

import cube.Move;
import cube.Cube;

import java.util.Stack;

public final class Game {
    private final Stack<Move> undo = new Stack<>();
    private final Stack<Move> redo = new Stack<>();
    private final Cube cube = new Cube();

    private int playerMoves = 0;

    public void step(Move move) {
        cube.step(move);
        undo.push(move.inverted());
        redo.clear();
        playerMoves++;
    }

    public boolean takeBack() {
        if (undo.isEmpty())
            return false;

        Move move = undo.pop();
        cube.step(move);
        redo.push(move.inverted());
        playerMoves--;

        return true;
    }

    public boolean repeat() {
        if (redo.isEmpty())
            return false;

        Move move = redo.pop();
        cube.step(move);
        undo.push(move.inverted());
        playerMoves++;

        return true;
    }

    public void mix() {
        cube.mix();
        undo.clear();
        redo.clear();
        playerMoves = 0;
    }

    // return null if resolve not found
    public Stack<Move> resolve() {
        if (cube.isStartPosition())
            return new Stack<>();

        int i = 1;

        while (i <= 5) {
            Stack<Move> stack = resolve(i++);

            if (stack != null)
                return stack;
        }

        return null;
    }

    // return null if resolve not found on choose depth
    public Stack<Move> resolve(int depth) {

        for (Move move : Move.values()) {
            cube.step(move);

            if (depth == 1 && cube.isStartPosition()) {
                Stack<Move> result = new Stack<>();
                result.push(move);
                cube.step(move.inverted());

                return result;
            }

            if (depth > 1) {
                Stack<Move> result = resolve(depth - 1);

                if (result != null) {
                    result.push(move);
                    cube.step(move.inverted());

                    return result;
                }
            }

            cube.step(move.inverted());
        }

        return null;
    }

    public int getUndoScore() {
        return undo.size();
    }

    public int getRedoScore() {
        return redo.size();
    }

    public int getPlayerMoves() {
        return playerMoves;
    }

    public Cube getCube() {
        return cube;
    }
}
