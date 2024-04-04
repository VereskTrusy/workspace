package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViewResolverComposite implements ViewResolver {
	private List<ViewResolver> viewResolvers;
	
	// 초기화 블록
	{
		ViewResolver CNVR = new ContentNegotiatingViewResolver();
		
		ViewResolver IRVR = new InternalResourceViewResolver();
		IRVR.setPrefix("/WEB-INF/views/");
		IRVR.setSuffix(".jsp");
		
		viewResolvers = new ArrayList<ViewResolver>();
		viewResolvers.add(CNVR);
		viewResolvers.add(IRVR); // 항상 가장 마지막에 동작해야함.
	}

	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else if(viewName.startsWith("forward:")) {
			String path = viewName.substring("forward:".length());
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else {
			for(ViewResolver single : viewResolvers) {
				try {
					single.resolveView(viewName, req, resp);
					log.info("{} 이(가) {}을(를) 해결하였습니다. ", single.getClass().getSimpleName(), viewName);					
					break;
				}catch(CannotResolverViewNameException e) {
					log.warn("{} 이(가) {}을(를) 해결하지 못했습니다. ", single.getClass().getSimpleName(), viewName);
					continue;
				}
			}
		}
	}
}
