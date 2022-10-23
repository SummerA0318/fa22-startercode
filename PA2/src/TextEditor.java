/*
    Name: Yubing Lin
    PID:  A16994291
 */

/**
 * Methods to edit texts
 * @author Yubing Lin
 * @since  Oct 10, 2022
 */
public class TextEditor {

    /* instance variables */
    private String text;
    private IntStack undo;
    private StringStack deletedText;
    private IntStack redo;
    private StringStack insertedText;
    public final int CAPACITY = 10;
    public final int DELETE_CODE = 2;
    public final int UNDO_RECORD = 3;
    public final int REDO_RECORD = 3;
    public final int LOWER_BOUND = 2;

    /**
     * Constructor for the class. Initialize the variables.
     */
    public TextEditor() {
        this.text = "";
        this.undo = new IntStack(CAPACITY);
        this.deletedText = new StringStack(CAPACITY);
        this.redo = new IntStack(CAPACITY);
        this.insertedText = new StringStack(CAPACITY);
    }

    /**
     * Get the text
     * @return the text being operated in the class
     */
    public String getText() {
        return this.text;
    }

    /**
     * Get the length of the text
     * @return the length of text the string
     */
    public int length() {
        return this.text.length();
    }

    /**
     * Convert the characters from index i to j to be in reverse case
     * @param i the starter index, inclusive
     * @param j the ending index, exclusive
     * @throws IllegalArgumentException if i or j is out of range of the text
     * or i is not smaller than j
     */
    public void caseConvert(int i, int j) {
        if (i < 0 | j > this.length() | i >= j) {
            throw new IllegalArgumentException();
        }
        String newText = this.text.substring(0, i);
        // Determine if each character between index i and j are letter and convert to reverse case
        for (int k=i; k<j; k++) {
            char cha = this.text.charAt(k);
            if (Character.isUpperCase(cha)) {
                newText += Character.toLowerCase(cha);
            } else if (Character.isLowerCase(cha)) {
                newText += Character.toUpperCase(cha);
            } else {
                newText += cha;
            }
        }
        newText += this.text.substring(j);
        this.text = newText;
        int[] caseRecord = {i, j, 0};
        this.undo.multiPush(caseRecord);
    }

    /**
     * Insert some content into the text at given index
     * @param i the given index to insert the content, i is where the index where the
     *          first character of input should be at
     * @param input the given string to insert into the text
     * @throws NullPointerException if the input string is null
     * @throws IllegalArgumentException if i is out of range of the text
     */
    public void insert(int i, String input) {
        if (input == null) {
            throw new NullPointerException();
        }
        if (i < 0 | i > this.length()) {
            throw new IllegalArgumentException();
        }
        String newText = this.text.substring(0, i) + input + this.text.substring(i);
        this.text = newText;
        int[] insertRecord = {i, i+input.length(), 1};
        this.undo.multiPush(insertRecord);
    }

    /**
     * Delete parts of the text from index i to j
     * @param i the starter of deleted content, inclusive
     * @param j the ending of deleted content, exclusive
     * @throws IllegalArgumentException if i or j is out of range of the text
     * or i is not smaller than j
     */
    public void delete(int i, int j) {
        if (i < 0 | j > this.length() | i >= j) {
            throw new IllegalArgumentException();
        }
        String newText = this.text.substring(0, i) + this.text.substring(j);
        this.deletedText.push(this.text.substring(i, j));
        this.text = newText;
        int[] deleteRecord = {i, j, DELETE_CODE};
        this.undo.multiPush(deleteRecord);
    }

    /**
     * Undo the previous operation based on the recorded operation, indexes of operation
     * and change in text
     * @return true if undo successfully, false otherwise
     */
    public boolean undo() {
        if (this.undo.isEmpty()) {
            return false;
        }
        int[] lastDone = this.undo.multiPop(UNDO_RECORD);
        // Determine which operation the previous one is based on the record
        // Then accordingly undo the operation
        if (lastDone[0] == 0) {
            this.redo.multiPush(new int[] {lastDone[LOWER_BOUND], lastDone[1], 0});
            this.caseConvert(lastDone[LOWER_BOUND], lastDone[1]);
            this.undo.multiPop(UNDO_RECORD);
        }
        if (lastDone[0] == 1) {
            this.redo.multiPush(new int[] {lastDone[LOWER_BOUND], lastDone[1], 1});
            this.insertedText.push(this.text.substring(lastDone[LOWER_BOUND], lastDone[1]));
            this.text = this.text.substring(0, lastDone[LOWER_BOUND])
                    + this.text.substring(lastDone[1]);
        }
        if (lastDone[0] == DELETE_CODE) {
            this.redo.multiPush(new int[] {lastDone[LOWER_BOUND], lastDone[1], DELETE_CODE});
            String deleted = this.deletedText.pop();
            int len = deleted.length();
            String newStr = this.text.substring(0, lastDone[LOWER_BOUND]) + deleted
                    + this.text.substring(lastDone[1] - len);
            this.text = newStr;
        }
        return true;
    }

    /**
     * Redo the previous operation based on the recorded operation, indexes of operation
     * and change in text
     * @return true if undo successfully, false otherwise
     */
    public boolean redo() {
        if (this.redo.isEmpty()) {
            return false;
        }
        int[] undone = this.redo.multiPop(REDO_RECORD);
        this.redo.clear();
        // Determine which operation the previous one is based on the record
        // Then accordingly redo the operation
        if (undone[0] == 0) {
            this.caseConvert(undone[LOWER_BOUND], undone[1]);
            this.undo.multiPop(UNDO_RECORD);
        }
        if (undone[0] == 1) {
            String insert = this.insertedText.pop();
            int len = insert.length();
            this.text = this.text.substring(0, undone[LOWER_BOUND]) + insert
                    + this.text.substring(undone[1] - len);
        }
        if (undone[0] == DELETE_CODE) {
            String newStr = this.text.substring(0, undone[LOWER_BOUND])
                    + this.text.substring(undone[1]);
            this.text = newStr;
        }
        return true;
    }
}
