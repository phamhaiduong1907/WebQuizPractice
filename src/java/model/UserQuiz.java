/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.time.LocalTime;

/**
 *
 * @author long
 */
public class UserQuiz {

    private int userQuizID;
    private Quiz quiz;
    private Boolean questionType;
    private Topic topic;
    private Dimension dimension;
    private int numOfQ;

    public int getNumOfQ() {
        return numOfQ;
    }

    public void setNumOfQ(int numOfQ) {
        this.numOfQ = numOfQ;
    }
    
    
    public int getUserQuizID() {
        return userQuizID;
    }

    public void setUserQuizID(int userQuizID) {
        this.userQuizID = userQuizID;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

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
    
    
}
