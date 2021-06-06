package unifor.programming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HuffmanTree {
    private NoBinario root;
    private String code = "";
    private String encodedTree = "";
    private Queue littleQueueTree;
    private Queue littleQueueData;

    private String allText = "";

    public HuffmanTree() throws IOException {
        this.root = null;
    }

    public HuffmanTree(NoBinario root) throws IOException {
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
            // Integer.toBinaryString(root.letter)
            String binario = String.format("%8s", Integer.toBinaryString(root.letter)).replace(' ', '0');

            encodedTree += "1" + binario;
            return;
        } else {
            encodedTree += "0";
        }

        encodeTree(root.left);

        encodeTree(root.right);

    }
    public void buildTree(Queue queueTree) {
        littleQueueTree = queueTree;

        if(root == null && littleQueueTree.front().equals("0")){
            NoBinario littleRoot = new NoBinario();
            root = littleRoot;
            littleQueueTree.dequeue();
            buildTree(root);
        }else if(littleQueueTree.front().equals("1")){
            String letterBinary = "";
            littleQueueTree.dequeue();
            for (int i = 0; i < 8; i++) {
                letterBinary += littleQueueTree.front();
                littleQueueTree.dequeue();
            }
            System.out.println("Left Binario em String: " + letterBinary);
            char letter = (char) Integer.parseInt(letterBinary, 2);
            System.out.println("Left Binario em char: " + letter);
            NoBinario littleRoot = new NoBinario(letter);
            root = littleRoot;
        }
    }

    private void buildTree(NoBinario root) {
        if(root.left == null && root.letter == Character.MIN_VALUE){
            if(littleQueueTree.front().equals("0")){
                NoBinario littleRoot = new NoBinario();
                root.left = littleRoot;
                littleQueueTree.dequeue();
                buildTree(root.left);
            }else if(littleQueueTree.front().equals("1")){
                String letterBinary = "";
                littleQueueTree.dequeue();
                for (int i = 0; i < 8; i++) {
                    letterBinary += littleQueueTree.front();
                    littleQueueTree.dequeue();
                }
                System.out.println("Left Binario em String: " + letterBinary);
                char letter = (char) Integer.parseInt(letterBinary, 2);
                System.out.println("Left Binario em char: " + letter);
                NoBinario littleRoot = new NoBinario(letter);
                root.left = littleRoot;
//                buildTree(littleNo.previous);


            }
        }

        if(root.right == null && root.letter == Character.MIN_VALUE){
            if(littleQueueTree.front().equals("0")){
                root.right = new NoBinario();
                littleQueueTree.dequeue();
                buildTree(root.right);
            }else if(littleQueueTree.front().equals("1")){
                String letterBinary = "";
                littleQueueTree.dequeue();
                for (int i = 0; i < 8; i++) {
                    letterBinary += littleQueueTree.front();
                    littleQueueTree.dequeue();
                }
                System.out.println("Right Binario em String: " + letterBinary);
                char letter = (char) Integer.parseInt(letterBinary, 2);
                System.out.println("Right Binario em char: " + letter);
                root.right = new NoBinario(letter);
//                buildTree(littleNo.previous);


            }
        }
    }

    public void buildData(Queue queueData) throws IOException {
        littleQueueData = queueData;


        if(root != null){
            buildData(root);
        }else{
            System.out.println("Root é nula");
        }

        System.out.println(allText);
    }
    private void buildData(NoBinario root) throws IOException {

        if (root.letter != Character.MIN_VALUE) {
            System.out.print(root.letter);

            allText += root.letter;
            buildData(this.root);

        } else {

            if (!littleQueueData.empty() && littleQueueData.front().equals("0")) {
                littleQueueData.dequeue();
                buildData(root.left);
            }
            if (!littleQueueData.empty() && littleQueueData.front().equals("1")) {
                littleQueueData.dequeue();
                buildData(root.right);
            }
        }
    }

    public String getEncodedTree() {
        return encodedTree;
    }

    public String getAllText() {
        return allText;
    }
}
