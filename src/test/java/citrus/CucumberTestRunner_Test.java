package citrus;

import static io.cucumber.core.options.Constants.OBJECT_FACTORY_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import io.cucumber.spring.CucumberContextConfiguration;
import org.citrusframework.config.CitrusSpringConfig;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.context.ContextConfiguration;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME, value = "org.citrusframework.cucumber.CitrusReporter") // генерит отчетик
@ConfigurationParameter(
    key = OBJECT_FACTORY_PROPERTY_NAME, value = "org.citrusframework.cucumber.backend.spring.CitrusSpringObjectFactory")
@SelectClasspathResource("/cucumber")
@CucumberContextConfiguration
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class CucumberTestRunner_Test {
}
