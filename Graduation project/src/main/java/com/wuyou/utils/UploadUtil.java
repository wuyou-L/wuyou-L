package com.wuyou.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class UploadUtil {

    public Map<String, Object> uploadFile(MultipartFile[] files, String basePath, String domainPath) {

        // 文件对象，基础路径，文件路径，访问域名
        Map<String, Object> map = new HashMap<>();
        // 格式化出文件路径
        SimpleDateFormat filePathFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String filePathDate = filePathFormat.format(date);
        String realPath = basePath + filePathDate;
        // 判断当前文件路径是否存在
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (MultipartFile file : files) {
            // 设置文件新名称
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString();
            File fileOnServer = new File(realPath, fileName + suffix);
            try {
                // 文件上传
                file.transferTo(fileOnServer);
//                map.put("domainPath", domainPath + filePathDate + "/" + fileName + suffix);
                map.put("domainPath", "/uploadimage/" + filePathDate + "/" + fileName + suffix);
                map.put("filePath", realPath + "/" + fileName + suffix);
//                map.put("fileName", fileName + suffix);
                map.put("fileName", fileName + suffix);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return map;
    }
}
