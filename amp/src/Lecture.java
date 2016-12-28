import java.util.Vector;

public class Lecture {
	String lecture;
	String professor;
	
	int numofPerson; // �� ���� �ο�	
	public Lecture(String lecture, String professor) {
		this.lecture = lecture;
		this.professor = professor;
	}
	public Lecture() { // ������
		numofPerson = 0;
	} 
	public Vector<Object> getAll() { // ���׸� ����
		Vector<Object> myvector = new Vector<Object>();
		myvector.add(this.lecture);
		myvector.add(this.professor);
		myvector.add(Integer.toString(this.numofPerson));
		return myvector;
	}
	public String getLecture() {
		return lecture;
	}
	public void setLecture(String lecture) {
		this.lecture = lecture;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public int getNumofPerson() {
		return numofPerson;
	}
	public void setNumofPerson(int num) {
		this.numofPerson = num;
	}
	public void addNumofPerson() {
		this.numofPerson += 1;
	}
}
