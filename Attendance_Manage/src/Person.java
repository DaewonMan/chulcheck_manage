import java.util.Vector;


public abstract class Person {
	public String name;
	public String sNumber;
	public int age;
	public double grade;
	Lecture[] bk;
	String status;
	
	public Vector<Object> getCheckAll() {
		Vector<Object> myvector = new Vector<Object>();
		myvector.add(this.sNumber);
		myvector.add(this.name);
		myvector.add(this.age);
		myvector.add(this.status);
		return myvector;
	}
	public Vector<Object> getAll() {
		Vector<Object> myvector = new Vector<Object>();
		myvector.add(this.sNumber);
		myvector.add(this.name);
		myvector.add(this.age);
		myvector.add(this.grade);
		return myvector;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentNumber() {
		return sNumber;
	}
	public void setStudentNumber(String sNumber) {
		this.sNumber = sNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double num) {
		this.grade += num ;
	}
	public Lecture[] getBk() {
		return bk;
	}
	public void setBk(Lecture[] bk) {
		this.bk = bk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
