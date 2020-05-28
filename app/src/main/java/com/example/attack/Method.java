
package com.example.attack;

import java.io.File;

public class Method {

    public static void load_Directory_Files(File directory){
        File[] fileList = directory.listFiles();
        if(fileList != null && fileList.length > 0){
            for (int i=0; i<fileList.length; i++){
                if(fileList[i].isDirectory()){
                    load_Directory_Files(fileList[i]);
                }
                else {
                    String name = fileList[i].getName().toLowerCase();
                    for (String extension: Constant.audioExtension){
                        //check the type of file
                        if(name.endsWith(extension)){
                            Constant.allmedialist.add(fileList[i]);
                            //when we found file
                            break;
                        }
                    }
                }
            }
        }
    }

}
