package com.guzhz.utils;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author：Guzhz
 * @date ：2020/6/26 14:15
 */
@Repository
public class FileUpload {


    /**
     *
     * @param upLoadFile
     * @return
     */
    public static String uploadFile(MultipartFile upLoadFile){
        if (upLoadFile.isEmpty()) {
            System.out.println("文件为空空");
            return null;
        }
        String filePath = "F:\\Study\\Book6\\JavaEE\\images";
//        String filePath = "/root/guzhz";

        //获取原始文件名
        String fileName = upLoadFile.getOriginalFilename();

        /*新的文件名*/
        fileName =  UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));

        File file = new File(filePath,fileName);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            System.out.println("创建成功！");
        }
        try {
            upLoadFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgPath = "/images/"+fileName;
        return imgPath;
    }

}
