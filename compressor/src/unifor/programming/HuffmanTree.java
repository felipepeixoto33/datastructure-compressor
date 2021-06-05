package unifor.programming;
public class HuffmanTree {
    private NoBinario root;
    private String code = "";
    private String encodedTree = "";
    private Queue littleQueue;

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
    public void buildTree(Queue queue) {
        littleQueue = queue;
        if(root == null){
            root = new NoBinario(null);
        }
        buildTree(root);
    }
    private void buildTree(NoBinario root) {
        NoBinario littleNo;
        if(root.left == null){
            littleNo = root.left;
            if(littleQueue.front().equals("0")){
                littleNo = new NoBinario(root);
                System.out.println("Tirando da fila: "+littleQueue.front());
                littleQueue.dequeue();
                buildTree(littleNo);
            }else if(littleQueue.front().equals("1")){
                String letterBinary = "";
                System.out.println("Tirando da fila antes de entrar no for: "+littleQueue.front());
                littleQueue.dequeue();
                for (int i = 0; i < 8; i++) {
                    letterBinary += littleQueue.front();
                    System.out.println("Tirando da fila: "+littleQueue.front());
                    littleQueue.dequeue();
                }
                System.out.println("Binario em String: " + letterBinary);
                char letter = (char) Integer.parseInt(letterBinary, 2);
                System.out.println("Binario em char: " + letter);
                littleNo = new NoBinario(letter, root);
//                buildTree(littleNo.previous);


            }
        }
        if(root.right == null){
            littleNo = root.right;
            if(littleQueue.front().equals("0")){
                littleNo = new NoBinario(root);
                System.out.println("Tirando da fila: "+littleQueue.front());
                littleQueue.dequeue();
                buildTree(littleNo);
            }else if(littleQueue.front().equals("1")){
                String letterBinary = "";
                System.out.println("Tirando da fila: "+littleQueue.front());
                littleQueue.dequeue();
                for (int i = 0; i < 8; i++) {
                    letterBinary += littleQueue.front();
                    System.out.println("Tirando da fila: "+littleQueue.front());
                    littleQueue.dequeue();
                }
                System.out.println("Binario em String: " + letterBinary);
                char letter = (char) Integer.parseInt(letterBinary, 2);
                System.out.println("Binario em char: " + letter);
                littleNo = new NoBinario(letter, root);
//                buildTree(littleNo.previous);


            }
        }
    }

    public String getEncodedTree() {
        return encodedTree;
    }

}
