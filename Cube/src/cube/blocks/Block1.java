package cube.blocks;

public class Block1 implements Block {
    protected final Color color;             // единственный цвет
    protected Color edge;                    // цвет единственной грани
    private int spin = 0;                    // вращение [0 - 3]

    public Color getColor() {
        return this.color;
    }

    public Color getEdge() {
        return edge;
    }

    public void setEdge(Color edge) {
        this.edge = edge;
    }

    public int getSpin() {
        return spin;
    }

    public void setSpin(int spin) {
        this.spin = spin;
    }

    public Block1(Color color) {
        this.color = color;
        this.edge = color;
    }

    @Override
    public Color[] getColors() {
        return new Color[] {color};
    }

    @Override
    public Color[] getEdges() {
        return new Color[] {edge};
    }

    @Override
    public String toString() {
        return color.toString();
    }
}