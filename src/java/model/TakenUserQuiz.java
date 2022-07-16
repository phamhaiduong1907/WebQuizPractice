/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author long
 */
public class TakenUserQuiz {
    private int userQuizID;
    private Quiz quiz;
    private Date takenDate;
    private int mark;
    private LocalTime duration;
    private Boolean questionType;
    private Topic topic;
    private Dimension dimension;

    public Boolean getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Boolean questionType) {
        this.questionType = questionType;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
    
    

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public int getUserQuizID() {
        return userQuizID;
    }

    public void setUserQuizID(int TakenID) {
        this.userQuizID = TakenID;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz Quiz) {
        this.quiz = Quiz;
    }

    public Date getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(Date TakenDate) {
        this.takenDate = TakenDate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int CorrectAns) {
        this.mark = CorrectAns;
    }
    
       
}
