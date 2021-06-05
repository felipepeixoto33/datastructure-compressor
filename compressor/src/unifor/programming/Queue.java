package unifor.programming;

public class Queue {
    private No primeiro;
    private No ultimo;
    private int contadora;

    public Queue() {
        primeiro = null;
        ultimo = null;
        contadora = 0;
    }

    public void enqueue(Object valor) {
        contadora++;
        No novo = new No(valor);
        if (primeiro == null) {
            primeiro = novo;
            ultimo = novo;
        } else {
            ultimo.proximo = novo;
            ultimo = novo;
        }
    }

    public void dequeue() {
        if (contadora > 1) {
            primeiro = primeiro.proximo;
            contadora--;
        } else if (contadora == 1) {
            primeiro = null;
            ultimo = null;
            contadora--;
        }/* else {
                System.out.println("Não é possivel remover o valore");
            }*/
    }


    public boolean search(Object valor) {
        No aux = primeiro;
        while (aux != null) {
            if (aux.dado.equals(valor)) {
                return true;
            }
            aux = aux.proximo;
        }
        return false;
    }

    public void show() {
        No aux = primeiro;
        while (aux != null) {
            System.out.print(aux.dado + " ");
            aux = aux.proximo;
        }
        System.out.println();
    }

    public boolean empty() {
        return (contadora == 0) ? true : false;
    }

    public Object front() {
        return primeiro.dado;
    }

    public int size() {
        return contadora;
    }

}
