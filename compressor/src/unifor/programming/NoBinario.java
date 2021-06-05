package unifor.programming;

public class NoBinario {
    int data; //Freq
    char letter;
    NoBinario previous;
    NoBinario left;
    NoBinario right;

    public NoBinario(int data, char letter) {
        this.data = data;
        this.letter = letter;
        this.left = null;
        this.right = null;
    }
    public NoBinario(NoBinario previous){
        this.previous = previous;
        this.left = null;
        this.right = null;
    }
    public NoBinario(char letter, NoBinario previous){
        this.letter = letter;
        this.previous = previous;
        this.left = null;
        this.left = null;
    }
}