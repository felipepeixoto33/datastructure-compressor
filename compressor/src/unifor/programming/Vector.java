package unifor.programming;

import java.io.IOException;

public class Vector {
    private Object v[];
    private int contador;

    public Vector() {
        v = new Object[10];
        contador = 0;
    }
    public Vector(int n) {
        contador = 0;
        v = new Object[n];
    }
    public void adicionar(Object valor) {
        if(contador == v.length){
            Object aux[] = new Object[(int)(v.length * 1.5)];
            for (int i = 0; i < v.length; i++){
                aux[i] = v[i];
            }
            v = aux;
        }
        v[contador] = valor;
        contador++;
    }
    public void adicionar(Object valor, int posicao) {
        if (posicao <= contador && contador < v.length) {
            for (int i = contador; i > posicao; i--) {
                v[i] = v[i - 1];
            }
            v[posicao] = valor;
            contador++;
        }else if(posicao == v.length){
            Object aux[] = new Object[(int)(v.length * 1.5)];
            for (int i = 0; i < v.length; i++){
                aux[i] = v[i];
            }
            v = aux;
            v[posicao] = valor;
            contador++;
        }else if(posicao > v.length){
            System.out.println("Você não pode por a carroça na frente dos bois!!");
        }

    }
    public void remover(int posicao) {
        if(posicao < contador) {
            for (int i = posicao; i < contador - 1; i++) {
                v[i] = v[i + 1];
            }
            contador--;
        }else{
            System.out.println("Não existe valor nessa posição!!");
        }
    }
    public boolean pesquisar(Object valor) {
        for (int i = 0; i < contador; i++){
            if(v[i].equals(valor)) {
                return true;
            }
        }
        return false;
    }
    public Object pesquisarElemento(int posicao) throws Exception {
        if(posicao < contador) {
            return v[posicao];
        }else{
            throw new Exception("Posição inválida");
        }
    }
    public boolean hasSomething(int posicao){
        if(posicao < contador) {
            if ((int)v[posicao] != 0)
            return true;
        }
        return false;
    }
    public int pesquisarIndice(Object valor){
        for (int i = 0; i < contador; i++){
            if(v[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }
    public int tamanho() {
        return contador;
    }
    public void exibir() {
        for(int i = 0; i < contador;i++){
            System.out.print(v[i]+" ");
        }
        System.out.println();
    }
}
