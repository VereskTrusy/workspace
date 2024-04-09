package kr.or.ddit.case4.bts.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BtsVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InMemoryBtsDAOImpl implements BtsDAO{
	
//	Map<String, Object[]> btsMap = new LinkedHashMap<String, Object[]>();
//	{
//		btsMap.put("B001", new Object[] {"뷔", "bts/bui", 100});
//		btsMap.put("B002", new Object[] {"제이홉", "bts/jhop", 200});
//		btsMap.put("B003", new Object[] {"지민", "bts/jimin", 300});
//		btsMap.put("B004", new Object[] {"진", "bts/jin", 120});
//		btsMap.put("B005", new Object[] {"정국", "bts/jungkuk", 30});
//		btsMap.put("B006", new Object[] {"알엠", "bts/rm", 40});
//		btsMap.put("B007", new Object[] {"슈가", "suga", 50});
//	}
	
	// 주입받기
	@Resource(name = "btsMap")
	private final Map<String, Object[]> btsMap;
	
	@PostConstruct
	public void init() {
		btsMap.put("B001", new Object[] {"뷔", "bts/bui", 100});
		btsMap.put("B002", new Object[] {"제이홉", "bts/jhop", 200});
		btsMap.put("B003", new Object[] {"지민", "bts/jimin", 300});
		btsMap.put("B004", new Object[] {"진", "bts/jin", 120});
		btsMap.put("B005", new Object[] {"정국", "bts/jungkuk", 30});
		btsMap.put("B006", new Object[] {"알엠", "bts/rm", 40});
		btsMap.put("B007", new Object[] {"슈가", "suga", 50});
	}
	
	@Override
	public BtsVO selectBts(String code) {
		// vo로 만드는 작업
		BtsVO vo = null;
		
		// code가 null 일 경우
		if(StringUtils.isBlank(code)) {
			return null;
		}
		
		// map 에 code가 포함 안됐을때
		if(!btsMap.containsKey(code)) {
			return null;
		}
		
		Object[] arr = btsMap.get(code);
		
		if(arr == null) {
			return null;
		}
		
		// vo 에 담기
		vo = new BtsVO();
		vo = new BtsVO((String)code, (String)arr[0], (String)arr[1], (int)arr[2]);
		
		return vo;
	}

	@Override
	public List<BtsVO> selectBtsList() {
		List<BtsVO> list = new ArrayList<BtsVO>();
		
		for(Entry<String, Object[]> entry : btsMap.entrySet()) {
			Object[] arr = entry.getValue();
			list.add(new BtsVO(entry.getKey(), (String)arr[0], (String)arr[1], (int)arr[2]));
		}
		
		return list;
	}

	@Override
	public void incrementHit(String code) {
		Object[] arr = btsMap.get(code);
		arr[2] = (int) arr[2] + 1;
		btsMap.put(code, arr);
	}
	
}
