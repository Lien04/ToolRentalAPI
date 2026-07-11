package common;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	private Map<Context, Object> context;
	
	public ScenarioContext() {
		this.context = new HashMap<>();
	}
	public void setContext(Context key, Object value) {
		context.put(key, value);
	}
	public Object getContext(Context key) {
		return context.get(key);
	}
}