package it.tdt.edu.vn;

import javafx.util.Pair;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Processfile {

    private ArrayList<Students> ArrSt;
    private List<Room> ListRoom;
    private List<Course> ListCs;
    private Course cs;
    private HashMapCourse HashCourse;
    public HashMapIdStudent HashIdStudent;
    private Graph graph;
    private Students st;
    private ProcessRoom ProcessRoom;
    private Room[] Room;
    private SchedulingExam Schedule;
    private ProcessTime PrTime;
    private int size = 0;
    public ArrayList<String[]> ArrResult;

    public Processfile(boolean flag, String PathDKMH, String PathDSPT) throws IOException {
        ArrSt = new ArrayList<>();
        ListRoom = new ArrayList<>();
        ListCs = new ArrayList<>();
        cs = new Course();
        st = new Students();
        HashCourse = new HashMapCourse();
        HashIdStudent = new HashMapIdStudent();
        Schedule = new SchedulingExam();
        graph = new Graph();
        PrTime = new ProcessTime();
        writeXLSXFile(flag, PathDKMH, PathDSPT);
    }

    public void readXLSXFileDKMH(String path) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet s = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell c;
        String idCourse = "";
        String nameCourse = "";
        String group = "";
        String team = "";
        String class_ = "";
        String id = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        int FIRST_ROW_TO_GET = 1;
        size = s.getLastRowNum();
        for (int i = FIRST_ROW_TO_GET; i < s.getLastRowNum() + 1; i++) {
            row = s.getRow(i);
            if (row == null) {
                continue;
            } else {

                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (cn == 0) {
                        idCourse = c.getStringCellValue();
                    }
                    if (cn == 1) {
                        nameCourse = c.getStringCellValue();
                    }
                    if (cn == 2) {
                        group = c.getStringCellValue();
                    }
                    if (cn == 3) {
                        team = c.getStringCellValue();
                    }
                    if (cn == 4) {
                        class_ = c.getStringCellValue();
                    }
                    if (cn == 5) {
                        id = c.getStringCellValue();
                    }
                    if (cn == 6) {
                        firstName = c.getStringCellValue();
                    }
                    if (cn == 7) {
                        lastName = c.getStringCellValue();
                    }
                    if (cn == 8) {
                        if (c.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                            email = c.getStringCellValue();
                        }
                    }

                }
                cs = new Course(idCourse, nameCourse);
                st = new Students(idCourse, nameCourse, group, team, class_, id, firstName, lastName, email);
                if (!cs.getIdCourse().equals("500002") && !cs.getIdCourse().equals("500003")) {
                    HashIdStudent.addStudent2HashMapIdStudent(st, cs);
                    HashCourse.addHashMapCourse(cs, st);
                }


            }
        }
        //System.out.println(ListCs.size());
        graph.setWeight(HashIdStudent, HashCourse);
        // Hash map dung de lu tru sv tuong ung vs khoa hoc
        List<Integer> ListWeight = graph.arrangeWeight(HashCourse);
        Pair<Integer, String>[] pr = new Pair[HashCourse.HashMapCourse.size()];
        pr = graph.matchWeight2Course(ListWeight, HashCourse);
        ProcessRoom = new ProcessRoom(ListRoom);
        Room = ProcessRoom.Room;
        loadGroupAndTeam();
        Schedule.loadData(pr, 0, HashCourse);

    }

    private void loadGroupAndTeam() {
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
            cs.addGroup();
            cs.addTeam();
        }
    }

    public void readXLSXFileDSPT(String path) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFSheet s = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell c;

        String IdRoom = "";
        int Capacity = 0;
        String Properties = "";
        String Note = "";
        int FIRST_ROW_TO_GET = 1;
        for (int i = FIRST_ROW_TO_GET; i < s.getLastRowNum() + 1; i++) {
            row = s.getRow(i);
            if (row == null) {
                continue;
            } else {

                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (cn == 0) {
                        IdRoom = c.getStringCellValue();
                    }
                    if (cn == 1) {
                        Capacity = Integer.parseInt(c.getStringCellValue());
                    }
                    if (cn == 2) {
                        Properties = c.getStringCellValue();
                    }
                    if (cn == 3) {
                        Note = c.getStringCellValue();
                    }
                }

                Room r = new Room(IdRoom, Capacity, Properties, Note);
                ListRoom.add(r);
            }
        }
    }

    private int getPositionFiNal(int number) {
        int posiont = 0;
        if (number >= 1 && number <= 4) {
            posiont = 3;
        }
        if (number >= 5 && number <= 8) {
            posiont = 4;
        }
        if (number >= 9 && number <= 12) {
            posiont = 5;
        }
        if (number >= 13 && number <= 16) {
            posiont = 6;
        }
        if (number >= 17 && number <= 20) {
            posiont = 7;
        }
        if (number >= 21 && number <= 24) {
            posiont = 8;
        }
        if (number >= 25 && number <= 28) {
            posiont = 3;
        }
        if (number >= 29 && number <= 32) {
            posiont = 4;
        }
        if (number >= 33 && number <= 36) {
            posiont = 5;
        }
        if (number >= 37 && number <= 40) {
            posiont = 6;
        }
        if (number >= 41 && number <= 44) {
            posiont = 7;
        }
        if (number >= 45 && number <= 48) {
            posiont = 8;
        }
        return posiont;
    }

    private int getPositionMidTerm(int number) {
        int position = 0;
        if (number >= 1 && number <= 10) {
            position = 3;
        }
        if (number >= 11 && number <= 20) {
            position = 4;
        }
        if (number >= 21 && number <= 30) {
            position = 5;
        }
        if (number >= 31 && number <= 40) {
            position = 6;
        }
        if (number >= 41 && number <= 50) {
            position = 7;
        }
        if (number >= 51 && number <= 60) {
            position = 8;
        }
        if (number >= 61 && number <= 70) {
            position = 9;
        }
        return position;
    }

    private String getWeek(int number) {
        String Week = "";
        if (number >= 1 && number <= 24) {
            Week = "Tuần 1";
        } else {
            Week = "Tuần 2";
        }

        return Week;
    }
    public void writeXLSXFile(boolean flag, String PathDKMH, String PathDSPT) throws IOException {
        readXLSXFileDSPT(PathDSPT);
        readXLSXFileDKMH(PathDKMH);
        if (flag == true) {
            graph.GraphScheduling(HashCourse, ProcessRoom, Room, Schedule, PrTime, flag);
            graph.processTimeTable(HashCourse, HashIdStudent);
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("MidTermTest");
            int r = 0;
            for (String tmp : HashIdStudent.HashMapIdStudent.keySet()) {
                Students st = HashIdStudent.HashMapIdStudent.get(tmp);
                String[] timeTable = new String[9];
                for (int i = 0; i < 9; i++) {
                    timeTable[i] = "";
                }
                Iterator<Entry<Course, String>> iterator = st.TableRoom.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Course, String> pair = (Map.Entry<Course, String>) iterator.next();
                    String Week = getWeek(pair.getKey().getHour());
                    int hour = pair.getKey().getHour() % 4 + 1;
                    int position = getPositionFiNal(pair.getKey().getHour());
                    timeTable[position] += pair.getKey().getNameCourse() + "\n"
                            + pair.getKey().getIdCourse() + "\n"
                            + "Ca thi:" + hour + "\n"
                            + "Phòng Thi:" + pair.getValue() + "\n" + Week + "\n";

                }
                //iterating r number of row
                XSSFRow row = sheet.createRow(r);
                if (r == 0) {
                    for (int c = 0; c < 9; c++) {
                        XSSFCell cell = row.createCell(c);
                        if (c == 0) {
                            cell.setCellValue("MSSV");
                        }
                        if (c == 1) {
                            cell.setCellValue("Họ lót");
                        }
                        if (c == 2) {
                            cell.setCellValue("Tên");
                        }
                        if (c == 3) {
                            cell.setCellValue("Thứ Hai");
                        }
                        if (c == 4) {
                            cell.setCellValue("Thứ Ba");
                        }
                        if (c == 5) {
                            cell.setCellValue("Thứ Tư");
                        }
                        if (c == 6) {
                            cell.setCellValue("Thứ Năm");
                        }
                        if (c == 7) {
                            cell.setCellValue("Thứ Sáu");
                        }
                        if (c == 8) {
                            cell.setCellValue("Thứ Bảy");
                        }
                    }
                } else {
                    for (int c = 0; c < 9; c++) {
                        XSSFCell cell;
                        if (c == 0) {
                            cell = row.createCell(c);
                            cell.setCellValue(st.getId());
                        }
                        if (c == 1) {
                            cell = row.createCell(c);
                            cell.setCellValue(st.getFirstName());
                        }
                        if (c == 2) {
                            cell = row.createCell(c);
                            cell.setCellValue(st.getLastName());
                        }
                        if (c >= 3) {
                            cell = row.createCell(c);
                            cell.setCellValue(timeTable[c]);
                        }

                    }
                }
                r++;
            }
            // File file = new File(Folder+"/"+st.getId()+".xlsx");
            String userHomeFolder = System.getProperty("user.home");
            FileOutputStream fileOut = new FileOutputStream(userHomeFolder + "/" + "Desktop" + "/" + "FinalTest.xlsx");
            //write this workbook to an Outputstream.
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } else if (flag == false) {
            graph.GraphScheduling(HashCourse, ProcessRoom, Room, Schedule, PrTime, flag);
            graph.processTimeTable(HashCourse, HashIdStudent);
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("MidTermTest");
            int r = 0;
            for (String tmp : HashIdStudent.HashMapIdStudent.keySet()) {
                Students st = HashIdStudent.HashMapIdStudent.get(tmp);
                String[] timeTable = new String[10];
                for (int i = 0; i < 10; i++) {
                    timeTable[i] = "";
                }
                Iterator<Entry<Course, String>> iterator = st.TableRoom.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Course, String> pair = (Map.Entry<Course, String>) iterator.next();
                    int hour = pair.getKey().getHour() % 10 + 1;
                    int position = getPositionMidTerm(pair.getKey().getHour());
                    timeTable[position] += pair.getKey().getNameCourse() + "\n"
                            + pair.getKey().getIdCourse() + "\n"
                            + "Ca thi:" + hour + "\n"
                            + "Phòng Thi:" + pair.getValue() + "\n";

                }
                //iterating r number of row
                XSSFRow row = sheet.createRow(r);
                if (r == 0) {
                    for (int c = 0; c < 10; c++) {
                        XSSFCell cell = row.createCell(c);
                        if (c == 0) {
                            cell.setCellValue("MSSV");
                        }
                        if (c == 1) {
                            cell.setCellValue("Họ lót");
                        }
                        if (c == 2) {
                            cell.setCellValue("Tên");
                        }
                        if (c == 3) {
                            cell.setCellValue("Thứ Hai");
                        }
                        if (c == 4) {
                            cell.setCellValue("Thứ Ba");
                        }
                        if (c == 5) {
                            cell.setCellValue("Thứ Tư");
                        }
                        if (c == 6) {
                            cell.setCellValue("Thứ Năm");
                        }
                        if (c == 7) {
                            cell.setCellValue("Thứ Sáu");
                        }
                        if (c == 8) {
                            cell.setCellValue("Thứ Bảy");
                        }
                        if (c == 9) {
                            cell.setCellValue("Chủ nhật");
                        }
                    }
                } else {
                    for (int c = 0; c < 10; c++) {
                        XSSFCell cell;
                        if (c == 0) {
                            cell = row.createCell(c);
                            cell.setCellValue(st.getId());
                        }
                        if (c == 1) {
                            cell = row.createCell(c);
                            cell.setCellValue(st.getFirstName());
                        }
                        if (c == 2) {
                            cell = row.createCell(c);
                            cell.setCellValue(st.getLastName());
                        }
                        if (c >= 3) {
                            cell = row.createCell(c);
                            cell.setCellValue(timeTable[c]);
                        }

                    }
                }
                r++;
            }
            // File file = new File(Folder+"/"+st.getId()+".xlsx");
            String userHomeFolder = System.getProperty("user.home");
            FileOutputStream fileOut = new FileOutputStream(userHomeFolder + "/" + "Desktop" + "/" + "MidTermTest.xlsx");

            //write this workbook to an Outputstream.
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }
    }
}
