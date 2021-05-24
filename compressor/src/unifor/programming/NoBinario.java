package unifor.programming;

public class NoBinario {
    int data;
    char letter;
    NoBinario left;
    NoBinario right;

    public NoBinario(int data, char letter) {
        this.data = data;
        this.letter = letter;
        this.left = null;
        this.right = null;
    }
}