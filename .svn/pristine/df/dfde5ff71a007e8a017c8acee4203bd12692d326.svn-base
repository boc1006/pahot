package cn.pahot.example.consumer.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boc.common.core.dao.ack.MainTransactionACKDao;
import com.boc.common.exception.BizException;

@Service("sampleConsumerBiz")
public class SampleConsumerBiz {
	
	@Autowired
	private MainTransactionACKDao mainTransactionACKDao;
	
	/**
	 * 分布式消息消费业务逻辑处理,必须处于一个事务中
	 * @param messageId
	 * @param userJson
	 * @throws BizException
	 */
	@Transactional
	public void sampleXAConsumer(long messageId,String userJson) throws BizException {
		//首先对消息消费情况做幂等性判断
		int count = mainTransactionACKDao.countByIdempotents(messageId);
		if(count > 0) {//说明该消息已经消费完成
			return ;//不做任何处理,直接返回正确值,因为此方法不需要任何返回值,直接return;
		}
		
		//TODO 实现自己的业务逻辑
		
		//业务处理完成后需要保存messageId
		mainTransactionACKDao.saveByIdempotents(messageId);
	}
}
