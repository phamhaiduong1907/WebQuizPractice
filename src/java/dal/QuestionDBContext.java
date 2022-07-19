/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Course;
import model.Dimension;
import model.Lesson;
import model.Question;

/**
 *
 * @author Hai Duong
 */
public class QuestionDBContext extends DBContext {
    
    public Question getQuestion(int questionID) {
        Question question = new Question();
        try {
            String sql = "SELECT [questionID]\n"
                    + "      ,[questionContent]\n"
                    + "      ,[status]\n"
                    + "      ,[mediaURL]\n"
                    + "      ,[lessonID]\n"
                    + "      ,[dimensionID]\n"
                    + "      ,[levelID]\n"
                    + "      ,[explanation]\n"
                    + "      ,[mediaID], courseID\n"
                    + "FROM [dbo].[Question]\n"
                    + "WHERE questionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, questionID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                LessonDBContext dbLesson = new LessonDBContext();
                LevelDBContext dbLevel = new LevelDBContext();
                DimensionDBContext dbDimension = new DimensionDBContext();
                MediaTypeDBContext dbMediaType = new MediaTypeDBContext();
                AnswerDBContext dbAnswer = new AnswerDBContext();
                CourseDBContext dbCourse = new CourseDBContext();
                question.setCourse(dbCourse.getCourse(rs.getInt("courseID")));
                question.setQuestionID(rs.getInt("questionID"));
                question.setQuestionContent(rs.getString("questionContent"));
                question.setStatus(rs.getBoolean("status"));
                question.setMediaURL(rs.getString("mediaURL"));
                question.setLesson(dbLesson.getLesson(rs.getInt("lessonID")));
                question.setLevel(dbLevel.getLevel(rs.getInt("levelID")));
                question.setDimension(dbDimension.getDimensionByDimensionID(rs.getInt("dimensionID")));
                question.setExplanation(rs.getString("explanation"));
                question.setMediaType(dbMediaType.getMediaType(rs.getInt("mediaID")));
                question.setAnswers(dbAnswer.getAnswerForQuestion(questionID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
    
    public int insertQuestion(String questionContent, String mediaUrl, int lessonID, int dimensionID, int levelID, String explanation, int mediaID, String rawMediaType, int courseID, ArrayList<Answer> answers) {
        
        String generatedColumns[] = {"ID"};
        String sql_insert_question = "INSERT INTO [dbo].[Question]\n"
                + "           ([questionContent]\n"
                + "           ,[status]\n"
                + "           ,[mediaURL]\n"
                + "           ,[lessonID]\n"
                + "           ,[dimensionID]\n"
                + "           ,[levelID]\n"
                + "           ,[explanation]\n"
                + "           ,[mediaID]\n"
                + "           ,[courseID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)\n"
                + "\n"
                + "DECLARE @id1 AS int SET @id1 = (SELECT SCOPE_IDENTITY());\n"
                + "DECLARE @mediaID1 AS int SET @mediaID1 = (SELECT mediaID FROM Question WHERE questionID = @id1)\n"
                + "IF @mediaID1 != 4\n"
                + "UPDATE Question\n"
                + "SET mediaURL = 'question_media_' + CAST(@id1 AS varchar(max)) + ?\n"
                + "WHERE questionID = @id1\n"
                + "ELSE \n"
                + "UPDATE Question\n"
                + "SET mediaURL = NULL\n"
                + "WHERE questionID = @id1";
        String sql_insert_answer = "INSERT INTO [dbo].[Answer]\n"
                + "           ([questionID]\n"
                + "           ,[answerContent]\n"
                + "           ,[isTrue])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm_insert_question = connection.prepareStatement(sql_insert_question, generatedColumns);
            stm_insert_question.setString(1, questionContent);
            stm_insert_question.setBoolean(2, false);
            stm_insert_question.setString(3, null);
            stm_insert_question.setInt(4, lessonID);
            stm_insert_question.setInt(5, dimensionID);
            stm_insert_question.setInt(6, levelID);
            stm_insert_question.setString(7, explanation);
            stm_insert_question.setInt(8, mediaID);
            stm_insert_question.setInt(9, courseID);
            stm_insert_question.setString(10, rawMediaType);
            stm_insert_question.executeUpdate();
            ResultSet rs = stm_insert_question.getGeneratedKeys();
            int questionID = 0;
            if (rs.next()) {
                questionID = rs.getInt(1);
            }
            for (Answer a : answers) {
                PreparedStatement stm_insert_answer = connection.prepareStatement(sql_insert_answer);
                stm_insert_answer.setInt(1, questionID);
                stm_insert_answer.setString(2, a.getAnswerContent());
                stm_insert_answer.setBoolean(3, a.isIsTrue());
                stm_insert_answer.executeUpdate();
            }
            connection.commit();
            return questionID;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return -1;
    }
    
    public void importQuestion(String questionContent, boolean status, String mediaURL, int courseID,
            int lessonID, int dimensionID, String explanation, Integer mediaID, int levelID, ArrayList<Answer> answers) {
        String sql_insert_question = "INSERT INTO [dbo].[Question]\n"
                + "           ([questionContent]\n"
                + "           ,[status]\n"
                + "           ,[mediaURL]\n"
                + "           ,[courseID]\n"
                + "           ,[lessonID]\n"
                + "           ,[dimensionID]\n"
                + "           ,[explanation]\n"
                + "           ,[mediaID]\n"
                + "           ,[levelID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        
        String sql_insert_answer = "INSERT INTO [dbo].[Answer]\n"
                + "           ([questionID]\n"
                + "           ,[answerContent]\n"
                + "           ,[isTrue])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        
        PreparedStatement stm_insert_question = null;
        PreparedStatement stm_insert_answer = null;
        try {
            connection.setAutoCommit(false);
            stm_insert_question = connection.prepareStatement(sql_insert_question);
            stm_insert_question.setString(1, questionContent);
            stm_insert_question.setBoolean(2, status);
            stm_insert_question.setString(3, mediaURL);
            stm_insert_question.setInt(4, courseID);
            stm_insert_question.setInt(5, lessonID);
            stm_insert_question.setInt(6, dimensionID);
            stm_insert_question.setString(7, explanation);
            if (mediaID == null) {
                stm_insert_question.setNull(8, Types.INTEGER);
            } else {
                stm_insert_question.setInt(8, mediaID);
            }
            stm_insert_question.setInt(9, levelID);
            stm_insert_question.executeUpdate();
            
            int identity = new CommonDBContext().getIdentity("Question");
            for (Answer answer : answers) {
                stm_insert_answer = connection.prepareStatement(sql_insert_answer);
                stm_insert_answer.setInt(1, identity);
                stm_insert_answer.setString(2, answer.getAnswerContent());
                stm_insert_answer.setBoolean(3, answer.isIsTrue());
                stm_insert_answer.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean isLevelExisted(String level) {
        boolean isExisted = false;
        try {
            String sql = "Select levelName from Level";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (level.equalsIgnoreCase(rs.getString("levelName"))) {
                    isExisted = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExisted;
    }
    
    public ArrayList<model.Level> getLevels() {
        ArrayList<model.Level> levels = new ArrayList<>();
        try {
            String sql = "Select * from Level";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.Level level = new model.Level();
                level.setLevelID(rs.getInt("levelID"));
                level.setLevelName(rs.getString("levelName"));
                levels.add(level);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return levels;
    }
    
    public ArrayList<Integer> getIdRangeOfQuestion(String subjectName, String lesson, String dimension) {
        ArrayList<Integer> idRange = new ArrayList<>();
        try {
            String sql = "select c.courseID, d.dimensionID, l.lessonID from\n"
                    + "Course c inner join Lesson l on c.courseID = l.lessonID\n"
                    + "inner join Dimension d on c.courseID = d.courseID\n"
                    + "where LOWER(c.courseName) = ? and LOWER(d.dimensionName) = ? "
                    + "and LOWER(l.lessonName) = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectName);
            stm.setString(2, dimension);
            stm.setString(3, lesson);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                idRange.add(rs.getInt("courseID"));
                idRange.add(rs.getInt("lessonID"));
                idRange.add(rs.getInt("dimensionID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idRange;
    }
    
    public ArrayList<Question> getAllQuestionsForList() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "select q.*, c.courseName, l.levelName, \n"
                    + "les.lessonName, d.dimensionName from Question q\n"
                    + "inner join Course c on q.courseID = c.courseID\n"
                    + "inner join Dimension d on q.dimensionID = d.dimensionID\n"
                    + "inner join Lesson les on les.lessonID = q.lessonID\n"
                    + "inner join [Level] l on l.levelID = q.levelID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                Course course = new Course();
                Lesson lesson = new Lesson();
                model.Level level = new model.Level();
                Dimension dimension = new Dimension();
                course.setCourseID(rs.getInt("courseID"));
                course.setCourseName(rs.getString("courseName"));
                dimension.setDimensionID(rs.getInt("dimensionID"));
                dimension.setDimensionName(rs.getString("dimensionName"));
                level.setLevelID(rs.getInt("levelID"));
                level.setLevelName(rs.getString("levelName"));
                lesson.setLessonID(rs.getInt("lessonID"));
                lesson.setLessonName(rs.getString("lessonName"));
                question.setCourse(course);
                question.setDimension(dimension);
                question.setLesson(lesson);
                question.setLevel(level);
                question.setQuestionID(rs.getInt("questionID"));
                question.setQuestionContent(rs.getString("questionContent"));
                question.setStatus(rs.getBoolean("status"));
                questions.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }
    
    public ArrayList<Question> searchQuestionsByAttributesForList(int subjectID, int dimensionID, int lessonID,
            Boolean status, int levelID, String content) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = " select q.questionID, q.questionContent, c.courseName, les.lessonName, \n"
                    + "d.dimensionName, l.levelName, q.[status] from Question q \n"
                    + "inner join Course c on q.courseID = c.courseID\n"
                    + "inner join [Level] l on l.levelID = q.levelID\n"
                    + "inner join Lesson les on les.lessonID = q.lessonID\n"
                    + "inner join Dimension d on d.dimensionID = q.dimensionID ";
            String sql_base = " select q.questionID, q.questionContent, c.courseName, les.lessonName, \n"
                    + "d.dimensionName, l.levelName, q.[status] from Question q \n"
                    + "inner join Course c on q.courseID = c.courseID\n"
                    + "inner join [Level] l on l.levelID = q.levelID\n"
                    + "inner join Lesson les on les.lessonID = q.lessonID\n"
                    + "inner join Dimension d on d.dimensionID = q.dimensionID ";
            
            String intersect = "\nintersect\n";
            
            String sql_subject = " where c.courseID = ? ";
            String sql_dimension = " where d.dimensionID = ? ";
            String sql_lesson = " where les.lessonID = ? ";
            String sql_status = " where q.status = ? ";
            String sql_level = " where l.levelID = ? ";
            String sql_content = " where LOWER(q.questionContent) like ? ";
            
            if (subjectID >= 1) {
                sql += intersect + sql_base + sql_subject;
            }
            
            if (dimensionID >= 1) {
                sql += intersect + sql_base + sql_dimension;
            }
            
            if (lessonID >= 1) {
                sql += intersect + sql_base + sql_lesson;
            }
            
            if (status != null) {
                sql += intersect + sql_base + sql_status;
            }
            
            if (levelID >= 1) {
                sql += intersect + sql_base + sql_level;
            }
            
            if (content != null && content.trim().length() != 0) {
                sql += intersect + sql_base + sql_content;
            }
            
            PreparedStatement stm = connection.prepareStatement(sql);
            int i = 1;
            
            if (subjectID >= 1) {
                stm.setInt(i++, subjectID);
            }
            
            if (dimensionID >= 1) {
                stm.setInt(i++, dimensionID);
            }
            
            if (lessonID >= 1) {
                stm.setInt(i++, lessonID);
            }
            
            if (status != null) {
                stm.setBoolean(i++, status);
            }
            
            if (levelID >= 1) {
                stm.setInt(i++, levelID);
            }
            
            if (content != null && content.trim().length() != 0) {
                stm.setString(i++, "%" + content + "%");
            }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionID(rs.getInt("questionID"));
                question.setQuestionContent(rs.getString("questionContent"));
                question.setStatus(rs.getBoolean("status"));
                Course course = new Course();
                Dimension dimension = new Dimension();
                model.Level level = new model.Level();
                Lesson lesson = new Lesson();
                course.setCourseName(rs.getString("courseName"));
                lesson.setLessonName(rs.getString("lessonName"));
                dimension.setDimensionName(rs.getString("dimensionName"));
                level.setLevelName(rs.getString("levelName"));
                question.setCourse(course);
                question.setDimension(dimension);
                question.setLesson(lesson);
                question.setLevel(level);
                questions.add(question);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }
    
    public ArrayList<Lesson> getLessonsBySubjectID(int subjectID) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "Select l.lessonID, l.lessonName from Lesson l inner join\n"
                    + "Topic t on l.topicID = t.topicID inner join Course c\n"
                    + "on t.courseID = c.courseID where c.courseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setLessonID(rs.getInt("lessonID"));
                lesson.setLessonName(rs.getString("lessonName"));
                lessons.add(lesson);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessons;
    }
    
    public ArrayList<Dimension> getDimensionsBySubjectID(int subjectID) {
        ArrayList<Dimension> dimensions = new ArrayList<>();
        try {
            String sql = "SELECT dimensionID, dimensionName from Dimension where courseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Dimension dimension = new Dimension();
                dimension.setDimensionID(rs.getInt("dimensionID"));
                dimension.setDimensionName(rs.getString("dimensionName"));
                dimensions.add(dimension);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dimensions;
    }
    
    public void changeQuestionStatus(int questionID, boolean status) {
        try {
            String sql = " update Question set [status] = ? where questionID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, questionID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getQuestionStatusByID(int questionID) {
        try {
            String sql = "select [status] from Question where questionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, questionID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("status");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public int getLevelIDByName(String levelName) {
        try {
            String sql = "Select levelID from [Level] where LOWER(levelName) = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, levelName.toLowerCase());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("levelID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int updateQuestion(int questionID, String questionContent, String mediaUrl, int lessonID, int dimensionID, int levelID, String explanation, int mediaID, String rawMediaType, int courseID, ArrayList<Answer> answers) {
        
        String sql_update_question = "UPDATE [dbo].[Question]\n"
                + "   SET [questionContent] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[mediaURL] = ?\n"
                + "      ,[lessonID] = ?\n"
                + "      ,[dimensionID] = ?\n"
                + "      ,[levelID] = ?\n"
                + "      ,[explanation] = ?\n"
                + "      ,[mediaID] = ?\n"
                + "      ,[courseID] = ?\n"
                + " WHERE questionID = ?\n"
                + "\n"
                + "DECLARE @id1 AS int SET @id1 = ?;\n"
                + "UPDATE Question\n"
                + "SET mediaURL = 'question_media_' + CAST(@id1 AS varchar(max)) + ?\n"
                + "WHERE questionID = @id1";
        String sql_insert_answer = "INSERT INTO [dbo].[Answer]\n"
                + "           ([questionID]\n"
                + "           ,[answerContent]\n"
                + "           ,[isTrue])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        String sql_delete_answer = "DELETE FROM [dbo].[Answer]\n"
                + "      WHERE questionID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm_update_question = connection.prepareStatement(sql_update_question);
            stm_update_question.setString(1, questionContent);
            stm_update_question.setBoolean(2, false);
            stm_update_question.setString(3, null);
            stm_update_question.setInt(4, lessonID);
            stm_update_question.setInt(5, dimensionID);
            stm_update_question.setInt(6, levelID);
            stm_update_question.setString(7, explanation);
            stm_update_question.setInt(8, mediaID);
            stm_update_question.setInt(9, courseID);
            stm_update_question.setInt(10, questionID);
            stm_update_question.setInt(11, questionID);
            stm_update_question.setString(12, rawMediaType);
            stm_update_question.executeUpdate();
            PreparedStatement stm_delete_answer = connection.prepareStatement(sql_delete_answer);
            stm_delete_answer.setInt(1, questionID);
            stm_delete_answer.executeUpdate();
            for (Answer a : answers) {
                PreparedStatement stm_insert_answer = connection.prepareStatement(sql_insert_answer);
                stm_insert_answer.setInt(1, questionID);
                stm_insert_answer.setString(2, a.getAnswerContent());
                stm_insert_answer.setBoolean(3, a.isIsTrue());
                stm_insert_answer.executeUpdate();
            }
            connection.commit();
            return questionID;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return -1;
        
    }
    
    public ArrayList<Question> getQuestionsFromQuizQuestion(int quizID) {
        ArrayList<Question> questions = new ArrayList<>();
        
        String sql = "select q.* from Question q join QuizQuestion qq\n"
                + "on q.questionID = qq.questionID join Quiz qu \n"
                + "on qu.quizID = qq.quizID\n"
                + "where qq.quizID = ?";
        
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, quizID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question = getQuestion(rs.getInt("questionID"));
                
                questions.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
        
    }
    
}
