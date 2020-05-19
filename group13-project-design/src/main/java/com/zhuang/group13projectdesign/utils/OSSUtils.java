package com.zhuang.group13projectdesign.utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class OSSUtils {
    private static final String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static final String accessKeyId = "*******";
    private static final String accessKeySecret = "*******";

    public static String createOSS(String bucketName){
        try {
            // 创建ClientConfiguration实例，按照您的需要修改默认参数。
            ClientConfiguration conf = new ClientConfiguration();
            // 开启支持CNAME。CNAME是指将自定义域名绑定到存储空间上。
            conf.setSupportCname(true);
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret,conf);
            // 创建存储空间。
            ossClient.createBucket(bucketName);
            // 关闭OSSClient。
            ossClient.shutdown();
            return "success";
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "false";
    }

    public static String uploadString(String bucketName,String pramaName){
        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            // 上传字符串。
            String content = "Hello OSS";
            ossClient.putObject(bucketName, pramaName, new ByteArrayInputStream(content.getBytes()));

            // 关闭OSSClient。
            ossClient.shutdown();
            return "success";
        } catch (Exception e) {
        e.printStackTrace();
        }
        return "false";
    }

    public static String uploadFile(String bucketName,String pathName,String fileName){
        //fileName 文件具体路径+名称  pathName 为OSS的全存储名称 访问地址https://tdwallimg.oss-cn-beijing.aliyuncs.com/
        //pathName : tdwalltest/咋赚.png
        //https://tdwallimg.oss-cn-beijing.aliyuncs.com/tdwalltest/咋赚.png

        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            InputStream inputStream = new FileInputStream(fileName);
            PutObjectResult putObjectResult=ossClient.putObject(bucketName, pathName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return "success";
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "false";
    }

    public static String uploadLocalFile(String bucketName,String pathName,String filePath){
        //pathName 为上传到OSS的具体存储名称  fliePath为需要上传文件的本地路径 如/users/local/myfile.txt。
        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
            ossClient.putObject(bucketName, pathName, new File(filePath));
            // 关闭OSSClient。
            ossClient.shutdown();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public static String downloadFile(String bucketName,String pathName,String localName){
        //pathName 为OSS上文件名  localName 为要存储到本地的文件路径+名称
        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(bucketName, pathName), new File(localName));
            // 关闭OSSClient。
            ossClient.shutdown();
            return "success";
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "false";
    }

    //单个删除文件
    public static String deleteOneFile(String bucketName,String pathName){
       try {
           // 创建OSSClient实例。
           OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
           // 删除文件。
           ossClient.deleteObject(bucketName, pathName);

           // 关闭OSSClient。
           ossClient.shutdown();
           return "success";
       } catch (Exception e) {
        e.printStackTrace();
       }
       return "false";
   }

   //批量删除文件
   public static String deleteFiles(String bucketName,List<String> keys){
     /*  List<String> keys = new ArrayList<String>();
       keys.add("pathName0");
       keys.add("pathName1");
       keys.add("pathName2");*/
       try {
           // 创建OSSClient实例。
           OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
           DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
           List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
           // 关闭OSSClient。
           ossClient.shutdown();
           return "success";
       } catch (Exception e) {
          e.printStackTrace();
       }
       return "false";
   }


}
