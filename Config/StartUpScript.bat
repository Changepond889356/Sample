title StartUpScript
set automationTestScriptsLocation=D:\POC\MOH\ASIS\AutomationTesting\Development\Source\Scripts
set TestNGLocation=D:\POC\MOH\ASIS\AutomationTesting\Development\Config
set TestNG=testng.xml

cd %automationTestScriptsLocation%
set classpath=%automationTestScriptsLocation%\bin;%automationTestScriptsLocation%\Library\*
java org.testng.TestNG %TestNGLocation%\%TestNG%
pause


