package examples;

public class Question {

    private String question;
    private String[] options;
    private int correct;
    private int pos;
    
    public Question(String question, String[] options, int correct){
        this.question = question;
        this.options = options;
        this.correct = correct;
    }
    
    public String getQuestion() {
        return this.question;
    }
    public String[] getOptions() {
        return this.options;
    }
    
     public int getCorrect() {
        return this.correct;
    }
}
