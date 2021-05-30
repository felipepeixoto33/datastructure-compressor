package unifor.programming;

public class HuffmanTree {
    private NoBinario root;
    private String code = "";
    private String encodedTree = "";

    public HuffmanTree() {
        this.root = null;
    }

    public HuffmanTree(NoBinario root) {
        this.root = root;
    }



    public void show() {
        show(root);
    }

    public void show(NoBinario root) {
        System.out.print(root.letter + "(" + root.data + ")");
        if(root.left != null) {
            show(root.left);
        }
        if(root.right != null) {
            show(root.right);
        }
    }

    public void encodeTree() {
        if(root!=null) {
            encodeTree(root);
        }
    }

    private void encodeTree(NoBinario root) {

        if(root.left == null && root.right == null) {
            encodedTree += "1" + Integer.toBinaryString(root.letter);
            return;
        } else {
            encodedTree += "0";
        }

        encodeTree(root.left);

        encodeTree(root.right);

    }

    public String getEncodedTree() {
        return encodedTree;
    }

}
