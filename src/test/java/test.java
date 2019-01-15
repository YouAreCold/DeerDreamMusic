
import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


public class test {
public static void main(String[] args) {
	MultipartFile file = null;
	
	String result = "";  
    //创建处理EXCEL的类  
    ReadExcel readExcel = new ReadExcel();  
    //解析excel，获取上传的事件单  
    List<Map<String, Object>> userList = readExcel.getExcelInfo(file);  
    for (Map<String, Object> map : userList) {
		System.out.println(map);
	}
}

}
