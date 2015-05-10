package com.geek.sequence.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
/**
 * handle <sequence:sequence/> xml config
 * 
 * @author 机冷
 * @version $Id: SequenceNamespaceHandler.java, v 0.1 2015年5月10日 下午10:26:38 机冷 Exp $
 */
public class SequenceNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		//register
		registerBeanDefinitionParser("sequence",
				new SequenceBeanDefinitionParser());
	}

}
