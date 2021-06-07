package cube.blocks;

public final class Block3 extends Block2 {
    private final Color color3;
    private Color edge3;

    public Block3(Color color, Color color2, Color color3) {
        super(color, color2);
        this.color3 = color3;
        this.edge3 = color3;
    }

    public Color getColor3() {
        return color3;
    }

    public Color getEdge3() {
        return edge3;
    }

    public void setEdge3(Color edge3) {
        this.edge3 = edge3;
    }

    @Override
    public Color[] getColors() {
        return new Color[] {color, color2, color3};
    }

    @Override
    public Color[] getEdges() {
        return new Color[] {edge, edge2, edge3};
    }

    @Override
    public String toString() {
        return color.toString() + " " + color2.toString() + " " + color3.toString();
    }

}