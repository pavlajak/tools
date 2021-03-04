package filetree;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileTree {
    public static void main(String[] args) {
        FileTree ft = new FileTree("D:\\");
        ft.indentPrint();
    }

    private FTFolder root;
    public FileTree(String path){
        root = new FTFolder(path);
    }

    public void indentPrint(){
        indentPrint(root, 0);
    }

    private void indentPrint(FTFolder ftf, int indent){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) sb.append(' ');
        String indnt = sb.toString();

        System.out.printf("%s+%s %s\n", indnt, ftf.name, util.PrettyPrinter.bytesToPretty(ftf.size));
        ftf.fileList.forEach(f -> System.out.printf("%s %s %s\n", indnt, f.name, util.PrettyPrinter.bytesToPretty(f.size)));
        ftf.folderList.forEach(f -> indentPrint(f, indent + 1));
    }

    class FTFolder{
        private String name;
        private long size = -1;
        private List<FTFile> fileList;
        private List<FTFolder> folderList;

        public FTFolder(){}
        public FTFolder(String path){
            fileList = new ArrayList<>();
            folderList = new ArrayList<>();

            File thisFolder = new File(path);
            if(!thisFolder.exists()){
                System.out.printf("No such folder %s\n", path);
                return;
            } if (thisFolder.isFile()){
                System.out.printf("%s is not a directory\n", path);
                return;
            }
            name = thisFolder.getName();
            try {
                Arrays.asList(thisFolder.listFiles()).forEach(f -> {
                    if (f.isDirectory()) {
                        FTFolder child = new FTFolder(f.getAbsolutePath());
                        folderList.add(child);
                        size += child.size;
                    } else {
                        FTFile child = new FTFile(f.getName(), f.length());
                        fileList.add(child);
                        size += child.size;
                    }
                });
            } catch (NullPointerException npe){ //recycle bin produces null pointers

            }
        }
    }

    class FTFile{
        private String name;
        private long size;
        public FTFile(String name, long size){
            this.name = name;
            this.size = size;
        }
    }
}
