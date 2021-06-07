package cube;

import cube.blocks.*;
import java.util.*;

public final class Cube {
    // все грани
    private HashMap<Color, List<Block1>> edges = new HashMap<>(6);
    // все блоки
    private final Block[] blocks = new Block[26];
    // блоки1, блоки2 и блоки3
    private final Block1[] blocks1 = new Block1[6];
    private final Block2[] blocks2 = new Block2[12];
    private final Block3[] blocks3 = new Block3[8];

    public Cube() {
        int iter = 0;
        int index1 = 0, index2 = 0, index3 = 0;

        for (Color color1 : Color.values()) {
            if (!edges.containsKey(color1))
                edges.put(color1, new ArrayList<>());

            List<Block1> list1 = edges.get(color1);

            Block1 block1 = Block.getInstance(color1);
            blocks[iter++] = block1;
            blocks1[index1++] = block1;
            list1.add(block1);

            for (Color color2 : color1.getNearColors())
                if (color2.getNum() > color1.getNum()) {
                    if (!edges.containsKey(color2))
                        edges.put(color2, new ArrayList<>());

                    List<Block1> list2 = edges.get(color2);

                    Block2 block2 = Block.getInstance(color1, color2);
                    blocks[iter++] = block2;
                    blocks2[index2++] = block2;
                    list1.add(block2);
                    list2.add(block2);

                    for (Color color3 : color2.getNearColors())
                        for (Color color : color1.getNearColors())
                            if (color3.getNum() > color2.getNum() && color3 == color) {
                                if (!edges.containsKey(color3))
                                    edges.put(color3, new ArrayList<>());

                                List<Block1> list3 = edges.get(color3);

                                Block3 block3 = Block.getInstance(color1, color2, color3);
                                blocks[iter++] = block3;
                                blocks3[index3++] = block3;
                                list1.add(block3);
                                list2.add(block3);
                                list3.add(block3);
                            }
                }
        }
    }

    public void step(Move move) {
        step(move.getColor(), move.getDirection());
    }

    public void step(Color color, Direction direction) {
        Color[] nearColors = color.getNearColors();
        int dir = direction.getNum();

        for (Block1 block : edges.get(color)) {
            block.setSpin((block.getSpin() + dir) % 4);

            if (block instanceof Block2) {
                if (block.getEdge() != color)
                    for (int i = 0; i < nearColors.length; i++)
                        if (nearColors[i] == block.getEdge()) {
                            block.setEdge(nearColors[(i + dir) % 4]);
                            edges.get(nearColors[i]).remove(block);
                            edges.get(nearColors[(i + dir) % 4]).add(block);
                            break;
                        }

                if (((Block2) block).getEdge2() != color)
                    for (int i = 0; i < nearColors.length; i++)
                        if (nearColors[i] == ((Block2) block).getEdge2()) {
                            ((Block2) block).setEdge2(nearColors[(i + dir) % 4]);
                            edges.get(nearColors[i]).remove(block);
                            edges.get(nearColors[(i + dir) % 4]).add(block);
                            break;
                        }


                if (block instanceof Block3)
                    if (((Block3) block).getEdge3() != color)
                        for (int i = 0; i < nearColors.length; i++)
                            if (nearColors[i] == ((Block3) block).getEdge3()) {
                                ((Block3) block).setEdge3(nearColors[(i + dir) % 4]);
                                edges.get(nearColors[i]).remove(block);
                                edges.get(nearColors[(i + dir) % 4]).add(block);
                                break;
                            }
            }
        }
    }

    public boolean isStartPosition() {
        for (Block block : blocks) {
            Color[] edges = block.getEdges();
            Color[] colors = block.getColors();

            if (!Arrays.equals(edges, colors))
                return false;
        }

        return true;
    }

//    public Block1 findCurrentBlock(Color edge) {
//        for (Block1 block1 : blocks1)
//            if (block1.getEdge() == edge)
//                return block1;
//
//        throw new IllegalArgumentException("Wrong edge");
//    }
//
//    public Block2 findCurrentBlock(Color edge1, Color edge2) {
//        for (Block2 block2 : blocks2)
//            if (block2.getEdge() == edge1 && block2.getEdge2() == edge2 || block2.getEdge() == edge2 && block2.getEdge2() == edge1)
//                return block2;
//
//        throw new IllegalArgumentException("Wrong edges combination");
//    }
//
//    public Block3 findCurrentBlock(Color edge1, Color edge2, Color edge3) {
//        for (Block3 block3 : blocks3)
//            if (block3.getEdge() == edge1 && block3.getEdge2() == edge2 && block3.getEdge3() == edge3 ||
//                    block3.getEdge() == edge1 && block3.getEdge2() == edge3 && block3.getEdge3() == edge2 ||
//                    block3.getEdge() == edge2 && block3.getEdge2() == edge1 && block3.getEdge3() == edge3 ||
//                    block3.getEdge() == edge2 && block3.getEdge2() == edge3 && block3.getEdge3() == edge1 ||
//                    block3.getEdge() == edge3 && block3.getEdge2() == edge1 && block3.getEdge3() == edge2 ||
//                    block3.getEdge() == edge3 && block3.getEdge2() == edge2 && block3.getEdge3() == edge1)
//                return block3;
//
//        throw new IllegalArgumentException("Wrong edges combination");
//    }
//
//    public Block findCurrentBlock(Color[] edges) {
//        switch (edges.length) {
//            case 1 : return findCurrentBlock(edges[0]);
//            case 2 : return findCurrentBlock(edges[0], edges[1]);
//            case 3 : return findCurrentBlock(edges[0], edges[1], edges[2]);
//        }
//
//        throw new IllegalArgumentException("Wrong number edges");
//    }

    // альтернативный метод
    public Block getCurrentBlock(Color... edges) {
        TreeSet<Color> set1 = new TreeSet<>(Arrays.asList(edges));

        for (Block block : blocks) {
            TreeSet<Color> set2 = new TreeSet<>(Arrays.asList(block.getEdges()));

            if (set1.equals(set2))
                return block;
        }

        throw new IllegalArgumentException("Wrong edges combination");
    }

    public Block getStartBlock(Color... colors) {
        TreeSet<Color> set1 = new TreeSet<>(Arrays.asList(colors));

        for (Block block : blocks) {
            TreeSet<Color> set2 = new TreeSet<>(Arrays.asList(block.getColors()));

            if (set1.equals(set2))
                return block;
        }

        throw new IllegalArgumentException("Wrong colors combination");
    }

    public void mix() {
        int mix = 30;   // кол-во перемешиваний

        while (mix-- > 0) {
            Color color = Color.getColor((int) (Math.random() * 6 + 1));
            Direction direction = Direction.getDirection((int) (Math.random() * 3 + 1));

            step(color, direction);
        }
    }

    public HashMap<Color, List<Block1>> getEdges() {
        return edges;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public Block1[] getBlocks1() {
        return blocks1;
    }

    public Block2[] getBlocks2() {
        return blocks2;
    }

    public Block3[] getBlocks3() {
        return blocks3;
    }
}
