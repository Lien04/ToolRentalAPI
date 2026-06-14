Feature:ToolsAPITesting
Scenario: Check single tool response when send request successfully
When send single tool request with valid URL and method and params
Then single tool API responds status code 200 and list of tools