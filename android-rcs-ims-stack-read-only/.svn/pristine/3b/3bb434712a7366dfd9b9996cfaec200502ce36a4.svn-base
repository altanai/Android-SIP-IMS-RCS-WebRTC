@echo off

set LOCAL_LOG=.\sip.txt
set REMOTE_LOG=/sdcard/sip.txt

del %LOCAL_LOG%

adb pull %REMOTE_LOG% .

traceviewer %LOCAL_LOG%

pause


