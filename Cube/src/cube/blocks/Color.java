package cube.blocks;

public enum Color {
    RED(1),
    GREEN(2),
    WHITE(3),
    YELLOW(4),
    BLUE(5),
    ORANGE(6);

    private final int num;

    Color(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    private Color[] nearColors;

    static {
        RED.nearColors = new Color[]{GREEN, WHITE, BLUE, YELLOW};
        GREEN.nearColors = new Color[]{ORANGE, WHITE, RED, YELLOW};
        WHITE.nearColors = new Color[]{GREEN, ORANGE, BLUE, RED};
        YELLOW.nearColors = new Color[]{GREEN, RED, BLUE, ORANGE};
        BLUE.nearColors = new Color[]{RED, WHITE, ORANGE, YELLOW};
        ORANGE.nearColors = new Color[]{BLUE, WHITE, GREEN, YELLOW};
    }

    public Color[] getNearColors() {
        return nearColors;
    }

    public static Color getColor(int index) {
        for (Color color : Color.values())
            if (color.num == index)
                return color;

        throw new IllegalArgumentException("index must be from 1 to 6 include");
    }

    public String toHex() {
        switch (this) {
            case RED: return "#f00";
            case GREEN: return "#007f00";
            case WHITE: return "#fff";
            case YELLOW: return "#ff0";
            case BLUE: return "#00f";
            case ORANGE: return "#ffa500";
            default: return "000";  // black
        }
    }
}
