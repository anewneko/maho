package bot.discord.maho.core.Config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;

import bot.discord.maho.discord.Command.Command;

@Configuration
public class CommandBeanRegistrationConfig implements BeanDefinitionRegistryPostProcessor  {
	private BeanDefinitionRegistry registry;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		this.registry = registry;
		var scanner = new ClassPathScanningCandidateComponentProvider(false);
	    scanner.addIncludeFilter(new AssignableTypeFilter(Command.class));
	    scanner.findCandidateComponents("bot.discord.maho.discord.Command.Impl")
	    	   .forEach(this::registerBeanDefinition); 
	}
	
	
	private void registerBeanDefinition(BeanDefinition b) {
		try {
			var clazz = Class.forName(b.getBeanClassName());
			if (!clazz.isInterface() && Command.class.isAssignableFrom(clazz)) 
				registry.registerBeanDefinition(clazz.getSimpleName(), 
											    BeanDefinitionBuilder.genericBeanDefinition(clazz).getBeanDefinition());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
