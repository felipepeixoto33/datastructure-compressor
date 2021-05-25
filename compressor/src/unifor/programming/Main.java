package unifor.programming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        PriorityQueue fQueue = new PriorityQueue();
        Vector listaLetras = new Vector();
        //Receiving frequencies and characters

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/doc/texto.txt"));
            while (buffer.ready()){
                char[] phrase = buffer.readLine().toCharArray();
                for (int i = 0; i < phrase.length; i++){
                    System.out.println(phrase[i]);
                }
            }
        }catch (Error e){
            System.out.println("Cry T.T");
        }




//        NoBinario noA = new NoBinario(1, 'a');
//        NoBinario noB = new NoBinario(2, 'b');
//        NoBinario noC = new NoBinario(3, 'c');
//        NoBinario noD = new NoBinario(4, 'd');
//        NoBinario noE = new NoBinario(5, 'e');
//        NoBinario noF = new NoBinario(6, 'f');
//
//        fQueue.enqueue(noA, 1);
//        fQueue.enqueue(noB, 2);
//        fQueue.enqueue(noC, 3);
//        fQueue.enqueue(noD, 4);
//        fQueue.enqueue(noE, 5);
//        //fQueue.enqueue(noF, 6);
//        //fQueue.show();
//
//
//
//        //Creating the nodes
//
//        HuffmanTree tree = new HuffmanTree();
//
//        while(fQueue.length() > 1) {
//            int cont = 0;
//
//            NoBinario first = fQueue.front();
//            fQueue.dequeue();
//            NoBinario second = fQueue.front();
//            fQueue.dequeue();
//
//            NoBinario intern = new NoBinario(first.data + second.data, '/');
//            intern.left = first;
//            intern.right = second;
//
//            fQueue.enqueue(intern, intern.data);
//        }
//        String code = "";
//
//        decode(fQueue.front(), code);
//
//    }
//
//    public static void decode(NoBinario root, String code) {
//
//        if(root.left == null && root.right == null && Character.isLetter(root.letter)) {
//            System.out.println(root.letter + " : " + code);
//            return;
//        }
//
//        decode(root.left, code + "0");
//        decode(root.right, code + "1");

    }

}
