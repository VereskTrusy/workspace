package kr.or.ddit.common.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import kr.or.ddit.common.vo.AlarmVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AlarmController {
	
	private final SimpMessageSendingOperations simpMessageSendingOperations;
	
	//쪽지 알림
	@MessageMapping("/msg")
	public void AlarmMsg(AlarmVO message) throws InterruptedException {
		log.info("receiver>> "+message.getReceiver());
		Thread.sleep(5000);
		simpMessageSendingOperations.convertAndSend("/sub/msg/"+message.getReceiver(), "쪽지 왔어요");
	}
	
	//메일 알림
	@MessageMapping("/mail")
	public void AlarmMail(AlarmVO message) throws InterruptedException {
		log.info("receiver>> "+message.getReceiver());
		Thread.sleep(5000);
		simpMessageSendingOperations.convertAndSend("/sub/mail/"+message.getReceiver(), "메일 왔어요");
	}
	
	//메신저 알림
	@MessageMapping("/messenger")
	public void AlarmMessenger(AlarmVO message) throws InterruptedException {
		log.info("receiver>> "+message.getReceiver());
		Thread.sleep(5000);
		simpMessageSendingOperations.convertAndSend("/sub/messenger/"+message.getReceiver(), "메신저 왔어요");
	}
	
}
