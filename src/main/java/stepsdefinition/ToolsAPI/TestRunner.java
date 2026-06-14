package stepsdefinition.ToolsAPI;

import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		 features = "src/main/java/features",
		 glue = {"stepsdefinition"},
		 plugin = {"pretty", "html:target/cucumber-reports"})

public class TestRunner {
}
