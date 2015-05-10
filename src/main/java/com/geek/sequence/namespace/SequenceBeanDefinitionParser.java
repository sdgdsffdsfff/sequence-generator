package com.geek.sequence.namespace;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.geek.sequence.impl.DefaultSequence;
import com.geek.sequence.impl.DefaultSequenceDao;

/**
 * parse sequence bean definition
 * 
 * @author 机冷
 * @version $Id: SequenceBeanDefinitionParser.java, v 0.1 2015年5月10日 下午10:28:11 机冷 Exp $
 */
public class SequenceBeanDefinitionParser implements BeanDefinitionParser {
    /**DefaultSequenceDao instance*/
    private static final DefaultSequenceDao DEFAULT_SEQUENCE_DAO;

    /**
     * 确保DefaultSequenceDao只被实例化一次
     */
    static {
        DEFAULT_SEQUENCE_DAO = new DefaultSequenceDao();
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
        //设置对应的class类型
        builder.getRawBeanDefinition().setBeanClass(DefaultSequence.class);
        builder.getRawBeanDefinition().setSource(parserContext.extractSource(element));
        if (parserContext.isNested()) {
            // Inner bean definition must receive same scope as containing bean.
            builder.setScope(parserContext.getContainingBeanDefinition().getScope());
        }
        //设置释放延长初始化
        if (parserContext.isDefaultLazyInit()) {
            // Default-lazy-init applies to custom bean definitions as well.
            builder.setLazyInit(true);
        }
        String name = element.getAttribute("name");
        if (StringUtils.hasLength(name)) {
            builder.addPropertyValue("name", name);
        }
        builder.addPropertyValue("sequenceDao", DEFAULT_SEQUENCE_DAO);
        //remove <bean id="sequence" class="com.geek.sequence.impl.DefaultSequenceDao" />
        //builder.addPropertyReference("sequenceDao", SEQUENCE_DAO_ID);

        AbstractBeanDefinition definition = builder.getBeanDefinition();
        //生成bean id
        //String id = parserContext.getReaderContext().generateBeanName(definition);
        
        //注册bean definition
        //FIXME sequence name作为当前sequence的bean id
        parserContext.getRegistry().registerBeanDefinition(name, definition);

        return definition;
    }

}
