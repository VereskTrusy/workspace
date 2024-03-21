package kr.or.ddit.bts.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.bts.service.BtsService;
import kr.or.ddit.bts.service.BtsServiceImpl;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.utils.BtsComparator;
import kr.or.ddit.vo.BtsVO;

@WebServlet("/case2/bts")
public class BtsControllerServlet_case2 extends HttpServlet{
	private BtsService service = new BtsServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BtsVO> btsList = service.readBtsList();
		Collections.sort(btsList, new BtsComparator());
		
		req.setAttribute("btsList", btsList);
		String viewName = "/WEB-INF/views/bts/btsForm_case2.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// request 디코딩 설정
		req.setCharacterEncoding("UTF-8");
		
		// 쌤이 한거..
		String member = req.getParameter("member");
		
		int status = 200;
		if(StringUtils.isBlank(member)) {
			status = 400;
		}
		
		try {
			// bts 자원 획득
			BtsVO bts = service.readBts(member);
			
			service.incrementHit(member);
			
			String path = bts.getPath();
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			
			
			req.setAttribute("bts", bts);
			req.setAttribute("content", prefix + path + suffix);
		} catch(PkNotFoundException e) {
			status = e.getStatus();
		}
		
		if(status == 200) {
			req.getRequestDispatcher("/WEB-INF/views/bts/base.jsp").forward(req, resp);
		}else {
			resp.sendError(status);
		}
	}
}

