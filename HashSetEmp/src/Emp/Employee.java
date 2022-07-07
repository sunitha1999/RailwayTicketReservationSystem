package Emp;

public class Employee {
	
	int eid;
	String ename;
	double esal;
	String edept;


public Employee(int eid, String ename, double esal, String edept){
	this.eid=eid;
	this.ename=ename;
	this.esal=esal;
	this.edept=edept;
}


@Override
public String toString() {
	return "Employee [eid=" + eid + ", ename=" + ename + ", esal=" + esal + ", edept=" + edept + "]";
}



}