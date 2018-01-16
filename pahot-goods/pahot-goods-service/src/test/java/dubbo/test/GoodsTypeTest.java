package dubbo.test;

import cn.pahot.goods.entity.GoodsTypeEntity;
import cn.pahot.goods.facade.GoodsTypeFacade;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-goods-consumer.xml")
public class GoodsTypeTest {
    @Autowired
    private GoodsTypeFacade goodsTypeFacade;

    @Test
    public void addGoodsType() {
        GoodsTypeEntity gte = new GoodsTypeEntity();
        gte.setName("测试");
        gte.setEname("sub_sub");
        gte.setIcon("111111");
        gte.setParentId(0);
        gte.setPath("1111");
        gte.setSort(2);
        gte.setAa01(1);
        goodsTypeFacade.addGoodsType(gte);
    }

    @Test
    public void updateGoodsType() {
        GoodsTypeEntity gte = new GoodsTypeEntity();
        gte.setId(1025);
//        gte.setName("发嘎嘎");
//        gte.setEname("aaaaaaaaaaaaa");
//        gte.setIcon("aaaaaaaaaaaaa");
//        gte.setParentId(1003);
        gte.setPath("aaaaaaa");
//        gte.setSort(2);
        gte.setAb01(2);
        goodsTypeFacade.updateGoodsType(gte);
    }


    @Test
    public void updateGoodsTypeState() {
        goodsTypeFacade.updateGoodsTypeState(1025, "00", 10);
    }

    @Test
    public void getGoodsTypeList() {
        GoodsTypeEntity goodsTypeEntity = new GoodsTypeEntity();
        List<GoodsTypeEntity> list = goodsTypeFacade.getGoodsTypeList(goodsTypeEntity);
        System.out.println(list);


        // GoodsTypeEntity goodsTypeEntity =  new   GoodsTypeEntity();

        DTO<String, String> dto = new BaseDTO<>();
        // list = this.goodsTypeFacade.getGoodsTypeList2(dto);
        System.out.println(list);

    }


    @Test
    public void getGoodsTypeForPage() {
      /*  GoodsTypeEntity goodsTypeEntity = new GoodsTypeEntity();
        List<GoodsTypeEntity> list =  goodsTypeFacade.getGoodsTypeForPage(goodsTypeEntity);
        System.out.println(list);*/
        PageParams<DTO<String, String>> params = new PageParams<>();
        params.setPageNo(1);
        params.setPageSize(2);
        PageInfo<GoodsTypeEntity> pageInfo = goodsTypeFacade.getGoodsTypeForPage(params);
        System.out.println(pageInfo);
        // GoodsTypeEntity goodsTypeEntity =  new   GoodsTypeEntity();

     /*   DTO<String, String> dto = new BaseDTO<>();
        list =   this.goodsTypeFacade.getGoodsTypeList2(dto);
        System.out.println(list);*/

    }

}
