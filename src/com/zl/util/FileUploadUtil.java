package com.zl.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    private String filePath;  
    
    public void setFilePath(String filePath) {  
        System.out.println(filePath);  
        this.filePath = filePath;  
    }  
       
    //实现文件上传的功能，返回上传后新的文件名称  
    public String uploadFile(MultipartFile fileImage ) {  
	    // 处理上传的单个图片    
	    String originalFileName = fileImage.getOriginalFilename();// 原始名称
	    // 上传图片
	    String newFileName = null;
	    if (fileImage != null && originalFileName != null && originalFileName.length() > 0) {
	        // 存储图片的物理路径，实际中是要写到配置文件中的，不能在这写死
	        String pic_path = filePath;
	        // 新的图片名称
	        newFileName = UUID.randomUUID()
	                + originalFileName.substring(originalFileName
	                        .lastIndexOf("."));     
	        File newFile = new File(pic_path + newFileName);//新图片   
	        try {
				fileImage.transferTo(newFile);// 将内存中的数据写入磁盘
		        return newFileName;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return newFileName;
    }  
    
    
    //多个文件的上传
/*    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Model model, HttpServletRequest request,
            Integer id,
            @Validated(value = { ValidGroup1.class }) ItemsCustom itemsCustom,
            BindingResult bindingResult, 
            @RequestParam MultipartFile[] items_pic)
            throws Exception {  

        //多个图片，不存数据库了，在此打印一下即可
        for(MultipartFile myfile : items_pic) {
            if(myfile.isEmpty()){  
                System.out.println("文件未上传");  
            }else{  
                System.out.println("文件长度: " + myfile.getSize());  
                System.out.println("文件类型: " + myfile.getContentType());  
                System.out.println("文件名称: " + myfile.getName());  
                System.out.println("文件原名: " + myfile.getOriginalFilename());  
                System.out.println("========================================");  

                //写入磁盘，和上面的单个文件上传一模一样
                String originalFileName = myfile.getOriginalFilename();
                String pic_path = "E:\\github\\develop\\upload\\temp\\";
                String newFileName = UUID.randomUUID()
                        + originalFileName.substring(originalFileName
                                .lastIndexOf("."));
                File newFile = new File(pic_path + newFileName);
                myfile.transferTo(newFile);
            }  
        }   

        return "/WEB-INF/jsp/success.jsp";
    }*/
}
