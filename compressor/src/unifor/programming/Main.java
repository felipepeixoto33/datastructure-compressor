package unifor.programming;

import java.awt.image.BufferedImage;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        decompile("texto2");


    }

    public static void compile(String fileName) throws Exception {

        Vector allLetters = new Vector();
        PriorityQueue fQueue = new PriorityQueue();
        Vector frequencyList = new Vector(26);
        for(int i = 0; i < 26; i++){
            frequencyList.adicionar(0);
        }

        //Receiving frequencies and characters

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/doc/" + fileName + ".txt"));
            while (buffer.ready()){
                char[] phrase = buffer.readLine().toCharArray();
                for (int i = 0; i < phrase.length; i++){
                    allLetters.adicionar(Character.toLowerCase(phrase[i])); //Add the current element to the 'allLetters' array
                    int position = (int) Character.toLowerCase(phrase[i]) - 97;
                    int value = (int)frequencyList.pesquisarElemento(position)+1;
                    frequencyList.remover(position);
                    frequencyList.adicionar(value, position);
                }
                for(int i = 0; i < frequencyList.tamanho(); i++){
                    char character = (char)(i+97);
                    if(frequencyList.hasSomething(i)){
                        fQueue.enqueue(new NoBinario( (int) frequencyList.pesquisarElemento(i), character ), (int) frequencyList.pesquisarElemento(i) );
                    }
                }
                //fQueue.show();
            }
        }catch (Error e){
            System.out.println("Cry T.T");
        }


        //Creating the nodes

        HuffmanTree tree = new HuffmanTree();

        while(fQueue.length() > 1) {
            int cont = 0;

            NoBinario first = fQueue.front();
            fQueue.dequeue();
            NoBinario second = fQueue.front();
            fQueue.dequeue();

            NoBinario intern = new NoBinario(first.data + second.data, '/');
            intern.left = first;
            intern.right = second;

            fQueue.enqueue(intern, intern.data);
        }

        String code = "";
        Vector codes = new Vector();
        encode(fQueue.front(), code, codes);
        codes.exibir();

        //Putting the code and table into our file

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/doc/texto2.txt"));
        for(int i = 0; i < allLetters.tamanho(); i++) {
            for(int j = 0; j < codes.tamanho(); j++) {
                String actual = (String) codes.pesquisarElemento(j);
                if(actual.charAt(0) == (char) allLetters.pesquisarElemento(i)){

                    System.out.print(actual.substring(1));
                    writer.write(actual.substring(1));
                }
            }
        }

        writer.write("-");

        for(int i = 0; i < codes.tamanho(); i++) {

            writer.write((String) codes.pesquisarElemento(i));

        }

        writer.write(";");
        writer.close();

    }

    public static void decompile(String fileName) throws Exception {

        BufferedReader buffer = new BufferedReader(new FileReader("src/doc/" + fileName + ".txt"));
        //System.out.println(buffer.readLine());

        String encoded = "";
        String table = "";
        Vector letters = new Vector();
        Vector codes = new Vector();

        while(buffer.ready()) {
            //System.out.println(buffer.readLine());
            char[] line = buffer.readLine().toCharArray();
            //System.out.println(line);
            boolean tableOver = false;
            for(int i = 0; i < line.length; i++) {
                //System.out.println(line[i]);

                if(line[i] == '-') {
                    tableOver = true;
                    continue;
                }

                if(!tableOver) {
                    encoded += line[i];
                } else {

                    table += line[i];

                    if(Character.isDigit(line[i])) {
                        String aCode = "";
                        while(Character.isDigit(line[i])) {
                            aCode += line[i];
                            i++;
                        }
                        codes.adicionar(aCode);
                    }

                    if(Character.isLetter(line[i])) {
                        letters.adicionar(line[i]);
                    }



                }

            }

            if(letters.tamanho() != codes.tamanho()) {
                System.out.println("Error: Table is Wrong");
            }

        }


//        letters.exibir();
//        codes.exibir();

        String decoded = "";
        String aCode = "";

        for(int i = 0; i < encoded.length(); i++) {

            if(codes.pesquisar(aCode)) {
                //System.out.println( letters.pesquisarElemento( codes.pesquisarIndice(aCode) ) );

                decoded += letters.pesquisarElemento( codes.pesquisarIndice(aCode) );
                aCode = "";
            }

            aCode += encoded.charAt(i);

        }

        BufferedWriter writer = new BufferedWriter( new FileWriter("src/doc/texto3.txt") );
        writer.write(decoded);
        writer.close();

    }

    public static void encode(NoBinario root, String code, Vector storeArray) {

        if(root.left == null && root.right == null && Character.isLetter(root.letter)) {
            //System.out.println(root.letter + " : " + code);
            storeArray.adicionar(root.letter + code);
            return;
        }

        encode(root.left, code + "0", storeArray);
        encode(root.right, code + "1", storeArray);

    }

    public static void putCode(String[] args) {

    }

}
