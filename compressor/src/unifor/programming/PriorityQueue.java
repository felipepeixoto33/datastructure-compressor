package unifor.programming;


public class PriorityQueue {
    private NoPrioridade primeiro;
    private NoPrioridade ultimo;
    private int cont;

    public PriorityQueue() {
        primeiro = null;
        ultimo = null;
        cont = 0;
    }

    public void enqueue(NoBinario valor) {
        NoPrioridade novo  = new NoPrioridade(valor, valor.data);

        if (primeiro == null) {  // Lista vazia?
            primeiro = novo;
            ultimo = novo;
        } else {



            if( novo.prioridade < primeiro.prioridade) {

                novo.proximo = primeiro;
                primeiro.anterior = novo;
                primeiro = novo;

            } else if(novo.prioridade >= ultimo.prioridade) {

                ultimo.proximo = novo;
                novo.anterior = ultimo;
                ultimo = novo;

            } else {


                NoPrioridade aux = primeiro;

                while( aux != null && novo.prioridade >= aux.prioridade) { //
                    aux = aux.proximo;
                }

                novo.proximo = aux;
                novo.anterior = aux.anterior;
                aux.anterior = novo;
                novo.anterior.proximo = novo;

            }
        }
        cont++;
    }

    public void dequeue() {
        if(primeiro != null) {

            if(length() > 1) {

                primeiro = primeiro.proximo;
                primeiro.anterior = null;

            } else {

                primeiro = null;
                ultimo = null;

            }

        }
        cont--;
    }

    public boolean search(int valor) {
        NoPrioridade aux = primeiro;
        while(aux != null) {
            if( (int) aux.dado == valor) {
                return true;
            }
            aux = aux.proximo;
        }

        return false;
    }

    public int searchIndex(int valor) {

        if(valor < length()) {
            NoPrioridade aux = primeiro;

            for (int i = 0; i < valor; i++) {
                aux = aux.proximo;
            }

            return (int) aux.dado;
        } else {
            System.out.println("It's not possible to get a Object from a position that is not on the list.\n" +
                    "Search Error. Invalid Position.");
            System.exit(1);
            return -1;
        }


    }

    public int searchElement(int posicao) {
        NoPrioridade aux = primeiro;

        for (int i = 0; i < posicao; i++) {
            aux = aux.proximo;
        }

        return (int) aux.dado;
    }

    public int length() {
        return this.cont;
    }

    public NoBinario front() {
        if(primeiro != null) {
            return (NoBinario) primeiro.dado;
        }

        return null;

    }

    public void show() {
        NoPrioridade aux = primeiro;

        while (aux != null) {
            NoBinario test = (NoBinario) aux.dado;
            System.out.print(test.data + "(" + test.letter + ") ");
            aux = aux.proximo;
        }
        System.out.println();
    }

    public void clear() {

        while(length() > 0) {

            dequeue();

        }

    }
}