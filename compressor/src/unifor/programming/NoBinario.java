package unifor.programming;

public class NoBinario {
    int data; //Freq
    char letter;
    NoBinario left;
    NoBinario right;

    public NoBinario(int data, char letter) {
        this.data = data;
        this.letter = letter;
        this.left = null;
        this.right = null;
    }
    public NoBinario(){
        this.letter = Character.MIN_VALUE;
        this.left = null;
        this.right = null;
    }
    public NoBinario(char letter){
        this.letter = letter;
        this.left = null;
        this.left = null;
    }
}