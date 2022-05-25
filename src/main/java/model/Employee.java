package model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "employeehiber")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tabnum")
    private Integer tabNum;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "position")
    private String position;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "active")
    private Boolean active;

    public Employee(Integer tabNum, String name, String surname, String middleName, Integer age, String position, Double salary) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.age = age;
        this.tabNum = tabNum;
        this.position = position;
        this.salary = salary;
    }

    public Employee(Integer tabNum, String name, String surname, String middleName, Integer age, String position, Double salary, Boolean active) {
        this.tabNum = tabNum;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.age = age;
        this.position = position;
        this.salary = salary;
        this.active = active;
    }
}
