import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class HW10Composite {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        File file1 = new File("Document.txt", 10);
        File file2 = new File("Image.jpg", 20);
        File file3 = new File("Presentation.pptx", 30);

        Directory mainFolder = new Directory("MainFolder");
        Directory subFolder1 = new Directory("SubFolder1");
        Directory subFolder2 = new Directory("SubFolder2");

        mainFolder.addComponent(file1);
        mainFolder.addComponent(subFolder1);
        subFolder1.addComponent(file2);
        subFolder1.addComponent(subFolder2);
        subFolder2.addComponent(file3);

        System.out.println("Displaying File System Structure:");
        mainFolder.display();
        System.out.println("\nTotal Size of MainFolder: " + mainFolder.getSize() + " KB");

        System.out.println("\nTrying to add file1 again to MainFolder:");
        mainFolder.addComponent(file1);

        System.out.println("\nRemoving file2 from SubFolder1:");
        subFolder1.removeComponent(file2);

        System.out.println("\nDisplaying File System Structure after modifications:");
        mainFolder.display();
        System.out.println("\nTotal Size of MainFolder after modifications: " + mainFolder.getSize() + " KB");

        in.close();
    }
}

abstract class FileSystemComponent{
    protected String name;

    public FileSystemComponent(String name){
        this.name = name;
    }

    public abstract void display();
    public abstract int getSize();
}

class File extends FileSystemComponent{
    private int size;

    public File(String name, int size){
        super(name);
        this.size = size;
    }

    public void display(){
        System.out.println("File: " + name + ", Size: " + size + " KB");
    }

    public int getSize(){
        return size;
    }
}

class Directory extends FileSystemComponent{
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name){
        super(name);
    }

    public void addComponent(FileSystemComponent component){
        if (!components.contains(component)){
            components.add(component);
            System.out.println("Added " + component.name + " to " + this.name);
        } else {
            System.out.println(component.name + " already exists in " + this.name);
        }
    }

    public void removeComponent(FileSystemComponent component){
        if (components.contains(component)){
            components.remove(component);
            System.out.println("Removed " + component.name + " from " + this.name);
        } else {
            System.out.println(component.name + " does not exist in " + this.name);
        }
    }

    public void display(){
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components){
            component.display();
        }
    }

    public int getSize(){
        int totalSize = 0;
        for (FileSystemComponent component : components){
            totalSize += component.getSize();
        }
        return totalSize;
    }
}