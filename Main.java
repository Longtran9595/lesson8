import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private double[] grades;
    private String gender;

    public Student(int id, String name, double[] grades, String gender) {
        this.id = id;
        this.name = name;
        this.grades = grades;
        this.gender = gender;
    }

    // Getter và setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double[] grades) {
        this.grades = grades;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Hàm tính điểm trung bình
    public double calculateAverageGrade() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }
}

class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Hàm thêm sinh viên
    public void addStudent(Student student) {
        students.add(student);
    }

    // Hàm sửa thông tin sinh viên theo ID
    public void editStudent(int id, String name, double[] grades, String gender) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(name);
                student.setGrades(grades);
                student.setGender(gender);
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên có ID " + id);
    }

    // Hàm xóa sinh viên theo ID
    public void deleteStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên có ID " + id);
    }

    // Hàm tính điểm trung bình của các học viên
    public double calculateClassAverage() {
        double sum = 0;
        for (Student student : students) {
            sum += student.calculateAverageGrade();
        }
        return sum / students.size();
    }

    // Hàm tìm kiếm học viên theo ID
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Hàm tìm kiếm học viên theo tên gần đúng
    public ArrayList<Student> searchStudentByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    // Hàm lấy ra học viên có điểm trung bình lớn nhất
    public Student getStudentWithHighestAverage() {
        if (students.isEmpty()) {
            return null;
        }

        Student highestAvgStudent = students.get(0);
        double highestAvg = highestAvgStudent.calculateAverageGrade();

        for (Student student : students) {
            double avg = student.calculateAverageGrade();
            if (avg > highestAvg) {
                highestAvg = avg;
                highestAvgStudent = student;
            }
        }
        return highestAvgStudent;
    }

    // Hàm trả về danh sách tất cả sinh viên
    public ArrayList<Student> getAllStudents() {
        return students;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();

        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Tính điểm trung bình của lớp");
            System.out.println("5. Tìm kiếm sinh viên theo ID");
            System.out.println("6. Tìm kiếm sinh viên theo tên gần đúng");
            System.out.println("7. Hiển thị sinh viên có điểm trung bình cao nhất");
            System.out.println("8. Hiển thị danh sách sinh viên");
            System.out.println("9. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ dòng new line

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Đọc bỏ dòng new line
                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập giới tính: ");
                    String gender = scanner.nextLine();
                    double[] grades = new double[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Nhập điểm " + (i + 1) + ": ");
                        grades[i] = scanner.nextDouble();
                    }
                    Student student = new Student(id, name, grades, gender);
                    studentManager.addStudent(student);

                    // In danh sách sinh viên sau khi thêm
                    System.out.println("Danh sách sinh viên sau khi thêm:");
                    ArrayList<Student> allStudents = studentManager.getAllStudents();
                    for (Student s : allStudents) {
                        System.out.println("ID: " + s.getId() + ", Tên: " + s.getName() +
                                ", Giới tính: " + s.getGender() + ", Điểm trung bình: " + s.calculateAverageGrade());
                    }
                    break;
                case 2:
                    System.out.print("Nhập ID sinh viên cần sửa: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine(); // Đọc bỏ dòng new line
                    System.out.print("Nhập tên mới: ");
                    String editName = scanner.nextLine();
                    double[] editGrades = new double[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Nhập điểm mới " + (i + 1) + ": ");
                        editGrades[i] = scanner.nextDouble();
                    }
                    System.out.print("Nhập giới tính mới: ");
                    String editGender = scanner.nextLine();
                    studentManager.editStudent(editId, editName, editGrades, editGender);
                    break;
                case 3:
                    System.out.print("Nhập ID sinh viên cần xóa: ");
                    int deleteId = scanner.nextInt();
                    studentManager.deleteStudent(deleteId);
                    break;
                case 4:
                    double classAverage = studentManager.calculateClassAverage();
                    System.out.println("Điểm trung bình của lớp: " + classAverage);
                    break;
                case 5:
                    System.out.print("Nhập ID sinh viên cần tìm: ");
                    int findId = scanner.nextInt();
                    Student foundStudent = studentManager.findStudentById(findId);
                    if (foundStudent != null) {
                        System.out.println("Thông tin sinh viên:");
                        System.out.println("ID: " + foundStudent.getId());
                        System.out.println("Tên: " + foundStudent.getName());
                        System.out.println("Giới tính: " + foundStudent.getGender());
                        System.out.println("Điểm trung bình: " + foundStudent.calculateAverageGrade());
                    } else {
                        System.out.println("Không tìm thấy sinh viên có ID " + findId);
                    }
                    break;
                case 6:
                    System.out.print("Nhập tên gần đúng cần tìm: ");
                    String searchName = scanner.nextLine();
                    ArrayList<Student> searchResults = studentManager.searchStudentByName(searchName);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Kết quả tìm kiếm:");
                        for (Student result : searchResults) {
                            System.out.println("ID: " + result.getId() + ", Tên: " + result.getName() +
                                    ", Giới tính: " + result.getGender() + ", Điểm trung bình: " + result.calculateAverageGrade());
                        }
                    } else {
                        System.out.println("Không tìm thấy sinh viên có tên gần đúng " + searchName);
                    }
                    break;
                case 7:
                    Student highestAvgStudent = studentManager.getStudentWithHighestAverage();
                    if (highestAvgStudent != null) {
                        System.out.println("Sinh viên có điểm trung bình cao nhất:");
                        System.out.println("ID: " + highestAvgStudent.getId());
                        System.out.println("Tên: " + highestAvgStudent.getName());
                        System.out.println("Giới tính: " + highestAvgStudent.getGender());
                        System.out.println("Điểm trung bình: " + highestAvgStudent.calculateAverageGrade());
                    } else {
                        System.out.println("Danh sách sinh viên trống.");
                    }
                    break;
                case 8:
                    System.out.println("Danh sách sinh viên:");
                    ArrayList<Student> allStudentsList = studentManager.getAllStudents();
                    for (Student s : allStudentsList) {
                        System.out.println("ID: " + s.getId() + ", Tên: " + s.getName() +
                                ", Giới tính: " + s.getGender() + ", Điểm trung bình: " + s.calculateAverageGrade());
                    }
                    break;
                case 9:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ.");
                    break;
            }
        }
    }
}
