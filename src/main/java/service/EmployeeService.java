package service;

import model.Employee;
import repository.EmployeeRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {

    private final EmployeeRepository repository = new EmployeeRepository();

    public List<Employee> getAll() {
        return repository.getAll();
    }

    public Employee getById(Integer id) {
        return repository.getById(id);
    }

    public boolean deleteById(Integer id) {
        Employee temp = getById(id);
        return repository.delete(temp);
    }

    public boolean updateEmployeeFromFile(String path) {
        try {
            List<Employee> listInDB = repository.getAll();
            List<Employee> listFromFile = getEmployeeFromCSV(path);
            Map<Integer, Employee> mapFromDB = mapFromList(listInDB);
            Map<Integer, Employee> mapFromFile = mapFromList(listFromFile);

            for (Employee employee : listInDB) {
                if (mapFromFile.get(employee.getTabNum()) == null) {
                    employee.setActive(false);
                    repository.update(employee);
                }
            }
            for (Employee employee : listFromFile) {
                if (mapFromDB.get(employee.getTabNum()) == null) {
                    employee.setActive(true);
                    repository.save(employee);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Employee> getEmployeeFromCSV(String path) {
        List<Employee> result = new ArrayList<>();
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(";");
                if (arr.length == 7) {
                    result.add(new Employee(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], Double.parseDouble(arr[6])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private Map<Integer, Employee> mapFromList(List<Employee> employees) {
        Map<Integer, Employee> result = new HashMap<>();
        for (Employee employee : employees) {
            result.put(employee.getTabNum(), employee);
        }
        return result;
    }

    public boolean fillDB(String path) {
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(";");
                if (arr.length == 8) {
                    System.out.println("Заносим в базу: "+line);
                    repository.save(new Employee(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], Double.parseDouble(arr[6]), arr[7].equals("true")));
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }

    }

}
