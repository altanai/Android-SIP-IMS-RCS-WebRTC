@echo on

echo %JAVA_HOME%

set CLASSPATH=.\tracesviewer.jar

java tools.tracesviewer.TracesViewer -server_file %1

