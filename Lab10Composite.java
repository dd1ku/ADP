import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Lab10Composite{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        Directory root = new Directory("Root");
        File file1 = new File("File1.txt");
        File file2 = new File("File2.txt");

        Directory subDir = new Directory("SubDirectory");
        File subFile1 = new File("SubFile1.txt");

        root.add(file1);
        root.add(file2);
        subDir.add(subFile1);
        root.add(subDir);

        root.display(1);

        in.close();
    }
}
abstract class FileSystemComponent{
    protected String name;

    public FileSystemComponent(String name){
        this.name = name;
    }

    public abstract void display(int depth);

    public void add(FileSystemComponent component){
        throw new UnsupportedOperationException();
    }

    public void remove(FileSystemComponent component){
        throw new UnsupportedOperationException();
    }

    public FileSystemComponent getChild(int index){
        throw new UnsupportedOperationException();
    }
}

class File extends FileSystemComponent{
    public File(String name){
        super(name);
    }

    public void display(int depth){
        System.out.println(" ".repeat(depth) + "- File: " + name);
    }
}

class Directory extends FileSystemComponent{
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name){
        super(name);
    }

    public void add(FileSystemComponent component){
        children.add(component);
    }

    public void remove(FileSystemComponent component){
        children.remove(component);
    }

    public FileSystemComponent getChild(int index){
        return children.get(index);
    }

    public void display(int depth){
        System.out.println(" ".repeat(depth) + "- Directory: " + name);
        for (FileSystemComponent component : children) {
            component.display(depth + 2);
        }
    }
}