package it.tdt.edu.vn;


import javafx.util.Pair;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Processfile {

    private ArrayList<Students> ArrSt;
    private List<Room> ListRoom;
    private List<String> ListCs;
    private Course cs;
    private HashMapCourse HashCourse;
    private HashMapStudent HashStudent;
    private HashMapIdStudent HashIdStudent;
    private Graph graph;
    private Students st;
    private ProcessRoom ProcessRoom;
    private Room[] Room;
    private SchedulingExam Schedule;
    private ProcessTime PrTime;

    public Processfile(boolean flag,String Folder) throws IOException {
        ArrSt = new ArrayList<>();
        ListRoom = new ArrayList<>();
        ListCs = new ArrayList<>();
        cs = new Course();
        st = new Students();
        HashCourse = new HashMapCourse();
        HashStudent = new HashMapStudent();
        HashIdStudent = new HashMapIdStudent();
        Schedule = new SchedulingExam();
        graph = new Graph();
        PrTime = new ProcessTime();
        writeXLSXFile(flag,Folder);
    }

    public void readXLSXFileDKMH() throws IOException {
        InputStream ExcelFileToRead = new FileInputStream("KetQuaDangKyMonHoc.xlsx");
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
                HashIdStudent.addStudent2HashMapIdStudent(st, cs);
                ArrSt.add(st);

                if (!ListCs.contains(idCourse)) {
                    ListCs.add(idCourse);
                }
                HashCourse.addHashMapCourse(cs, st);

            }
        }
        graph.setWeight(HashIdStudent, HashCourse);
        // Hash map dung de lu tru sv tuong ung vs khoa hoc
        addStu2Course(ListCs, ArrSt);
        List<Integer> ListWeight = graph.arrangeWeight(HashCourse);
        Pair<Integer, String>[] pr = new Pair[HashCourse.HashMapCourse.size()];
        pr = graph.matchWeight2Course(ListWeight, HashCourse);
        ProcessRoom = new ProcessRoom(ListRoom);
        Room = ProcessRoom.Room;
        loadGroupAndTeam();
        Schedule.loadData(pr, 0, HashCourse);



    }

    private void addStu2Course(List<String> ListCs, ArrayList<Students> ArrSt) {
        for (int j = 0; j < ListCs.size(); j++) {
            for (int i = 0; i < ArrSt.size(); i++) {
                if (ArrSt.get(i).getIdCourse().equals(ListCs.get(j))) {
                    //addToList(lsCs.get(j), arrSt.get(i));
                    HashStudent.addHashMapStudent(ListCs.get(j), ArrSt.get(i));
                }
            }
        }
    }

    private void loadGroupAndTeam() {
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
            cs.addGroup();
            cs.addTeam();
        }
    }

    public void readXLSXFileDSPT() throws IOException {
        InputStream ExcelFileToRead = new FileInputStream("DanhSachPhongThi.xlsx");
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


    public void writeXLSXFile(boolean flag,String Folder) throws IOException {
        readXLSXFileDSPT();
        readXLSXFileDKMH();
        if (flag == true) {
            graph.GraphScheduling(HashCourse, ProcessRoom, Room, Schedule, PrTime, flag);
            graph.processTimeTable(HashCourse, HashIdStudent);

            for (String tmp : HashIdStudent.HashMapIdStudent.keySet()) {
                Students st = HashIdStudent.HashMapIdStudent.get(tmp);
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("FinalTest");

                String[] timeTable = new String[7];
                for (int i = 0; i < 7; i++) {
                    timeTable[i] = "";
                }
                for (int i = 0; i < st.TableRoom.size(); i++) {
                    int timeSlot = st.ListHour.get(i) / 7 + 1;
                    timeTable[st.ListHour.get(i) % 6 + 1] += st.ListCourse.get(i).getNameCourse() + "\r\n" +
                            st.ListCourse.get(i).getIdCourse() + "\r\n" +
                            "Ca thi:" + timeSlot + "\r\n" +
                            "Phòng Thi:" + st.TableRoom.get(st.ListCourse.get(i).getNameCourse()) + "\r\n";

                    //iterating r number of rows
                    for (int r = 0; r < 2; r++) {
                        XSSFRow row = sheet.createRow(r);
                        if (r == 0) {
                            for (int c = 0; c < 7; c++) {
                                XSSFCell cell = row.createCell(c);
                                if (c == 0) {
                                    cell.setCellValue("");
                                }
                                if (c == 1) {
                                    cell.setCellValue("Thứ Hai");
                                }
                                if (c == 2) {
                                    cell.setCellValue("Thứ Ba");
                                }
                                if (c == 3) {
                                    cell.setCellValue("Thứ Tư");
                                }
                                if (c == 4) {
                                    cell.setCellValue("Thứ Năm");
                                }
                                if (c == 5) {
                                    cell.setCellValue("Thứ Sáu");
                                }
                                if (c == 6) {
                                    cell.setCellValue("Thứ Bảy");
                                }
                            }
                        } else {
                            for (int c = 0; c < 7; c++) {
                                XSSFCell cell = row.createCell(c);
                                cell.setCellValue(timeTable[c]);
                            }
                        }

                    }
                    File file = new File(Folder+"/"+st.getId()+".xlsx");
                    FileOutputStream fileOut = new FileOutputStream(file);

                    //write this workbook to an Outputstream.
                    wb.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                }
            }
        } else if(flag == false) {
            graph.GraphScheduling(HashCourse, ProcessRoom, Room, Schedule, PrTime, flag);
            graph.processTimeTable(HashCourse, HashIdStudent);

            for (String tmp : HashIdStudent.HashMapIdStudent.keySet()) {
                Students st = HashIdStudent.HashMapIdStudent.get(tmp);
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("MidTermTest");

                String[] timeTable = new String[7];
                for (int i = 0; i < 7; i++) {
                    timeTable[i] = "";
                }
                for (int i = 0; i < st.TableRoom.size(); i++) {
                    int timeSlot = st.ListHour.get(i) / 7 + 1;
                    timeTable[st.ListHour.get(i) % 6 + 1] += st.ListCourse.get(i).getNameCourse() + "\r\n" +
                            st.ListCourse.get(i).getIdCourse() + "\r\n" +
                            "Ca thi:" + timeSlot + "\r\n" +
                            "Phòng Thi:" + st.TableRoom.get(st.ListCourse.get(i).getNameCourse()) + "\r\n";

                    //iterating r number of rows
                    for (int r = 0; r < 2; r++) {
                        XSSFRow row = sheet.createRow(r);
                        if (r == 0) {
                            for (int c = 0; c < 7; c++) {
                                XSSFCell cell = row.createCell(c);
                                if (c == 0) {
                                    cell.setCellValue("");
                                }
                                if (c == 1) {
                                    cell.setCellValue("Thứ Hai");
                                }
                                if (c == 2) {
                                    cell.setCellValue("Thứ Ba");
                                }
                                if (c == 3) {
                                    cell.setCellValue("Thứ Tư");
                                }
                                if (c == 4) {
                                    cell.setCellValue("Thứ Năm");
                                }
                                if (c == 5) {
                                    cell.setCellValue("Thứ Sáu");
                                }
                                if (c == 6) {
                                    cell.setCellValue("Thứ Bảy");
                                }
                            }
                        } else {
                            for (int c = 0; c < 7; c++) {
                                XSSFCell cell = row.createCell(c);
                                cell.setCellValue(timeTable[c]);
                            }
                        }

                    }
                    File file = new File(Folder+"/"+st.getId()+".xlsx");
                    FileOutputStream fileOut = new FileOutputStream(file);

                    //write this workbook to an Outputstream.
                    wb.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                }
            }
        }
    }
}
