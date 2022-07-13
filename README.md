# interpreter

Program for working with files via simple key-value language

Set files location: 
inside Util.kt change BASE_URL to proper location

Available actions:
- create file: CREATE
- add content to a file from another file: ADD
- replace line in file with content from another file: REPLACE

All available keys:
- ACTION - what action to perform (required)
- IN_FILE - file that will be opened and modified (required for ADD and REPLACE action types)
- OUT_FILE - file where result will be saved (required)
- READ_FILE - file from where content will be read (required for ADD and REPLACE action types)
- INDEX - number of line that will be replaced (required for REPLACE action type)

Examples:

create empty file: ACTION=CREATE;OUT_FILE=out.txt;

add content to file from another file:
ACTION=ADD;IN_FILE=in.txt;READ_FILE=read.txt;OUT_FILE=out.txt;

replace line with content from another file:
ACTION=REPLACE;IN_FILE=in.txt;READ_FILE=read.txt;INDEX=1;OUT_FILE=out.txt;
