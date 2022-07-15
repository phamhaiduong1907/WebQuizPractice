/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.test;

import dal.QuestionDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Answer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Hai Duong
 */
@MultipartConfig
public class QuestionImportController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        QuestionDBContext dbQuestions = new QuestionDBContext();

        Part part = request.getPart("uploadedQuestion");
        String errorMessage = "";
        HttpSession session = request.getSession();

        //application/vnd.ms-excel
        //application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
        if (part.getContentType().equals("application/vnd.ms-excel")
                || part.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            FileInputStream inputstream = (FileInputStream) part.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputstream);

            // Get first sheet of the submited workbook
            Sheet sheet = workbook.getSheetAt(0);

            // check if sheet is blank
            boolean isBlankSheet = sheet.getPhysicalNumberOfRows() == 0;

            // count the number of successfully or unsuccessfully imported questions
            int countWrong = 0;
            int countRight = 0;

            // if sheet is not empty
            if (!isBlankSheet) {
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {

//                    String message = "Errors happen when importing file at line " + i + ": ";
                    boolean isValidQuestion = true;

                    // get row at index i
                    Row row = sheet.getRow(i);

                    // if row is empty continue to the next loop
                    if (checkEmptyRow(row)) {
                        continue;
                    }
                    out.print("LINE " + (i + 1) + ": ");

                    // If row is not empty, create question attributes according to each row found
                    ArrayList<Answer> answers = new ArrayList<>();

                    String subjectName, dimension, lesson, level, content, explanation;
                    subjectName = dimension = lesson = level = content = explanation = "";

                    // Get last cell's index
                    int lastColumn = (int) row.getLastCellNum();

                    for (int j = 0; j < lastColumn; j++) {

                        // Iterate over each cell
                        Cell cell = row.getCell(j);
                        String cellValue = "";

                        // in case cell index < 5, they contain compulsory fields of data
                        // if contain any null fields => increase a wrong question
                        // => stop reading that row
                        if (j < 5) { // check not null cell

                            if (cell == null || cell.getStringCellValue().trim().equals("")) {

                                String emptyAttributes = "";
                                switch (j) {
                                    case 0:
                                        emptyAttributes = "subject name";
                                        break;
                                    case 1:
                                        emptyAttributes = "dimension";
                                        break;
                                    case 2:
                                        emptyAttributes = "lesson";
                                        break;
                                    case 3:
                                        emptyAttributes = "level";
                                        break;
                                    case 4:
                                        emptyAttributes = "content";
                                        break;
                                }
//                                message += "empty "+emptyAttributes+", ";
                                isValidQuestion = false;
                                continue;

                            } else {

                                // get cell value
                                if (cell.getCellType() == CellType.STRING) {
                                    cellValue = cell.getStringCellValue().trim();
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    cellValue = (((int) cell.getNumericCellValue()) + "").trim();
                                }

                                // check the references information whether the user include not null data
                                if (j == 0) {

                                    subjectName = cellValue;

                                } else if (j == 1) {

                                    dimension = cellValue;

                                } else if (j == 2) {

                                    lesson = cellValue;

                                } else if (j == 3) {

                                    if (dbQuestions.isLevelExisted(cellValue.trim())) {

                                        level = cellValue;

                                    } else {

//                                        message += "check the level of the question, ";
                                        isValidQuestion = false;

                                    }
                                } else if (j == 4) {

                                    content = cellValue;

                                }
                            }
                        } else if (j == 5) {

                            if (cell == null) {

                                // skip null cell if no explanation provided
                                continue;

                            } else {

                                // get cell value
                                if (cell.getCellType() == CellType.STRING) {
                                    cellValue = cell.getStringCellValue();
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    cellValue = ((int) cell.getNumericCellValue()) + "";
                                }

                                explanation = cellValue;
                            }

                        } else {
                            if (cell == null || cell.getStringCellValue().trim().equals("")) {
                                // skip null cell, if all cells are null 
                                // => answer list size = 0 => invalid question
                                continue;
                            } else {

                                //get cell value
                                if (cell.getCellType() == CellType.STRING) {
                                    cellValue = cell.getStringCellValue();
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    cellValue = ((int) cell.getNumericCellValue()) + "";
                                }

                                String answer_parts[] = cellValue.trim().split("\\-");

                                // not follow format " true/false (-) content of answer "
                                if (answer_parts.length <= 1) {

//                                    message += "wrong format of imported answer, ";
                                    isValidQuestion = false;
                                } else {

                                    if (answer_parts[0].trim().equalsIgnoreCase("true")
                                            || answer_parts[0].trim().equalsIgnoreCase("false")) {

                                        // handle read data
                                        Answer answer = new Answer();
                                        answer.setIsTrue(Boolean.parseBoolean(answer_parts[0].trim()));
                                        answer.setAnswerContent(answer_parts[1].trim());
                                        answers.add(answer);
                                        out.print("Answer: " + answer_parts[1].trim() + " ");

                                    } else {

                                        // not find "true" or "false" at the first position string when split string
//                                        message += "wrong format of imported answer, ";
                                        isValidQuestion = false;

                                    }
                                }
                            }

                        }
                    }

                    // After breaking or iterating over the loop
                    // If contains error when inputting/typing manually
                    if (!isValidQuestion) {

                        // pile up error
//                        errorMessage += message;
                        errorMessage += (i + 1) + ", ";
                        countWrong++;
                        continue;
                    }

                    // If all the physical basic test for attributes pass, test for logic content of the question
                    // Check whether the input subject, dimension and lesson is logically related and all defined in db
                    ArrayList<Integer> idRangeOfQuestion = dbQuestions.getIdRangeOfQuestion(subjectName.toLowerCase(), lesson.toLowerCase(), dimension.toLowerCase());

                    // Check if subject name with respective dimension and lesson is existed in db
                    if (idRangeOfQuestion.size() != 3) {

//                        message += "can not find respective information in the system, ";
                        isValidQuestion = false;

                    }

                    // Check if answer has both false and true options
                    int countTrueAnswer, countFalseAnswer;
                    countTrueAnswer = countFalseAnswer = 0;

                    if (answers.size() < 2) { // if all answer cells are null then answer list size = 0

//                        message += "do not provide enough answer options for question, ";
                        isValidQuestion = false;

                    } else {
                        for (Answer answer : answers) {
                            if (answer.isIsTrue()) {

                                countTrueAnswer++;

                            } else if (!answer.isIsTrue()) {

                                countFalseAnswer++;

                            }
                        }
                        if (countTrueAnswer < 1 || countFalseAnswer < 1) {
//                            message += "should provide at least 1 false and 1 true answer\n";
                            isValidQuestion = false;
                        }
                    }

                    if (isValidQuestion) {
                        // insert into database
                        int levelID = dbQuestions.getLevelIDByName(level);
                        dbQuestions.importQuestion(content, false, null, idRangeOfQuestion.get(0),
                                idRangeOfQuestion.get(1), idRangeOfQuestion.get(2), explanation, null, levelID, answers);
                        countRight++;
                    } else {

                        // pile up errors
                        countWrong++;
                        errorMessage += (i + 1);
//                        errorMessage += message;
                    }
                }

                if (errorMessage.equals("")) {
                    errorMessage = null;
                } else {
                    errorMessage = "Errors happen when importing file at lines: " + errorMessage;
                    errorMessage = errorMessage.substring(0, errorMessage.length() - 2);
                }
                session.setAttribute("countWrong", countWrong);
                session.setAttribute("countRight", countRight);

            } else {
                errorMessage = "Your uploaded file is empty. Please check the file again";
            }
        } else {
            errorMessage = "Your uploaded file is invalid. Please check the file type again, the system accept *.xlsx/*xls only";
        }

        if (errorMessage == null) {
            session.setAttribute("errorMessage", null);
        } else {
            session.setAttribute("errorMessage", errorMessage);
        }

        response.sendRedirect("questionlist");
    }

    private static boolean checkEmptyRow(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
