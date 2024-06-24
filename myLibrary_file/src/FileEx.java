import javax.imageio.IIOException;
import java.io.*;
import java.util.Scanner;

public class FileEx {
    public static void main(String[] args) throws IOException{
        Scanner scr = new Scanner(System.in);

        while (true){
            showMenu();
            System.out.print(">>>");
            int option = scr.nextInt();

            switch (option){
                case 1:
                    addBook(scr);
                    break;
                case 2:
                    listBook();
                    break;
                case  3:
                    System.out.print("Aramak istediğiniz kitap başlığını giriniz: ");
                    String search = scr.next();
                    searchBook(search);
                    break;
                case 4:
                    System.out.print("Silmek istediğiniz kitap başlığını giriniz: ");
                    String delete = scr.next();
                    deleteBook(delete);
                    break;
                case 5:
                    System.out.print("Değiştirmek istediğiniz kitap başlığını giriniz: ");
                    String update = scr.next();
                    System.out.print("Kitabın yeni başlığı: ");
                    String updatevariable = scr.next();
                    updateBook(update,updatevariable);
                    break;
                case 6:
                    return;
            }
        }
    }

    public static void showMenu(){
        System.out.println("Add:1, List:2, Search:3, Delete:4, Update:5, Exit:6");
    }

    public static void addBook(Scanner scr) throws IOException {
        String title, author, category, pyear, price;
        System.out.print("title? ");
        title = scr.next();
        System.out.print("author? ");
        author = scr.next();
        System.out.print("category? ");
        category = scr.next();
        System.out.print("pyear? ");
        pyear = scr.next();
        System.out.print("price? ");
        price = scr.next();

        writeFile(title, author, category, pyear, price);
    }

    public static void writeFile(String title,String author,String category,String pyear,String price) throws IOException{
        File file = new File("src/myfile");

        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.append("\n" + title + "\t" + author + "\t" + category + "\t" + pyear + "\t" + price);
        bufferedWriter.close();

        System.out.println("Yazma işlemi tamamlandı!");
    }

    public static void listBook() throws IOException{
        File file = new File("src/myfile");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        StringBuilder stringBuilder = new StringBuilder();

        boolean isexist = false;

        while (line != null){
            stringBuilder.append(line+"\n");
            line = bufferedReader.readLine();
            isexist = true;
        }
        bufferedReader.close();

        if(isexist){
            System.out.println(stringBuilder.toString());
        }else{
            System.out.println("Herhangi bir kitap yok!");
        }
        System.out.println("Okuma başarılı \n");
    }

    public static void searchBook(String search) throws IOException{
        File file = new File("src/myfile");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        while (line != null){
            String[] parts = line.split("\t");
            if (parts[0].equalsIgnoreCase(search)){
                System.out.println("Aradığınız başlıkta bir kitap var\n" +line+ "\n");
            }
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    public static void deleteBook(String delete) throws IOException{
        File file = new File("src/myfile");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        StringBuilder stringBuilder = new StringBuilder();

        while (line != null){
            String[] parts = line.split("\t");
            if (parts[0].equalsIgnoreCase(delete)){
                stringBuilder.append(line + "\n");
            }
            line = bufferedReader.readLine();
        }
        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(stringBuilder);
        System.out.println("Silme Başarılı");
        bufferedWriter.close();
    }

    public static void updateBook(String update,String updatevariable) throws IOException{
        File file = new File("src/myfile");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        StringBuilder stringBuilder = new StringBuilder();

        while (line != null){
            String[] parts = line.split("\t");
            if (!parts[0].equalsIgnoreCase(update)){
                parts[0] = updatevariable;
                for(int i = 0; i < parts.length; i++){
                    stringBuilder.append(parts[i] + "\t");
                }
                stringBuilder.append("\n");
            }else{
                stringBuilder.append(line + "\n");
            }
            line = bufferedReader.readLine();
        }
        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(stringBuilder);
        System.out.println("Değiştirme işlemi başarılı\n");
        bufferedWriter.close();
    }

}
