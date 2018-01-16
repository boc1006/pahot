package cn.pahot.xa.scheduler.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

public interface ACKMapper {
	
	@Select("select count(1) as cnt from pt_xa_ackid where id = #{id}")
	public Long mainTransactionACK(@Param("id") long id);
}
