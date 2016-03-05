# Bug Report 1  
**Title**: ICS Import Duplicates Modified Events in Repeating Series’ Reported by: Siraj Rauff  
**Date Reported**: January 26, 2016  
**Program name**: BORG Calendar  
**Configuration**: OS X 10.11.3, Java version 1.8.0_71, Runtime build 1.8.0_71-b15   
**Report type**: Bug  
**Reproducibility**: Yes – consistently.  
**Priority**: Moderate  
**Problem Summary**:   
After importing a calendar from Google Calendar using .ics format, any events that were part of a series and later modified (start/end time) appeared twice in BORG. Creating the calendar in iCloud caused the same problem.  

**Problem Description**:  
BORG treats modified events in a repeating series as new events (not part of the series).

**Steps to Reproduce**:  
1. Create a calendar with a single event (Google Calendar, iCloud, Outlook, etc.)   
2. Set event to repeat once (should still appear as one event)  
3. Change the end time of the event  
4. Export as .ICS  
5. Import into BORG Calendar using Import option Menu option Ical > ICS   
6. Event appears twice  

**New or old bug**: New  
