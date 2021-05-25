package unifor.programming;

public class Vector {
    private No primeiro;
    private No ultimo;
    private int contadora;

    public Vector(){
        primeiro = null;
        ultimo = null;
        contadora = 0;
    }
    public void adicionar(Object valor){
        contadora++;
        No novo = new No(valor);
        if(primeiro == null){
            primeiro = novo;
            ultimo = novo;
        }else{
            ultimo.proximo = novo;
            ultimo = novo;
        }
    }
    public void adicionar(Object valor, int posicao){
        contadora++;
        No novo = new No(valor);

        if(primeiro == null) {
            primeiro = novo;
            ultimo = novo;
        }
        if(posicao >= 0 && posicao < contadora) {

            if (posicao == 0) {
                novo.proximo = primeiro;
                primeiro = novo;
            } else if (posicao == contadora - 1) {
                ultimo.proximo = novo;
                ultimo = novo;
            } else {
                No aux = primeiro;
                int i = 0;
                while (i < posicao - 1) {
                    aux = aux.proximo;
                    i++;
                }
                novo.proximo = aux.proximo;
                aux.proximo = novo;
            }
        }else{
            System.out.println("Posiçao invalida jovem");
        }
    }
    public void remover(int posicao){
        if(primeiro != null && posicao >= 0 && posicao < contadora) {
            No aux = primeiro;
            if(posicao == 0){
                primeiro = primeiro.proximo;
            }else {
                int i = 0;  //1
                while (i < posicao - 1) {
                    aux = aux.proximo;
                    i++;
                }
                aux.proximo = aux.proximo.proximo;
                if(posicao == contadora -1){
                    ultimo = aux;
                }
            }
            contadora--;
        }else{
            System.out.println("Posiçao invalida jovem");
        }
    }
    public boolean pesquisar(Object valor){
        No aux = primeiro;
        while (aux != null){
            if(aux.dado == valor){
                return true;
            }
            aux = aux.proximo;
        }
        return false;
    }
    public int pesquisarIndice(Object valor){
        No aux = primeiro;
        int i = 0;
        while (aux != null) {
            if(aux.dado == valor){
                return i;
            }
            i++;
            aux = aux.proximo;
        }
        return -1;
    }
    public Object pesquisarElemento(int posicao) throws Exception{
        if(posicao >= 0 && posicao < contadora) {
            No aux = primeiro;
            int i = 0;
            while (i < posicao) {
                aux = aux.proximo;
                i++;
            }
            return aux.dado;
        }else{
            throw new Exception("Posição inválida");
        }
    }
    public void exibir(){
        No aux = primeiro;
        while (aux != null){
            System.out.print(aux.dado + " ");
            aux = aux.proximo;
        }
        System.out.println();
    }
    public int tamanho(){
        return contadora;
    }
}
