package cube.blocks;

public interface Block {

    static Block1 getInstance(Color color) {
        return new Block1(color);
    }

    static Block2 getInstance(Color color1, Color color2) {
        return new Block2(color1, color2);
    }

    static Block3 getInstance(Color color1, Color color2, Color color3) {
        return new Block3(color1, color2, color3);
    }

    Color[] getColors();
    Color[] getEdges();
}
