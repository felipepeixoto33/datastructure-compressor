package unifor.programming;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Arquivo: ");
        String fileName = scanner.nextLine();

        System.out.println("Digite o número para a operação a ser realizada. 1 = Compactar. 2 = Descompactar.");
        int operation = scanner.nextInt();

        while(operation != 1 && operation != 2) {
            System.out.println("Operação inválida. Operações dispoíveis: 1 (Compactar), 2 (Descompactar).");
            operation = scanner.nextInt();
        }

        if(operation == 1) {
            compact(fileName);
        } else {
            decompact(fileName);
        }

    }

    public static void compact(String fileName) throws Exception {

        Vector allCharacters = new Vector();
        PriorityQueue fQueue = new PriorityQueue();
        Vector frequencyList = new Vector(256);
        for(int i = 0; i < 255; i++){
            frequencyList.adicionar(0);
        }

        //Receiving frequencies and characters

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/doc/" + fileName + ".txt"));
            while (buffer.ready()){
                char[] phrase = buffer.readLine().toCharArray();
                for (int i = 0; i < phrase.length; i++){
                    allCharacters.adicionar(phrase[i]); //Add the current element to the 'allLetters' array
                    int position = phrase[i];
                    //System.out.println(phrase[i] + " pos ASC: " + position );
                    int value = (int)frequencyList.pesquisarElemento(position)+1;
                    frequencyList.remover(position);
                    frequencyList.adicionar(value, position);
                }
                for(int i = 0; i < frequencyList.tamanho(); i++){
                    char character = (char)(i);
                    if(frequencyList.hasSomething(i)){
                        NoBinario huffmanNode = new NoBinario((int) frequencyList.pesquisarElemento(i), character);
                        fQueue.enqueue( huffmanNode );
                    }
                }
                //fQueue.show();
            }
        } catch (Error e){
            System.out.println("Cry T.T");
        }


        //Creating the nodes



        while(fQueue.length() > 1) {

            NoBinario first = fQueue.front();
            fQueue.dequeue();
            NoBinario second = fQueue.front();
            fQueue.dequeue();

            NoBinario intern = new NoBinario(first.data + second.data, Character.MIN_VALUE);
            intern.left = first;
            intern.right = second;

            fQueue.enqueue(intern);

        }

        HuffmanTree tree = new HuffmanTree(fQueue.front());
        //tree.show();

        String code = "";
        Vector codes = new Vector();

        encode(fQueue.front(), code, codes);

        //codes.exibir();

        //System.out.println(codes);

        //Putting the code and table into our file

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/doc/" + fileName + "-c.txt"));

        tree.encodeTree();

        writer.write(tree.getEncodedTree());

        writer.write("\n");

        for(int i = 0; i < allCharacters.tamanho(); i++) {
            for(int j = 0; j < codes.tamanho(); j++) {
                String actual = (String) codes.pesquisarElemento(j);
                if(actual.charAt(0) == (char) allCharacters.pesquisarElemento(i)){

                    //System.out.print(actual.substring(1));
                    writer.write(actual.substring(1));
                }
            }
        }

        writer.close();

    }


    public static void decompact(String fileName) throws Exception {
        Vector vectorTreeBinary = new Vector(2);
        Queue queueBinary;
        HuffmanTree huffmanTree = new HuffmanTree();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/doc/" + fileName + "-c.txt"));
            while (buffer.ready()){
                String data = buffer.readLine();
                int size = data.split("").length;
                queueBinary = new Queue();
                for(int i = 0; i < size; i++){
                    queueBinary.enqueue(data.split("")[i]);
                }
                vectorTreeBinary.adicionar(queueBinary);
            }
        }catch (Error err){
            System.out.println("Você deve compactar primeiro!");
        }

//        for(int i = 0; i < vectorTreeBinary.tamanho(); i++){
//            huffmanTree.buildTree((Queue) vectorTreeBinary.pesquisarElemento(i));
//        }

        huffmanTree.buildTree((Queue) vectorTreeBinary.pesquisarElemento(0));
        huffmanTree.buildData((Queue) vectorTreeBinary.pesquisarElemento(1));

    }


    public static void encode(NoBinario root, String code, Vector storeArray) {

        if(root.right == null && root.left == null) { // Is it a Leaf Node?
            storeArray.adicionar(root.letter + code);
            return;
        }

        encode(root.left, code + "0", storeArray);
        encode(root.right, code + "1", storeArray);

    }

}
