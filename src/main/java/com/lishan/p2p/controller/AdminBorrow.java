package com.lishan.p2p.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lishan.p2p.pojo.Borrow;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.service.BorrowService;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/adminBorrow")
public class AdminBorrow {
	@Autowired
	private BorrowService service;
	
	
	/**
	 * 查询标
	 */
	@RequestMapping("/showInvestList")
	public String showInvestList(HttpSession session,Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,String starttime,String endtime,String names) {
		PageHelper.startPage(pn,10);
		List<Invest> investList=service.showInvestList(starttime,endtime,names);
		m.addAttribute("list", investList);
		PageInfo<Invest>  p = new PageInfo<>(investList);
		m.addAttribute("page", p);
		session.setAttribute("istart", starttime);
		session.setAttribute("iend", endtime);
		session.setAttribute("inames", names);
		return "jsp/invest-list";
	}
	/**
	 * 发布标
	 */
	@RequestMapping("/insertInvest")
	@ResponseBody
	public String insertInvest(String proname,Integer biaolimit,Integer id,String des,String xydj,Integer uid) {
		Borrow borbiao=service.getMyByBorrowId(id);
		Invest invest=new Invest();
		invest.setProname(proname);invest.setBiaolimit(biaolimit);invest.setBid(id);
		invest.setBiaodate(new Date()); invest.setJemoney(borbiao.getJemoney());
		invest.setDes(des);invest.setXydj(xydj);invest.setUid(uid);
		service.insertInvest(invest);
		return "1";
	}
	/**
	 * 点击发标
	 */
	@RequestMapping("/updateBiao")
	@ResponseBody
	public Borrow updateBiao(Integer id){
		Borrow borrlist=service.getMyByBorrowId(id);
		
		return borrlist;
	}
	/**
	 * 借款申请审核不通过updateBuTongg
	 */
	@RequestMapping("/updateBuTongg")
	@ResponseBody
	public String updateBuTongg(Integer id) {
		service.updateBuTongg(id);
		return "1";
	}
	/**
	 * 借款申请审核通过
	 */
	@RequestMapping("/updateTongg")
	@ResponseBody
	public String updateTongg(Integer id,Integer uid) {
		service.updateTongg(id,uid);
		return "1";
	}
	/**
	 * 查询借款列表
	 * 根据时间搜索
	 * @param m
	 * @param pn
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/showBorrow")
	public String showBorrow(HttpSession session,Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,String starttime,String endtime,String names) throws ParseException {
		
		PageHelper.startPage(pn,6);
		List<Borrow> blist=service.showBorrowByDate(starttime,endtime,names);
		PageInfo<Borrow>  p = new PageInfo<>(blist);
		m.addAttribute("page", p);
		m.addAttribute("list", blist);
		session.setAttribute("start", starttime);
		session.setAttribute("end", endtime);
		session.setAttribute("names", names);
		return "jsp/borrow";
	}
	
	/**
	 * 导出标 Excel
	 */
	@RequestMapping("/daochu/portInvestExcel")
	public void portInvestExcel(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
		 String fileName = sdf.format(new Date()) + ".xls";  
		  
		 response.setContentType("application/x-excel");  
		 response.setCharacterEncoding("UTF-8");  
		 response.addHeader("Content-Disposition", "attachment;filename="  
		            + fileName);// excel文件名  	
		 
		 try {  
		        // 1.创建excel文件  
		        WritableWorkbook book = Workbook.createWorkbook(response  
		                .getOutputStream());  
		        // 居中  
		        WritableCellFormat wf = new WritableCellFormat();  
		        wf.setAlignment(Alignment.CENTRE);  
		  
		        WritableSheet sheet = book.createSheet("shet", 0);  
		        SheetSettings settings = sheet.getSettings();  
		        settings.setVerticalFreeze(2);  
	            // 3.添加第一行及第二行标题数据  
	            sheet.addCell(new Label(0, 0, "ID", wf));  
	            sheet.addCell(new Label(1, 0, "用户名", wf));  
	            sheet.addCell(new Label(2, 0, "借款金额", wf));  
	            sheet.addCell(new Label(3, 0, "借款期限", wf));
	            sheet.addCell(new Label(4, 0, "月利率/% ", wf)); 
	            sheet.addCell(new Label(5, 0, "发标时间", wf));  
	            sheet.addCell(new Label(6, 0, "发表期限/天", wf));
	            sheet.addCell(new Label(7, 0, "联系电话", wf));
		            // 4.历史数据，业务数据，不用关注  
	            List<Invest> list=service.showInvest();
		            if (list != null && list.size() > 0) {  
		                // 5.将历史数据添加到单元格中  
		                for (int j = 0; j < list.size(); j++) {  
		                    sheet.addCell(new Label(0, j+1, list.get(j).getId() + "", wf));
		                    sheet.addCell(new Label(1, j+1, list.get(j).getBorrow().getUsers().getName() + "", wf)); 
		                    sheet.addCell(new Label(2, j+1, list.get(j).getJemoney() + "", wf)); 
		                    sheet.addCell(new Label(3, j+1, list.get(j).getBorrow().getTlimit() + "", wf)); 
		                    sheet.addCell(new Label(4, j+1, list.get(j).getBorrow().getRate() + "", wf));
		                    SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        			String date=smf.format(list.get(j).getBiaodate());
		                    sheet.addCell(new Label(5, j+1, date + "", wf)); 
		                    sheet.addCell(new Label(6, j+1, list.get(j).getBiaolimit() + "", wf)); 
		                    sheet.addCell(new Label(7, j+1, list.get(j).getBorrow().getTel() + "", wf));
		                }  
		            }  
		        // 6.写入excel并关闭  
		        book.write();  
		        book.close();  
		      
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
	}
	
	/**
	 * 下载借款申请
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
		@RequestMapping("/downBorrow")
		public void downBorrow(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
			//处理中文的编码的问题
			String parameter = request.getParameter("fileName");
			//获取用户要下载的文件的文件名
			String fname=(String)session.getAttribute("fileName");
			String fileName = new String(fname.getBytes("iso-8859-1"),"utf-8");
			System.out.println(fileName);
			//服务器加载用户要下载的文件数据。e:\fileName
			String realPath ="e:\\fileName";
			File file = new File(realPath,fileName);
			System.out.println(file);
			//通知浏览器以下载的方式请求资源
			//设置文件媒体格式
			response.setContentType(request.getServletContext().getMimeType(fileName));
			//解决文件下载的中文乱码问题
			//判断浏览器的类型
			String header = request.getHeader("User-Agent");
			if(header.contains("Firefox")){
				//判断如果是火狐浏览器，使用base64编码
				BASE64Encoder base64Encoder = new BASE64Encoder();
				fileName = "=?utf-8?B?"	+ base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
			}else{
				//谷歌浏览器及其他浏览器处理
				fileName = URLEncoder.encode(fileName,"utf-8");
			}
			//设置要被下载的文件的文件名
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			//使用IO技术，将数据发送（使用response对象发送数据）
			//获取输入流，读取文件数据
			FileInputStream in = new FileInputStream(file);
			//获取输出流，写出文件数据
			ServletOutputStream out = response.getOutputStream();
			//定义一个缓冲区
			byte[] buf = new byte[1024];
			while(in.read(buf) != -1){
				out.write(buf);
			}
			in.close();
			out.close();
		}
	
	/**
	 * //导出借款申请 Excel   jxl
	 * @param request
	 * @param response
	 */
		@RequestMapping("/daochu/portExcel")
		public void invitationExcel(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
			 String fileName = sdf.format(new Date()) + ".xls";  
			  
			 response.setContentType("application/x-excel");  
			 response.setCharacterEncoding("UTF-8");  
			 response.addHeader("Content-Disposition", "attachment;filename="  
			            + fileName);// excel文件名  	
			 try {  
			        // 1.创建excel文件  
			        WritableWorkbook book = Workbook.createWorkbook(response  
			                .getOutputStream());  
			        // 居中  
			        WritableCellFormat wf = new WritableCellFormat();  
			        wf.setAlignment(Alignment.CENTRE);  
			  
			        WritableSheet sheet = book.createSheet("shet", 0);  
			        SheetSettings settings = sheet.getSettings();  
			        settings.setVerticalFreeze(2);  
		            // 3.添加第一行及第二行标题数据  
		            sheet.addCell(new Label(0, 0, "ID", wf));  
		            sheet.addCell(new Label(1, 0, "用户名", wf));  
		            sheet.addCell(new Label(2, 0, "申请金额", wf));  
		            sheet.addCell(new Label(3, 0, "申请时间", wf));
		            sheet.addCell(new Label(4, 0, "月利率/% ", wf)); 
		            sheet.addCell(new Label(5, 0, "借款期限", wf));  
		            sheet.addCell(new Label(6, 0, "月收入", wf));
		            sheet.addCell(new Label(7, 0, "联系电话", wf));
			            // 4.历史数据，业务数据，不用关注  
		            session.setAttribute("fileName", fileName);
		            List<Borrow> list=service.showBorrow();
			            if (list != null && list.size() > 0) {  
			                // 5.将历史数据添加到单元格中  
			                for (int j = 0; j < list.size(); j++) {  
			                    sheet.addCell(new Label(0, j+1, list.get(j).getId() + "", wf));
			                    sheet.addCell(new Label(1, j+1, list.get(j).getUsers().getName() + "", wf)); 
			                    sheet.addCell(new Label(2, j+1, list.get(j).getJemoney() + "", wf)); 
			                    sheet.addCell(new Label(3, j+1, list.get(j).getSqdate() + "", wf)); 
			                    sheet.addCell(new Label(4, j+1, list.get(j).getRate() + "", wf));
			                    SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        			String date=smf.format(list.get(j).getSqdate());
			                    sheet.addCell(new Label(5, j+1, date + "", wf)); 
			                    sheet.addCell(new Label(6, j+1, list.get(j).getYuecom() + "", wf)); 
			                    sheet.addCell(new Label(7, j+1, list.get(j).getTel() + "", wf));
			                }  
			            }  
			        // 6.写入excel并关闭  
			        book.write();  
			        book.close();  
			    } catch (Exception e) {  
			        e.printStackTrace();  
			    }  
			/* return "forward:/adminBorrow/downBorrow.action";*/
		}
	
	/**
	 * 导出Excel   poi
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	/*@RequestMapping("/daochu/portExcel")
	public String portExcel(HttpSession session) throws FileNotFoundException, IOException {
		//创建工作簿
		HSSFWorkbook workBook = new HSSFWorkbook();
		//创建工作表  工作表（sheet）的名字叫hello
		HSSFSheet sheet = workBook.createSheet("申请列表");
		// 内容样式类型
        HSSFCellStyle style_content = workBook.createCellStyle();
        style_content.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        style_content.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style_content.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style_content.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		
		//创建行,第1行
		HSSFRow row = sheet.createRow(0);
		//创建列，1行第1列，设置第1行1列的值
		HSSFCell cell0 = row.createCell(0);
		cell0.setCellValue("ID");
		//创建列，1行第2列，设置第1行2列的值
		HSSFCell cell1 = row.createCell(1);
		cell1.setCellValue("用户名");
		//创建列，1行第3列，设置第1行2列的值
		HSSFCell cell2 = row.createCell(2);
		cell2.setCellValue("申请金额");
		//创建列，1行第4列，设置第1行2列的值
		HSSFCell cell3 = row.createCell(3);
		cell3.setCellValue("申请时间");
		//创建列，1行第5列，设置第1行2列的值
		HSSFCell cell4 = row.createCell(4);
		cell4.setCellValue("月利率/%");
		//创建列，1行第6列，设置第1行2列的值
		HSSFCell cell5 = row.createCell(5);
		cell5.setCellValue("借款期限");
		//创建列，1行第7列，设置第1行2列的值
		HSSFCell cell6 = row.createCell(6);
		cell6.setCellValue("月收入");
		//创建列，1行第8列，设置第1行2列的值
		HSSFCell cell7 = row.createCell(7);
		cell7.setCellValue("联系电话");
		
		//设置单元格边框
		cell0.setCellStyle(style_content);
		cell1.setCellStyle(style_content);
		cell2.setCellStyle(style_content);
		cell3.setCellStyle(style_content);
		cell4.setCellStyle(style_content);
		cell5.setCellStyle(style_content);
		cell6.setCellStyle(style_content);
		cell7.setCellStyle(style_content);
		
		//获取申请列表
		List<Borrow> list=service.showBorrow();
		for (int i = 0; i <list.size(); i++) {
			HSSFRow row1 = sheet.createRow(i+1);
			//创建第2行第1列，并设置第2行第1列的值
			HSSFCell cell10 = row1.createCell(0);
			cell10.setCellValue(list.get(i).getId());
			//创建第2行第2列，并设置第2行第2列的值
			HSSFCell cell11 = row1.createCell(1);
			cell11.setCellValue(list.get(i).getUsers().getUsername());
			//创建第2行第3列，并设置第2行第3列的值
			HSSFCell cell12 = row1.createCell(2);
			cell12.setCellValue(list.get(i).getJemoney());
			//创建第2行第4列，并设置第2行第4列的值
			SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=smf.format(list.get(i).getSqdate());
			HSSFCell cell13 = row1.createCell(3);
			cell13.setCellValue(date);
			//创建第2行第5列，并设置第2行第5列的值
			HSSFCell cell14 = row1.createCell(4);
			cell14.setCellValue(list.get(i).getRate());
			//创建第2行第6列，并设置第2行第6列的值
			HSSFCell cell15 = row1.createCell(5);
			cell15.setCellValue(list.get(i).getTlimit());
			//创建第2行第7列，并设置第2行第7列的值
			HSSFCell cell16 = row1.createCell(6);
			cell16.setCellValue(list.get(i).getYuecom());
			//创建第2行第8列，并设置第2行第8列的值
			HSSFCell cell17 = row1.createCell(7);
			cell17.setCellValue(list.get(i).getTel());
			
			//设置单元格边框
			cell10.setCellStyle(style_content);
			cell11.setCellStyle(style_content);
			cell12.setCellStyle(style_content);
			cell13.setCellStyle(style_content);
			cell14.setCellStyle(style_content);
			cell15.setCellStyle(style_content);
			cell16.setCellStyle(style_content);
			cell17.setCellStyle(style_content);
			
			//生成文件
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
			 String fileName = sdf.format(new Date()) + ".xls"; 
			 session.setAttribute("fileName", fileName);
			workBook.write(new FileOutputStream(new File("e:\\fileName")));
			//最后记得关闭工作簿
			workBook.close();
			
			
		}
		return "forward:/adminBorrow/downBorrow.action";
	}
	*/
}
