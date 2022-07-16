/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class QuizHistory {
    private int quizHistoryID;
    private Quiz quizID;
    private Account account;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp finishTime;
    private float mark;

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
    
    

    public QuizHistory() {
    }

    public int getQuizHistoryID() {
        return quizHistoryID;
    }

    public void setQuizHistoryID(int quizHistoryID) {
        this.quizHistoryID = quizHistoryID;
    }

    public Quiz getQuizID() {
        return quizID;
    }

    public void setQuizID(Quiz quizID) {
        this.quizID = quizID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }
    
    
    
}
