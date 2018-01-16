package dubbo.test;

import cn.pahot.goods.entity.GoodsSkuAttrEntity;
import cn.pahot.goods.entity.GoodsSkuSpectEntity;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-goods-consumer.xml")
public class GoodsSkuAttrTest {
    @Autowired
    private GoodsTypeFacade goodsSkuAttrFacade;


    @Test
    public void addGoodsSkuAttr() {
        GoodsSkuAttrEntity gse = new GoodsSkuAttrEntity();
        gse.setAa01(1);
        gse.setAa02(System.currentTimeMillis());
        gse.setGoodsTypeId(1);
        gse.setSkuSpecId(1);
        // gse.setSkuSpecId(1);
        gse.setSkuSpecName("11");
        gse.setSkuSpecType("1");
        gse.setSkuSpecTypeName("111");
        gse.setSkuSpecVal("111");
        gse.setSort(1);
        goodsSkuAttrFacade.addGoodsSkuAttr(gse);
    }

    @Test
    public void updateGoodsSkuAttr() {
        GoodsSkuAttrEntity gse = new GoodsSkuAttrEntity();
        gse.setId(1);
        gse.setAb01(1);
        gse.setAb02(System.currentTimeMillis());
        gse.setGoodsTypeId(12);
        gse.setSkuSpecId(12);
        gse.setSkuSpecId(12);
        gse.setSkuSpecName("112");
        gse.setSkuSpecType("12");
        gse.setSkuSpecTypeName("11123");
        gse.setSkuSpecVal("1112");
        gse.setSort(12);
        goodsSkuAttrFacade.updateGoodsSkuAttr(gse);
    }

    @Test
    public void delGoodsSkuAttr() {
        goodsSkuAttrFacade.delGoodsSkuAttr(1);
    }

    @Test
    public void getGoodsSkuSpect() {
        GoodsSkuSpectEntity gsse = new GoodsSkuSpectEntity();
        // gsse.setTypename("bb");
        List<GoodsSkuSpectEntity> list = goodsSkuAttrFacade.getGoodsSkuSpect(gsse);

        System.out.println(list);
    }

    @Test
    public void getGoodsSkuAttr() {
       /* GoodsSkuSpectEntity gsse = new GoodsSkuSpectEntity();
        gsse.setTypename("bb");*/
        PageParams<DTO<String, String>> pageParams = new PageParams<>();
        pageParams.setPageNo(1);
        pageParams.setPageSize(3);
        DTO<String, String> dto = new BaseDTO<>();
        pageParams.setParams(dto);
        PageInfo<GoodsSkuAttrEntity> page = goodsSkuAttrFacade.getGoodsSkuAttr(pageParams);
        System.out.println(page);

    }

    @Test
    public void updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType() {
        Integer goodsTypeId = 1000;
        String skuSpecType = "1";
        String skuSpecTypeName = "jjjjjjjjjjjjj";
        Integer userId = 111;
        goodsSkuAttrFacade.updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(goodsTypeId, skuSpecType, skuSpecTypeName, userId);
    }

    @Test
    public void delGoodsAttrGoodsTypeId() {
        goodsSkuAttrFacade.delGoodsSkuAttrByGoodsTypeId(1000);
    }


    @Test()
    public void addOrUpdateGoodsSkuAttr() {
        List<GoodsSkuAttrEntity> goodsSkuAttrEntitryList = new ArrayList<>();
        GoodsSkuAttrEntity gse = new GoodsSkuAttrEntity();
        gse.setAa01(1);
        gse.setAa02(System.currentTimeMillis());
        gse.setGoodsTypeId(1);
        gse.setSkuSpecId(1);
        gse.setSkuSpecName("11");
        gse.setSkuSpecType("1");
        gse.setSkuSpecTypeName("111");
        gse.setSkuSpecVal("111");
        gse.setSort(1);
        goodsSkuAttrEntitryList.add(gse);

        gse = new GoodsSkuAttrEntity();
        gse.setId(28);
        gse.setAb01(1);
        gse.setAb02(System.currentTimeMillis());
        gse.setGoodsTypeId(12);
        gse.setSkuSpecId(12);
        gse.setSkuSpecId(12);
        gse.setSkuSpecName("112");
        gse.setSkuSpecType("12");
        gse.setSkuSpecTypeName("11123");
        gse.setSkuSpecVal("1112");
        gse.setSort(12);
        goodsSkuAttrEntitryList.add(gse);
        goodsSkuAttrFacade.addOrUpdateGoodsSkuAttr(goodsSkuAttrEntitryList);
    }
}
