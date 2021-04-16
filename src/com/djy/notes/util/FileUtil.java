package com.djy.notes.util;

import com.djy.notes.entity.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author djy
 */
public class FileUtil {
    // 使用的基础目录
    public static String userFilePath;
    //静态代码块
    static {
        //通过类加载器获取相对路径
        URL resource = FileUtil.class.getClassLoader().getResource("");
        userFilePath = resource.getPath() + "user.txt";
    }

    /**
     * 将用户信息写入userFilePath
     * @param user
     */
    public static void write(User user) {
        //创建文件类的对象
        File file = new File(userFilePath);
        //假如文件不存在
        if (!file.exists()) {
            try {
                //创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //获取用户id和用户名
        int userId = user.getUserId();
        String userName = user.getUserName();
        String password = user.getPassword();
        String telephone =user.getTelephone();
        String email =user.getEmail();
        String sign =user.getSign();

        //通过,拼接字符串
        String str = String.join(",",userId+"",userName+"",password+"",telephone+"",email+"",sign);
        try {
            //写入文件中
            FileUtils.writeStringToFile(file,str,StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前登录用户的id
     * @return
     */
    public static int getUserId(){
        File file = new File(userFilePath);
        try {
            //读取文件内容
            String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
            //通过,分割，返回数组，用户id在数组0号索引位置
            return Integer.parseInt(str.split(",")[0]);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("无法获取文件:"+userFilePath);
        }
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public static String getUserName(){
        File file = new File(userFilePath);
        try {
            String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
            //通过,分割，返回数组，用户id在数组1号索引位置
            return str.split(",")[1];
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("无法获取文件:"+userFilePath);
        }
    }
    /**
     * 获取当前登录用户密码
     * @return
     */
    public static String getPassword(){
        File file = new File(userFilePath);
        try {
            String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
            //通过,分割，返回数组，用户id在数组2号索引位置
            return str.split(",")[2];
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("无法获取文件:"+userFilePath);
        }
    }
}
