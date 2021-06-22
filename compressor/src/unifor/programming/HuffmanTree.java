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
        /*
        Percorre toda a árvore e a codifica, adicionando '0' caso o nó
        encontrado não seja um nó folha e '1', caso seja.
        Caso seja um nó folha, também adiciona a representação
        binária de sua letra ao seu lado.
        Adiciona tudo isso a uma String para a posterior utilização.
        */
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
        //Caso o primeiro valor da fila seja igual a 0, ele tira esse valor da fila e é criado um novo nó, do qual vira o nó raiz.
        //Caso seja igual a 1, ele tira esse valor da fila, e pega concatena os proximos 8 valores da fila
        //e transforma para letra.

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
         /*
        É criada a árvore de forma recursiva, onde eu checo se não existe naquele determinado nó passado para a função
        se não existe o nó esquerdo ou direito, e se nesse nó não tem nenhuma letra, caso as condições sejam satisfeitas
        ele checara pelo primeiro valor da fila, caso seja igual a 0, ele criará um novo nó no ramo esquerdo e direito
        do nó raiz e chamará novamente a função, caso seja igual a 1 ele pegara os próximos 8 valores da fila,
         e transformará esse binario em letra e atribuirá essa letra ao novo nó.
         */
        //root.letter == Character.MIN_VALUE, quer dizer que não há nenhuma letra nesse nó
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
        NoBinario raiz = root;
        NoBinario aux = root;
        while (!littleQueueData.empty()){

            //root.letter == Character.MIN_VALUE, quer dizer que não há nenhuma letra neesse nó
            //Enquanto a fila não estiver vazia,ele checara se naquele nó tem alguma letra, caso tenha
            //ele concatena numa string essa letra.Ele pegará o primeiro valor da fila, caso seja igual a 0
            //ele percorre a arvore para a esquerda, caso seja igual a 1 ele percorre a arvore para a direita

            if (aux.letter != Character.MIN_VALUE) {
                allText += aux.letter;
                aux = raiz;
            } else {
                if(littleQueueData.front().equals("0")){
                    littleQueueData.dequeue();
                    aux = aux.left;
                }else if(littleQueueData.front().equals("1")){
                    littleQueueData.dequeue();
                    aux = aux.right;
                }
            }
        }
        allText += aux.letter;

//*******************VERSÃO RECURSIVA NA QUAL PASSEI 3HRS PRA NO FINAL DA STACKOVERFLOW*******************

//        if (root.letter != Character.MIN_VALUE) {
//            allText += root.letter;
//            buildData(this.root);
//
//        } else {
//
//            if (!littleQueueData.empty() && littleQueueData.front().equals("0")) {
//                littleQueueData.dequeue();
//                buildData(root.left);
//            }
//            if (!littleQueueData.empty() && littleQueueData.front().equals("1")) {
//                littleQueueData.dequeue();
//                buildData(root.right);
//            }
//        }

    }

    public String getEncodedTree() {
        return encodedTree;
    }

    public String getAllText() {
        return allText;
    }
}
