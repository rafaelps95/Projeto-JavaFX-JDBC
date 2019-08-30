package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	public List<Department> findAll(){
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "M�veis"));
		list.add(new Department(2, "Cama mesa e banho"));
		list.add(new Department(3, "Eletr�nicos"));
		return list;
	}
}
