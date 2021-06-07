package cube.blocks;

public class Block2 extends Block1 {
    protected final Color color2;
    protected Color edge2;

    public Block2(Color color, Color color2) {
        super(color);
        this.color2 = color2;
        this.edge2 = color2;
    }

    public Color getColor2() {
        return color2;
    }

    public Color getEdge2() {
        return edge2;
    }

    public void setEdge2(Color edge2) {
        this.edge2 = edge2;
    }

    @Override
    public Color[] getColors() {
        return new Color[] {color, color2};
    }

    @Override
    public Color[] getEdges() {
        return new Color[] {edge, edge2};
    }

    @Override
    public String toString() {
        return color.toString() + " " + color2.toString();
    }

}
