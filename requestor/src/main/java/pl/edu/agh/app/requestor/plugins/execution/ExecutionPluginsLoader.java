package pl.edu.agh.app.requestor.plugins.execution;


import lombok.Getter;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;
import pl.edu.agh.app.requestor.plugins.execution.interfaces.Plugin;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ExecutionPluginsLoader {

    private static final String PACKAGE = "pl.edu.agh.app.requestor.plugins.execution";


    @Getter
    private List<Plugin> plugins;

    @PostConstruct
    private void registerPlugins() {
        plugins = new LinkedList<>();

        BeanDefinitionRegistry bdr = new SimpleBeanDefinitionRegistry();
        ClassPathBeanDefinitionScanner s = new ClassPathBeanDefinitionScanner(bdr);
        s.resetFilters(false); // deletes scanning dor spring Service, Controller etc
        s.setIncludeAnnotationConfig(false);  //deletes some spring classes

        TypeFilter tf = new AssignableTypeFilter(Plugin.class);
        s.addIncludeFilter(tf);
        s.scan(PACKAGE);


        String[] allPluginsNames = bdr.getBeanDefinitionNames();
        Arrays.asList(allPluginsNames)
                .stream()
                .forEach(name -> {
                    try {
                        String beanClassName = bdr.getBeanDefinition(name).getBeanClassName();
                        Object plugin = Class.forName(beanClassName).newInstance();
                        plugins.add((Plugin) plugin);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }


    public Optional<Plugin> findByName(String name) {
        return plugins.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

}
