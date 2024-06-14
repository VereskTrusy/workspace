package kr.or.ddit.case10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case10")
public class FileUploadController {
	
	@Value("file:F:/02.saveFolder/")
	private Resource saveFolder;
	
	
	@GetMapping("upload4")
	public String formHandler4() {
		return "case10/fileForm3";
	}
	
	/**
	 * 
	 * @param co
	 * @param errors
	 * @param redirectAttributes
	 * @return
	 * @throws IOException
	 */
	@PostMapping("upload4")
	public String uploadProcess4(@Valid @ModelAttribute("co") Case10CommandObject co, BindingResult errors, RedirectAttributes redirectAttributes) throws IOException {	 	
		List<FileUploadVO> fileList = co.getFileList();
		redirectAttributes.addFlashAttribute("co", co);
		for(FileUploadVO fileVO: fileList) {
			Resource saveRes = saveFolder.createRelative(fileVO.getSaveName());
			FileUtils.copyInputStreamToFile(fileVO.getFile().getInputStream(), saveRes.getFile());
			log.info("{} 업로드 완료, DB에 저장할 메타데이터 : {}", fileVO.getFileName(), fileList);
		}
		
		return "redirect:/case10/upload3";
	}
	
	
	
	@GetMapping("upload3")
	public String formHandler3() {
		return "case10/fileForm3";
	}
	
	@PostMapping("upload3")
	public String uploadProcess3(
			  @RequestParam String[] upload
			, @RequestParam int count
			, @RequestPart(name="uploadFile", required = false) MultipartFile[] uploadFiles
			, RedirectAttributes redirectAttributes
	) throws IOException {
		log.info("upload : {}", upload);
		log.info("count : {}", count);
	 	
		List<FileUploadVO> fileList = new ArrayList<>();
		redirectAttributes.addFlashAttribute("fileList", fileList);
		for(MultipartFile uploadFile: uploadFiles) {
			if(uploadFile == null && uploadFile.isEmpty()) {
				continue;
			}
			
			String mime = uploadFile.getContentType();
			if(!mime.startsWith("image/")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
			String fileName = uploadFile.getOriginalFilename();
			String saveName = UUID.randomUUID().toString();
			long fileSize = uploadFile.getSize();
			fileList.add(new FileUploadVO(fileName, saveName, fileSize));
			
			Resource saveRes = saveFolder.createRelative(saveName);
			FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), saveRes.getFile());
			log.info("{} 업로드 완료, DB에 저장할 메타데이터 : {}", fileName, fileList);
		}
		
		return "redirect:/case10/upload3";
	}
	
	
	@GetMapping("upload2")
	public String formHandler2() {
		return "case10/fileForm1";
	}
	
	@PostMapping("upload2")
	public String uploadProcess2(
			  @RequestParam String upload
			, @RequestParam int count
			, @RequestPart(required = false) MultipartFile uploadFile
	) throws IOException {
		log.info("uploader : {}", upload);
		log.info("uploader : {}", count);
		
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String mime = uploadFile.getContentType();
			if(!mime.startsWith("image/")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
			String fileName = uploadFile.getOriginalFilename();
			String saveName = UUID.randomUUID().toString();
			long fileSize = uploadFile.getSize();
			FileUploadVO fileVO = new FileUploadVO(fileName, saveName, fileSize);
			
			Resource saveRes = saveFolder.createRelative(saveName);
			FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), saveRes.getFile());
			log.info("{} 업로드 완료, DB에 저장할 메타데이터 : {}", fileName, fileVO);
		}
		
		return "redirect:/case10/upload1";
	}
	
	
	@GetMapping("upload1")
	public String formHandler1() {
		return "case10/fileForm1";
	}
	
	@PostMapping("upload1")
	public String uploadProcess1(
			  @RequestParam String uploader
			, @RequestParam int count
			, MultipartHttpServletRequest req
			, RedirectAttributes redirectAttributes
	) throws IOException {
		
		log.info("uploader : {}", uploader);
		log.info("uploader : {}", count);
		
		MultipartFile uploadFile = req.getFile("uploadFile");
		
		String fileName = uploadFile.getOriginalFilename();
		String saveName = UUID.randomUUID().toString();
		long fileSize = uploadFile.getSize();
		FileUploadVO fileVO = new FileUploadVO(fileName, saveName, fileSize);
		
		Resource saveRes = saveFolder.createRelative(saveName);
		FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), saveRes.getFile());
		log.info("{} 업로드 완료, DB에 저장할 메타데이터 : {}", fileName, fileVO);
		redirectAttributes.addFlashAttribute("fileVO", fileVO);
		
		return "redirect:/case10/upload1";
	}
}
