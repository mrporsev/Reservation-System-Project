/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

/**
 *
 * @author porse
 */
public class GroupLesson {

    private String group_lesson_id;
    private String date;
    private String time;
    private String lesson_content;

    public GroupLesson(String id, String date, String time, String content) {
        this.group_lesson_id = id;
        this.date = date;
        this.time = time;
        this.lesson_content = content;
    }
    
    public String getId() {
        return this.group_lesson_id;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public String getLessonContent() {
        return this.lesson_content;
    }

}
