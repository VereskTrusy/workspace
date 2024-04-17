package kr.or.ddit.prod.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ctc.wstx.util.StringUtil;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {
	
	@Autowired
	private final ProdDAO dao;
	
	@Value("/resources/prodImages/")
	private Resource prodImages;
	
	@PostConstruct
	public void init() throws IOException {
		if(!prodImages.exists()) {
			prodImages.getFile().mkdirs(); // 폴더 계층구조 만들기
		}
	}
	
	private void processImage(ProdVO prod) {
		String saveName = prod.getProdImg();
		if(StringUtils.isBlank(saveName)) {
			return;
		}
		try {
			Resource saveRes = prodImages.createRelative(saveName);
			MultipartFile uploadFile = prod.getProdImage();
			FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), saveRes.getFile());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Override
	public List<ProdVO> retriveProdList() {
		List<ProdVO> list = dao.selectProdList();
		return list;
	}

	@Override
	public ProdVO retriveProd(String prodId) throws PkNotFoundException {
		ProdVO item = dao.selectProd(prodId);
		if(item == null) throw new PkNotFoundException(400);
			
		return item;
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		if(dao.insertProd(prod) > 0) {
			processImage(prod);
			return ServiceResult.OK;
		} else {
			return ServiceResult.FAIL;
		}
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		// 디비 조회 uuid
		// prod uuid 똑 같고, image not null -> 업뎃
		// prod uuid 똑 같고, image null -> 이미지 빼고 업뎃
		ProdVO oriProd = dao.selectProd(prod.getProdId());
		String prodImg = oriProd.getProdImg();
		if() {
			
		}
		int rowcnt = dao.updateProd(prod);
		if(rowcnt != -1) return ServiceResult.OK;
		else return ServiceResult.FAIL;
	}
	
	
}
