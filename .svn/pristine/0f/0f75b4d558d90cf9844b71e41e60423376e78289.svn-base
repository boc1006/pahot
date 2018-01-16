package dubbo.test;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boc.common.enums.DataSourceEnums;

import cn.pahot.xa.scheduler.AppScheduler;
import cn.pahot.xa.scheduler.dao.ACKMapper;
import cn.pahot.xa.scheduler.datasource.DataSourceContextHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppScheduler.class)
public class MulitDSTest {
	
	@Autowired
	private ACKMapper ackMapper;
	
	@org.junit.Test
	public void testMulitDataSource() {
		for(int i = 0 ; i < 1 ; i ++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int k = 0 ; k < 2 ; k++) {
						System.out.println("--------------");
						if(k % 2 == 0) {
							DataSourceContextHolder.setDataSourseType(DataSourceEnums.PT_UPMS.key);
							System.out.println("===========>"+ackMapper.mainTransactionACK(1));
						}else{
							DataSourceContextHolder.setDataSourseType(DataSourceEnums.PT_LOGGER.key);
							System.out.println("+++++++++++>"+ackMapper.mainTransactionACK(1));
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
//					System.out.println("111111111");
					
				}
			}).start();
		}
		try {
			TimeUnit.SECONDS.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
