# Bug Report 3
**Title**: Does Not Flag user when Database Port is in use   
**Reported by**: Skyler Layne  
**Date Reported**: January 24, 2016  
**Program name**: BORG Calendar  
**Configuration**: OS X 10.11.3, Java version 1.8.0_60, Runtime build 1.8.0_60-b27   
**Report type**: Bug  
**Reproducibility**: Yes.  
**Priority**: Minor  
**Problem Summary**:  
When running something that uses the same port as that which is specified in the database configuration, no warning or error is provided.  

**Problem Description**:  
When spawning a program to listen on the same port as the database connection that BORG uses. BORG acts as though it does not need to use the port which is specified, closes the connection with the database server and continues to open the GUI. This has many potential problems such as man in the middle attacks, where a user can inject themselves and listen to anything which is happening to the database. Often this can be uses during testing to mock database data however, should be left out of production especially for a program with security claims.  
BORG Debug output:
```
Jan 25, 2016 3:33:42 PM net.sf.borg.control.Borg init
FINE: Debug logging turned on
Jan 25, 2016 3:33:42 PM net.sf.borg.common.SocketClient sendMsg
INFO: Connection closed by server.
```
**Steps to Reproduce**:  
1. Open BORG, specify a database port.  
2. Start a service on the port.  
3. Relaunch BORG.  

**New or old bug**: New  
