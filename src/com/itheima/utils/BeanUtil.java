package com.itheima.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 填充表单数据到javabean的工具
 */
public class BeanUtil {
	/**
	 * 封装表单中的数据到javabean中
	 * @param request	表单中的数据
	 * @param clazz		封装到哪个javabean
	 * @return	封装好的javabean对象
	 * 使用的是泛型。泛型必须先声明再使用。声明必须在返回值之前
	 * T指的就是泛型，它可以是任意字符，只是作为一个占位符。
	 * 声明时用什么字符，使用时就得用什么
	 */
	public static <T> T fillBean(HttpServletRequest request,Class<T> clazz){
		//1.定义一个T类型的javabean
		T bean = null;
		try{
			//2.实例化bean对象
			bean = clazz.newInstance();
			//3.使用BeanUtils的方法进行封装
			BeanUtils.populate(bean, request.getParameterMap());
			//4.返回
			return bean;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}



	/**
	 * 适用于文件上传，将表单参数封装到bean对象中，同时如果表单项是文件上传项，还要完成文件上传功能
	 * @param request	请求对象
	 * @param clazz	要封装的实体类字节码
	 * @param <T>	泛型
	 * @return		返回封装好的对象
	 */
	public static <T> T fillUploadBean(HttpServletRequest request,Class<T> clazz){
		try {
			//1 判断表单是否是文件上传到form表单，是否有multipart/form-data
			if(ServletFileUpload.isMultipartContent(request)){
				//2 创建磁盘工厂对象DiskFileItemFactory
				DiskFileItemFactory factory=new DiskFileItemFactory();
				//3 创建文件上传的核心对象ServletFileUpload
				ServletFileUpload fileUpload=new ServletFileUpload(factory);
				//4 解析request请求，得到List<FileItem>
				List<FileItem> fileItems = fileUpload.parseRequest(request);

				//实例化对象，用来封装表单参数
				T bean = clazz.newInstance();
				//创建map集合，用来保存表单参数值
				Map<String,Object> parameterMap=new HashMap<>();
				String filename=""; //保存文件名，最后存到parameterMap集合中
				//5 遍历List<FileItem>，判断是普通表单项还是文件上传项
				for (FileItem fileItem : fileItems) {
					//获取表单的name属性，例如name="subject",name="picture"
					String fieldName = fileItem.getFieldName();
					//fileItem表示所有表单项，包括文件上传项和普通表单项
					if(fileItem.isFormField()){//true表示普通表单项
						//6 如果是普通表单项那么就通过反射将表单参数封装到bean对象中
						//获取表单的value属性值
						String fieldValue=fileItem.getString("utf-8");
						//将普通的请求参数名和请求参数值封装到map集合中
						parameterMap.put(fieldName,fieldValue);
					}else{ //false表示文件上传项
						//7将图片写到服务器磁盘中，也要将图片的名称反射封装到bean对象的属性中
						//7.1 找到项目根路径(webapp)中的upload目录
						File dirFile=new File(request.getServletContext().getRealPath("upload"));
						//7.2 获取文件名称名
						filename = fileItem.getName();  //a.jpg
						//如果有上传文件就写到服务器磁盘中
						if(!StringUtils.isBlank(filename)){
							//生成唯一文件名：前缀
							String prefix = UUID.randomUUID().toString();
							//后缀：.jpg
							String subfix = filename.substring(filename.lastIndexOf("."));
							//前缀+后缀组合成新的唯一文件名
							filename=prefix+subfix; //sdui9usdfuoduf9sc9887rjnndf.jpg
							//3 将文件写到磁盘中
							fileItem.write(new File(dirFile,filename));
						}
					}
				}
				if(!StringUtils.isBlank(filename)){  //有上传图片就保存文件名，没有上传就不保存。
					//保存图片名称到集合中，循环结束后封装到javabean中
					parameterMap.put("picture",filename);
				}
				//3.使用BeanUtils的方法进行封装
				BeanUtils.populate(bean, parameterMap);
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
