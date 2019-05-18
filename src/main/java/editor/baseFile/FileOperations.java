package editor.baseFile;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;

import pub.util.ConvertUtil;
import pub.util.Uuid;
import pub.util.WjbUtil;
@Controller
@RequestMapping("/file")
public class FileOperations {
	@Value(value = "#{prop.filedrive}")
	private String filedrive;
	@Value(value = "#{prop.filedir}")
	private String filepath;
	@ResponseBody
	@RequestMapping("/upload")
	public JSONObject upload(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		String flag="失败";
		try{
			request.setCharacterEncoding("utf-8");
			//文档id
			String wdid=request.getParameter("fj");
			//创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
			//判断 request 是否有文件上传,即多部分请求
			if(multipartResolver.isMultipart(request)){
				//转换成多部分request  
				MultipartHttpServletRequest multiRequest =         
						multipartResolver.resolveMultipart(request);
				//取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while(iter.hasNext()){
					//取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if(file != null){
						//取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						//如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if(myFileName.trim() !=""){
							//重命名上传后的文件名
							String fileName = file.getOriginalFilename();
							//定义上传路径
							String dirPath = this.filedrive+this.filepath;
							File dir = new File(dirPath);
							if(!dir.exists()){
								dir.mkdirs();
							}
							//String fn = wdid+fileName.substring(fileName.indexOf("."), fileName.length());
							File localFile = new File(dir, fileName);
							String filePath = ConvertUtil.createString(localFile);
							file.transferTo(localFile);
							//删除原有文件
							WjbUtil.deleteFj(wdid);
							//保存新增或修改的文档路径
							WjbUtil.saveFj(fileName, filePath, wdid);
							//结果绑定文件路径
							flag="成功";
							result.put("filename", fileName);
						}
					}
				}
			}
 
		}catch(Exception e){
			e.printStackTrace();
			result.put("flag", flag);
			return result;
		}
		result.put("flag", flag);
		return result;
	}
	
	@RequestMapping(value = "/download", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<byte[]> download(@RequestParam(name = "fileName") String fileName,
			HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		Pattern pattern = Pattern.compile("\\w*\\.\\w+");
		Matcher matcher = pattern.matcher(fileName);
		
		//检查文件名中非法字符，只允许是字母、数字和下划线
		/*if (matcher.matches()) {*/
			try {
				headers.setContentDispositionFormData("myfile", fileName);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				// 获取物理路径
				String filePath =  this.filedrive+this.filepath+File.separator+fileName;
				File pic = new File(filePath);
				if (pic.exists()) {
					return new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(pic), headers, HttpStatus.CREATED);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}
		
		return null;
	}
	
	
}
