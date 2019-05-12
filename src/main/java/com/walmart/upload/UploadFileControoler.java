package com.walmart.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileControoler {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileControoler.class);
    
	   
	   
    /*public String upload64(@RequestParam("file") MultipartFile file,HttpServletRequest request){
    		System.out.println(canvas);
    		//��ȡ��ĿͼƬ·��E:\homework_ssm\huochetou-master\huochetou-web\huochetou-portal\src\main\webapp\
    		System.out.println(request.getServletContext().getRealPath("/")); 
    		
    		BASE64Decoder decoder = new BASE64Decoder();
			String Pathc = "";
			try {
				byte[] b = decoder.decodeBuffer(canvas);//ת��õ�ͼƬ����

				ByteArrayInputStream bais = new ByteArrayInputStream(file);
				BufferedImage bi1 = ImageIO.read(bais);
				
				String projrctPath = request.getServletContext().getRealPath("/");
				String imgNmae = StringUtil.game(5)+StringUtil.getRandomChar('a', 'z');
				String Path = projrctPath+"img"+"\\"+imgNmae+".png";
				Pathc = imgNmae+".png";
				System.out.println(Path);
				File w2 = new File(Path);

				ImageIO.write(bi1, "png", w2); //д����ָ������Ŀ·����
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return Pathc;	
    }*/
    
    @RequestMapping("/uploadFile")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        if (file.isEmpty()) {
            return "�ϴ�ʧ�ܣ���ѡ���ļ�";
        }
        String projrctPath = request.getServletContext().getRealPath("/");
        LOGGER.info("������·����"+projrctPath);
        String fileName = file.getOriginalFilename();
        String path = "/upload";
        File dest = new File(path + "/" + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("�ϴ��ɹ�");
            return "�ϴ��ɹ�";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "�ϴ�ʧ�ܣ�";
    }


}
