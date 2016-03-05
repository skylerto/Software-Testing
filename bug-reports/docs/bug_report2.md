# Bug Report 2
**Title**: Repeating Events Cannot be Edited Atomically Reported by: Siraj Rauff  
**Date Reported**: January 26, 2016  
**Program name**: BORG Calendar  
**Configuration**: OS X 10.11.3, Java version 1.8.0_71, Runtime build 1.8.0_71-b15   
**Report type**: Bug  
**Reproducibility**: Yes – consistently.  
**Priority**: Minor  
**Problem Summary**:  
After creating or importing a calendar with repeated events, it is not possible to modify a single repeated event. This does not allow me to move my work schedule for one day while keeping it a part of the work series (so I can use programs to analyze it later).  

**Problem Description**:  
BORG treats recurring events as groups, not allowing for the modification of an event atomically if its part of a series. Changing one will change all the events. This is not consistent behavior as other calendar systems allow for a single event to be modified but still considered part of a series so it can be deleted/edited as part of the series.  
**Steps to Reproduce**:  
1. Create a recurring event with at least two occurrences.  
2. Modify one occurrence.  
3. Both occurrences will be modified.  

**New or old bug**: New
