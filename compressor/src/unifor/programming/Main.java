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

        while (operation != 1 && operation != 2) {
            System.out.println("Operação inválida. Operações dispoíveis: 1 (Compactar), 2 (Descompactar).");
            operation = scanner.nextInt();
        }

        if (operation == 1) {
            compact(fileName);
        } else {
            decompact(fileName);
        }

    }

    public static void compact(String fileName) throws Exception {

        Vector allCharacters = new Vector();
        Vector allText = new Vector();
        PriorityQueue fQueue = new PriorityQueue();
        Vector frequencyList = new Vector(256); //Lista com as frequência de cada caractere da tabela ASCII.
        for (int i = 0; i < 255; i++) {
            frequencyList.adicionar(0);
        }

        //Receiving frequencies and characters

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/doc/" + fileName + ".txt"));
            boolean multipleLines = false; //Indica se o arquivo tem ou não multiplas linhas.
            while (buffer.ready()) { //Ler o arquivo de texto, linha por linha.
                Vector frase = new Vector(); //Array com a linha atual, char por char, mas com a possibilidade de se adicionar '\n'
                char[] phrase = buffer.readLine().toCharArray(); //Pega cada linha do arquivo e a transforma em um Array de Chars.

                for (int i = 0; i < phrase.length; i++) {
                    frase.adicionar(phrase[i]);
                }

                if (multipleLines) {
                    frase.adicionar('\n', 0); //Caso o arquivo possua multiplas linhas, adiciona um '\n' no início da frase.
                }

                //frase.exibir();

                for (int i = 0; i < frase.tamanho(); i++) {
                    allCharacters.adicionar(frase.pesquisarElemento(i)); //Add the current element to the 'allLetters' array

                    if (!allText.pesquisar(frase.pesquisarElemento(i))) {
                        allText.adicionar(frase.pesquisarElemento(i)); //Array com todos os tipos de letras que existem no arquivo.
                    }

                }

                //Add '\n' to phrase.
                //Caso esse FOR execute mais de uma vez, significa que o arquivo tem mais de uma linha.
                multipleLines = true;

                //fQueue.show();
            }

            allText.exibir();

            for (int i = 0; i < allText.tamanho(); i++) {
                // Passa pelo array com cada tipo de letra e registra a sua frequência

                int position = (char) allText.pesquisarElemento(i); //Position on the ASCII table
                if (position > 255) {
                    continue;
                }

                System.out.println(i + "# " + position);
                System.out.println("element:" + allText.pesquisarElemento(i));
                //System.out.println(phrase[i] + " pos ASC: " + position );

                int value = (int) frequencyList.pesquisarElemento(position) + 1;
                frequencyList.remover(position);
                frequencyList.adicionar(value, position);


            }

            for (int i = 0; i < frequencyList.tamanho(); i++) {
                //Para cada letra que aparecer, cria um nó e o insere na lista de prioridade pela frequência.
                char character = (char) (i);
                if (frequencyList.hasSomething(i)) {
                    NoBinario huffmanNode = new NoBinario((int) frequencyList.pesquisarElemento(i), character);
                    fQueue.enqueue(huffmanNode);
                }
            }


        } catch (Error e) {
            System.out.println("Cry T.T");
        }


        //Creating the nodes


        while (fQueue.length() > 1) {
            //Cria a árvore de Huffmann.
            /*
            Enquanto o tamanho da lista com os nós for maior que um, pega
            os dois primeiros nós e cria um novo nó pai, com a soma da frequência deles
            e com o filho esquerdo sendo o primeiro nó filho, e o direito, o segundo.
            */
            NoBinario first = fQueue.front();
            fQueue.dequeue();
            NoBinario second = fQueue.front();
            fQueue.dequeue();

            NoBinario intern = new NoBinario(first.data + second.data, Character.MIN_VALUE);
            intern.left = first;
            intern.right = second;

            fQueue.enqueue(intern);

        }

        //Após criar a árvore na nossa lista de prioridade, o adicionamos à nossa classe da Árvore de Huffmann
        HuffmanTree tree = new HuffmanTree(fQueue.front());
        //tree.show();

        String code = ""; // Código de cada letra de acordo com sua posição na árvore.
        Vector codes = new Vector(); // Array que guarda as letras do arquivo seguidas de seus códigos de posição.

        encode(fQueue.front(), code, codes); // Método responsável por codificar cada letra na árvore.

        //codes.exibir();

        //System.out.println(codes);

        //Putting the code and table into our file

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/doc/" + fileName + "-c.txt"));
        //Cria um arquivo para depositar a versão compilada do arquivo raiz.

        tree.encodeTree(); //Encodifica a árvore

        writer.write(tree.getEncodedTree()); //Pega a String com a árvore codificada e a escreve no arquivo.

        writer.write("\n");

        for (int i = 0; i < allCharacters.tamanho(); i++) {
            /*
            Percorre o array com todas as letras do arquivo e adiciona o seu respectivo
            código ao arquivo.
            */
            for (int j = 0; j < codes.tamanho(); j++) {
                //Para cada letra no arquivo, pesquisa o seu respectivo código no array de códigos.
                String actual = (String) codes.pesquisarElemento(j);
                //String a letra atual seguida do seu próprio código.
                if (actual.charAt(0) == (char) allCharacters.pesquisarElemento(i)) {
                    //Caso o código encontrado seja a da letra atual, a escreve no arquivo.
                    //System.out.print(actual.substring(1));
                    writer.write(actual.substring(1));
                }
            }
        }

        writer.close();

    }


    public static void decompact(String fileName) throws Exception {
        System.out.println("Descompactando...");
        Vector vectorTreeBinary = new Vector(2);
        Queue queueBinary;
        HuffmanTree huffmanTree = new HuffmanTree();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/doc/" + fileName + "-c.txt"));
            while (buffer.ready()) {
                System.out.println("entrou");
                String data = buffer.readLine();
                String[] lineArr = data.split("");
                int size = lineArr.length;
                queueBinary = new Queue();

                for (int i = 0; i < size; i++) {
                    queueBinary.enqueue(lineArr[i]);
                }
                vectorTreeBinary.adicionar(queueBinary);
            }
        } catch (Error err) {
            System.out.println("Você deve compactar primeiro!");
        }

//        for(int i = 0; i < vectorTreeBinary.tamanho(); i++){
//            huffmanTree.buildTree((Queue) vectorTreeBinary.pesquisarElemento(i));
//        }

        huffmanTree.buildTree((Queue) vectorTreeBinary.pesquisarElemento(0));
        huffmanTree.buildData((Queue) vectorTreeBinary.pesquisarElemento(1));


        BufferedWriter writer = new BufferedWriter(new FileWriter("src/doc/" + fileName + "-dc.txt"));
        writer.write(huffmanTree.getAllText());
        writer.close();
    }


    public static void encode(NoBinario root, String code, Vector storeArray) {

        if (root.right == null && root.left == null) { // Is it a Leaf Node?
            storeArray.adicionar(root.letter + code);
            return;
        }

        /*
        Percorre toda a árvore de huffmann e, quando entra em um nó esquerdo, adiciona '0' ao código
        da letra atual, ao encontrar um nó direito, adiciona um.
        Após encontrar o nó folha, guarda o código da letra no array com todos os códigos.
        */
        encode(root.left, code + "0", storeArray);
        encode(root.right, code + "1", storeArray);

    }

}
