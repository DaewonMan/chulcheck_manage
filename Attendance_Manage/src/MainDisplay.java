/* Attendance Manage Program
 * 
 *    final exams project
 *             
 **/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class MainDisplay implements ActionListener{

	JFrame iFrame;
	JPanel iPaneLbl, temp, lecPaneLbl, perPaneLbl, attPaneLbl;
	JButton byPerson, byLecture,  Application, extProgram;
	JButton attendPerson, absencePerson, latePerson, leavePerson;
	JLabel iName, lecName, perName, attName;
	
	JTable lectureTable, personTable, appTable;
	String category[] = {"강좌명", "교수명", "수강인원"};
	String category2[] = {"학번", "이름", "나이", "점수"};
	String category3[] = {"학번", "이름", "나이", "상태"};
	
	DefaultTableModel model, model2, model3;
	JScrollPane tbl_sp, tbl_sp2, tbl_sp3;
	
	Person[] libPerson;
	int countPerson;
	Lecture[] libLecture;
	int countLecture;
	
	JComboBox box;
	static int [][] appPerson = new int[100][100]; // 수강 신청한 사람과 과목을 할당할 변수, 최대 강좌 100, 최대 수강생 100명 등록가능
	
	public MainDisplay(){
		
		libPerson = new Person[100];
		libLecture = new Lecture[100];		
		countPerson = countLecture = 0;
		
		iFrame=new JFrame("Attendance Management Program"); // 프레임 생성
		iFrame.setLayout(null);
		iFrame.setBounds(40, 40, 1270, 800);
		iFrame.setResizable(false);

		iPaneLbl=new JPanel(null); // 프레임 안 패널 생성
		iPaneLbl.setBounds(10, 0, 530, 50);
		iPaneLbl.setBackground(Color.black);
		
		iName=new JLabel("출석 관리 프로그램"); // 패널안 문자열 출력
		iName.setBounds(135, 5, 500, 40);
		iName.setForeground(Color.white);
		iName.setFont(new Font("돋움", Font.BOLD, 30));
		iPaneLbl.add(iName);
		iFrame.add(iPaneLbl);
		
		lecPaneLbl = new JPanel(null); // 강좌 목록 위 패널
		lecPaneLbl.setBounds(550, 0, 249, 50);
		lecPaneLbl.setBackground(Color.black);
		
		lecName=new JLabel("강좌 목록"); // 패널안 문자열 출력
		lecName.setBounds(80, 5, 140, 40);
		lecName.setForeground(Color.white);
		lecName.setFont(new Font("돋움", Font.BOLD, 20));
		lecPaneLbl.add(lecName);
		iFrame.add(lecPaneLbl);
		
		perPaneLbl = new JPanel(null); // 수강생 목록 위 패널
		perPaneLbl.setBounds(805, 0, 445, 50);
		perPaneLbl.setBackground(Color.black);
		
		perName=new JLabel("수강생 목록"); // 패널안 문자열 출력
		perName.setBounds(160, 5, 140, 40);
		perName.setForeground(Color.white);
		perName.setFont(new Font("돋움", Font.BOLD, 20));
		perPaneLbl.add(perName);
		iFrame.add(perPaneLbl);
		
		attPaneLbl = new JPanel(null); // 수강생 목록 위 패널
		attPaneLbl.setBounds(150, 400, 710, 50);
		attPaneLbl.setBackground(Color.black);
		
		attName=new JLabel("출 석 부"); // 패널안 문자열 출력
		attName.setBounds(325, 5, 100, 40);
		attName.setForeground(Color.white);
		attName.setFont(new Font("돋움", Font.BOLD, 20));
		attPaneLbl.add(attName);
		iFrame.add(attPaneLbl);

		byPerson=new JButton("수강생 등록"); // 수강생 버튼
		byPerson.setBounds(140, 90, 250, 30);
		//byPerson.setFont(new Font("돋움", Font.BOLD, 15)); // 글씨체 수정
		byPerson.addActionListener(this);
		iFrame.add(byPerson);
		
		byLecture=new JButton("과목 등록"); // 과목 등록 버튼
		byLecture.setBounds(140, 160, 250, 30);
		byLecture.addActionListener(this);
		iFrame.add(byLecture);
		
		Application=new JButton("수강 신청"); // 수강 신청
		Application.setBounds(140, 230, 250, 30);
		Application.addActionListener(this);
		iFrame.add(Application);
		
		extProgram=new JButton("프로그램 종료"); // 종료 버튼
		extProgram.setBounds(140, 300, 250, 30);
		extProgram.addActionListener(this);
		iFrame.add(extProgram);
		
		attendPerson=new JButton("출석"); // 출석 버튼
		attendPerson.setBounds(1010, 450, 100, 30);
		attendPerson.addActionListener(this);
		iFrame.add(attendPerson);
		
		absencePerson=new JButton("결석"); // 결석 버튼
		absencePerson.setBounds(1140, 450, 100, 30);
		absencePerson.addActionListener(this);
		iFrame.add(absencePerson);
		
		latePerson=new JButton("지각"); // 지각 버튼
		latePerson.setBounds(1010, 500, 100, 30);
		latePerson.addActionListener(this);
		iFrame.add(latePerson);
		
		leavePerson=new JButton("조퇴"); // 조퇴 버튼
		leavePerson.setBounds(1140, 500, 100, 30);
		leavePerson.addActionListener(this);
		iFrame.add(leavePerson);
		
		box = new JComboBox();
		box.addItem(" ");
		box.setBounds(870, 400, 100, 50);
		//box.addActionListener(this);
		iFrame.add(box);
		
		model = new DefaultTableModel(category,0); // 강좌
		lectureTable = new JTable(model);		
		tbl_sp = new JScrollPane(lectureTable);
		tbl_sp.setBounds(550, 60, 250, 340);
		iFrame.add(tbl_sp);
	
		model2 = new DefaultTableModel(category2, 0); // 수강
		personTable = new JTable(model2);
		tbl_sp2 = new JScrollPane(personTable);
		tbl_sp2.setBounds(805, 60, 446, 340);
		iFrame.add(tbl_sp2);		
		iFrame.setVisible(true);
		
		model3 = new DefaultTableModel(category3, 0); // 수강 내역
		appTable = new JTable(model3);
		tbl_sp3 = new JScrollPane(appTable);
		tbl_sp3.setBounds(150, 450, 821, 320);
		iFrame.add(tbl_sp3);		
		iFrame.setVisible(true);
	}
	private void load() {
		try {
			// 파일 입력
			FileInputStream fis = new FileInputStream("library.txt");
			InputStreamReader isr = new InputStreamReader(fis, "MS949"); // 자바에서 문자열 utf-8형식 그러므로 "MS949" 형식으로 설정
			BufferedReader br = new BufferedReader(isr);
			
			FileInputStream fis2 = new FileInputStream("applicationList.txt");
			InputStreamReader isr2 = new InputStreamReader(fis2, "MS949"); // 자바에서 문자열 utf-8형식 그러므로 "MS949" 형식으로 설정
			BufferedReader br2 = new BufferedReader(isr2);
			
			String temp;
			int tmp = 0;
			
			for(int i = 0; i < 100;i++){ // applicationList.txt를 한 줄씩 읽어들인다.
				for(int j = 0;j < 100;j++){
					tmp = Integer.parseInt(br2.readLine().trim()); // 한 줄 씩 읽는다.
					appPerson[j][i] = tmp; //한 사람에 대한 수강 신청 여부를 할당
				}
			}
			
			if(br.readLine().trim().equals("Lecture")){ // 처음 강좌부터 읽어들인다.
				while(true){
					temp = br.readLine().trim(); // 한 줄 씩 읽는다.
					if(!temp.equals("Person")){ // 강좌일 때
						libLecture[countLecture] = new Lecture();
						libLecture[countLecture].setLecture(temp);
						libLecture[countLecture].setProfessor(br.readLine().trim());
						temp = br.readLine().trim();
						if(temp.equals("0") || temp.equals(null) || temp.equals(0)) {
							libLecture[countLecture++].setNumofPerson(0);
						}
						else {
							libLecture[countLecture++].setNumofPerson(Integer.parseInt(temp));
						}
							
					}
					else{ // 수강자 일때
						while(true){
						libPerson[countPerson] = new Person() {
						};
						libPerson[countPerson].setStudentNumber(br.readLine().trim()); // 학번
						libPerson[countPerson].setName(br.readLine().trim()); // 이름
						libPerson[countPerson].setAge(Integer.parseInt(br.readLine().trim())); // 나이
						libPerson[countPerson++].setGrade(Double.parseDouble(br.readLine().trim())); // 점수
						}
					}
				}
			}
			
			br.close();
			isr.close();
			fis.close();
			
			br2.close();
			isr2.close();
			fis2.close();
		} catch (Exception e) {
			refresh();
			return;
		}
		refresh();
	}
	public void actionPerformed(ActionEvent iEvent) { // 버튼 클릭했을 때
		if(iEvent.getSource()==byPerson) { // 수강버튼 클릭 시
			String s_number = JOptionPane.showInputDialog("학번을 입력하세요");
			String name = JOptionPane.showInputDialog("이름을 입력하세요");
			int age = Integer.parseInt(JOptionPane.showInputDialog("나이를 입력하세요"));
			
			libPerson[countPerson++] = new Student(s_number,name, age);
			refresh();
		}
		else if(iEvent.getSource()==byLecture) { // 강좌버튼 클릭 시
			String lecture = JOptionPane.showInputDialog("강좌명을 입력하세요");
			String professor = JOptionPane.showInputDialog("교수명을 입력하세요");			
			libLecture[countLecture++] = new Lecture(lecture, professor);
			refresh();
		}
		else if(iEvent.getSource()==Application){ // 수강 신청 버튼
			int lecture = lectureTable.getSelectedRow(); // 행 번호 할당
			int person = personTable.getSelectedRow();
			
			if(appPerson[lecture][person] != 0) {
				JOptionPane.showMessageDialog(temp, "이미 수강 신청하였습니다!!!");
				return;
			}
			else {
				appPerson[lecture][person] = 1;
			}
			application(lecture, person); // 수강 신청
		}
		else if(iEvent.getSource()==box) { // 콤보박스 목록 선택 시
			int check = box.getSelectedIndex();
			
			for (int i = 0;i < countPerson;i++) {
				libPerson[i].setStatus("");
			}
			model3 = new DefaultTableModel(category3, 0);
			for(int i=0; i<countPerson; i++){
				if(appPerson[check][i] == 1) {
					model3.addRow(libPerson[i].getCheckAll());
				}
			}
			appTable.setModel(model3);
			
			iFrame.invalidate();
			
		}
		else if(iEvent.getSource()==attendPerson || iEvent.getSource()==absencePerson || iEvent.getSource()==leavePerson || iEvent.getSource()==latePerson){ // 출석,결석,지각,조퇴 버튼
			try {
				int checkIndex = appTable.getSelectedRow(); // 선택된 학생의 인덱스
				int check = box.getSelectedIndex(); //  선택된 콤보박스 인덱스
				int attNum = 0;
				
				String serialNum = (String) model3.getValueAt(checkIndex, 0); // 행, 열에 해당하는 값
				for (int i = 0;i < countPerson;i++) {
					if (libPerson[i].getStudentNumber().equals(serialNum) ) { // 학생목록에 학번에 해당하는 인덱스 할당
						attNum = i;
						break;
					}
				}
				if(iEvent.getSource()==attendPerson) {
					libPerson[attNum].setStatus("출석");
					libPerson[attNum].setGrade(0); // 출석 점수 증가
				}
				else if (iEvent.getSource()==latePerson) {
					libPerson[attNum].setStatus("지각");
					libPerson[attNum].setGrade(-0.5); // 지각 점수 증가
				}
				else if (iEvent.getSource()==leavePerson) {
					libPerson[attNum].setStatus("조퇴");
					libPerson[attNum].setGrade(-0.5); // 조퇴 점수 증가
				}
				else {
					libPerson[attNum].setStatus("결석");
					libPerson[attNum].setGrade(-1); // 결석 점수 감소
				}
				model3 = new DefaultTableModel(category3, 0);
				for(int i=0; i<countPerson; i++){
					if(appPerson[check][i] == 1) {
						model3.addRow(libPerson[i].getCheckAll());
					}
				}
				appTable.setModel(model3);
				
				iFrame.invalidate();
				refresh();
			} 
			catch(Exception e) {
				e.printStackTrace();
			}	
		}
		else // 프로그램 종료
		{
			try {
				// 파일 출력
				FileOutputStream fos = new FileOutputStream("library.txt");
				OutputStreamWriter osw = new OutputStreamWriter(fos, "MS949");
				BufferedWriter bw = new BufferedWriter(osw);
				
				FileOutputStream fos2 = new FileOutputStream("applicationList.txt");
				OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "MS949");
				BufferedWriter bw2 = new BufferedWriter(osw2);
				
				bw.write("Lecture\r\n");
				for(int i=0; i<countLecture; i++){
					bw.write(libLecture[i].getLecture()+"\r\n");
					bw.write(libLecture[i].getProfessor()+"\r\n");
					bw.write(libLecture[i].getNumofPerson()+"\r\n");
				}
				bw.write("Person\r\n");
				for(int i=0; i<countPerson; i++){
					bw.write(libPerson[i].getStudentNumber()+"\r\n");
					bw.write(libPerson[i].getName()+"\r\n");
					bw.write(libPerson[i].getAge()+"\r\n");
					bw.write(libPerson[i].getGrade()+"\r\n");
				}
				
				for(int i = 0;i < 100;i++) { // 수강신청 목록 파일에 쓰기
					for(int j = 0;j < 100;j++) {
						bw2.write(appPerson[j][i] + "\r\n");
					}
				}
				bw.flush(); // ??
				osw.close();
				fos.close();
				
				bw2.flush(); // ??
				osw2.close();
				fos2.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
	private void application(int lecture, int person) {
		libLecture[lecture].addNumofPerson();
		refresh();
	}
	private void refresh() {
		
		model = new DefaultTableModel(category, 0);
		iFrame.remove(box); // 기존의 콤보박스 제거
		box = new JComboBox(); // 콤보박스 다시 생성
		
		for(int i=0; i<countLecture; i++){
			model.addRow(libLecture[i].getAll());
			box.addItem(libLecture[i].getLecture()); // 콤보 박스 목록 할당
		}
		if (countLecture == 0){ // 강의가 하나도 없을 시
			box.addItem("없음");
		}
		lectureTable.setModel(model);
		
		box.setBounds(870, 400, 100, 50);
		box.setSelectedIndex(0);
		box.addActionListener(this); // 콤보박스 목록 선택 시 동작
		iFrame.add(box);
		
		model2 = new DefaultTableModel(category2, 0);
		for(int i=0; i<countPerson; i++){
			model2.addRow(libPerson[i].getAll());
		}
		personTable.setModel(model2);
		
		iFrame.invalidate();
	}
	public static void main(String[] args) {
		MainDisplay MDisMDis=new MainDisplay();
		MDisMDis.load();
	}
}