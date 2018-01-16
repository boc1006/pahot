package cn.pahot.business.biz;

import cn.pahot.business.dao.BusinessAccountDao;
import cn.pahot.business.entity.BusinessAccountEntity;
import cn.pahot.business.exception.ExceptionCode;
import cn.pahot.member.entity.AccountEntity;
import cn.pahot.member.enums.RegTypeEnum;
import cn.pahot.member.enums.TerminalEnum;
import cn.pahot.member.facade.MemberFacade;
import com.boc.common.exception.BizException;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商家子账户管理
 */
@Service("businessAccountBiz")
public class BusinessAccountBiz extends BaseBiz {

    @Autowired
    private BusinessAccountDao businessAccountDao;

    @Autowired
    private MemberFacade memberFacade;

    /**
     * 查找一个子账号
     *
     * @param id 会员登录号
     * @return
     */
    public BusinessAccountEntity getAccountItem(Integer id) {

        if (id == null) {
            return null;
        }
        return businessAccountDao.getShopAccountItem(id);
    }

    /**
     * 添加一个子账号
     *
     * @param businessAccountEntity
     */
    @Transactional
    public void addAccount(BusinessAccountEntity businessAccountEntity, boolean isReg) {
        businessAccountEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        checkParamas(businessAccountEntity, "aa01", "aa02", "merchant_id", "name", "username", "root");
        if (isReg) {
            //1.注册一个会员信息
            AccountEntity accountEntity = memberFacade.bizRegistryMember(businessAccountEntity.getUsername(),
                    true,
                    "",
                    true,
                    RegTypeEnum.REG_TYPE_01.key,
                    TerminalEnum.TERMINAL_OTHER.key,
                    businessAccountEntity.getName(),
                    businessAccountEntity.getUsername());
            //2.判断当前信息是否已经存在于商家的子账号系统中
            checkAccount(accountEntity.getId());
            businessAccountEntity.setId(accountEntity.getId());
        }
        //3.添加子账号信息
        businessAccountEntity.setState(BusinessAccountEntity.STATE_OK);
        businessAccountDao.addShopAccount(businessAccountEntity);

    }

    /**
     * 更新账号信息
     *
     * @param businessAccountEntity
     */
    public void updateAccountInfo(BusinessAccountEntity businessAccountEntity) {
        checkParamas(businessAccountEntity, "id");
        checkAccount(businessAccountEntity.getId());

        if (StringUtil.isEmpty(businessAccountEntity.getState())) {
            businessAccountDao.updateShopAccountItem(businessAccountEntity);
        } else {
            checkParamas(businessAccountEntity, "state");

            BusinessAccountEntity params = new BusinessAccountEntity();
            params.setId(businessAccountEntity.getId());
            params.setState(businessAccountEntity.getState());
            businessAccountDao.updateShopAccountItem(params);
        }

    }


    /**
     * 获取账户列表
     *
     * @return
     */
    public List<BusinessAccountEntity> getAccountList() {
        return businessAccountDao.getShopAccountList();
    }


    /*判断账号是否存在*/
    private void checkAccount(Integer id) {
        BusinessAccountEntity businessAccountEntity = getAccountItem(id);

        if (businessAccountEntity == null) {
            throw new BizException(ExceptionCode.ERRORCODR_EXIST, "当前账号不存在");
        } else if (businessAccountEntity.getState().equals(BusinessAccountEntity.STATE_OK)) {
            throw new BizException(ExceptionCode.ERRORCODR_STATE, "当前账号状态异常:" + businessAccountEntity.getState());
        }

    }
}
