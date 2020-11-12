package FilesExplorer_queue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FilesExplorer_queue {

/*
Find files ans show their Paths
*/

        public static List<String> getFileTree(String root) throws IOException {

            File folders = new File(root);
            File[] files = folders.listFiles(); //take all files and directories from root
            List<String> listFiles = new ArrayList<>();
            Queue<String> queueDir = new ArrayDeque<>(); //queue of strings with names of directories
            //Do first detour in root directory
            for (int i = 0; i < files.length; i ++){
                if (files[i].isFile()){
                    listFiles.add(files[i].getAbsolutePath()); //add found file
                }
                if (files[i].isDirectory()){
                    queueDir.offer(files[i].getPath()); //put new found directory to queue
                }
            }
            //Then we are performing next detours until queue is empty
            //If we find new folders we add them to queue
            while (queueDir.size() > 0){
                folders = new File(queueDir.poll());
                files = folders.listFiles();
                for (int i = 0; i < files.length; i ++){
                    if (files[i].isFile()){
                        listFiles.add(files[i].getAbsolutePath()); //add found file
                    }
                    if (files[i].isDirectory()){
                        queueDir.offer(files[i].getPath()); //put new found directory to queue
                    }
                }
            }
            return listFiles;
        }

        public static void main(String[] args) {
            try {
                System.out.println(getFileTree("root")); //send root directory
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

