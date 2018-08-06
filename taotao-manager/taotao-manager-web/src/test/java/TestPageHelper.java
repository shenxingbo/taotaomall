import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import java.util.List;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/6下午4:28
 **/

public class TestPageHelper {
    @Test
    public void testPageHelper() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(Long.parseLong("536563"));
        PageHelper.startPage(1,10);
        List<TbItem> list = mapper.selectByExample(example);

        for (TbItem tbItem : list) {
            System.out.println(tbItem.getId());
        }

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println(total);

    }
}
