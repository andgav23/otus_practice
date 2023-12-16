package citrus;

import static io.cucumber.core.options.Constants.OBJECT_FACTORY_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME, value = "pretty")
//@ConfigurationParameter(
//    key = PLUGIN_PROPERTY_NAME, value ="org.citrusframework.cucumber.CitrusReporter")
@ConfigurationParameter(
    key = OBJECT_FACTORY_PROPERTY_NAME, value ="org.citrusframework.cucumber.backend.spring.CitrusSpringObjectFactory")
@SelectClasspathResource("/cucumber")
public class CucumberRunerTest {
}
