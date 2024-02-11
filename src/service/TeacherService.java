package service;

import common.Db;
import common.RunnnableAsMenu;
import entity.Teacher;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TeacherService implements RunnnableAsMenu {
    Scanner sc = new Scanner(System.in);

    @Override
    public void initialize() {

        System.out.println("neche nefer muellim qeydiyyat edeceksiniz?");
        int count = new Scanner(System.in).nextInt();

        Teacher[] teachers = new Teacher[count];

        for (int i = 0; i < count; i++) {
            System.out.println("Qeydiyyat nomresi:" + (i + 1));
            teachers[i] = requireAndCreate();

        }

        //printAll();
        Db.teachers = teachers;


        {
            String faylAdi = "fayl.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(faylAdi))) {

                writer.write(Arrays.toString(Db.teachers));
                System.out.println("Fayla müvəffəqiyyətlə yazıldı  " + faylAdi);
            } catch (IOException e) {
                System.err.println("Yazma xətası: " + e.getMessage());
            }
        }
    }


    @Override
    public void initializeNew() {
        Teacher[] oldTeachers = Db.teachers;
        System.out.println("Neçə nefer yaratmaq istəyirsiniz?");
        int additionalCount = new Scanner(System.in).nextInt();
        Teacher[] newTeachers = new Teacher[oldTeachers.length + additionalCount];
        for (int i = 0; i < oldTeachers.length; i++) {
            newTeachers[i] = oldTeachers[i];
        }

        for (int i = oldTeachers.length; i < newTeachers.length; i++) {
            newTeachers[i] = requireAndCreate();
        }

        String fileName = "fayl.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Teacher teacher : newTeachers) {
                writer.append(teacher.toString());
                writer.newLine();
            }
            System.out.println("Dosyaya başarıyla yazıldı: " + fileName);
        } catch (IOException e) {
            System.err.println("Yazma hatası: " + e.getMessage());
        }

    }


    @Override
    public void update() {
        try {
            System.out.println("Neçənci müəllimi yeniləmək istəyirsiniz?");
            int updateIndex = new Scanner(System.in).nextInt();
            Teacher teacher = Db.teachers[updateIndex - 1];
            System.out.println("Hansi xananı yeniləmək istəyirsiniz (name, surname, age)?");
            String updateField = new Scanner(System.in).nextLine();
            if (updateField.equals("name")) {
                System.out.println("Adını daxil edin:");
                teacher.setName(new Scanner(System.in).nextLine());
            } else if (updateField.equals("surname")) {
                System.out.println("Soyadını daxil edin:");
                teacher.setSurname(new Scanner(System.in).nextLine());
            } else if (updateField.equals("age")) {
                System.out.println("Yaşını daxil edin:");
                teacher.setAge(new Scanner(System.in).nextInt());
            }
        } catch (Exception e) {
            System.err.println("Yeniləmə zamanı xəta baş verdi: " + e.getMessage());
        }

        String fileName = "fayl.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Teacher teacher : Db.teachers) {
                writer.write(teacher.toString());
                writer.newLine();
            }
            System.out.println("Fayla müvəffəqiyyətlə əlavə edildi: " + fileName);
        } catch (IOException e) {
            System.err.println("Yazma xətası: " + e.getMessage());
        }

    }

    @Override
    public void delete() {
        System.out.println("necenci muellimi silmek istiyorsunuz?");
        int toDelete = new Scanner(System.in).nextInt();

        int indexToDelete = toDelete - 1;

        for (int i = indexToDelete; i < Db.teachers.length - 1; i++) {
            Db.teachers[i] = Db.teachers[i + 1];
        }

        Db.teachers[Db.teachers.length - 1] = null;

        String fileName = "fayl.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Teacher teacher : Db.teachers) {
                if (teacher != null) {
                    writer.write(teacher.toString());
                    writer.newLine();
                }
            }
            System.out.println("Dosyaya başarıyla eklendi: " + fileName);
        } catch (IOException e) {
            System.err.println("Yazma hatası: " + e.getMessage());
        }
    }


    @Override
    public void printAll() {
        Teacher[] teachers = Db.teachers;
        System.out.println("Qeydiyyatdan kechen muellimler.");
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] == null) continue;
            System.out.println((i + 1) + ". " + teachers[i]);
        }
    }


    @Override
    public void find() {
        Teacher[] teachers = Db.teachers;
        System.out.println("Axtarmaq istediyiniz muellimin adini ve ya soyadini daxil edin:");
        String text = new Scanner(System.in).nextLine();
        for (int i = 0; i < teachers.length; i++) {
            Teacher t = teachers[i];
            if (t == null) continue;

            if (t.getName().equals(text) || t.getSurname().equals(text)) {
                System.out.println(t);
            }

        }
    }

    private Teacher requireAndCreate() {
        Teacher teacher = new Teacher();

        System.out.println("Muellimin adini daxil edin:");
        teacher.setName(new Scanner(System.in).nextLine());

        System.out.println("Muellimin soyadini daxil edin:");
        teacher.setSurname(new Scanner(System.in).nextLine());

        System.out.println("Muellimin yashini daxil edin:");
        teacher.setAge(new Scanner(System.in).nextInt());


        return teacher;
    }
    public void get(){
        try {
            FileReader fr = new FileReader("fayl.txt");
            BufferedReader bf = new BufferedReader(fr);
            String s;
            while ((s = bf.readLine()) != null) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}