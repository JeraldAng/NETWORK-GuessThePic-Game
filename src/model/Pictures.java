package model;

public class Pictures {
    private String category;
    private String word;
    private String[] imagepath;

    public Pictures(String category, String word, String[] imagepath){
        this.category = category;
        this.word = word;
        this.imagepath = imagepath;
    }

    public Pictures(){

    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setImagepath(String[] imagepath) {
        this.imagepath = imagepath;
    }

    public String getWord() {
        return word;
    }

    public String[] getImagepath() {
        return imagepath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString(){
        return category + "," + word + "," + String.join(",", imagepath);
    }
}
