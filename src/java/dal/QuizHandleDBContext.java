/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Course;
import model.Question;
import model.Quiz;
import model.QuizHistory;
import model.ResultQuestion;

/**
 *
 * @author ADMIN
 */
public class QuizHandleDBContext extends DBContext {

    public void insertQuizHistory(int quizID, String username, Timestamp startTime, Timestamp endTime, Timestamp finishTime, ArrayList<Question> questions) {

        String sql = "INSERT INTO [dbo].[QuizHistory]\n"
                + "           ([quizID]\n"
                + "           ,[username]\n"
                + "           ,[startTime]\n"
                + "           ,[endTime]\n"
                + "           ,[finishTime])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        String sql_insertQuestion = "INSERT INTO [dbo].[ResultQuestion]\n"
                + "           ([quizHistoryID]\n"
                + "           ,[questionID]\n"
                + "           ,[isAnswered]\n"
                + "           ,[isMarked])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        PreparedStatement stm = null;
        PreparedStatement stm_question = null;

        try {
            connection.setAutoCommit(false);
            stm = connection.prepareStatement(sql);
            stm.setInt(1, quizID);
            stm.setString(2, username);
            if (startTime == null) {
                stm.setNull(3, Types.TIMESTAMP);
            } else {
                stm.setTimestamp(3, startTime);
            }

            if (endTime == null) {
                stm.setNull(4, Types.TIMESTAMP);
            } else {
                stm.setTimestamp(4, endTime);
            }

            if (finishTime == null) {
                stm.setNull(5, Types.TIMESTAMP);
            } else {
                stm.setTimestamp(5, finishTime);
            }
            stm.executeUpdate();

            int quizHistoryID = new CommonDBContext().getIdentity("QuizHistory");

            for (Question q : questions) {
                stm_question = connection.prepareStatement(sql_insertQuestion);
                stm_question.setInt(1, quizHistoryID);
                stm_question.setInt(2, q.getQuestionID());
                stm_question.setBoolean(3, false);
                stm_question.setBoolean(4, false);
                stm_question.executeUpdate();
            }
            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public ArrayList<ResultQuestion> getQuestionsFromQuizQuestion(int quizID, int qhid) {
        ArrayList<ResultQuestion> questions = new ArrayList<>();

        String sql = "select q.*, qq.[order], rq.isAnswered,rq.isMarked from Question q join QuizQuestion qq\n"
                + "               on q.questionID = qq.questionID join Quiz qu \n"
                + "              on qu.quizID = qq.quizID join ResultQuestion rq\n"
                + "			  on rq.questionID = q.questionID	\n"
                + "              where qq.quizID = ? and quizHistoryID = ? order by [order] asc";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, quizID);
            stm.setInt(2, qhid);
            rs = stm.executeQuery();
            while (rs.next()) {
                ResultQuestion question = new ResultQuestion();
                question.setQuestionID(rs.getInt("questionID"));
                question.setQuestionContent(rs.getString("questionContent"));
                question.setStatus(rs.getBoolean("status"));
                question.setMediaURL(rs.getString("mediaURL"));
                question.setLesson(new LessonDBContext().getLesson(rs.getInt("lessonID")));
                question.setDimension(new DimensionDBContext().getDimensionByDimensionID(rs.getInt("dimensionID")));
                question.setLevel(new LevelDBContext().getLevel(rs.getInt("levelID")));
                question.setExplanation(rs.getString("explanation"));
                question.setMediaType(new MediaTypeDBContext().getMediaType(rs.getInt("mediaID")));
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                question.setCourse(c);
                question.setIsMarked(rs.getBoolean("isMarked"));
                question.setIsAnswered(rs.getBoolean("isAnswered"));
                question.setOrder(rs.getInt("order"));
                question.setAnswers(new AnswerDBContext().getResultAnswer(rs.getInt("questionID"), qhid));
                questions.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;

    }

    public boolean isAnswered(ArrayList<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.isIsChecked()) {
                return true;
            }
        }

        return false;
    }

//    public boolean isExistAnswer(int quizHistoryID, int answerID) {
//        String sql = "select count(*) as total from Result\n"
//                + "where quizHistoryID = ? and answerID = ?";
//        
//        PreparedStatement stm = 
//        
//        return false;
//    }
    public void deleteAnswers(int questionID, int qhid) {
        String sql = "delete from Result \n"
                + "where answerID in (select answerID from Answer where questionID = ? ) and quizHistoryID = ?";

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, questionID);
            stm.setInt(2, qhid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertQuestionStatus(int qhid, int questionID, boolean isAnswered, Boolean isMarked) {
        String sql = "update ResultQuestion \n"
                + "set isAnswered = ? , isMarked = ?\n"
                + "where quizHistoryID = ? and questionID = ?";

        PreparedStatement stm = null;
        try {
            if (isMarked == null) {
                isMarked = false;
            }
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, isAnswered);
            stm.setBoolean(2, isMarked);
            stm.setInt(3, qhid);
            stm.setInt(4, questionID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertAnswers(ArrayList<Integer> answerIDs, int qhid, int questionID, Boolean isMarked) {
        deleteAnswers(questionID, qhid);
        boolean isAnswered = true;
        if (answerIDs.size() == 0) {
            isAnswered = false;
        }

        insertQuestionStatus(qhid, questionID, isAnswered, isMarked);

        String sql = "INSERT INTO [dbo].[Result]\n"
                + "           ([quizHistoryID]\n"
                + "           ,[answerID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";

        for (Integer answerID : answerIDs) {
            try {
                PreparedStatement stm_insert_answer = connection.prepareStatement(sql);
                stm_insert_answer.setInt(1, qhid);
                stm_insert_answer.setInt(2, answerID);
                stm_insert_answer.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<ResultQuestion> getQuestionByFilter(int typeFilter, int qhid, int quizID) {
        ArrayList<ResultQuestion> resultQuestions = new ArrayList<>();
        String sql_1 = "select q.*,rq.isAnswered,rq.isMarked,qq.[order] from Question q join ResultQuestion rq\n"
                + "on q.questionID = rq.questionID join QuizQuestion qq \n"
                + "on qq.questionID = q.questionID\n"
                + "where rq.quizHistoryID = ? and isAnswered = ? and quizID = ?	order by [order] asc";
        String sql_2 = "select q.*,rq.isAnswered,rq.isMarked,qq.[order] from Question q join ResultQuestion rq\n"
                + "on q.questionID = rq.questionID join QuizQuestion qq \n"
                + "on qq.questionID = q.questionID\n"
                + "where rq.quizHistoryID = ?  and isMarked = ?	 and quizID = ? order by [order] asc";
        String sql_3 = "select q.*,rq.isAnswered,rq.isMarked,qq.[order] from Question q join ResultQuestion rq\n"
                + "on q.questionID = rq.questionID join QuizQuestion qq \n"
                + "on qq.questionID = q.questionID\n"
                + "where rq.quizHistoryID = ?  and quizID = ? order by [order] asc";

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // unanswered
            if (typeFilter == 1) {
                stm = connection.prepareStatement(sql_1);
                stm.setInt(1, qhid);
                stm.setBoolean(2, false);
                stm.setInt(3, quizID);
                //marked
            } else if (typeFilter == 2) {
                stm = connection.prepareStatement(sql_2);
                stm.setInt(1, qhid);
                stm.setBoolean(2, true);
                stm.setInt(3, quizID);

                //Answered
            } else if (typeFilter == 3) {
                stm = connection.prepareStatement(sql_1);
                stm.setInt(1, qhid);
                stm.setBoolean(2, true);
                stm.setInt(3, quizID);

            } else if (typeFilter == 4) {
                stm = connection.prepareStatement(sql_3);
                stm.setInt(1, qhid);
                stm.setInt(2, quizID);

            }

            rs = stm.executeQuery();
            while (rs.next()) {
                ResultQuestion question = new ResultQuestion();
                question.setQuestionID(rs.getInt("questionID"));
                question.setQuestionContent(rs.getString("questionContent"));
                question.setStatus(rs.getBoolean("status"));
                question.setMediaURL(rs.getString("mediaURL"));
                question.setLesson(new LessonDBContext().getLesson(rs.getInt("lessonID")));
                question.setDimension(new DimensionDBContext().getDimensionByDimensionID(rs.getInt("dimensionID")));
                question.setLevel(new LevelDBContext().getLevel(rs.getInt("levelID")));
                question.setExplanation(rs.getString("explanation"));
                question.setMediaType(new MediaTypeDBContext().getMediaType(rs.getInt("mediaID")));
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                question.setCourse(c);
                question.setIsMarked(rs.getBoolean("isMarked"));
                question.setIsAnswered(rs.getBoolean("isAnswered"));
                question.setOrder(rs.getInt("order"));
                question.setAnswers(new AnswerDBContext().getResultAnswer(rs.getInt("questionID"), qhid));
                resultQuestions.add(question);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultQuestions;

    }

    public int getWrongQuestion(int qhid, int quizid) {
        String sql = "select count( distinct(a.questionID)) as total  from Result r right join Answer a\n"
                + "on r.answerID = a.answerID and r.quizHistoryID = ? \n"
                + "right join QuizQuestion qq  \n"
                + "on qq.questionID = a.questionID\n"
                + "and quizID= ?\n"
                + "where (r.answerID is not null and a.isTrue = 0)  or (r.answerID is null and a.isTrue = 1)";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, qhid);
            stm.setInt(2, quizid);

            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void scoreExam(Timestamp finishTime, int qhid, Quiz quiz) {
        String sql = "update QuizHistory\n"
                + "set finishTime = ?, mark = ?\n"
                + "where quizHistoryID = ?";

        float mark = ((float) quiz.getNumOfQuestion() - (float) getWrongQuestion(qhid, quiz.getQuizID())) / (float) quiz.getNumOfQuestion() * 100;

        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, finishTime);
            stm.setFloat(2, mark);
            stm.setInt(3, qhid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public QuizHistory getQuizHistoryByID(int id) {
        String sql = "select * from QuizHistory\n"
                + "where quizHistoryID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                QuizHistory q = new QuizHistory();
                q.setAccount(new AccountDBContext().getAccount(rs.getString("username")));
                q.setEndTime(rs.getTimestamp("endTime"));
                q.setStartTime(rs.getTimestamp("startTime"));
                q.setQuizID(new QuizDBContext().getAQuiz(rs.getInt("quizID")));
                q.setQuizHistoryID(id);
                q.setFinishTime(rs.getTimestamp("finishTime"));
                q.setMark(rs.getFloat("mark"));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int getResultQuestionByIsAnswered(boolean isAnswered, int qhid) {
        String sql = "select count(*) as total from ResultQuestion\n"
                + "where isAnswered = ? and quizHistoryID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, isAnswered);
            stm.setInt(2, qhid);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizHandleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;

    }

}
