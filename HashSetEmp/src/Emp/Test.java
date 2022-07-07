package Emp;

//import java.util.Set;
//import java.util.HashSet;

import java.util.List;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Set<Employee> eset=new HashSet<>();
		
		List<Employee> elist=new ArrayList<Employee>();
		
//		eset.add(new Employee(101,"Ram",123.45,"cse"));
//		eset.add(new Employee(102,"Kevin",345.67,"ece"));
//		eset.add(new Employee(101,"Ram",123.45,"cse"));
//		eset.add(new Employee(103,"Sam",678.67,"eee"));
		
		elist.add(new Employee(101,"Ram",123.45,"cse"));
		elist.add(new Employee(102,"Kevin",345.67,"ece"));
		elist.add(new Employee(101,"Ram",123.45,"cse"));
		elist.add(new Employee(103,"Sam",678.67,"eee"));
		
		for(Employee e: elist)
			System.out.println(e);

	}

}
