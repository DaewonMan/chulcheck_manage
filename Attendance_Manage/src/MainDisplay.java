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
	String category[] = {"���¸�", "������", "�����ο�"};
	String category2[] = {"�й�", "�̸�", "����", "����"};
	String category3[] = {"�й�", "�̸�", "����", "����"};
	
	DefaultTableModel model, model2, model3;
	JScrollPane tbl_sp, tbl_sp2, tbl_sp3;
	
	Person[] libPerson;
	int countPerson;
	Lecture[] libLecture;
	int countLecture;
	
	JComboBox box;
	static int [][] appPerson = new int[100][100]; // ���� ��û�� ����� ������ �Ҵ��� ����, �ִ� ���� 100, �ִ� ������ 100�� ��ϰ���
	
	public MainDisplay(){
		
		libPerson = new Person[100];
		libLecture = new Lecture[100];		
		countPerson = countLecture = 0;
		
		iFrame=new JFrame("Attendance Management Program"); // ������ ����
		iFrame.setLayout(null);
		iFrame.setBounds(40, 40, 1270, 800);
		iFrame.setResizable(false);

		iPaneLbl=new JPanel(null); // ������ �� �г� ����
		iPaneLbl.setBounds(10, 0, 530, 50);
		iPaneLbl.setBackground(Color.black);
		
		iName=new JLabel("�⼮ ���� ���α׷�"); // �гξ� ���ڿ� ���
		iName.setBounds(135, 5, 500, 40);
		iName.setForeground(Color.white);
		iName.setFont(new Font("����", Font.BOLD, 30));
		iPaneLbl.add(iName);
		iFrame.add(iPaneLbl);
		
		lecPaneLbl = new JPanel(null); // ���� ��� �� �г�
		lecPaneLbl.setBounds(550, 0, 249, 50);
		lecPaneLbl.setBackground(Color.black);
		
		lecName=new JLabel("���� ���"); // �гξ� ���ڿ� ���
		lecName.setBounds(80, 5, 140, 40);
		lecName.setForeground(Color.white);
		lecName.setFont(new Font("����", Font.BOLD, 20));
		lecPaneLbl.add(lecName);
		iFrame.add(lecPaneLbl);
		
		perPaneLbl = new JPanel(null); // ������ ��� �� �г�
		perPaneLbl.setBounds(805, 0, 445, 50);
		perPaneLbl.setBackground(Color.black);
		
		perName=new JLabel("������ ���"); // �гξ� ���ڿ� ���
		perName.setBounds(160, 5, 140, 40);
		perName.setForeground(Color.white);
		perName.setFont(new Font("����", Font.BOLD, 20));
		perPaneLbl.add(perName);
		iFrame.add(perPaneLbl);
		
		attPaneLbl = new JPanel(null); // ������ ��� �� �г�
		attPaneLbl.setBounds(150, 400, 710, 50);
		attPaneLbl.setBackground(Color.black);
		
		attName=new JLabel("�� �� ��"); // �гξ� ���ڿ� ���
		attName.setBounds(325, 5, 100, 40);
		attName.setForeground(Color.white);
		attName.setFont(new Font("����", Font.BOLD, 20));
		attPaneLbl.add(attName);
		iFrame.add(attPaneLbl);

		byPerson=new JButton("������ ���"); // ������ ��ư
		byPerson.setBounds(140, 90, 250, 30);
		//byPerson.setFont(new Font("����", Font.BOLD, 15)); // �۾�ü ����
		byPerson.addActionListener(this);
		iFrame.add(byPerson);
		
		byLecture=new JButton("���� ���"); // ���� ��� ��ư
		byLecture.setBounds(140, 160, 250, 30);
		byLecture.addActionListener(this);
		iFrame.add(byLecture);
		
		Application=new JButton("���� ��û"); // ���� ��û
		Application.setBounds(140, 230, 250, 30);
		Application.addActionListener(this);
		iFrame.add(Application);
		
		extProgram=new JButton("���α׷� ����"); // ���� ��ư
		extProgram.setBounds(140, 300, 250, 30);
		extProgram.addActionListener(this);
		iFrame.add(extProgram);
		
		attendPerson=new JButton("�⼮"); // �⼮ ��ư
		attendPerson.setBounds(1010, 450, 100, 30);
		attendPerson.addActionListener(this);
		iFrame.add(attendPerson);
		
		absencePerson=new JButton("�Ἦ"); // �Ἦ ��ư
		absencePerson.setBounds(1140, 450, 100, 30);
		absencePerson.addActionListener(this);
		iFrame.add(absencePerson);
		
		latePerson=new JButton("����"); // ���� ��ư
		latePerson.setBounds(1010, 500, 100, 30);
		latePerson.addActionListener(this);
		iFrame.add(latePerson);
		
		leavePerson=new JButton("����"); // ���� ��ư
		leavePerson.setBounds(1140, 500, 100, 30);
		leavePerson.addActionListener(this);
		iFrame.add(leavePerson);
		
		box = new JComboBox();
		box.addItem(" ");
		box.setBounds(870, 400, 100, 50);
		//box.addActionListener(this);
		iFrame.add(box);
		
		model = new DefaultTableModel(category,0); // ����
		lectureTable = new JTable(model);		
		tbl_sp = new JScrollPane(lectureTable);
		tbl_sp.setBounds(550, 60, 250, 340);
		iFrame.add(tbl_sp);
	
		model2 = new DefaultTableModel(category2, 0); // ����
		personTable = new JTable(model2);
		tbl_sp2 = new JScrollPane(personTable);
		tbl_sp2.setBounds(805, 60, 446, 340);
		iFrame.add(tbl_sp2);		
		iFrame.setVisible(true);
		
		model3 = new DefaultTableModel(category3, 0); // ���� ����
		appTable = new JTable(model3);
		tbl_sp3 = new JScrollPane(appTable);
		tbl_sp3.setBounds(150, 450, 821, 320);
		iFrame.add(tbl_sp3);		
		iFrame.setVisible(true);
	}
	private void load() {
		try {
			// ���� �Է�
			FileInputStream fis = new FileInputStream("library.txt");
			InputStreamReader isr = new InputStreamReader(fis, "MS949"); // �ڹٿ��� ���ڿ� utf-8���� �׷��Ƿ� "MS949" �������� ����
			BufferedReader br = new BufferedReader(isr);
			
			FileInputStream fis2 = new FileInputStream("applicationList.txt");
			InputStreamReader isr2 = new InputStreamReader(fis2, "MS949"); // �ڹٿ��� ���ڿ� utf-8���� �׷��Ƿ� "MS949" �������� ����
			BufferedReader br2 = new BufferedReader(isr2);
			
			String temp;
			int tmp = 0;
			
			for(int i = 0; i < 100;i++){ // applicationList.txt�� �� �پ� �о���δ�.
				for(int j = 0;j < 100;j++){
					tmp = Integer.parseInt(br2.readLine().trim()); // �� �� �� �д´�.
					appPerson[j][i] = tmp; //�� ����� ���� ���� ��û ���θ� �Ҵ�
				}
			}
			
			if(br.readLine().trim().equals("Lecture")){ // ó�� ���º��� �о���δ�.
				while(true){
					temp = br.readLine().trim(); // �� �� �� �д´�.
					if(!temp.equals("Person")){ // ������ ��
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
					else{ // ������ �϶�
						while(true){
						libPerson[countPerson] = new Person() {
						};
						libPerson[countPerson].setStudentNumber(br.readLine().trim()); // �й�
						libPerson[countPerson].setName(br.readLine().trim()); // �̸�
						libPerson[countPerson].setAge(Integer.parseInt(br.readLine().trim())); // ����
						libPerson[countPerson++].setGrade(Double.parseDouble(br.readLine().trim())); // ����
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
	public void actionPerformed(ActionEvent iEvent) { // ��ư Ŭ������ ��
		if(iEvent.getSource()==byPerson) { // ������ư Ŭ�� ��
			String s_number = JOptionPane.showInputDialog("�й��� �Է��ϼ���");
			String name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���");
			int age = Integer.parseInt(JOptionPane.showInputDialog("���̸� �Է��ϼ���"));
			
			libPerson[countPerson++] = new Student(s_number,name, age);
			refresh();
		}
		else if(iEvent.getSource()==byLecture) { // ���¹�ư Ŭ�� ��
			String lecture = JOptionPane.showInputDialog("���¸��� �Է��ϼ���");
			String professor = JOptionPane.showInputDialog("�������� �Է��ϼ���");			
			libLecture[countLecture++] = new Lecture(lecture, professor);
			refresh();
		}
		else if(iEvent.getSource()==Application){ // ���� ��û ��ư
			int lecture = lectureTable.getSelectedRow(); // �� ��ȣ �Ҵ�
			int person = personTable.getSelectedRow();
			
			if(appPerson[lecture][person] != 0) {
				JOptionPane.showMessageDialog(temp, "�̹� ���� ��û�Ͽ����ϴ�!!!");
				return;
			}
			else {
				appPerson[lecture][person] = 1;
			}
			application(lecture, person); // ���� ��û
		}
		else if(iEvent.getSource()==box) { // �޺��ڽ� ��� ���� ��
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
		else if(iEvent.getSource()==attendPerson || iEvent.getSource()==absencePerson || iEvent.getSource()==leavePerson || iEvent.getSource()==latePerson){ // �⼮,�Ἦ,����,���� ��ư
			try {
				int checkIndex = appTable.getSelectedRow(); // ���õ� �л��� �ε���
				int check = box.getSelectedIndex(); //  ���õ� �޺��ڽ� �ε���
				int attNum = 0;
				
				String serialNum = (String) model3.getValueAt(checkIndex, 0); // ��, ���� �ش��ϴ� ��
				for (int i = 0;i < countPerson;i++) {
					if (libPerson[i].getStudentNumber().equals(serialNum) ) { // �л���Ͽ� �й��� �ش��ϴ� �ε��� �Ҵ�
						attNum = i;
						break;
					}
				}
				if(iEvent.getSource()==attendPerson) {
					libPerson[attNum].setStatus("�⼮");
					libPerson[attNum].setGrade(0); // �⼮ ���� ����
				}
				else if (iEvent.getSource()==latePerson) {
					libPerson[attNum].setStatus("����");
					libPerson[attNum].setGrade(-0.5); // ���� ���� ����
				}
				else if (iEvent.getSource()==leavePerson) {
					libPerson[attNum].setStatus("����");
					libPerson[attNum].setGrade(-0.5); // ���� ���� ����
				}
				else {
					libPerson[attNum].setStatus("�Ἦ");
					libPerson[attNum].setGrade(-1); // �Ἦ ���� ����
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
		else // ���α׷� ����
		{
			try {
				// ���� ���
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
				
				for(int i = 0;i < 100;i++) { // ������û ��� ���Ͽ� ����
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
		iFrame.remove(box); // ������ �޺��ڽ� ����
		box = new JComboBox(); // �޺��ڽ� �ٽ� ����
		
		for(int i=0; i<countLecture; i++){
			model.addRow(libLecture[i].getAll());
			box.addItem(libLecture[i].getLecture()); // �޺� �ڽ� ��� �Ҵ�
		}
		if (countLecture == 0){ // ���ǰ� �ϳ��� ���� ��
			box.addItem("����");
		}
		lectureTable.setModel(model);
		
		box.setBounds(870, 400, 100, 50);
		box.setSelectedIndex(0);
		box.addActionListener(this); // �޺��ڽ� ��� ���� �� ����
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