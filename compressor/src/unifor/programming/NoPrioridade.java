package unifor.programming;

class NoPrioridade {
    public NoPrioridade proximo;
    public int prioridade;
    public NoPrioridade anterior;
    public Object dado;

    public NoPrioridade(Object dado, int prioridade) {
        this.dado = dado;
        this.prioridade = prioridade;
        this.anterior = null;
        this.proximo = null;
    }
}
